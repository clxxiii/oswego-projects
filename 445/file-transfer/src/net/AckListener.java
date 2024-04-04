package net;

import java.util.Optional;

import packet.*;

public class AckListener {
  private FileSender fs;

  public AckListener(FileSender fs) {
    this.fs = fs;
  }

  public void listen() {
    while (true) {
      Optional<Packet> op = fs.recieve();

      if (!op.isPresent()) {
        System.out.println("Failed to recieve ack");
        continue;
      }

      if (op.get().opcode != Opcode.ACK) {
        System.out.println("Recieved a packet other than an ack");
        break;
      }

      AckPacket ack = (AckPacket) op.get();
      short blockNum = ack.blockNumber;
      DataPacket cachePacket = fs.getPacket(blockNum);

      if (cachePacket != null) {
        fs.removePacket(blockNum);
        if (cachePacket.data.length >= 512) {
          fs.slideWindow();
        } else {
          endTimer();
          break;
        }
      }
    }
  }

  private void endTimer() {
    long end = System.nanoTime();
    long bits = fs.getFileSize() * 8;
    long start = fs.start;

    long time = (end - start);

    double Gbps = (double) bits / (double) time;
    System.out.println("Throughput in mbps: " + Gbps * 1000);
  }
}
