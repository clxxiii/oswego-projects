package tftpd;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.text.ParseException;

import net.sourceforge.argparse4j.inf.Namespace;
import packet.*;

public class App {
  public static void main(String[] args) {
    Namespace ns = Args.parse(args);
    int port = Integer.valueOf(ns.getString("port"));

    DatagramChannel channel;
    try {
      channel = DatagramChannel.open();
      InetSocketAddress local = new InetSocketAddress("localhost", port);
      channel.bind(local);
      recieveData(channel);
      channel.close();
    } catch (IOException e) {
      System.out.println(e);
      System.exit(1);
      return;
    }
  }

  private static void recieveData(DatagramChannel channel) {
    ByteBuffer buffer = ByteBuffer.allocate(512);
    SocketAddress remote = null;
    while (true) {
      try {
        remote = channel.receive(buffer);
      } catch (IOException e) {
        System.out.println(e);
        continue;
      }

      RequestPacket packet;
      try {
        packet = (RequestPacket) Packet.parse(buffer);
      } catch (ParseException e) {
        ErrorPacket errpacket = new ErrorPacket(ErrorCode.NOT_DEFINED, "Failed to parse the sent packet");
        sendError(channel, remote, errpacket.toBuffer());
        continue;
      }

      if (!(packet.opcode == Opcode.RRQ || packet.opcode == Opcode.WRQ)) {
        sendError(channel, remote, new ErrorPacket(ErrorCode.ILLEGAL_OPERATION).toBuffer());
      }

      ServerSession session = new ServerSession(packet, channel);
      session.begin();
    }
  }

  private static void sendError(DatagramChannel channel, SocketAddress addr, ByteBuffer b) {
    try {
      channel.connect(addr);
      channel.send(b, addr);
    } catch (IOException e) {
      // If we fail to send the error what else are you supposed to do?
      // Send an error that the error couldn't send??
      System.out.println(e);
    }
  }

}
