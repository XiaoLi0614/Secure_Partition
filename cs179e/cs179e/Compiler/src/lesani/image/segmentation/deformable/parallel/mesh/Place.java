package lesani.image.segmentation.deformable.parallel.mesh;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by IntelliJ IDEA.
 * User: mohsen
 * Date: Mar 25, 2010
 * Time: 11:09:05 PM
 */

public class Place {
    private ExecutorService executorService;

    public Place() {
        executorService = Executors.newSingleThreadExecutor();
    }

    public void async(Runnable runnable) {
        executorService.execute(runnable);
    }

    public <T> Future<T> at(Callable<T> callable) {
        return executorService.submit(callable);
    }

    public void finish() {
        executorService.shutdown();
    }
}

