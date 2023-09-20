package dev.clxxiii.csc375.websocket;

import dev.clxxiii.csc375.model.StartMessage;
import java.io.IOException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
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
    if (json.get("stations") != null && json.get("slots") != null &&
        json.get("threads") != null) {
      StartMessage data = new StartMessage(json);
      System.out.println(data);
    }
  }
}
