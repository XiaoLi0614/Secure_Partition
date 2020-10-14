package lesani.image.core.op.core;

import lesani.image.core.image.GSImage;
import lesani.image.core.op.core.base.UOp;
import lesani.collection.option.None;
import lesani.collection.func.Fun;
import lesani.parallel.ProcessPower;

import static lesani.parallel.ProcessPower.parfor;

/**
 * Created by IntelliJ IDEA.
 * User: mohsen
 * Date: Feb 27, 2010
 * Time: 7:25:52 PM
 */

public class Abs extends UOp<GSImage> {

    private static Abs theInstance = new Abs();

    public static Abs instance() {
        return theInstance;
    }

    private Abs() {
    }

    public GSImage compute(GSImage image) {
		int width = image.getWidth();
		int height = image.getHeight();

		GSImage cutImage = new GSImage(width, height);

		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++)
                cutImage.valueUncheckedSet(i, j,
                        Math.abs(image.get(i, j))
                );
		return cutImage;
    }
    public GSImage parCompute(final GSImage image) {
		int width = image.getWidth();
		final int height = image.getHeight();

		final GSImage cutImage = new GSImage(width, height);

		parfor (new ProcessPower.ForLoop(0, width, new Fun<Integer, None>() { public None apply(Integer i) {
			for (int j = 0; j < height; j++)
                cutImage.valueUncheckedSet(i, j,
                        Math.abs(image.get(i, j))
                );
            return None.instance();
        }}));

		return cutImage;
    }

}
