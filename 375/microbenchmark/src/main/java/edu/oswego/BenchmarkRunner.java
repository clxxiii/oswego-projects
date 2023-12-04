package edu.oswego;

import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.*;

@Fork(2)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 5)
@Measurement(iterations = 5)
@Threads(Threads.MAX)
public class BenchmarkRunner {

  @Benchmark
  public void hwlrNonBlocking(Wallet w, CollectionManager b) {
    b.doAction(0.25, w, CollectionType.NONBLOCKING);
  }

  @Benchmark
  public void hwlrBlocking(Wallet w, CollectionManager b) {
    b.doAction(0.25, w, CollectionType.BLOCKING);
  }

  @Benchmark
  public void hrlwNonBlocking(Wallet w, CollectionManager b) {
    b.doAction(0.75, w, CollectionType.NONBLOCKING);
  }

  @Benchmark
  public void hrlwBlocking(Wallet w, CollectionManager b) {
    b.doAction(0.75, w, CollectionType.BLOCKING);
  }
}
