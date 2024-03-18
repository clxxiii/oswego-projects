package tftp;

import java.net.InetSocketAddress;

import packet.*;

public class ClientSession {
  public final String src; // File on local fs
  public final String dst; // File on remote fs
  public final Opcode code; // RRQ or WRQ: Reading from server or writing to server
  public final InetSocketAddress addr;

  public ClientSession(String src, String dst, Opcode code, InetSocketAddress addr) {
    this.src = src;
    this.dst = dst;
    this.code = code;
    this.addr = addr;
  }

}
