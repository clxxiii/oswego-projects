package net;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.nio.channels.DatagramChannel;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

import object.Key;
import packet.*;

public class FileSender extends PacketHandler {
  private File localFile;
  private short windowMin;
  private short lastStored;
  private volatile ConcurrentHashMap<Short, DataPacket> cache;
  private InputStream in;
  private Key key;
  protected long start;

  private ReentrantLock slidingLock = new ReentrantLock();

  private final int WINDOW_SIZE;

  public FileSender(File file, Key key, int windowSize, DatagramChannel channel, InetSocketAddress remote) {
    super(channel, remote);
    localFile = file;
    this.key = key;
    windowMin = 1;
    lastStored = 0;
    cache = new ConcurrentHashMap<>();
    WINDOW_SIZE = windowSize;
  }

  public void begin() {
    try {
      in = new FileInputStream(localFile);
    } catch (Exception e) {
      System.out.println("could not create file input stream");
      return;
    }

    // Load packets into cache
    for (int i = 0; i < (WINDOW_SIZE * 2); i++) {
      loadPacket();
    }

    // Start Timer
    start = System.nanoTime();
    for (int i = 1; i <= WINDOW_SIZE; i++) {
      sendAndTimeout((short) i);
    }

    // Start Listening to Acks
    AckListener listener = new AckListener(this);
    listener.listen();
  }

  protected DataPacket getPacket(short i) {
    return cache.get(i);
  }

  protected void removePacket(short i) {
    cache.remove(i);
  }

  public void sendAndTimeout(short i) {
    DataPacket p = cache.get(i);
    send(p);
    new Thread(new Timeout(i, this)).start();
  }

  public long getFileSize() {
    return localFile.length();
  }

  public void slideWindow() {
    slidingLock.lock();
    try {
      if (cache.get(windowMin) != null) {
        return;
      }
      windowMin++;
      loadPacket();
      sendAndTimeout((short) (windowMin + (WINDOW_SIZE - 1)));
    } finally {
      slidingLock.unlock();
    }
  }

  public void loadPacket() {
    byte[] data;
    try {
      int available = in.available();

      if (available == 0) {
        return;
      } else if (available < 512) {
        data = new byte[available];
      } else {
        data = new byte[512];
      }

      in.read(data, 0, data.length);
      data = key.encrypt(data);

      lastStored++;
      cache.put(lastStored, new DataPacket(lastStored, data));
      if (available == 512) {
        lastStored++;
        cache.put(lastStored, new DataPacket(lastStored, new byte[0]));
      }
    } catch (IOException e) {
      System.out.println("Failed to read file.");
      System.out.println(e);
    }
  }
}
