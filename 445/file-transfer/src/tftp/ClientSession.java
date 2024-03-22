package tftp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.nio.channels.DatagramChannel;
import java.util.HashMap;
import java.util.Optional;

import object.Session;
import object.File;
import packet.*;

public class ClientSession extends Session {
  public final String localPath; // File on local fs
  public final String remotePath; // File on remote fs
  public final Opcode code; // RRQ or WRQ: Reading from server or writing to server

  public ClientSession(String src, String dst, Opcode code, InetSocketAddress remote) {
    this.localPath = src;
    this.remotePath = dst;
    this.code = code;
    this.remote = remote;
  }

  public void begin() {
    try {
      channel = DatagramChannel.open();
      channel.connect(remote);
    } catch (IOException e) {
      System.out.println("Failed to connect");
      System.exit(1);
    }

    if (code == Opcode.RRQ) {
      downloadFile();
    } else {
      uploadFile();
    }
  }

  private void uploadFile() {
    // Handshake
    File local = new File(localPath);

    if (!local.exists() || !local.canRead()) {
      System.out.println("Cannot find file: " + local.getAbsolutePath());
    }

    HashMap<String, String> options = new HashMap<>();
    options.put("length", String.valueOf(local.length()));

    RequestPacket req = new RequestPacket(code, remotePath);
    send(req);

    // Wait for response
    Optional<Packet> oack = recieve();

    if (!oack.isPresent()) {
      System.exit(1);
    }

    FileInputStream in;
    try {
      in = new FileInputStream(local);
    } catch (FileNotFoundException e) {
      System.out.println("Something terribly bad has gone wrong.");
      System.exit(1);
      return;
    }

    byte[] dataBuffer = new byte[512];
    int blockNumber = 1;
    try {
      while (in.available() >= 0) {
        if (in.available() == 0) {
          send(new DataPacket(blockNumber, new byte[0]));
          break;
        }
        in.read(dataBuffer, 0, 512);

        send(new DataPacket(blockNumber, dataBuffer));
        Optional<Packet> ack = recieve();
        if (!ack.isPresent()) {
          in.close();
          throw new IOException();
        }
      }
      in.close();
    } catch (IOException e) {
      System.out.println(e);
      System.exit(1);
      return;
    }

  }

  private void downloadFile() {
    // Handshake
    File local = new File(localPath);

    if (local.exists()) {
      System.out.println("File at " + local.getAbsolutePath() + " already exists, cancelling download.");
    }

    if (!local.canWrite()) {
      System.out.println("Cannot write to " + local.getAbsolutePath() + ", cancelling download.");
    }

    RequestPacket req = new RequestPacket(code, remotePath);
    send(req);
  }
}
