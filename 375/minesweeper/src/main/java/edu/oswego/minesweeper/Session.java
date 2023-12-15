package edu.oswego.minesweeper;

import edu.oswego.minesweeper.object.Grid;
import edu.oswego.minesweeper.options.CellActionOptions;
import edu.oswego.minesweeper.options.MakeGridOptions;
import java.io.IOException;
import java.text.ParseException;

import edu.oswego.minesweeper.solver.Solver;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

public class Session {

  private final WebSocketSession ws;
  private Grid grid;
  private Solver solver;
  private Thread solverThread;

  public Session(WebSocketSession ws) { this.ws = ws; }
  public void parseAction(JSONObject json) {
    // Parse Action
    String action;
    try {
      action = json.getString("action");
    } catch (JSONException e) {
      sendError(
              "No Action",
              "You must specify an action to perform. Valid actions are 'makeGrid', 'flag', and 'reveal'"
      );
      return;
    }

    try {
      if (action.equalsIgnoreCase("makeGrid")) {
        makeGrid(new MakeGridOptions(json));
      }
      else if (action.equalsIgnoreCase("flag")) {
        flagCell(new CellActionOptions(json));
      }
      else if (action.equalsIgnoreCase("reveal")) {
        revealCell(new CellActionOptions(json));
      }
      else if (action.equalsIgnoreCase("startSolve")) {
        startSolving();
      }
      else if (action.equalsIgnoreCase("stopSolve")) {
        stopSolving();
      }
      else {
        sendError(
                "Invalid Action",
                "Valid actions are 'makeGrid', 'flag', and 'reveal'"
        );
      }
    } catch (ParseException e) {
      sendError("Parse Error", e.getMessage());
    }
  }
  private void makeGrid(MakeGridOptions options) {
    grid = new Grid(options.width, options.height, options.bombs);
    solver = new Solver(grid, () -> { sendMessage(grid.toJSON()); });
    sendMessage(grid.toJSON());
  }
  private void flagCell(CellActionOptions options) {
    grid.flag(options.x, options.y);
    sendMessage(grid.toJSON());
  }
  private void revealCell(CellActionOptions options) {
    grid.reveal(options.x, options.y);
    sendMessage(grid.toJSON());
  }
  private void startSolving() {
    solver.start();
  }
  private void stopSolving() {
    solver.stop();
  }

  /*
   * Private Functions
   */
  private void sendError(String title, String desc) {
    JSONObject error = new JSONObject()
            .put("error", title)
            .put("message", desc);
    sendMessage(error);
  }
  protected synchronized void sendMessage(JSONObject msg) {
    TextMessage payload = new TextMessage(msg.toString());

    try {
      ws.sendMessage(payload);
    } catch (IOException e) {
      System.out.println(e);
    }
  }
}
