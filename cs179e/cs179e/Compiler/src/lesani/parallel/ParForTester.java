package lesani.parallel;

import lesani.collection.option.None;
import lesani.collection.func.Fun;

import static lesani.parallel.ProcessPower.*;

/**
 * Created by IntelliJ IDEA.
 * User: mohsen
 * Date: May 23, 2010
 * Time: 11:30:55 AM
 */

public class ParForTester {
    public static void main(String[] args) {
        System.out.println("-----------------------------------------------");
        System.out.println("Serial For");
        for (int i = 0; i < 15; i++) {
            System.out.println("Hello world from threadId=" + Thread.currentThread().getId() + " iteration " + i);

        }
        System.out.println("Parallel For");
        parfor (
            new ForLoop(
                0,
                15,
                new Fun<Integer, None>() {
                    public None apply(Integer i) {
                        System.out.println("Hello world from ThreadId=" + Thread.currentThread().getId() + ": iteration = " + i);
                        return None.instance();
                    }
                }
            )
        );

        System.out.println("-----------------------------------------------");
        final int[] array = new int[15];

        System.out.println("Serial For");
        for (int i = 0; i < 15; i++) {
            array[i] = array[i] * 2000;
            System.out.println("ThreadId=" + Thread.currentThread().getId() + ": index = " + i);
        }
        System.out.println("Parallel For");
        parfor (
            new ForLoop(
                0,
                15,
                new Fun<Integer, None>() {
                    public None apply(Integer i) {
                        array[i] = array[i] * 2000;
                        System.out.println("ThreadId=" + Thread.currentThread().getId() + ": index = " + i);
                        return None.instance();
                    }
                }
            )
        );
        System.out.println("-----------------------------------------------");

        setNumThreads(5);
        parfor (new ForLoop(0, 2, new Fun<Integer, None>() { public None apply(Integer i) {
            System.out.println("Index = " + i);
            return None.instance();
        }}));

        System.out.println("-----------------------------------------------");

        final Integer[] nums = new Integer[10];
        for (int i = 0; i < nums.length; i++)
            nums[i] = i;

        Double[] results = parfor (
            new ForLoop<Double>(0, 10, new Fun<Integer, Double>() {
                public Double apply(Integer i) {

                    double j = (double)nums[i] / 2;
                    return j;
                }
            }),
            Double.class
        );

        for (Double result : results) {
            System.out.println(result);
        }

        System.out.println("-----------------------------------------------");

        final Integer[] nums2 = new Integer[10];
        for (int i = 0; i < nums.length; i++)
            nums2[i] = i;

        Double[] results2 = parfor (
            0, 10, new Fun<Integer, Double>() {
                public Double apply(Integer i) {

                    double j = (double)nums2[i] / 2;
                    return j;
                }
            },
            Double.class
        );

        for (Double result : results2) {
            System.out.println(result);
        }

        System.out.println("-----------------------------------------------");

        turnOff();
    }
}

/*
    parfor (new ForLoop(0, 15, new Function<Integer, None>() { public None apply(Integer i) {
        return None.instance();
    }}));
*/
