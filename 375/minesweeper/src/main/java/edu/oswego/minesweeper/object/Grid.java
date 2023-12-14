package edu.oswego.minesweeper.object;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReentrantLock;

public class Grid {
    public final int bombs;
    public final int width;
    public final int height;


    private final Cell[][] cells;
    private int flags;
    private boolean failed;
    // private double progress = 0;

    private final ReentrantLock revealLock;


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
       this.failed = false;

       revealLock = new ReentrantLock();

       // Generate true/false grid of what is and isn't a bomb
       boolean[][] bombGrid = generateBombGrid(bombs, width, height);

       cells = new Cell[height][width];
       for (int y = 0; y < height; y++) {
           for (int x = 0; x < width; x++) {
               // In each spot, create a new cell object
               // If statement to save value computation time for bombs
               if (!bombGrid[y][x]) {
                   cells[y][x] = new Cell(false, calculateValue(x,y,bombGrid));
               }
               else {
                   cells[y][x] = new Cell(true, 0);
               }
           }
       }
    }

    public Cell getCell(int x, int y) {
        return cells[y][x];
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
            for (int[] xy : getSurroundingIndexes(x, y)) {
                reveal(xy[0], xy[1]);
            }
        }
        if (status == 9) failed = true;
        return status;
    }

    /*
     * Private Construction Helpers
     */
    private int calculateValue(int x, int y, boolean[][] grid) {
        int count = 0;
        for (int[] xy : getSurroundingIndexes(x,y)) {
            if (grid[xy[1]][xy[0]]) count++;
        }
        return count;
    }

    public double getProgress() {
        // Count the squares
        int revealed = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (cells[y][x].revealed) revealed++;
            }
        }
        return (double) revealed / (double) ((height * width) - bombs);
    }

    public boolean isSolved() {
        return getProgress() == 1.0d && flags == bombs;
    }
    public boolean isFailed() { return failed; }


    /**
     * A cell is solvable if it is next to a cell that has been revealed.
     */
    public boolean isSolvable(int x, int y) {
        if (cells[y][x].revealed) return false;

        for (int[] xy : getSurroundingIndexes(x, y)) {
            if (getCell(xy[0],xy[1]).revealed) return true;
        }
        return false;
    }

    /**
     * A cell is significant if it is revealed and next to an unrevealed cell
     */
    public boolean isSignificant(int x, int y) {
        if (!cells[y][x].revealed) return false;

        for (int[] xy : getSurroundingIndexes(x, y)) {
            if (!getCell(xy[0],xy[1]).revealed) return true;
        }
        return false;
    }

    /**
     * Returns an (x,y) pair of every cell in a 3x3 surrounding area of the selected cell
     * @param x
     * @param y
     * @return
     */
    public int[][] getSurroundingIndexes(int x, int y) {
        ArrayList<int[]> indexes = new ArrayList<>();
        // Loops through the cells like this:
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

            int[] c = {newX, newY};
            indexes.add(c);
        }
        return indexes.toArray(new int[indexes.size()][2]);
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
                .put("flags", flags)
                .put("progress", getProgress())
                .put("solved", isSolved())
                .put("failed", failed);

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
        StringBuilder output = new StringBuilder();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                output.append(cells[y][x].toString());
            }
            output.append("\n");
        }

        return output.toString();
    }
}
