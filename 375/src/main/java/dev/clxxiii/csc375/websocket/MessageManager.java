package dev.clxxiii.csc375.websocket;

import dev.clxxiii.csc375.SessionManager;
import dev.clxxiii.csc375.object.BuildOptions;
import dev.clxxiii.csc375.object.EditSlotOptions;
import java.io.IOException;

import dev.clxxiii.csc375.object.GetAffinityOptions;
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
    if (json.getString("message").equals("build_map")) {
      BuildOptions data = new BuildOptions(json.getJSONObject("data"));
      SessionManager.buildMap(session.hashCode(), data);
    }

    if (json.getString("message").equals("edit_slot")) {
      EditSlotOptions data = new EditSlotOptions(json.getJSONObject("data"));
      SessionManager.editSlot(session.hashCode(), data);
    }

    if (json.getString("message").equals("get_affinity")) {
      GetAffinityOptions data = new GetAffinityOptions(json.getJSONObject("data"));
      SessionManager.getAffinity(session.hashCode(), data);
    }
  }

  @Override
  public void afterConnectionEstablished(WebSocketSession session)
      throws Exception {
    SessionManager.makeNew(session);
  }

  @Override
  public void afterConnectionClosed(WebSocketSession session,
                                    CloseStatus status) throws Exception {

    SessionManager.remove(session);
  }
}
