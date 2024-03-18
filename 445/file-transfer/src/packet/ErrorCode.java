package packet;

import java.util.Arrays;
import java.util.Optional;

public enum ErrorCode {
  NOT_DEFINED(0),
  FILE_NOT_FOUND(1),
  ACCESS_VIOLATION(2),
  DISK_FULL(3),
  ILLEGAL_OPERATION(4),
  UNKNOWN_ID(5),
  FILE_EXISTS(6),
  NO_SUCH_USER(7);

  public final short code;
  public final String meaning;

  ErrorCode(int code) {
    this.code = (short) code;

    switch (code) {
      case 1:
        this.meaning = "File not found.";
        break;
      case 2:
        this.meaning = "Access violation.";
        break;
      case 3:
        this.meaning = "Disk full or allocation exceeded.";
        break;
      case 4:
        this.meaning = "Illegal TFTP operation.";
        break;
      case 5:
        this.meaning = "Unknown transfer ID.";
        break;
      case 6:
        this.meaning = "File already exists.";
        break;
      case 7:
        this.meaning = "No such user.";
        break;
      default:
        this.meaning = "Not defined, see error message (if any).";
        break;
    }
  }

  public static Optional<ErrorCode> fromCode(short i) {
    return Arrays.stream(values()).filter(error -> error.code == i).findFirst();
  }
}
