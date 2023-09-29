package dev.clxxiii.csc375.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.BitSet;


public class StationMap {
  private final StationSlot[] slots;
  private final Station[] stations;
  final float routeAMileage = 10;
  final float routeBMileage = 20;
  final float routeCMileage = 15;
  final float routeDMileage = 5;

  public StationMap(int size) {
    slots = new StationSlot[size];
    stations = new Station[size];
  }

  /**
   * Puts a station slot into an index in the array, and returns the old one.
   */
  public StationSlot setSlot(StationSlot slot, int index) {
    StationSlot old = slots[index];
    slots[index] = slot;
    return old;
  }

  /**
   * Puts a station into an index in the array, and returns the old one.
   */
  public Station setStation(Station slot, int index) {
    Station old = stations[index];
    stations[index] = slot;
    return old;
  }

  public void editSlot(int index, float x, float y) {
    slots[index].x = x;
    slots[index].y = y;
  }

  public float getSlotAffinity(int i1, int i2) {
    float affinity = 1;
    Station station1 = stations[i1];
    Station station2 = stations[i2];
    if (station1 == null || station2 == null) return affinity;

    BitSet routes = station1.getSharedRoutes(station2);

    StationSlot slot1 = slots[i1];
    StationSlot slot2 = slots[i2];

    double distance = slot1.distanceFrom(slot2);

    if (routes.equals(station1.stationSet)) {
      affinity += (float) distance;
    }

    if (routes.isEmpty()) {
      affinity += (float) distance;
    }

    if (routes.get(0)) {
      float difference = (float) (routeAMileage - distance);

      // Prioritize 'too short' over 'too far'
      if (distance < 0) {
        difference = (float) Math.pow(distance, 3);
      }

      if (distance > 0) {
        difference = (float) Math.pow(distance, 2);
      }
      affinity += difference;
    }

    return affinity;
  }

  public JSONArray toJSON() {
    JSONArray arr = new JSONArray();

    for (int i = 0; i < slots.length; i++) {
      JSONObject slot = slots[i].toJSON();
      if (stations[i] != null) {
        slot.put("station", stations[i].toJSON());
      }
      arr.put(slot);
    }

    return arr;
  }
}
