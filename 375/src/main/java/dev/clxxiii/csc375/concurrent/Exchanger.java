package dev.clxxiii.csc375.concurrent;

import dev.clxxiii.csc375.model.Grid;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONObject;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

public class Exchanger {

  static int workerCount = 0;
  final int MIN_CYCLES = 10;
  final int MAX_CYCLES = 100;
  private Grid grid;
  private boolean running = true;
  final ReentrantLock affinityCheckLock = new ReentrantLock();

  public void checkSolution(Grid incomingGrid, WebSocketSession ws) {
    int incomingAffinity = incomingGrid.getTotalAffinity();

    affinityCheckLock.lock();
    try {
      int affinity = grid.getTotalAffinity();

      if (affinity < incomingAffinity) {
        grid = incomingGrid;
        sendGridUpdate(ws, grid, affinity);
      }

      if (running) {
        int cycles =
            ThreadLocalRandom.current().nextInt(MIN_CYCLES - 1, MAX_CYCLES);
        Worker worker = new Worker(grid.clone(), cycles, ws, this);
        new Thread(worker, "worker_" + ++workerCount).start();
      }
    } finally {
      affinityCheckLock.unlock();
    }
  }

  public void startRunning(int threads, WebSocketSession ws) {
    running = true;
    for (int i = 0; i < threads; i++) {
      startWorker(ws);
    }
  }

  private void startWorker(WebSocketSession ws) {
    int cycles =
        ThreadLocalRandom.current().nextInt(MIN_CYCLES - 1, MAX_CYCLES);
    Worker worker = new Worker(grid.clone(), cycles, ws, this);
    new Thread(worker, "worker_" + ++workerCount).start();
  }

  public synchronized Grid getGrid() { return grid; }
  public synchronized void setGrid(Grid grid) { this.grid = grid; }
  public synchronized void stopRunning() { this.running = false; }

  public static synchronized void sendGridUpdate(WebSocketSession ws, Grid grid,
                                                 int affinity) {
    JSONObject msg = new JSONObject()
                         .put("grid", grid.toJSON())
                         .put("affinity", affinity)
                         .put("grid_update", "current_best");

    TextMessage payload = new TextMessage(msg.toString());

    try {
      ws.sendMessage(payload);
    } catch (IOException e) {
      System.out.println(e);
    }
  }
}
