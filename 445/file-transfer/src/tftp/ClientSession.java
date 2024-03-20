package tftp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.DatagramChannel;
import java.util.HashMap;

import object.Session;
import object.File;
import packet.*;

public class ClientSession extends Session {
  public final String localPath; // File on local fs
  public final String remotePath; // File on remote fs
  public final Opcode code; // RRQ or WRQ: Reading from server or writing to server
  public final InetSocketAddress remote;
  public DatagramChannel channel;

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
