package packet;

import java.nio.ByteBuffer;

public class DataPacket extends Packet {

  final public short blockNumber;
  final public byte[] data;

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

  protected static DataPacket parseData(ByteBuffer buffer) {
    short blockNumber = buffer.getShort();
    byte[] data = new byte[buffer.remaining()];
    buffer.get(data);
    return new DataPacket(blockNumber, data);
  }
}
