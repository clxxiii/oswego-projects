package packet;

import java.nio.ByteBuffer;
import java.util.HashMap;

public class AckPacket extends Packet {
  final public short blockNumber;
  public HashMap<String, String> options;

  public AckPacket(int blockNumber) {
    super(Opcode.ACK);
    this.blockNumber = (short) blockNumber;
  }

  public AckPacket(int blockNumber, HashMap<String, String> options) {
    super(Opcode.ACK);
    this.blockNumber = (short) blockNumber;
    this.options = options;
  }

  public byte[] toBytes() {
    int bufferLength = 2 + // Opcode length
        2; // Block number length

    if (options != null) {
      for (String key : options.keySet()) {
        String value = options.get(key);

        bufferLength += key.length() + 1;
        bufferLength += value.length() + 1;
      }
    }

    ByteBuffer buffer = ByteBuffer.allocate(bufferLength);

    buffer.putShort(opcode.code);
    buffer.putShort(blockNumber);

    if (options == null) {
      buffer.flip();
      return buffer.array();
    }

    for (String key : options.keySet()) {
      String value = options.get(key);

      buffer.put(key.getBytes());
      buffer.put((byte) 0);
      buffer.put(value.getBytes());
      buffer.put((byte) 0);
    }

    buffer.flip();
    return buffer.array();
  }

  public static AckPacket parseAck(ByteBuffer buffer) {
    short blockNumber = buffer.getShort();

    byte[] remainingBytes = new byte[buffer.remaining()];
    buffer.get(remainingBytes);
    String[] data = new String(remainingBytes).split("\0");

    if (data.length < 1) {
      return new AckPacket(blockNumber);
    }

    HashMap<String, String> options = new HashMap<>();
    String key = "";
    for (int i = 0; i < data.length; i++) {
      if (i % 2 == 0) {
        key = data[i];
        continue;
      }

      String value = data[i];
      options.put(key, value);
    }

    return new AckPacket(blockNumber, options);
  }
}
