package edu.oswego.minesweeper.solver;

import edu.oswego.minesweeper.options.CellAction;

/**
 * A simple data class used by the forked solver processes to return a list of
 */
public class CellActionOptions {
    final CellAction action;
    final int x;
    final int y;
    CellActionOptions(int x, int y, CellAction action) {
        this.x = x;
        this.y = y;
        this.action = action;
    }
}
