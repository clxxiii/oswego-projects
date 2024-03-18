package tftpd;

import net.sourceforge.argparse4j.inf.Namespace;

public class App {
  public static void main(String[] args) {
    Namespace ns = Args.parse(args);
    int port = ns.getInt("port");
  }
}
