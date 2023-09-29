package dev.clxxiii.csc375;

import dev.clxxiii.csc375.model.Station;
import dev.clxxiii.csc375.model.StationMap;
import dev.clxxiii.csc375.model.StationSlot;
import dev.clxxiii.csc375.object.BuildOptions;
import dev.clxxiii.csc375.object.EditSlotOptions;
import java.io.IOException;
import java.util.Random;

import dev.clxxiii.csc375.object.GetAffinityOptions;
import org.json.JSONObject;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

public class Session {

  static Random random = new Random();

  private WebSocketSession ws;
  public StationMap map;
  private final int canvasScale = 2;

  protected Session(WebSocketSession ws) { this.ws = ws; }

  protected void buildMap(BuildOptions config) {
    map = new StationMap(config.slots);
    for (int i = 0; i < config.slots; i++) {
      StationSlot slot =
          new StationSlot(getRandomCoordinate(), getRandomCoordinate());
      map.setSlot(slot, i);

      if (i < config.stations) {
        Station station = new Station(getRandomBooleanArray());
        map.setStation(station, i);
      }
    }
    sendMessage(new JSONObject().put("map", map.toJSON()));
  }

  protected void editSlotCoords(EditSlotOptions opt) {
    map.editSlot(opt.index, opt.x, opt.y);
    sendMessage(new JSONObject().put("map", map.toJSON()));
  }

  protected void getAffinity(GetAffinityOptions opt) {
    float affinity = map.getSlotAffinity(opt.getFirst(), opt.getSecond());
    sendMessage(new JSONObject().put("affinity", affinity));
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
  private float getRandomCoordinate() {
    float halfCanvas = (float)(canvasScale / 2.0);
    return (random.nextFloat() * canvasScale) - halfCanvas;
  }

  private boolean[] getRandomBooleanArray() {
    boolean[] out = new boolean[4];
    out[0] = random.nextBoolean();
    out[1] = random.nextBoolean();
    out[2] = random.nextBoolean();
    out[3] = random.nextBoolean();
    return out;
  }
}
