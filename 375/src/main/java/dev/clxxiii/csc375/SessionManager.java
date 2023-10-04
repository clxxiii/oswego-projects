package dev.clxxiii.csc375;

import dev.clxxiii.csc375.object.GetAffinityOptions;
import dev.clxxiii.csc375.object.MakeGridOptions;
import java.util.HashMap;
import org.springframework.web.socket.WebSocketSession;

public class SessionManager {

  static HashMap<Integer, Session> map = new HashMap<>();

  public static int makeNew(WebSocketSession ws) {
    Session session = new Session(ws);
    map.put(ws.hashCode(), session);
    return ws.hashCode();
  }
  public static void remove(WebSocketSession ws) { map.remove(ws.hashCode()); }

  public static void makeGrid(int key, MakeGridOptions options) {
    Session session = map.get(key);
    session.makeGrid(options);
  }

  public static void getAffinity(int key, GetAffinityOptions options) {
    Session session = map.get(key);
    session.getAffinity(options);
  }
  public static void start(int key, int threads) {
    Session session = map.get(key);
    session.start(threads);
  }
  public static void stop(int key) {
    Session session = map.get(key);
    session.stop();
  }
}
