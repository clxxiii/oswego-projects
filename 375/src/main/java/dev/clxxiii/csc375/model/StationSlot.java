package dev.clxxiii.csc375.model;

public class StationSlot {
  float x, y;
  Station station;

  StationSlot(float x, float y) {
    this.x = x;
    this.y = y;
    this.station = null;
  }

  StationSlot(float x, float y, Station station) {
    this.x = x;
    this.y = y;
    this.station = station;
  }

  float affinityWith(StationSlot that) { return (float)0; }
}
