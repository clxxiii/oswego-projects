package tftpd;

import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;

public class Args {
  public static Namespace parse(String[] args) {
    ArgumentParser parser = ArgumentParsers
      .newFor("tftpd")
      .build()
      .defaultHelp(true)
      .description("The server for tftp");

    parser.addArgument("--port", "-p")
      .help("The port you wish to run the server on")
      .setDefault(26904);

    try {
      Namespace ns = parser.parseArgs(args);
      return ns;
    } catch (ArgumentParserException e) {
      parser.handleError(e);
      System.exit(1);
      return null;
    }
  }
}
