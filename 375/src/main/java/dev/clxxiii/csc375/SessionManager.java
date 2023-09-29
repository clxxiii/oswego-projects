package dev.clxxiii.csc375;

import dev.clxxiii.csc375.object.BuildOptions;
import dev.clxxiii.csc375.object.EditSlotOptions;
import java.util.HashMap;

import dev.clxxiii.csc375.object.GetAffinityOptions;
import org.springframework.web.socket.WebSocketSession;

public class SessionManager {

  static HashMap<Integer, Session> map = new HashMap<>();

  public static int makeNew(WebSocketSession ws) {
    Session session = new Session(ws);
    map.put(ws.hashCode(), session);
    return ws.hashCode();
  }
  public static void remove(WebSocketSession ws) { map.remove(ws.hashCode()); }

  public static void buildMap(int key, BuildOptions options) {
    Session session = map.get(key);
    session.buildMap(options);
  }

  public static void editSlot(int key, EditSlotOptions options) {
    Session session = map.get(key);
    session.editSlotCoords(options);
  }

  public static void getAffinity(int key, GetAffinityOptions options) {
    Session session = map.get(key);
    session.getAffinity(options);
  }
}
