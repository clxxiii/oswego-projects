package net;

import packet.AckPacket;

public class AckSender implements Runnable {
  private short blockNum;
  private FileReciever fr;

  public AckSender(short blockNum, FileReciever fr) {
    this.blockNum = blockNum;
    this.fr = fr;
  }

  public void run() {
    AckPacket ack = new AckPacket(blockNum);
    fr.send(ack);
  }
}
