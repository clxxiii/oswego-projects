package dev.clxxiii.csc375.model;

import org.json.JSONObject;

public class StartMessage {
  public int stations;
  public int slots;
  public int threads;

  public StartMessage(JSONObject json) {
    stations = json.getInt("stations");
    slots = json.getInt("slots");
    threads = json.getInt("threads");
  }
}
