package edu.oswego.minesweeper.object;

import org.json.JSONObject;

public class Cell {
  private int value;
  private final boolean bomb;
  public boolean revealed = false;
  public boolean flagged = false;

  protected Cell(boolean bomb) { this.bomb = bomb; }

  protected void setValue(int v) { value = v; }

  /**
   * Reveals the content of the cell and returns it.
   * If the square contains a bomb, then the value 9 will be returned.
   *
   */
  public int reveal() {
    revealed = true;
    flagged = false;
    if (bomb)
      return 9;
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
    if (bomb) return "x";
    return "" + value;
  }
}
