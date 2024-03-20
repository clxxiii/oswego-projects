package tftpd;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.DatagramChannel;
import java.util.HashMap;

import object.Session;
import object.File;
import packet.*;

public class ServerSession extends Session {
  public final RequestPacket request;

  final int MAX_PACKET_SIZE = 512 + 4; // Data has header size of 4, plus 512 bytes of data

  public ServerSession(RequestPacket packet, DatagramChannel channel) {
    this.request = packet;
    this.channel = channel;

    try {
      this.remote = (InetSocketAddress) channel.getRemoteAddress();
    } catch (IOException e) {
      System.out.println("Cannot build session without remote address:");
      System.out.println(e);
    }
  }

  public void begin() {
    if (request.opcode == Opcode.RRQ) {
      sendFile();
    } else {
      recieveFile();
    }
  }

  public void sendFile() {
    File localFile = new File(request.fileName);

    if (!localFile.exists()) {
      send(new ErrorPacket(ErrorCode.FILE_NOT_FOUND));
      return;
    }

    if (!localFile.canRead()) {
      send(new ErrorPacket(ErrorCode.ACCESS_VIOLATION));
    }

    HashMap<String, String> options = new HashMap<>();
    options.put("length", String.valueOf(localFile.length()));

    send(new OAckPacket(options));
  }

  public void recieveFile() {
    File localFile = new File(request.fileName);

    if (localFile.exists()) {
      send(new ErrorPacket(ErrorCode.FILE_EXISTS));
      return;
    }

    if (!localFile.canWrite()) {
      send(new ErrorPacket(ErrorCode.ACCESS_VIOLATION));
    }

    send(new OAckPacket(request.options));
  }
}
