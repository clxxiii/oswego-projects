package message;

import java.util.Random;

public class MessageGenerator {

  final long seed;
  public final Message Byte8;
  public final Message Byte64;
  public final Message Byte256;
  public final Message Byte512;
  public final Message Byte1024;

  public MessageGenerator(long seed) {
    this.seed = seed;
    Byte8 = makeMessage(8);
    Byte64 = makeMessage(64);
    Byte256 = makeMessage(256);
    Byte512 = makeMessage(512);
    Byte1024 = makeMessage(1024);
  }

  private Message makeMessage(int size) {
    long[] longs = new long[size / 8];
    Random rng = new Random(seed);
    for (int i = 0; i < (size / 8); i++) {
      longs[i] = rng.nextLong();
    }
    return Message.fromRaw(longs);
  }

  public Message getSize(int size) {
    switch (size) {
      case 8:
        return Byte8;
      case 64:
        return Byte64;
      case 256:
        return Byte256;
      case 512:
        return Byte512;
      case 1024:
        return Byte1024;
      default:
        return null;
    }
  }

  public static boolean validate(Message m1) {
    return true;
  }
}
