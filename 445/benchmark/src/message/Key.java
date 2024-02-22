package message;

public class Key {

  private long key;
  int uses;

  public Key(long key) {
    this.key = key;
    this.uses = 0;
  }

  protected long useKey() {
    if (uses <= 8) {
      uses++;
      return key;
    }

    update();
    uses = 1;
    return key;
  }

  private void update() {
    key ^= key << 13;
    key ^= key >>> 7;
    key ^= key << 17;
  }
}
