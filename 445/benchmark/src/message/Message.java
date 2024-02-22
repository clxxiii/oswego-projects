package message;

import java.nio.ByteBuffer;
import java.nio.LongBuffer;

public class Message {

  public long[] longs;
  int size;
  boolean encrypted;

  private Message(long[] longs, boolean encrypted) {
    this.longs = longs;
    size = longs.length * Long.BYTES;
    this.encrypted = encrypted;
  }

  private Message(byte[] bytes, boolean encrypted) {
    // Convert bytes to longs
    ByteBuffer buffer = ByteBuffer.wrap(bytes);
    LongBuffer longBuffer = buffer.asLongBuffer();
    long[] l = new long[longBuffer.capacity()];
    longBuffer.get(l);
    longs = l;

    size = bytes.length;
    this.encrypted = encrypted;
  }

  public static Message fromEncrypted(long[] longs) {
    return new Message(longs, true);
  }

  public static Message fromRaw(long[] longs) {
    return new Message(longs, false);
  }

  public static Message fromEncrypted(byte[] bytes) {
    return new Message(bytes, true);
  }

  public static Message fromRaw(byte[] bytes) {
    return new Message(bytes, false);
  }

  public void encrypt(Key key) {
    for (int i = 0; i < longs.length; i++) {
      longs[i] ^= key.useKey();
    }
    encrypted = !encrypted;
  }

  public void decrypt(Key key) {
    encrypt(key);
  }

  public ByteBuffer toBuffer() {
    ByteBuffer buffer = ByteBuffer.allocate(size);
    for (int i = 0; i < longs.length; i++) {
      buffer.putLong(longs[i]);
    }
    return buffer;
  }

  public byte[] bytes() {
    ByteBuffer buffer = ByteBuffer.allocate(size);
    buffer.asLongBuffer().put(longs);
    return buffer.array();
  }

  public boolean equals(Message m2) {
    if (m2.longs.length != this.longs.length)
      return false;

    for (int i = 0; i < this.longs.length; i++) {
      if (this.longs[i] != m2.longs[i])
        return false;
    }
    return true;
  }

  public Message clone() {
    return new Message(longs.clone(), encrypted);
  }
}
