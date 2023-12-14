package edu.oswego.minesweeper.solver;

import edu.oswego.minesweeper.Callback;
import edu.oswego.minesweeper.object.Cell;
import edu.oswego.minesweeper.object.Grid;



public class Solver {
    private final Grid grid;
    private final boolean[][] completed;

    private final Callback callback;

    private boolean solving;

    public Solver(Grid grid, Callback callback) {
        this.grid = grid;
        this.callback = callback;
        completed = new boolean[grid.height][grid.width];
    }
    public void start() {
        solving = true;
        solve();
    }
    public void stop() {
        solving = false;
    }

    /**
     * This solver employs two different strategies to detect which squares are safe or not:
     * 1. Looks at the values of each cell, and finds the "100% confirmed bomb or safe" spots relative to one cell.
     * 2. Random bullshit go
     */
    private void solve() {
        if (!solving) return;

        // If we haven't clicked anything yet, click the top left corner.
        if (grid.getProgress() == 0.0d) {
            revealSquare(0,0);
            solve();
            return;
        }

        // We need to keep track of whether the previous round of step 1 was successful or not.
        boolean successful = false;

        // Check the full grid for significant squares
        for (int y = 0; y < grid.height; y++) {
            for (int x = 0; x < grid.width; x++) {
                if (!completed[y][x] && grid.isSignificant(x,y)) {
                    Cell cell = grid.getCell(x,y);
                    // Count up untouched and flagged squares to check for completeness
                    int untouched = 0;
                    int flagged = 0;
                    for (int[] xy : grid.getSurroundingIndexes(x,y)) {
                        Cell c = grid.getCell(xy[0],xy[1]);
                        if (!c.revealed) {
                            if (c.flagged) flagged++;
                            else untouched++;
                        }
                    }

                    // KEY: If the number of flags is equal to the value of the square, then every surrounding cell
                    // is safe.
                    if (flagged == cell.getValue()) {
                        revealSurrounding(x, y);
                        completed[y][x] = true;
                        successful = true;
                    }

                    // KEY: If the number of untouched squares is equal to the value of the cell minus the number of
                    // flags, then we know that every untouched square is also a bomb.
                    if (untouched == (cell.getValue() - flagged)) {
                        flagSurrounding(x, y);
                        completed[y][x] = true;
                        successful = true;
                    }
                }
            }
        }

        // If the last step 1 worked, try again!
        if (successful) {
            solve();
            return;
        }

        // If the game is still unsolved and last round wasn't successful, we need to employ strategy 2

    }

    private void revealSurrounding(int x, int y) {
        for (int[] coords : grid.getSurroundingIndexes(x,y)) {
            Cell cell = grid.getCell(coords[0], coords[1]);
            if (!cell.revealed && !cell.flagged) revealSquare(coords[0],coords[1]);
        }
    }

    private void flagSurrounding(int x, int y) {
        for (int[] coords : grid.getSurroundingIndexes(x,y)) {
            Cell cell = grid.getCell(coords[0], coords[1]);
            if (!cell.revealed && !cell.flagged) flagSquare(coords[0],coords[1]);
        }
    }


    private void revealSquare(int x, int y) {
       grid.reveal(x, y);
       callback.run();
    }
    private void flagSquare(int x, int y) {
        grid.flag(x, y);
        callback.run();
    }

}
