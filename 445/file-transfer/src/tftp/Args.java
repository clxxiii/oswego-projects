package tftp;

import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.impl.Arguments;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;

public class Args {
  public static Namespace parse(String[] args) {

    ArgumentParser parser = ArgumentParsers.newFor("tftp").build()
        .description("Share files from a server running tftpd to here.");

    parser.addArgument("src")
        .help("The file you want to copy")
        .required(true);
    parser.addArgument("dst")
        .help("The destination for the copied file")
        .required(true);
    parser.addArgument("-v", "--verbose")
        .help("Prints additional debug information.")
        .action(Arguments.storeTrue());
    parser.addArgument("-D", "--drop-packets")
        .help("Randomly drops 1% of packets to test packet recovery")
        .action(Arguments.storeTrue());

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
