package tftp;
import net.sourceforge.argparse4j.inf.Namespace;

public class App {
  public static void main(String[] args) {
    String[] vargs = {"-v", "file.txt", "cs.oswego.edu:~/file.txt"};
    Namespace ns = Args.parse(vargs);
  }
}
