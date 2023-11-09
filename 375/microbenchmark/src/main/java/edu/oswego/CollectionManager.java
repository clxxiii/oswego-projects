package edu.oswego;

import edu.oswego.blocking.BlockingBroker;
import edu.oswego.nonblocking.NonBlockingBroker;
import java.util.concurrent.ThreadLocalRandom;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

@State(Scope.Benchmark)
public class CollectionManager {

  @Setup(Level.Iteration)
  public void setup() {
    NonBlockingBroker.clear();
    BlockingBroker.clear();
  }

  // If p is high, there are more writes than reads.
  public void doAction(double p, Wallet w, CollectionType t) {
    ThreadLocalRandom rand = ThreadLocalRandom.current();
    double chance = rand.nextDouble();

    if (t == CollectionType.BLOCKING) {
      if (chance > p) {
        w.balance = BlockingBroker.getDifference(w.id, w.startingBalance);
      } else {
        double transferAmount = rand.nextDouble() * 100;
        long account = rand.nextLong(Wallet.count + 1);
        w.balance -= transferAmount;
        BlockingBroker.makeTransaction(w.id, account, transferAmount);
      }

    } else if (t == CollectionType.NONBLOCKING) {
      if (chance > p) {
        w.balance = NonBlockingBroker.getDifference(w.id, w.startingBalance);
      } else {
        double transferAmount = rand.nextDouble() * 100;
        long account = rand.nextLong(Wallet.count + 1);
        w.balance -= transferAmount;
        NonBlockingBroker.makeTransaction(w.id, account, transferAmount);
      }
    }
  }
}
