package net;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.DatagramChannel;
import java.util.HashMap;
import java.util.Optional;

import object.Key;
import packet.*;

public class FileReciever extends PacketHandler {

  File localFile;
  FileOutputStream out;
  private short lastStoredBlock;
  private HashMap<Short, DataPacket> cache;
  private boolean recievedLastPacket;
  private Key key;

  public FileReciever(File file, Key key, DatagramChannel channel, InetSocketAddress remote) {
    super(channel, remote);
    localFile = file;
    this.key = key;
    lastStoredBlock = 0;
    cache = new HashMap<>();
    recievedLastPacket = false;
  }

  public void begin() {
    try {
      localFile.createNewFile();
      out = new FileOutputStream(localFile);
    } catch (IOException e) {
      send(new ErrorPacket(ErrorCode.NOT_DEFINED, "Could not create file"));
      return;
    }
    while (true) {
      Optional<Packet> op = recieve();

      if (!op.isPresent()) {
        System.out.println("Failed to recieve data");
        continue;
      }

      if (op.get().opcode != Opcode.DATA) {
        System.out.println("Recieved something other than DATA");
        break;
      }

      DataPacket p = (DataPacket) op.get();

      Thread ackSender = new Thread(new AckSender(p.blockNumber, this));
      ackSender.setName("Ack Sender Block " + p.blockNumber);
      ackSender.start();

      cache.put(p.blockNumber, p);
      boolean endOfFile = writeFile();
      if (endOfFile) {
        break;
      }
    }
  }

  public boolean writeFile() {
    short nextBlock = (short) (lastStoredBlock + 1);

    DataPacket p = cache.get(nextBlock);

    if (p == null) {
      return false;
    }
    if (p.data.length < 512) {
      recievedLastPacket = true;
    }

    try {
      byte[] data = key.decrypt(p.data);
      out.write(data);
    } catch (IOException e) {
      System.out.println("Failed to write file.");
      System.out.println(e);
      System.exit(1);
      return false;
    }
    lastStoredBlock = nextBlock;
    cache.remove(nextBlock);
    writeFile();

    return (cache.size() == 0 && recievedLastPacket);
  }
}
