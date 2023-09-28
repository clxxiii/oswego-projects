package dev.clxxiii.csc375.object;

import org.json.JSONObject;

public class EditSlotOptions {
  public int index;
  public float x;
  public float y;

  public EditSlotOptions(JSONObject json) {
    index = json.getInt("index");
    x = json.getFloat("x");
    y = json.getFloat("y");
  }
}
