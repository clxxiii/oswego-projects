package dev.clxxiii.csc375.object;

import org.json.JSONObject;

public class BuildOptions {
  public int stations;
  public int slots;

  public BuildOptions(JSONObject json) {
    stations = json.getInt("stations");
    slots = json.getInt("slots");
  }
}
