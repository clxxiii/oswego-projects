import static org.junit.Assert.assertEquals;

import org.junit.Test;

import net.sourceforge.argparse4j.inf.Namespace;
import packet.Opcode;
import tftp.App;
import tftp.Args;
import tftp.ClientSession;

public class ClientParseTests {
  @Test
  public void parseVerbose() {
    String[] args = { "-v", "./file.txt", "cs.oswego.edu:/home/efereira/file.txt" };
    Namespace ns = Args.parse(args);
    assertEquals(true, ns.getBoolean("verbose"));

    String[] args2 = { "./file.txt", "cs.oswego.edu:/home/efereira/file.txt" };
    Namespace ns2 = Args.parse(args2);
    assertEquals(false, ns2.getBoolean("verbose"));
  }

  @Test
  public void parsePort() {
    String[] args = { "-v", "./file.txt", "cs.oswego.edu:/home/efereira/file.txt" };
    Namespace ns = Args.parse(args);
    assertEquals(Integer.valueOf(26904), ns.getInt("port"));

    String[] args2 = { "./file.txt", "cs.oswego.edu:/home/efereira/file.txt", "-p", "8080" };
    Namespace ns2 = Args.parse(args2);
    assertEquals(Integer.valueOf(8080), Integer.valueOf(ns2.getString("port")));
  }

  @Test
  public void serverToClientDefaultPort() {
    String[] args = { "./file.txt", "cs.oswego.edu:/home/efereira/file.txt" };
    ClientSession session = App.makeSession(Args.parse(args));

    assertEquals("./file.txt", session.src);
    assertEquals("/home/efereira/file.txt", session.dst);
    assertEquals(Opcode.WRQ, session.code);
    assertEquals("cs.oswego.edu", session.remote.getAddress().getHostName());
    assertEquals(26904, session.remote.getPort());
  }

  @Test
  public void serverToClientSpecificPort() {
    String[] args = { "./file.txt", "cs.oswego.edu:/home/efereira/file.txt", "-p", "8080" };
    ClientSession session = App.makeSession(Args.parse(args));

    assertEquals("./file.txt", session.src);
    assertEquals("/home/efereira/file.txt", session.dst);
    assertEquals(Opcode.WRQ, session.code);
    assertEquals("cs.oswego.edu", session.remote.getAddress().getHostName());
    assertEquals(8080, session.remote.getPort());
  }

  @Test
  public void clientToServerDefaultPort() {
    String[] args = { "cs.oswego.edu:/home/efereira/file.txt", "./file.txt" };
    ClientSession session = App.makeSession(Args.parse(args));

    assertEquals("./file.txt", session.src);
    assertEquals("/home/efereira/file.txt", session.dst);
    assertEquals(Opcode.RRQ, session.code);
    assertEquals("cs.oswego.edu", session.remote.getAddress().getHostName());
    assertEquals(26904, session.remote.getPort());
  }

  @Test
  public void clientToServerSpecificPort() {
    String[] args = { "cs.oswego.edu:/home/efereira/file.txt", "./file.txt", "--port", "8080" };
    ClientSession session = App.makeSession(Args.parse(args));

    assertEquals("./file.txt", session.src);
    assertEquals("/home/efereira/file.txt", session.dst);
    assertEquals(Opcode.RRQ, session.code);
    assertEquals("cs.oswego.edu", session.remote.getAddress().getHostName());
    assertEquals(8080, session.remote.getPort());
  }
}