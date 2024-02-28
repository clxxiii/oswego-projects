package packet;

import java.nio.ByteBuffer;

public class AckPacket extends Packet {
  final short blockNumber;

  public AckPacket(int blockNumber) {
    super(Opcode.ACK);
    this.blockNumber = (short) blockNumber;
  }

  public byte[] toBytes() {
    int bufferLength = 2 + // Opcode length
        2; // Block number length
    ByteBuffer buffer = ByteBuffer.allocate(bufferLength);

    buffer.putShort(opcode.code);
    buffer.putShort(blockNumber);

    buffer.flip();
    return buffer.array();
  }
}
