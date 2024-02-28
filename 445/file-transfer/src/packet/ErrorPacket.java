package packet;

import java.nio.ByteBuffer;

public class ErrorPacket extends Packet {
  ErrorCode errorCode;
  String errorMsg;

  public ErrorPacket(ErrorCode code, String msg) {
    super(Opcode.ERROR);
    errorCode = code;
    errorMsg = msg;
  }

  public byte[] toBytes() {
    int bufferLength = 2 + // Opcode length
        2 + // Error code length
        errorMsg.getBytes().length +
        1; // Seperator length
    ByteBuffer buffer = ByteBuffer.allocate(bufferLength);

    buffer.putShort(opcode.code);
    buffer.putShort(errorCode.code);
    buffer.put(errorMsg.getBytes());
    buffer.put((byte) 0);

    buffer.flip();
    return buffer.array();
  }
}
