package dev.clxxiii.csc375.model;

public class Station {
  static int count = 0;

  int id;
  boolean international;

  Station() { id = ++count; }
}
