package object;

import enums.*;

public class ArgParser {
  public static Args parse(String[] args) {
    if (args.length < 5) {
      throw new IllegalArgumentException();
    }

    Args output = new Args()
        .setProtocol(parseProtocol(args[0]))
        .setPort(Integer.parseInt(args[1]))
        .setRunMode(parseMode(args[2]))
        .setType(parseType(args[3]))
        .setSize(Integer.parseInt(args[4]));

    if (args.length > 5) {
      output.setIp(args[5]);
    }

    // Makes sure the arguments are in a valid combination.
    validate(output);

    return output;
  }

  private static void validate(Args args) throws IllegalArgumentException {
    if (args.type == ConnectionType.CLIENT && args.ip == null) {
      throw new IllegalArgumentException("You must specify an IP if you are connecting as a client.");
    }

    if (args.runMode == RunMode.LATENCY) {
      if (args.size != 8 && args.size != 64 && args.size != 512) {
        throw new IllegalArgumentException("Valid messages sizes for latency are 8, 64 or 512");
      }
    }

    if (args.runMode == RunMode.THROUGHPUT) {
      if (args.size != 64 && args.size != 256 && args.size != 1024) {
        throw new IllegalArgumentException("Valid messages sizes for throughput are 64, 256 or 1024");
      }
    }
  }

  private static Protocol parseProtocol(String p) {
    if (p.equalsIgnoreCase("tcp")) {
      return Protocol.TCP;
    }

    if (p.equalsIgnoreCase("udp")) {
      return Protocol.UDP;
    }

    throw new IllegalArgumentException();
  }

  private static RunMode parseMode(String p) {
    if (p.equalsIgnoreCase("latency")) {
      return RunMode.LATENCY;
    }

    if (p.equalsIgnoreCase("throughput")) {
      return RunMode.THROUGHPUT;
    }

    throw new IllegalArgumentException();
  }

  private static ConnectionType parseType(String p) {
    if (p.equalsIgnoreCase("client")) {
      return ConnectionType.CLIENT;
    }

    if (p.equalsIgnoreCase("server")) {
      return ConnectionType.SERVER;
    }

    throw new IllegalArgumentException();
  }

}
