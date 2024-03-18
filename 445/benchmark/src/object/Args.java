package object;

import enums.*;

public class Args {
  public Protocol protocol;
  public int port;
  public RunMode runMode;
  public ConnectionType type;
  public int size;
  public String ip;

  protected Args setProtocol(Protocol p) {
    protocol = p;
    return this;
  }

  protected Args setPort(int p) {
    port = p;
    return this;
  }

  protected Args setRunMode(RunMode r) {
    runMode = r;
    return this;
  }

  protected Args setType(ConnectionType c) {
    type = c;
    return this;
  }

  protected Args setSize(int size) {
    this.size = size;
    return this;
  }

  protected Args setIp(String ip) {
    this.ip = ip;
    return this;
  }

  public String toString() {
    String typeString = this.type == ConnectionType.CLIENT ? "client" : "server";
    String runString = this.runMode == RunMode.LATENCY ? "latency" : "throughput";
    return "Setting up a " + typeString + " on port " + this.port + " to measure " + runString + " with " + this.size
        + " byte messages.";
  }
}
