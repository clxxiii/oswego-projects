package edu.oswego.mineserver;

import edu.oswego.mineserver.object.Grid;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class Solver {
    public static JSONObject solve(JSONObject data) throws IOException {
        Grid grid = new Grid(data.getJSONObject("grid"));
        int[][] significantSquares = getSignificantSquares(data.getJSONArray("cells"));
        Strategy1Solver[] solvers = new Strategy1Solver[significantSquares.length];
        CountDownLatch latch = new CountDownLatch(solvers.length);

        // Spawn threads to solve the solvers array
        for (int i = 0; i < solvers.length; i++) {
            int x = significantSquares[i][0];
            int y = significantSquares[i][1];
            solvers[i] = new Strategy1Solver(grid, x, y, latch);
            new Thread(solvers[i]).start();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new IOException();
        }

        JSONObject response = new JSONObject();
        JSONArray cells = new JSONArray();

        for (Strategy1Solver s : solvers) {
            if (s.result == CellAction.FLAG) cells.put("flag");
            else if (s.result == CellAction.REVEAL) cells.put("reveal");
            else cells.put("ignore");
        }
        response.put("cells", cells);


        return response;
    }

    private static int[][] getSignificantSquares(JSONArray array) {
        int[][] cells = new int[array.length()][2];
        for (int i = 0; i < array.length(); i++) {
            JSONObject obj = array.getJSONObject(i);
            int[] xy = {obj.getInt("x"), obj.getInt("y")};
            cells[i] = xy;
        }
        return cells;
    }
}
