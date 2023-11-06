package edu.oswego.benchmark;

public class Character {
  final String ign;
  double x;
  double y;

  public Character(String ign) { this.ign = ign; }

  public double getX() { return x; }

  public double getY() { return y; }

  public void setX(double x) { this.x = x; }

  public void setY(double y) { this.y = y; }
}
