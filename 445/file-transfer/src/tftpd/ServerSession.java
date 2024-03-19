package tftpd;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.DatagramChannel;

import net.Session;
import packet.*;

public class ServerSession extends Session {
  public final String file;
  public final Opcode code; // RRQ or WRQ: Reading from server or writing to server

  final int MAX_PACKET_SIZE = 512 + 4; // Data has header size of 4, plus 512 bytes of data

  public ServerSession(String file, Opcode code, DatagramChannel channel) {
    this.file = file;
    this.code = code;
    this.channel = channel;

    try {
      this.remote = (InetSocketAddress) channel.getRemoteAddress();
    } catch (IOException e) {
      System.out.println("Cannot build session without remote address:");
      System.out.println(e);
    }
  }

  public void begin() {
  }
}
