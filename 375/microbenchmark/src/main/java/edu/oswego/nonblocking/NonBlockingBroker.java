package edu.oswego.nonblocking;

import edu.oswego.Transaction;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

public class NonBlockingBroker {

  static ConcurrentLinkedQueue<Transaction> chain =
      new ConcurrentLinkedQueue<>();

  public static void makeTransaction(long id1, long id2, double amt) {
    chain.add(new Transaction(id1, id2, amt));
  }

  public static double getDifference(long accountId, double startingAmt) {
    double amount = startingAmt;

    Iterator<Transaction> iter = chain.iterator();
    while (iter.hasNext()) {
      Transaction t = iter.next();

      if (t.getFromId() == accountId) {
        amount -= t.getAmount();
      } else if (t.getToId() == accountId) {
        amount += t.getAmount();
      }
    }

    return amount;
  }

  public static void clear() { chain.clear(); }
}
