package edu.oswego.minesweeper.options;

import org.json.JSONException;
import org.json.JSONObject;

public class MakeGridOptions {
  public int width;
  public int height;
  public int bombs;

  public MakeGridOptions() {
    width = 7;
    height = 7;
    bombs = (int) Math.round((width * height) * 0.25);
  }
  public MakeGridOptions(JSONObject data) {
    try {
      this.width = data.getInt("width");
    } catch (JSONException e) {
      this.width = 7;
    }

    try {
      this.height = data.getInt("height");
    } catch (JSONException e) {
      this.height = 7;
    }

    try {
      this.bombs = data.getInt("bombs");
    } catch (JSONException e) {
      // If no bombs argument is provided, populate 1/4th the board with bombs
      bombs = (int) Math.round((width * height) * 0.25);
    }
  }
}
