package tftp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.DatagramChannel;

import net.Session;
import packet.*;

public class ClientSession extends Session {
  public final String src; // File on local fs
  public final String dst; // File on remote fs
  public final Opcode code; // RRQ or WRQ: Reading from server or writing to server
  public final InetSocketAddress remote;
  public DatagramChannel channel;

  public ClientSession(String src, String dst, Opcode code, InetSocketAddress remote) {
    this.src = src;
    this.dst = dst;
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

    // Handshake
    RequestPacket req = new RequestPacket(code, dst);
    send(req);

    if (code == Opcode.RRQ) {
      downloadFile();
    } else {
      uploadFile();
    }
  }

  private void uploadFile() {
  }

  private void downloadFile() {
  }
}
