package lesani.parallel;

import lesani.collection.option.None;
import lesani.collection.func.Fun;

import java.lang.reflect.Array;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created by IntelliJ IDEA.
 * User: mohsen
 * Date: May 4, 2010
 * Time: 11:45:01 PM
 */

public class ProcessPower {
    /*private*/ static ExecutorService executorService;
    private static int numThreads;
    private static CyclicBarrier barrier;

    static {
        int numThreads = getNumProcessors();
        setNumThreads(numThreads);
    }

    public static int init() {
        return numThreads;
    }
    public static void setNumThreads(int threadCount) {
        numThreads = threadCount;
        barrier = new CyclicBarrier(numThreads + 1);
        if (executorService != null)
            executorService.shutdown();
        // Has overhead. Should be used only at the beginning.
//        System.out.println("Before");
        executorService = Executors.newFixedThreadPool(numThreads);
//        System.out.println("After");
    }

    public static int getNumThreads() {
        return numThreads;
    }

    public static int getNumProcessors() {
        Runtime runtime = Runtime.getRuntime();
        return runtime.availableProcessors();
    }

    public static void turnOff() {
        executorService.shutdown();
    }

    public static class ForLoop<T> {
        int initValue;
        int limitValue;
//        Function<Integer, Boolean> condition;
//        Function<Integer, Integer> step;
        Fun<Integer, T> iter;

        /*
        ForLoop(int initValue, Function<Integer, Boolean> condition, Function<Integer, Integer> step, Function<Integer, None> iter) {
            this.initValue = initValue;
            this.condition = condition;
            this.step = step;
            this.iter = iter;
        }
        parfor (
            new ForLoop(
                0,
                new Function<Integer, Boolean>() {
                    public Boolean apply(Integer i) {
                        return i < 1000;
                    }
                },
                new Function<Integer, Integer>() {
                    public Integer apply(Integer i) {
                        return i + 1;
                    }
                },
                new Function<Integer, None>() {
                    public None apply(Integer i) {
                        array[i] = array[i] * 2000;
                        return None.instance();
                    }
                }
            )
        );
        */

        public ForLoop(int initValue, int limitValue, Fun<Integer, T> iter) {
            this.initValue = initValue;
            this.limitValue = limitValue;
            this.iter = iter;
        }
    }

    public static void parfor(final ForLoop<None> forLoop) {

        final int range = forLoop.limitValue - forLoop.initValue;

        int theSplit = range / numThreads;
        if (theSplit == 0)
            theSplit = 1;
        final int split = theSplit;

//        System.out.println("split = " + split);
        for (int i = 0; i < numThreads - 1; i++) {

            final int finalI = i;
            executorService.execute(new Runnable() {
                public void run() {
                    final int lower = split * finalI;
                    int theHigher = split * (finalI + 1);
                    if (lower >= forLoop.limitValue)
                        theHigher = forLoop.limitValue + 1;
                    final int higher = theHigher;
//                    System.out.println("lower = " + lower);
//                    System.out.println("higher = " + higher);
                    for (int j = lower; j < higher; j++) {
                          forLoop.iter.apply(j);
                    }
//                    System.out.println("out1");
                    try {
//                        System.out.println("Waiting");
                        barrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        executorService.execute(new Runnable() {
            public void run() {
                final int lower = split * (numThreads - 1);
                int theHigher = forLoop.limitValue;
                if (lower >= forLoop.limitValue)
                    theHigher = forLoop.limitValue + 1;
                final int higher = theHigher;

                for (int j = lower; j < higher; j++) {
                    forLoop.iter.apply(j);
                }
//                System.out.println("out2");
                try {
//                    System.out.println("Waiting");
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        });

        try {
//            System.out.println("Main Waiting");
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

//        System.out.println("Main out");
    }





    public static <T> T[] parfor(int lower, int higher, Fun<Integer, T> iter, final Class<T> c) {
        return parfor(new ForLoop<T>(lower, higher, iter), c);
    }

    @SuppressWarnings({"unchecked"})
    public static <T> T[] parfor(final ForLoop<T> forLoop, final Class<T> c) {
        final int range = forLoop.limitValue - forLoop.initValue;

//        final T[] result = (T[])new Object[range];
        final T[] result = (T[])Array.newInstance(c, range);

        int theSplit = range / numThreads;
        if (theSplit == 0)
            theSplit = 1;
        final int split = theSplit;

//        System.out.println("split = " + split);
        for (int i = 0; i < numThreads - 1; i++) {

            final int finalI = i;
            executorService.execute(new Runnable() {
                public void run() {
                    final int lower = split * finalI;
                    int theHigher = split * (finalI + 1);
                    if (lower >= forLoop.limitValue)
                        theHigher = forLoop.limitValue + 1;
                    final int higher = theHigher;
//                    System.out.println("lower = " + lower);
//                    System.out.println("higher = " + higher);
                    for (int j = lower; j < higher; j++) {
                          result[j] = forLoop.iter.apply(j);
                    }
//                    System.out.println("out1");
                    try {
//                        System.out.println("Waiting");
                        barrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        executorService.execute(new Runnable() {
            public void run() {
                final int lower = split * (numThreads - 1);
                int theHigher = forLoop.limitValue;
                if (lower >= forLoop.limitValue)
                    theHigher = forLoop.limitValue + 1;
                final int higher = theHigher;

                for (int j = lower; j < higher; j++) {
                    result[j] = forLoop.iter.apply(j);
                }
//                System.out.println("out2");
                try {
//                    System.out.println("Waiting");
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        });

        try {
//            System.out.println("Main Waiting");
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        return result;
//        System.out.println("Main out");
    }
}