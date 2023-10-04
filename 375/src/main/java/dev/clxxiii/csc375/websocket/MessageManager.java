package dev.clxxiii.csc375.websocket;

import dev.clxxiii.csc375.SessionManager;
import dev.clxxiii.csc375.object.MakeGridOptions;
import java.io.IOException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class MessageManager extends TextWebSocketHandler {

  @Override
  public void handleTextMessage(WebSocketSession session, TextMessage message)
      throws InterruptedException, IOException {

    String payload = message.getPayload();

    JSONObject json = new JSONObject(payload);
    if (json.getString("message").equals("make_grid")) {
      MakeGridOptions data = new MakeGridOptions(json.getJSONObject("data"));
      SessionManager.makeGrid(session.hashCode(), data);
    }
    if (json.getString("message").equals("go!")) {
      int threads = json.getJSONObject("data").getInt("threads");
      SessionManager.start(session.hashCode(), threads);
    }
    if (json.getString("message").equals("stop!")) {
      SessionManager.stop(session.hashCode());
    }
  }

  @Override
  public void afterConnectionEstablished(WebSocketSession session)
      throws Exception {
    SessionManager.makeNew(session);

    JSONObject json = new JSONObject().put("size", 7).put("types", 4);

    MakeGridOptions data = new MakeGridOptions(json);
    SessionManager.makeGrid(session.hashCode(), data);
  }

  @Override
  public void afterConnectionClosed(WebSocketSession session,
                                    CloseStatus status) throws Exception {

    SessionManager.remove(session);
  }
}
