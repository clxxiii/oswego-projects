package edu.oswego.blocking;

import edu.oswego.Transaction;
import java.util.concurrent.locks.ReentrantLock;

public class LinkedQueue {
  private class Node {
    Transaction t;
    Node next;
    Node(Transaction t) { this.t = t; }
  }

  ReentrantLock tailLock = new ReentrantLock();
  Node head;
  Node tail;

  public LinkedQueue() {
    head = new Node(new Transaction(-1l, -1l, 0));
    tail = head;
  }

  public void add(Transaction t) {
    tailLock.lock();
    try {
      tail.next = new Node(t);
      tail = tail.next;
    } finally {
      tailLock.unlock();
    }
  }

  public double iterate(long id) {
    double sum = 0;
    Node h = head;

    while (h != null) {
      if (h.t.getToId() == id) {
        sum -= h.t.getAmount();
      } else if (h.t.getFromId() == id) {
        sum += h.t.getAmount();
      }

      h = h.next;
    }

    return sum;
  }

  public void clear() {
    head = new Node(new Transaction(-1l, -1l, 0));
    tail = head;
  }
}
