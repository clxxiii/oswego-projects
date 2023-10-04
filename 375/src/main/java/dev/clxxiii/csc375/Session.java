package dev.clxxiii.csc375;

import dev.clxxiii.csc375.concurrent.Exchanger;
import dev.clxxiii.csc375.model.Grid;
import dev.clxxiii.csc375.object.GetAffinityOptions;
import dev.clxxiii.csc375.object.MakeGridOptions;
import java.io.IOException;
import java.util.Random;
import org.json.JSONObject;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

public class Session {

  static Random random = new Random();

  private WebSocketSession ws;
  Exchanger ex;

  protected Session(WebSocketSession ws) {
    ex = new Exchanger();
    this.ws = ws;
  }
  protected void makeGrid(MakeGridOptions data) {
    Grid grid = new Grid(data.size);
    for (int y = 0; y < data.size; y++) {
      for (int x = 0; x < data.size; x++) {
        grid.set(x, y, random.nextInt(0, data.types));
      }
    }
    ex.setGrid(grid);
    sendMessage(new JSONObject().put("grid", grid.toJSON()));
  }
  protected void getAffinity(GetAffinityOptions opt) {
    int affinity =
        ex.getGrid().getDirectAffinity(opt.x1, opt.y1, opt.x2, opt.y2);
    sendMessage(new JSONObject().put("affinity", affinity));
  }

  protected void start(int threads) { ex.startRunning(threads, ws); }

  protected void stop() {
    ex.stopRunning();
    sendMessage(new JSONObject().put("grid", ex.getGrid().toJSON()));
  }

  /*
   * Private Functions
   */
  private void sendMessage(JSONObject msg) {
    TextMessage payload = new TextMessage(msg.toString());

    try {
      ws.sendMessage(payload);
    } catch (IOException e) {
      System.out.println(e.toString());
    }
  }

  private void sendMessage(String msg) {
    JSONObject json = new JSONObject().put("announce", msg);
    TextMessage payload = new TextMessage(json.toString());

    try {
      ws.sendMessage(payload);
    } catch (IOException e) {
      System.out.println(e.toString());
    }
  }
}
