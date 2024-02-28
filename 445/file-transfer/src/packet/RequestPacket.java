package packet;

import java.nio.ByteBuffer;
import java.util.HashMap;

public class RequestPacket extends Packet {

  final String fileName;
  final String mode;
  HashMap<String, String> options;

  public RequestPacket(Opcode opcode, String fileName, String mode) {
    super(opcode);
    this.fileName = fileName;
    this.mode = mode;

    // Project only allows octet
  }

  public byte[] toBytes() {
    int bufferLength = 2 + // Opcode length
        fileName.getBytes().length +
        mode.getBytes().length +
        2; // Seperator length
    ByteBuffer buffer = ByteBuffer.allocate(bufferLength);

    buffer.putShort(opcode.code);
    buffer.put(fileName.getBytes());
    buffer.put((byte) 0);
    buffer.put(mode.getBytes());
    buffer.put((byte) 0);

    buffer.flip();
    return buffer.array();
  }

  public static RequestPacket parse(Opcode opcode, ByteBuffer buffer) {
    return new RequestPacket(opcode, null, null); // TODO: Implement Request Packet Parser
  }
}
