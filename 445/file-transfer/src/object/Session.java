package object;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.text.ParseException;
import java.util.Optional;

import packet.ErrorPacket;
import packet.Opcode;
import packet.Packet;

public class Session {
  public InetSocketAddress remote;
  public DatagramChannel channel;

  public final int MAX_PACKET_SIZE = 512 + 4; // Data has header size of 4, plus 512 bytes of data

  public void send(Packet p) {
    ByteBuffer buffer = p.toBuffer();

    if (buffer.capacity() > MAX_PACKET_SIZE) {
      System.out.println("ByteBuffer exceeded maximum capacity");
      System.exit(1);
    }

    try {
      channel.send(buffer, remote);
    } catch (IOException e) {
      System.out.println("Failed to send a packet:");
      System.out.println(p.toString());
      System.out.println(e);
    }
  }

  public Optional<Packet> recieve() {
    ByteBuffer buffer = ByteBuffer.allocate(MAX_PACKET_SIZE);

    try {
      channel.receive(buffer);
    } catch (IOException e) {
      System.out.println(e);
      return Optional.empty();
    }

    Packet packet;
    try {
      packet = Packet.parse(buffer);
    } catch (ParseException e) {
      System.out.println(e);
      return Optional.empty();
    }

    if (packet.opcode == Opcode.ERROR) {
      ErrorPacket error = (ErrorPacket) packet;
      System.out.print(error.errorCode.meaning);
      if (error.errorMsg != null) {
        System.out.println(": " + error.errorMsg);
      }
      System.exit(1);
    }

    return Optional.of(packet);
  }
}
