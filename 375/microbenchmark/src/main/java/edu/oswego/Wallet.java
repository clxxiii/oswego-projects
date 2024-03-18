package edu.oswego;

import java.util.concurrent.ThreadLocalRandom;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Thread)
public class Wallet {

  public static long count = 0;

  public long id;
  public double balance;
  public final double startingBalance;

  public Wallet() {
    id = count++;
    balance = ThreadLocalRandom.current().nextDouble() * 1000;
    startingBalance = balance;
  }
}
