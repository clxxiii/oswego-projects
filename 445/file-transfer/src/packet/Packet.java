package packet;

import java.nio.ByteBuffer;
import java.text.ParseException;
import java.util.Optional;

public abstract class Packet {
  public final Opcode opcode;

  protected Packet(Opcode opcode) {
    this.opcode = opcode;
  }

  public abstract byte[] toBytes();

  public static Packet parse(byte[] data) throws ParseException {
    return parse(ByteBuffer.wrap(data));
  }

  public static Packet parse(ByteBuffer buffer) throws ParseException {
    // Get and validate opcode
    short code = buffer.getShort();
    Optional<Opcode> opOpcode = Opcode.fromCode(code);
    if (!opOpcode.isPresent()) {
      throw new ParseException("Invalid opcode", 0);
    }
    Opcode opcode = opOpcode.get();

    switch (opcode.code) {
      case 1:
        return RequestPacket.parseRequest(Opcode.RRQ, buffer);
      case 2:
        return RequestPacket.parseRequest(Opcode.WRQ, buffer);
      case 3:
        return DataPacket.parseData(buffer);
      case 4:
        return AckPacket.parseAck(buffer);
      case 5:
        return ErrorPacket.parseError(buffer);
      default:
        throw new ParseException("Invalid opcode", 0);
    }
  }

}
