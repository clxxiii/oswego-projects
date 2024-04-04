package tftpd;

import java.net.InetSocketAddress;
import java.nio.channels.DatagramChannel;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

import net.FileReciever;
import net.FileSender;
import net.PacketHandler;
import object.File;
import object.Key;
import packet.*;

public class ServerSession extends PacketHandler {
  public final RequestPacket request;
  private File local;
  private Key key;

  final int MAX_PACKET_SIZE = 512 + 4; // Data has header size of 4, plus 512 bytes of data

  public ServerSession(RequestPacket packet, DatagramChannel channel, InetSocketAddress addr) {
    this.request = packet;
    this.channel = channel;
    this.remote = addr;
  }

  public void begin() {
    local = new File(request.fileName);
    int windowSize = Integer.valueOf(request.options.get("window"));

    if (!validate()) {
      return;
    }
    OAckPacket oack = handshake();

    long requestRand = Long.valueOf(request.options.get("random"));
    long oackRand = Long.valueOf(oack.options.get("random"));

    key = new Key(requestRand ^ oackRand);

    if (request.opcode == Opcode.WRQ) {
      FileReciever r = new FileReciever(local, key, channel, remote);
      r.begin();
    } else {
      FileSender s = new FileSender(local, key, windowSize, channel, remote);
      s.begin();
    }
  }

  public boolean validate() {
    if (request.opcode == Opcode.RRQ && !local.exists()) {
      send(new ErrorPacket(ErrorCode.FILE_NOT_FOUND));
      return false;
    }

    if (request.opcode == Opcode.WRQ && local.exists()) {
      send(new ErrorPacket(ErrorCode.FILE_EXISTS));
      return false;
    }

    return true;
  }

  private OAckPacket handshake() {
    HashMap<String, String> options = new HashMap<>();
    long rand = ThreadLocalRandom.current().nextLong();
    options.put("random", String.valueOf(rand));

    OAckPacket packet = new OAckPacket(options);
    send(packet);
    return packet;
  }
}
