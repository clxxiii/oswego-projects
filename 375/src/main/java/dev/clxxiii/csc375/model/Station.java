package dev.clxxiii.csc375.model;

import org.json.JSONObject;

public class Station {
  static int count = 0;

  int id;
  boolean servesA;
  boolean servesB;
  boolean servesC;
  boolean servesD;

  public Station(boolean[] service) {
    id = ++count;
    servesA = service[0];
    servesB = service[1];
    servesC = service[2];
    servesD = service[3];
  }

  public JSONObject toJSON() {
    return new JSONObject().put("id", id).put("service",
                                              new JSONObject()
                                                  .put("a", servesA)
                                                  .put("b", servesB)
                                                  .put("c", servesC)
                                                  .put("d", servesD));
  }
}
