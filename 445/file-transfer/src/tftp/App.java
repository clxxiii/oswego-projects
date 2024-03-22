package tftp;

import java.net.InetSocketAddress;

import net.sourceforge.argparse4j.inf.Namespace;
import packet.Opcode;

public class App {
  public static void main(String[] args) {
    Namespace ns = Args.parse(args);
    ClientSession session = makeSession(ns);
    session.begin();
  }

  public static ClientSession makeSession(Namespace ns) {
    String file1 = ns.getString("src");
    String file2 = ns.getString("dst");
    int port = Integer.valueOf(ns.getString("port"));

    String src; // The filename on the local fs
    String dst; // The filename on the remote fs

    Opcode code;
    InetSocketAddress addr;
    if (file1.contains(":")) {
      code = Opcode.RRQ;
      String[] serverSplit = file1.split(":");
      String ip = serverSplit[0];
      addr = new InetSocketAddress(ip, port);
      src = file2;
      dst = serverSplit[1];
    } else {
      code = Opcode.WRQ;
      String[] serverSplit = file2.split(":");
      String ip = serverSplit[0];
      addr = new InetSocketAddress(ip, port);
      src = file1;
      dst = serverSplit[1];
    }

    return new ClientSession(src, dst, code, addr);
  }
}
