package async.lab4;

import java.util.Collection;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IntStreamTasks {

    private static Stream<Integer> getStream(Collection<Integer> numbers, boolean parallel) {
        Stream<Integer> str = numbers.stream();
        if (parallel) {
            return str.parallel();
        }
        return str;
    }

    public static Collection<Integer> PrepareCollection(int size, int min, int max) {
        Random random = new Random(System.currentTimeMillis());

        return Stream.generate(() -> random.nextInt(min, max+1))
                     .limit(size)
                     .collect(Collectors.toList());
    }

    public static int StreamSum(Collection<Integer> numbers, boolean parallel) {

        return getStream(numbers, parallel).mapToInt(Integer::intValue).sum();
    }

    public static double StreamAvg(Collection<Integer> numbers, boolean parallel) {

        return getStream(numbers, parallel).mapToInt(Integer::intValue).average().getAsDouble();
    }

    public static double StreamStandardDeviation(Collection<Integer> numbers, boolean parallel) {

        double avg = getStream(numbers, parallel).mapToInt(Integer::intValue).average().getAsDouble();

        double sumOfSquaredDiff = getStream(numbers, parallel)
                                      .mapToDouble(Integer::intValue)
                                      .map(num -> Math.pow(num - avg, 2))
                                      .sum();

        return Math.sqrt(sumOfSquaredDiff / numbers.size());
    }

    public static Collection<Integer> DoubleValues(Collection<Integer> numbers, boolean parallel) {

        return getStream(numbers, parallel).map(num -> num*2).collect(Collectors.toList());
    }

    public static Collection<Integer> FilterDivisive2_3(Collection<Integer> numbers, boolean parallel) {

        return getStream(numbers, parallel).filter(num -> num % 6 == 0).collect(Collectors.toList());
    }
}
