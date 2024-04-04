package object;

import java.nio.ByteBuffer;

public class Key {

  private ByteBuffer buffer;
  private long key;
  private byte[] byteKey;
  int uses;

  public Key(long key) {
    this.uses = 0;
    buffer = ByteBuffer.allocate(Long.BYTES);
    setKey(key);
  }

  public byte[] decrypt(byte[] raw) {
    return encrypt(raw);
  }

  public byte[] encrypt(byte[] raw) {
    byte[] out = new byte[raw.length];
    for (int i = 0; i < raw.length; i++) {
      if (uses >= 64) {
        update();
      }
      byte key = byteKey[uses % 8];
      out[i] = (byte) (raw[i] ^ key);
      uses++;
    }

    return raw;
  }

  private void update() {
    long key = this.key;
    key ^= key << 13;
    key ^= key >>> 7;
    key ^= key << 17;
    setKey(key);
  }

  private void setKey(long key) {
    this.key = key;
    this.uses = 0;
    buffer.putLong(key);
    byteKey = buffer.array();
    buffer.clear();
  }
}
