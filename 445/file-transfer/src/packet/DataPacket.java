package packet;

import java.nio.ByteBuffer;

public class DataPacket extends Packet {

  public final short blockNumber;
  public final byte[] data;

  public DataPacket(int blockNumber, byte[] data) {
    super(Opcode.DATA);
    this.blockNumber = (short) blockNumber;
    this.data = data;
  }

  public ByteBuffer toBuffer() {
    int bufferLength = 2 + // Opcode length
        2 + // Block number length
        data.length;
    ByteBuffer buffer = ByteBuffer.allocate(bufferLength);

    buffer.putShort(opcode.code);
    buffer.putShort(blockNumber);
    buffer.put(data);

    return buffer;
  }

  protected static DataPacket parseData(ByteBuffer buffer) {
    short blockNumber = buffer.getShort();
    byte[] data = new byte[buffer.remaining()];
    buffer.get(data);
    return new DataPacket(blockNumber, data);
  }
}
