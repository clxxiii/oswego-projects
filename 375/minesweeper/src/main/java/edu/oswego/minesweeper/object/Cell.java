package edu.oswego.minesweeper.object;

import org.json.JSONObject;

import java.util.concurrent.locks.ReentrantLock;

public class Cell {
  private int value;
  private final boolean bomb;
  public boolean revealed = false;
  public boolean flagged = false;
  private final ReentrantLock revealLock;

  protected Cell(boolean bomb, int value) {
    this.bomb = bomb;
    this.value = value;

    revealLock = new ReentrantLock();
  }

  /**
   * Atomically reveals the content of the cell and returns it.
   * If the square contains a bomb, then the value 9 will be returned.
   */
  public int reveal() {
    revealLock.lock();
    int value = this.value;
    try {
      revealed = true;
      flagged = false;
      if (bomb) value = 9;
    }
    finally {
      revealLock.unlock();
    }
    return value;
  }

  public int getValue() {
    if (!revealed) return -1;
    return value;
  }

  public JSONObject toJSON() {
    JSONObject json = new JSONObject()
            .put("flagged", flagged)
            .put("revealed", revealed);

    if (revealed) {
      json.put("bomb", bomb);
      if (!bomb) {
        json.put("value", value);
      }
    }
    return json;
  }

  public String toString() {
    if (revealed) {
      if (bomb) return "x";
      return "" + value;
    }

    return "█";
  }
}
