package packet;

import java.nio.ByteBuffer;
import java.util.HashMap;

public class OAckPacket extends Packet {
  public HashMap<String, String> options;

  public OAckPacket(HashMap<String, String> options) {
    super(Opcode.OACK);
    this.options = options;
  }

  public ByteBuffer toBuffer() {
    int bufferLength = 2; // Opcode length

    if (options != null) {
      for (String key : options.keySet()) {
        String value = options.get(key);

        bufferLength += key.length() + 1;
        bufferLength += value.length() + 1;
      }
    }

    ByteBuffer buffer = ByteBuffer.allocate(bufferLength);

    buffer.putShort(opcode.code);

    if (options == null) {
      return buffer;
    }

    for (String key : options.keySet()) {
      String value = options.get(key);

      buffer.put(key.getBytes());
      buffer.put((byte) 0);
      buffer.put(value.getBytes());
      buffer.put((byte) 0);
    }

    return buffer;
  }

  public static OAckPacket parseOAck(ByteBuffer buffer) {
    byte[] remainingBytes = new byte[buffer.remaining()];
    buffer.get(remainingBytes);
    String[] data = new String(remainingBytes).split("\0");

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

    return new OAckPacket(options);
  }
}