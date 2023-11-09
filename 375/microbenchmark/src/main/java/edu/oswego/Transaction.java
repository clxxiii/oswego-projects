package edu.oswego;

public class Transaction {
  final private long fromId;
  final private long toId;
  final private double amount;

  public Transaction(long from, long to, double amt) {
    fromId = from;
    toId = to;
    amount = amt;
  }

  public long getFromId() { return fromId; }
  public long getToId() { return toId; }
  public double getAmount() { return amount; }
}
