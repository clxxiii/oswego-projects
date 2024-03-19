package packet;

import java.util.Optional;
import java.util.Arrays;

public enum Opcode {
  RRQ(1),
  WRQ(2),
  DATA(3),
  ACK(4),
  ERROR(5),
  OACK(6);

  public final short code;

  private Opcode(int i) {
    code = (short) i;
  }

  public static Optional<Opcode> fromCode(short i) {
    return Arrays.stream(values()).filter(error -> error.code == i).findFirst();
  }
}
