package edu.oswego.minesweeper;

import edu.oswego.minesweeper.object.Grid;
import edu.oswego.minesweeper.options.CellAction;
import edu.oswego.minesweeper.solver.Strategy1Solver;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SolverServer {

    private static Socket socket;
    private static PrintWriter out;
    private static BufferedReader in;
    private static ExecutorService executor = Executors.newSingleThreadExecutor();

    public SolverServer(String hostname, int port) throws IOException {
        socket = new Socket(hostname, port);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
    }

    public static Future<Strategy1Solver[]> sendTasks(int[][] cells, int start, int end, Grid grid) {
        JSONArray cellList = new JSONArray();
        for (int i = start; i < end; i++) {
            JSONObject xy = new JSONObject()
                    .put("x", cells[i][0])
                    .put("y", cells[i][1]);
            cellList.put(xy);
        }

        JSONObject payload = new JSONObject()
                .put("grid", grid.toJSON())
                .put("cells", cellList);

        return executor.submit(() -> {
            String responseString = sendMessage(payload.toString());
            JSONObject response = new JSONObject(responseString);
            JSONArray array = response.getJSONArray("cells");
            Strategy1Solver[] solvers = new Strategy1Solver[cells.length];
            for (int i = 0; i < cellList.length(); i++) {
                String action = array.getString(i);
                if (action.equalsIgnoreCase("reveal")) {
                    solvers[i] = new Strategy1Solver(CellAction.REVEAL);
                }
                else if (action.equalsIgnoreCase("flag")) {
                    solvers[i] = new Strategy1Solver(CellAction.FLAG);
                }
                else { solvers[i] = new Strategy1Solver(CellAction.IGNORE); }
            }
            return solvers;
        });
    }

    public static String sendMessage(String msg) {
       out.println(msg);
       String response;
       try {
           response = in.readLine();
       } catch (IOException e) {
           return "";
       }
       return response;
    }

}
