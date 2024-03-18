package edu.oswego.blocking;

import edu.oswego.Transaction;

public class BlockingBroker {

  static LinkedQueue chain = new LinkedQueue();

  public static void makeTransaction(long id1, long id2, double amt) {
    chain.add(new Transaction(id1, id2, amt));
  }

  public static double getDifference(long accountId, double startingAmt) {
    double difference = chain.iterate(accountId);
    return startingAmt + difference;
  }

  public static void clear() { chain.clear(); }
}
