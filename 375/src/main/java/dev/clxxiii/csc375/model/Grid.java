package dev.clxxiii.csc375.model;

import org.json.JSONArray;

public class Grid implements Cloneable {
  public int[][] grid;
  public final int size;

  public Grid(int size) {
    this.size = size;
    grid = new int[size][size];
  }

  public int get(int x, int y) { return grid[y][x]; }

  public void set(int x, int y, int type) { grid[y][x] = type; }

  public void swap(int x1, int y1, int x2, int y2) {
    int type = grid[y1][x1];
    grid[y1][x1] = grid[y2][x2];
    grid[y2][x2] = type;
  }

  public int getDirectAffinity(int x1, int y1, int x2, int y2) {
    int maxDistance = grid.length * 2;
    int type1 = grid[y1][x1];
    int type2 = grid[y2][x2];

    // How many rooms would you have to walk through to
    // get from one to the other
    int distance = Math.abs(x2 - x1) + Math.abs(y2 - y1) - 1;

    if (type1 == type2 && distance <= size / 4) {
      return (size / 4) - distance;
    }

    return 0;

    // if (type1 == type2) {
    //     return distance - maxDistance;
    // }
    // return maxDistance - distance;
  }

  public int getCellAffinity(int x, int y) {
    int total = 0;
    for (int x2 = 0; x2 < grid.length; x2++) {
      for (int y2 = 0; y2 < grid.length; y2++) {
        if (x2 == x && y2 == y)
          continue;
        total += getDirectAffinity(x, y, x2, y2);
      }
    }
    return total;
  }

  public int getTotalAffinity() {
    int total = 0;
    for (int x = 0; x < grid.length; x++) {
      for (int y = 0; y < grid.length; y++) {
        total += getCellAffinity(x, y);
      }
    }
    return total;
  }

  public Grid clone() {
    Grid newGrid = new Grid(size);
    for (int x = 0; x < size; x++) {
      for (int y = 0; y < size; y++) {
        newGrid.set(x, y, this.get(x, y));
      }
    }
    return newGrid;
  }

  public JSONArray toJSON() {
    JSONArray array = new JSONArray();
    for (int[] row : grid) {
      JSONArray jsonRow = new JSONArray(row);
      array.put(jsonRow);
    }
    return array;
  }
}
