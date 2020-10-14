package lesani.image.core.parallel.others;

import lesani.collection.func.Fun;
import lesani.parallel.ForkJoin;

import java.util.concurrent.ExecutionException;

/**
 * Created by IntelliJ IDEA.
 * User: mohsen
 * Date: Feb 27, 2010
 * Time: 10:39:20 PM
 */


public class ParalleizerTester {
    static class Range {
        int low;
        int high;

        Range(int low, int high) {
            this.low = low;
            this.high = high;
        }
    }
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        final int splitCount = 4;
        ForkJoin<Range, Range, Integer, Integer> paralleizer =
            new ForkJoin<Range, Range, Integer, Integer>(

                new ForkJoin.Forker<Range, Range>() {
                    public Range[] fork(Range range) {
                        int width = (range.high - range.low) / splitCount;
                        Range[] parts = new Range[splitCount];
                        int high = range.low;
                        for (int i = 0; i < splitCount - 1; i++) {
                            int low = high;
                            high = low + width;
                            parts[i] = new Range(low, high);
                        }
                        parts[parts.length - 1] = new Range(high, range.high);
                        return parts;
                    }
                },

                new Fun<Range, Integer>() {
                    @Override
                    public Integer apply(Range range) {
                        int sum = 0;
                        for (int i = range.low; i < range.high; i++) {
                            sum += i;
                            System.out.println(Thread.currentThread() + ": Number " + i);
                        }
                        return sum;
                    }
                },

                new ForkJoin.Joiner<Integer, Integer>() {

                    public Integer join(Integer[] integers) {
                        int sum = 0;
                        for (Integer integer : integers) {
                            sum += integer;
                        }
                        return sum;
                    }
                },

                splitCount,
                new Integer[splitCount]
        );

        System.out.println(paralleizer.compute(new Range(0, 100)));
    }
}
