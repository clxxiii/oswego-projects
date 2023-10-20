package dev.clxxiii.csc375.concurrent;

import dev.clxxiii.csc375.model.Grid;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.web.socket.WebSocketSession;

public class Worker implements Runnable {
  Grid grid;
  final int MUTATION_RATE = 100;
  final int cycles;
  final Exchanger ex;
  final WebSocketSession ws;
  public Worker(Grid grid, int cycles, WebSocketSession ws, Exchanger ex) {
    this.grid = grid;
    this.cycles = cycles;
    this.ws = ws;
    this.ex = ex;
  }

  public void run() {
    // Try to swap for every cycle
    for (int i = 0; i < cycles; i++) {

      int previousAffinity = grid.getTotalAffinity();

      int x1 = ThreadLocalRandom.current().nextInt(0, grid.size);
      int y1 = ThreadLocalRandom.current().nextInt(0, grid.size);
      int x2 = ThreadLocalRandom.current().nextInt(0, grid.size);
      int y2 = ThreadLocalRandom.current().nextInt(0, grid.size);
      // In the case that both coordinates are the same, make it try again
      if (x1 == x2 && y1 == y2) {
        i--;
        continue;
      }

      grid.swap(x1, y1, x2, y2);

      // Should we mutate?
      boolean mutate =
          ThreadLocalRandom.current().nextInt(0, MUTATION_RATE) == 0;
      if (mutate) {
        continue;
      }

      int affinity = grid.getTotalAffinity();

      // Swap back if worse affinity
      if (previousAffinity > affinity) {
        grid.swap(x1, y1, x2, y2);
      }
    }
    ex.checkSolution(grid, ws);
  }
}
