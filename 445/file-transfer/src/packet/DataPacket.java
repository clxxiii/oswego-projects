package packet;

import java.nio.ByteBuffer;

public class DataPacket extends Packet {

  final short blockNumber;
  final byte[] data;

  public DataPacket(int blockNumber, byte[] data) {
    super(Opcode.DATA);
    this.blockNumber = (short) blockNumber;
    this.data = data;
  }

  public byte[] toBytes() {
    int bufferLength = 2 + // Opcode length
        2 + // Block number length
        data.length;
    ByteBuffer buffer = ByteBuffer.allocate(bufferLength);

    buffer.putShort(opcode.code);
    buffer.putShort(blockNumber);
    buffer.put(data);

    buffer.flip();
    return buffer.array();
  }
}
