package edu.oswego.minesweeper.object;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Grid {
    public Cell[][] cells;

    private final int bombs;
    private final int width;
    private final int height;

    private int flags;


    /**
     * A grid is generated with the number of bombs given. This constructor is written in an optimistic manner,
     * in hopes that there are fewer bombs than tiles. If the bomb count nears the tile count, this step
     * might take a very long time.
     *
     * @param width
     * @param height
     * @param bombs
     */
    public Grid(int width, int height, int bombs) {
       this.width = width;
       this.height = height;
       this.bombs = bombs;
       this.flags = 0;

       boolean[][] bombGrid = generateBombGrid(bombs, width, height);
       cells = new Cell[height][width];

       for (int y = 0; y < height; y++) {
           for (int x = 0; x < width; x++) {
                cells[y][x] = new Cell(bombGrid[y][x]);
                if (!bombGrid[y][x]) cells[y][x].setValue(calculateValue(x,y,bombGrid));
           }
       }
    }

    public void flag(int x, int y) {
       boolean flagged = cells[y][x].flagged;
       if (flagged) {
           cells[y][x].flagged = false;
           flags--;
       } else {
           cells[y][x].flagged = true;
           flags++;
       }
    }

    public int reveal(int x, int y) {
        Cell cell = cells[y][x];

        // Recursion checks
        if (cell.revealed) return cell.getValue();

        int status = cell.reveal();

        // If the status is 0, Recursively reveal all bombs in the area.
        if (status == 0) {
            // 0 1 2
            // 3 4 5
            // 6 7 8
            for (int i = 0; i < 9; i++) {
                // Skip current bomb
                if (i == 4) continue;

                int xRelative = (i % 3) - 1; // turns every row into 0 1 2, then subtracts 1 to get -1 0 1
                int yRelative = ((int) Math.floor(i / 3.0d)) - 1; // makes one column of 0 1 2, then subtracts 1 to get -1 0 1

                int newX = x + xRelative;
                int newY = y + yRelative;

                // Check bounds
                if (newX < 0 || newX >= width) continue;
                if (newY < 0 || newY >= height) continue;

                reveal(newX, newY);
            }
        }
        return status;
    }

    /*
     * Private Construction Helpers
     */
    private int calculateValue(int x, int y, boolean[][] grid) {
        int count = 0;
        for (int wide = x - 1; wide <= x + 1; wide++) {
            // Check for edges
            if (wide < 0) continue;
            if (wide >= width) continue;

            for (int vert = y - 1; vert <= y + 1; vert++) {
                // Check for edges
                if (vert < 0) continue;
                if (vert >= height) continue;

                if (grid[vert][wide]) count++;
            }
        }
        return count;

    }
    private boolean[][] generateBombGrid(int bombs, int w, int h) {
        boolean[][] grid = new boolean[h][w];
        Random rand = new Random();

        // Place bombs on random squares
        int placed = 0;
        while (placed < bombs) {
           int x = rand.nextInt(w);
           int y = rand.nextInt(h);

           // If a bomb already exists on this cell, try again
           if (grid[y][x]) continue;

           grid[y][x] = true;
           placed++;
        }

        return grid;
    }

    /*
     * Translators
     */
    public JSONObject toJSON() {
        JSONObject json = new JSONObject()
                .put("width", width)
                .put("height", height)
                .put("bombs", bombs)
                .put("flags", flags);

        // Add cells to 2D array and add them to the object
        JSONArray grid = new JSONArray();
        for (int y = 0; y < height; y++)  {
            JSONArray row = new JSONArray();
            for (int x = 0; x < width; x++) {
                row.put(cells[y][x].toJSON());
            }
            grid.put(row);
        }

        json.put("grid", grid);

        return json;
    }

    public String toString() {
        String output = "";
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                output += cells[y][x].toString();
            }
            output += "\n";
        }

        return output;
    }
}
