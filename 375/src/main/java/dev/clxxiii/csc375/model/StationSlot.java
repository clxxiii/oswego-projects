package dev.clxxiii.csc375.model;

import org.json.JSONObject;

final

public class StationSlot {
  float x, y;

  public StationSlot(float x, float y) {
    this.x = x;
    this.y = y;
  }

  public double distanceFrom(StationSlot s2) {
    float xDist = Math.abs(x - s2.x);
    float yDist = Math.abs(y - s2.y);

    return Math.sqrt(Math.pow(xDist, 2) + Math.pow(yDist, 2));
  }

  public JSONObject toJSON() {
    return new JSONObject().put("x", x).put("y", y);
  }
}
