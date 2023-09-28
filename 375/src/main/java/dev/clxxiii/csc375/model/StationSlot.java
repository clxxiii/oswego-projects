package dev.clxxiii.csc375.model;

import org.json.JSONObject;

public class StationSlot {
  float x, y;

  public StationSlot(float x, float y) {
    this.x = x;
    this.y = y;
  }

  float affinityWith(StationSlot that) { return (float)0; }

  public JSONObject toJSON() {
    return new JSONObject().put("x", x).put("y", y);
  }
}
