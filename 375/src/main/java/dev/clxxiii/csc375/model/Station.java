package dev.clxxiii.csc375.model;

import org.json.JSONObject;

import java.util.BitSet;
import java.util.Random;

public class Station {
  static int count = 0;

  int id;
  BitSet stationSet = new BitSet(8);

  public Station(boolean[] service) {
    id = ++count;
    stationSet.set(0, service[0]);
    stationSet.set(1, service[1]);
    stationSet.set(2, service[2]);
    stationSet.set(3, service[3]);

    // Prevent 'no route' stations
    if (stationSet.isEmpty()) {
      int rd = new Random().nextInt(5);
      stationSet.set(rd);
    }
  }

  public BitSet getSharedRoutes(Station s2) {
    s2.stationSet.and(stationSet);
    return s2.stationSet;
  }

  public JSONObject toJSON() {
    return new JSONObject().put("id", id).put("service",
                                              new JSONObject()
                                                  .put("a", stationSet.get(0))
                                                  .put("b", stationSet.get(1))
                                                  .put("c", stationSet.get(2))
                                                  .put("d", stationSet.get(3)));
  }
}
