package edu.oswego.minesweeper.solver;

import edu.oswego.minesweeper.object.Cell;
import edu.oswego.minesweeper.object.Grid;
import edu.oswego.minesweeper.options.CellAction;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.RecursiveAction;

/**
 * Implements Strategy 1 on a single significant cell.
 * Returns a CellAction on whether to ignore, flag, or reveal all cells surrounding the given cell.
 */
public class Strategy1Solver implements Runnable {
    final Grid grid;
    final int x;
    final int y;
    final CountDownLatch latch;
    CellAction result = CellAction.IGNORE;
    public Strategy1Solver(Grid grid, int x, int y, CountDownLatch latch) {
        this.grid = grid;
        this.x = x;
        this.y = y;
        this.latch = latch;
    }

    public void run() {
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
            result = CellAction.REVEAL;
        }

        // KEY: If the number of untouched squares is equal to the value of the cell minus the number of
        // flags, then we know that every untouched square is also a bomb.
        if (untouched == (cell.getValue() - flagged)) {
            result = CellAction.FLAG;
        }

        latch.countDown();
    }
}
