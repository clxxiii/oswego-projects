package packet;

import java.nio.ByteBuffer;
import java.text.ParseException;
import java.util.Optional;

public class ErrorPacket extends Packet {
  final public ErrorCode errorCode;
  final public String errorMsg;

  public ErrorPacket(ErrorCode code, String msg) {
    super(Opcode.ERROR);
    errorCode = code;
    errorMsg = msg;
  }

  public ByteBuffer toBuffer() {
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
    return buffer;
  }

  protected static ErrorPacket parseError(ByteBuffer buffer) throws ParseException {
    short codeId = buffer.getShort();
    byte[] remainingBytes = new byte[buffer.remaining()];
    buffer.get(remainingBytes);
    String[] data = new String(remainingBytes).split("\0");
    String errorMsg = data[0];
    Optional<ErrorCode> opErrorCode = ErrorCode.fromCode(codeId);
    if (!opErrorCode.isPresent()) {
      throw new ParseException("Invalid Error Code", Short.BYTES * 2);
    }
    ErrorCode errorCode = opErrorCode.get();
    return new ErrorPacket(errorCode, errorMsg);
  }
}
