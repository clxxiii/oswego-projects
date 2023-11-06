package edu.oswego.benchmark;

import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Group;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Warmup;

@Fork(2)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 0)
@Measurement(iterations = 1)
public class BenchmarkRunner {

  @Benchmark
  @Group("high_reads_low_writes")
  public void hwlrHashMap() {}

  @Benchmark
  @Group("high_reads_low_writes")
  public void hwlrCustom() {}

  @Benchmark
  @Group("mid_reads_writes")
  public void mrwHashMap() {}

  @Benchmark
  @Group("mid_reads_writes")
  public void mrwCustom() {}

  @Benchmark
  @Group("low_reads_high_writes")
  public void lrhwHashMap() {}

  @Benchmark
  @Group("low_reads_high_writes")
  public void lrhwCustom() {}
}
