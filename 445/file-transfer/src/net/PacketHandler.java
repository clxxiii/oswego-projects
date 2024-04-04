package net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.text.ParseException;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import packet.ErrorPacket;
import packet.Opcode;
import packet.Packet;

public class PacketHandler {
  public InetSocketAddress remote;
  public DatagramChannel channel;

  public final int MAX_PACKET_SIZE = 512 + 4; // Data has header size of 4, plus 512 bytes of data

  public final int DROP_CHANCE = 100;
  public static boolean dropMode = false;

  public PacketHandler() {
  }

  public PacketHandler(DatagramChannel channel, InetSocketAddress remote) {
    this.channel = channel;
    this.remote = remote;
  }

  public void send(Packet p) {
    if (dropMode) {
      int random = ThreadLocalRandom.current().nextInt(DROP_CHANCE);
      if (random == 0) {
        return;
      }
    }

    ByteBuffer buffer = p.toBuffer();

    if (buffer.capacity() > MAX_PACKET_SIZE) {
      System.out.println("ByteBuffer exceeded maximum capacity");
      System.exit(1);
    }

    try {
      buffer.flip();
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
      if (error.errorMsg != null && error.errorMsg.length() > 0) {
        System.out.println(": " + error.errorMsg);
      } else {
        System.out.println(); // Print new line if no message
      }
      System.exit(1);
    }

    return Optional.of(packet);
  }
}
