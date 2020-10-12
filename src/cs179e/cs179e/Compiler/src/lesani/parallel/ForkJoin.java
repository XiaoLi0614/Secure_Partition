package lesani.parallel;

import lesani.collection.func.Fun;

import java.util.concurrent.*;

/**
 * Created by IntelliJ IDEA.
 * User: mohsen
 * Date: Feb 27, 2010
 * Time: 10:38:35 PM
 */

public class ForkJoin<T1, T2, T3, T4> {

    private ExecutorService executorService;

    private Forker<T1, T2> forker;
    private Fun<T2, T3> subProcess;
    private Joiner<T3, T4> joiner;
    private T3[] processedParts;

    public ForkJoin(
                 Forker<T1, T2> forker,
                 Fun<T2, T3> subProcess,
                 Joiner<T3, T4> joiner,
                 T3[] processedParts/*because java does not allow generic array creation.*/) {
        this.forker = forker;
        this.subProcess = subProcess;
        this.joiner = joiner;
        executorService = ProcessPower.executorService;
        this.processedParts = processedParts;
    }
    public ForkJoin(
                 Forker<T1, T2> forker,
                 Fun<T2, T3> subProcess,
                 Joiner<T3, T4> joiner,
                 int threadCount,
                 T3[] processedParts/*because java does not allow generic array creation.*/) {
        this.forker = forker;
        this.subProcess = subProcess;
        this.joiner = joiner;
        executorService = Executors.newFixedThreadPool(threadCount);
        this.processedParts = processedParts;
    }
    public ForkJoin(
                 Forker<T1, T2> forker,
                 Fun<T2, T3> subProcess,
                 Joiner<T3, T4> joiner,
                 ExecutorService executorService,
                 T3[] processedParts/*because java does not allow generic array creation.*/) {
        this.forker = forker;
        this.subProcess = subProcess;
        this.joiner = joiner;
        this.executorService = executorService;
        this.processedParts = processedParts;
    }

    // The number of splits can be more than the number of threadCount
    public static interface Forker<T1, T2> {
        T2[] fork(T1 t1);
    }
    public static interface Joiner<T1, T2> {
        T2 join(T1[] t1s);
    }

    public T4 compute(T1 t1) {
        try {
            T2[] parts = forker.fork(t1);
            Future<T3>[] futures = new Future[parts.length];
            
            for (int i = 0; i < parts.length; i++) {
                final T2 part = parts[i];
                futures[i] = executorService.submit(
                    new Callable<T3>() {
                        public T3 call() throws Exception {
                            return subProcess.apply(part);
                        }
                    }
                );
            }
//        T3[] processedParts = new T3[parts.length];
//        Vector<T3> processedParts = new Vector<T3>();
            for (int i = 0; i < futures.length; i++) {
                Future<T3> future = futures[i];
    //            processedParts.add(future.get());
                processedParts[i] = future.get();
            }

//        return joiner.join((T3[])processedParts.toArray());
            return joiner.join(processedParts);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void finish() {
        executorService.shutdown();
    }
}

