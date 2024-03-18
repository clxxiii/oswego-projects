import enums.Protocol;
import object.ArgParser;
import object.Args;

public class Main {
  public static void main(String[] args) {
    Args options;
    try {
      options = ArgParser.parse(args);
    } catch (IllegalArgumentException e) {
      if (e.getMessage() != null) {
        System.out.println(e.getMessage());
      }
      System.out.println(
          "benchmarks.jar [tcp|udp] [port] [latency|throughput] [client|server] [8|64|256|512|1024] <ip>");
      System.exit(1);
      return;
    }

    if (options.protocol == Protocol.TCP) {
      TCPHandler.setup(options);
    }
    if (options.protocol == Protocol.UDP) {
      UDPHandler.setup(options);
    }

  }
}