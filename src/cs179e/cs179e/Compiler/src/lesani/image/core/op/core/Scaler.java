package lesani.image.core.op.core;

import lesani.image.core.image.GSImage;
import lesani.image.core.op.core.base.UOp;
import lesani.collection.option.None;
import lesani.collection.func.Fun;
import lesani.parallel.ProcessPower;

import static lesani.parallel.ProcessPower.parfor;

/**
 * Created by IntelliJ IDEA.
 * User: Mohsen's Desktop
 * Date: May 4, 2007
 * Time: 11:50:26 AM
 */

public class Scaler extends UOp<GSImage> {

    private static Scaler theInstance = new Scaler();

    public static Scaler instance() {
        return theInstance;
    }

    private Scaler() {
    }

    public GSImage compute(GSImage image) {
		int width = image.getWidth();
		int height = image.getHeight();

		GSImage scaledImage = new GSImage(width, height);

		int minValue = Integer.MAX_VALUE;
        int maxValue = Integer.MIN_VALUE;
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++) {
				if (image.get(i, j) > maxValue)
						maxValue = image.get(i, j);
				if (image.get(i, j) < minValue)
						minValue = image.get(i, j);
            }

//        System.out.println("MaxValue = " + maxValue);
//        System.out.println("MinValue = " + minValue);

		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++) {
//                System.out.println(((double)((image.get(i, j) - minValue) * image.maxIntensity())) / (maxValue - minValue));
//                System.out.println(image.get(i, j));
				scaledImage.set(i, j,(int)
                        ((double)((image.get(i, j) - minValue) * image.maxIntensity())) / (maxValue - minValue)
                        // image.maxIntensity() = 255
                );
            }
		return scaledImage;
	}

    public GSImage parCompute(final GSImage image) {
		final int width = image.getWidth();
		final int height = image.getHeight();

		final GSImage scaledImage = new GSImage(width, height);

        final int[] minValue = {Integer.MAX_VALUE};
        final int[] maxValue = {Integer.MIN_VALUE};
        parfor (new ProcessPower.ForLoop(0, width, new Fun<Integer, None>() { public None apply(Integer i) {
			for (int j = 0; j < height; j++) {
				if (image.get(i, j) > maxValue[0])
						maxValue[0] = image.get(i, j);
				if (image.get(i, j) < minValue[0])
						minValue[0] = image.get(i, j);
            }
            return None.instance();
        }}));

//        System.out.println("MaxValue = " + maxValue);
//        System.out.println("MinValue = " + minValue);

		parfor (new ProcessPower.ForLoop(0, width, new Fun<Integer, None>() { public None apply(Integer i) {
			for (int j = 0; j < height; j++) {
//                System.out.println(((double)((image.get(i, j) - minValue) * image.maxIntensity())) / (maxValue - minValue));
//                System.out.println(image.get(i, j));
				scaledImage.set(i, j,(int)
                        ((double)((image.get(i, j) - minValue[0]) * image.maxIntensity())) / (maxValue[0] - minValue[0])
                        // image.maxIntensity() = 255
                );
            }
            return None.instance();
        }}));
            
		return scaledImage;
	}


}
