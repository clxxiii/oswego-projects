package packet;

import java.nio.ByteBuffer;

public class AckPacket extends Packet {
  public final short blockNumber;

  public AckPacket(int blockNumber) {
    super(Opcode.ACK);
    this.blockNumber = (short) blockNumber;
  }

  public ByteBuffer toBuffer() {
    int bufferLength = 2 + // Opcode length
        2; // Block number length

    ByteBuffer buffer = ByteBuffer.allocate(bufferLength);

    buffer.putShort(opcode.code);
    buffer.putShort(blockNumber);

    return buffer;
  }

  public static AckPacket parseAck(ByteBuffer buffer) {
    short blockNumber = buffer.getShort();

    return new AckPacket(blockNumber);
  }
}
