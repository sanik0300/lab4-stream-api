package async.lab4;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@Measurement(timeUnit = TimeUnit.MILLISECONDS)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@BenchmarkMode(Mode.AverageTime)
@Warmup(time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class StreamBenchmarks {

    private Collection<Integer> inputCollection;

    @Param({"true", "false"})
    private boolean runParallel;

    @Setup
    public void SetupCollection() {
        inputCollection = IntStreamTasks.PrepareCollection(10_000_000, 1, 100);
    }

    @Benchmark
    public int SumBenchmark() {
        return IntStreamTasks.StreamSum(inputCollection, runParallel);
    }

    @Benchmark
    public double AverageBenchmark() {
        return IntStreamTasks.StreamAvg(inputCollection, runParallel);
    }

    @Benchmark
    public double StdBenchmark() {
        return IntStreamTasks.StreamStandardDeviation(inputCollection, runParallel);
    }

    @Benchmark
    public Collection<Integer> DoubleBenchmark() {
        return IntStreamTasks.DoubleValues(inputCollection, runParallel);
    }

    @Benchmark
    public Collection<Integer> FilteringBenchmark() {
        return IntStreamTasks.FilterDivisive2_3(inputCollection, runParallel);
    }

    public static void main(String[] args) throws RunnerException {
        Options defaultOpt = new OptionsBuilder().build();
        new Runner(defaultOpt).run();
    }
}
