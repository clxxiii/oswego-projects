package edu.oswego.minesweeper.websocket;

import edu.oswego.minesweeper.Session;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashMap;

@Component
public class SessionManager extends TextWebSocketHandler {
  static HashMap<Integer, Session> map = new HashMap<>();

  @Override
  public void handleTextMessage(WebSocketSession ws, TextMessage message) {
    String payload = message.getPayload();
    JSONObject json = new JSONObject(payload);
    Session session = map.get(ws.hashCode());
    session.parseAction(json);
  }

  @Override
  public void afterConnectionEstablished(WebSocketSession ws) {
    Session session = new Session(ws);
    map.put(ws.hashCode(), session);
    System.out.println("Session " + ws.hashCode() + " has connected.");
    JSONObject json = new JSONObject()
            .put("action", "makeGrid");
    session.parseAction(json);
  }

  @Override
  public void afterConnectionClosed(WebSocketSession ws, CloseStatus status) {

    map.remove(ws.hashCode());
    System.out.println("Session " + ws.hashCode() + " has disconnected: " + status.toString());
  }
}
