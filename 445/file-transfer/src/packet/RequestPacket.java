package packet;

import java.nio.ByteBuffer;
import java.util.HashMap;

public class RequestPacket extends Packet {

  public final String fileName;
  public final String mode;
  public HashMap<String, String> options;

  public RequestPacket(Opcode opcode, String fileName, String mode) {
    super(opcode);
    this.fileName = fileName;
    this.mode = mode;
  }

  public RequestPacket(Opcode opcode, String fileName, String mode, HashMap<String, String> options) {
    super(opcode);
    this.fileName = fileName;
    this.mode = mode;
    this.options = options;
  }

  public byte[] toBytes() {
    int bufferLength = 2 + // Opcode length
        fileName.getBytes().length +
        mode.getBytes().length +
        2; // Seperator length

    if (options != null) {
      for (String key : options.keySet()) {
        String value = options.get(key);

        bufferLength += key.length() + 1;
        bufferLength += value.length() + 1;
      }
    }
    ByteBuffer buffer = ByteBuffer.allocate(bufferLength);

    buffer.putShort(opcode.code);
    buffer.put(fileName.getBytes());
    buffer.put((byte) 0);
    buffer.put(mode.getBytes());
    buffer.put((byte) 0);

    if (options == null) {
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

  protected static RequestPacket parseRequest(Opcode opcode, ByteBuffer buffer) {
    byte[] remainingBytes = new byte[buffer.remaining()];
    buffer.get(remainingBytes);
    String[] data = new String(remainingBytes).split("\0");
    String fileName = data[0];
    String mode = data[1];

    if (data.length <= 2) {
      return new RequestPacket(opcode, fileName, mode);
    }

    HashMap<String, String> options = new HashMap<>();
    String key = "";
    for (int i = 2; i < data.length; i++) {
      if (i % 2 == 0) {
        key = data[i];
        continue;
      }

      String value = data[i];
      options.put(key, value);
    }

    return new RequestPacket(opcode, fileName, mode, options);
  }
}
