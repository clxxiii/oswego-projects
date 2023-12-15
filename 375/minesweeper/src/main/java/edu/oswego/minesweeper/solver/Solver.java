package edu.oswego.minesweeper.solver;

import edu.oswego.minesweeper.Callback;
import edu.oswego.minesweeper.object.Cell;
import edu.oswego.minesweeper.object.Grid;
import edu.oswego.minesweeper.options.CellAction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;


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
        try {
            solve();
        } catch (InterruptedException e) {
            System.out.println("Solving process was interrupted, try again.");
        }
    }
    public void stop() {
        solving = false;
    }

    /**
     * This solver employs two different strategies to detect which squares are safe or not:
     * 1. Looks at the values of each cell, and finds the "100% confirmed bomb or safe" spots relative to one cell.
     * 2. For each significant square, calculate how likely a bomb is to be in each spot, and pick the one with the
     *    smallest chance.
     * Is there a smarter strategy 2, almost certainly. Do I know it? No, so this is what you get.
     */
    private void solve() throws InterruptedException {
        if (grid.isSolved()) return;
        if (!solving) return;

        // If we haven't clicked anything yet, click the top left corner.
        if (grid.getProgress() == 0.0d) {
            revealSquare(0,0);
            solve();
            return;
        }

        boolean step1Success = strategy1();
        // Display progress
        runCallback();

        // If the last step 1 worked, try again!
        if (step1Success) {
            solve();
            return;
        }

        /* Strategy 2, since it is based on luck, is being removed from initial parallel testing.
         *
        // If the game is still unsolved and last round wasn't successful, we need to employ strategy 2
        double[][] odds = new double[grid.height][grid.width];

        for (int[] coords : significantSquares) {
            int x = coords[0];
            int y = coords[1];
            Cell cell = grid.getCell(x, y);
            // Count up untouched and flagged squares to check for completeness
            int untouched = 0;
            int flagged = 0;
            int[][] surrounding = grid.getSurroundingIndexes(x, y);
            for (int[] xy : surrounding) {
                Cell c = grid.getCell(xy[0], xy[1]);
                if (!c.revealed) {
                    if (c.flagged) flagged++;
                    else untouched++;
                }
            }

            double bombOdds = (double) (cell.getValue() - flagged) / untouched;
            for (int[] xy : surrounding) {
                Cell c = grid.getCell(xy[0], xy[1]);
                if (!c.revealed && !c.flagged) {
                    odds[xy[1]][xy[0]] += bombOdds;
                }
            }
        }

        // Find minimum in matrix (that is not zero)
        int minY = 0;
        int minX = 0;
        double minValue = Double.MAX_VALUE;
        for (int y = 0; y < grid.height; y++) {
            for (int x = 0; x < grid.width; x++) {
                if (odds[y][x] != 0.0d) {
                    double affinity = odds[y][x];
                    if (affinity < minValue) {
                        minValue = affinity;
                        minY = y;
                        minX = x;
                    }
                }
            }
        }
        // Reveal square and start from beginning
        revealSquare(minX, minY);
        // If this reveals a bomb, solving will be set to false, which will fail the base case.
        solve();

         */
    }

    private boolean strategy1() throws InterruptedException {

        // We need to keep track of whether the previous round of step 1 was successful or not.
        boolean successful = false;

        // Check the full grid for significant squares
        int[][] significantSquares = getSignificantSquares();
        Strategy1Solver[] solvers = new Strategy1Solver[significantSquares.length];
        CountDownLatch latch = new CountDownLatch(solvers.length);
        for (int i = 0; i < solvers.length; i++) {
            int x = significantSquares[i][0];
            int y = significantSquares[i][1];
            solvers[i] = new Strategy1Solver(grid, x, y, latch);
            new Thread(solvers[i]).start();
        }

        latch.await();

        for (Strategy1Solver solver : solvers) {
            if (solver.result == CellAction.FLAG) {
                flagSurrounding(solver.x, solver.y);
                completed[solver.y][solver.x] = true;
                successful = true;
            }

            if (solver.result == CellAction.REVEAL) {
                revealSurrounding(solver.x, solver.y);
                completed[solver.y][solver.x] = true;
                successful = true;
            }
        }

        return successful;
    }

    private int[][] getSignificantSquares() {
        ArrayList<int[]> squares = new ArrayList<>();
        for (int y = 0; y < grid.height; y++) {
            for (int x = 0; x < grid.width; x++) {
                if (!completed[y][x] && grid.isSignificant(x,y)) {
                    int[] xy = {x, y};
                    squares.add(xy);
                }
            }
        }
        return squares.toArray(new int[squares.size()][2]);
    }

    private void revealSurrounding(int x, int y) {
        for (int[] coords : grid.getSurroundingIndexes(x,y)) {
            revealSquare(coords[0],coords[1]);
        }
    }

    private void flagSurrounding(int x, int y) {
        for (int[] coords : grid.getSurroundingIndexes(x,y)) {
            flagSquare(coords[0],coords[1]);
        }
    }


    private void revealSquare(int x, int y) {
        Cell cell = grid.getCell(x, y);
        if (cell.revealed || cell.flagged) return;

        int value = grid.reveal(x, y);
        if (value == 9) solving = false;
    }
    private void flagSquare(int x, int y) {
        Cell cell = grid.getCell(x, y);
        if (cell.flagged || cell.revealed) return;

        grid.flag(x, y);
    }

    private void runCallback() {
       new Thread(callback::run).start();
    }
}
