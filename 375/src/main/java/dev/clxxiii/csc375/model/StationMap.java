package dev.clxxiii.csc375.model;

import org.json.JSONArray;
import org.json.JSONObject;

public class StationMap {
  private StationSlot[] slots;
  private Station[] stations;

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
