package tftp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.DatagramChannel;
import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import net.FileReciever;
import net.FileSender;
import net.PacketHandler;
import object.File;
import object.Key;
import packet.*;

public class ClientSession extends PacketHandler {
  public final String localPath; // File on local fs
  public final String remotePath; // File on remote fs
  public final Opcode code; // RRQ or WRQ: Reading from server or writing to server
  private File local;
  private Key key;
  private RequestPacket request;
  private int windowSize;

  public ClientSession(String src, String dst, int windowSize, Opcode code, InetSocketAddress remote) {
    this.localPath = src;
    this.remotePath = dst;
    this.code = code;
    this.remote = remote;
    this.windowSize = windowSize;

    try {
      channel = DatagramChannel.open();
      channel.connect(remote);
    } catch (IOException e) {
      System.out.println("Failed to connect");
      System.exit(1);
    }
  }

  public void begin() {
    local = new File(localPath);

    validate();
    OAckPacket oack = handshake();

    long requestRand = Long.valueOf(request.options.get("random"));
    long oackRand = Long.valueOf(oack.options.get("random"));

    key = new Key(requestRand ^ oackRand);

    if (code == Opcode.RRQ) {
      FileReciever r = new FileReciever(local, key, channel, remote);
      r.begin();
    } else {
      FileSender s = new FileSender(local, key, windowSize, channel, remote);
      s.begin();
    }
  }

  private void validate() {
    if (code == Opcode.RRQ && local.exists()) {
      System.out.println("File at " + local.getAbsolutePath() + " already exists, cancelling download.");
      System.exit(1);
    }

    if (code == Opcode.WRQ && (!local.exists() || !local.canRead())) {
      System.out.println("Cannot find file: " + local.getAbsolutePath());
      System.exit(1);
    }
  }

  private OAckPacket handshake() {
    HashMap<String, String> options = new HashMap<>();
    long random = ThreadLocalRandom.current().nextLong();
    options.put("random", String.valueOf(random));
    options.put("window", String.valueOf(windowSize));

    request = new RequestPacket(code, remotePath, options);
    send(request);

    // Wait for response
    Optional<Packet> oack = recieve();

    if (!oack.isPresent()) {
      System.exit(1);
    }

    return (OAckPacket) oack.get();
  }
}
