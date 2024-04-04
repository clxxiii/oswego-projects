package net;

import packet.*;

public class Timeout implements Runnable {
  private static int RTT_Sum;
  private static int RTT_Trials;
  private FileSender fs;

  private final short blockNumber;

  public Timeout(short i, FileSender fs) {
    blockNumber = i;
    this.fs = fs;
    RTT_Sum = 50;
    RTT_Trials = 1;
  }

  public void run() {
    try {
      Thread.sleep(getEstimatedRTT());
    } catch (InterruptedException e) {
      System.out.println("Timeout was interrupted.");
      return;
    }

    DataPacket p = fs.getPacket(blockNumber);
    if (p != null) {
      fs.sendAndTimeout(blockNumber);
    }
  }

  private static int getEstimatedRTT() {
    return RTT_Sum / RTT_Trials;
  }

  public static void addTrial(int rtt) {
    RTT_Sum += rtt;
    RTT_Trials++;
  }
}
