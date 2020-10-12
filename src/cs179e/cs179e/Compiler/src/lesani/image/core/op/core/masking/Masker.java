package lesani.image.core.op.core.masking;

import lesani.image.core.image.Image;
import lesani.image.core.op.core.base.UOp;
import lesani.collection.option.None;
import lesani.collection.func.Fun;
import lesani.collection.func.concrete.IntBFun;
import lesani.collection.func.concrete.IntBoolFun;
import lesani.parallel.ProcessPower;

import static lesani.parallel.ProcessPower.parfor;

/**
 * Created by IntelliJ IDEA.
 * User: Mohsen's Desktop
 * Date: Apr 19, 2007
 * Time: 1:06:32 PM
*/

public class Masker<T extends Image> extends UOp<T> {

	private Mask mask;
    private int x1, x2, y1, y2;
    private IntBFun maskApplFun;
    private IntBFun maskAggrFun;
    private IntBoolFun shortcut;
    private boolean parallel = false;
    //private int maskFactor;
    // A mask with elements equal to the elements of mask are
    //    multiplied by maskFactor is applied.
    //private int imageFactor;
    // The image is multiplied by the imageFactor before adding
    //    the masked image to it.

	public Masker(Mask mask) {
        this(mask, -1, -1, -1, -1);
    }

	public Masker(Mask mask, int x1, int x2, int y1, int y2) {
        this(mask,
                x1, x2, y1, y2,
                new IntBFun() {
                    @Override
                    public Integer apply(Integer p1, Integer p2) {
                        return p1 * p2;
                    }
                },
                new IntBFun() {
                    @Override
                    public Integer apply(Integer p1, Integer p2) {
                        return p1 + p2;
                    }
                },
                new IntBoolFun() {
                    @Override
                    public Boolean apply(Integer p1) {
                        return false;
                    }
                }
        );
    }

    public Masker(Mask mask, IntBFun maskApplFun, IntBFun maskAggrFun, IntBoolFun shortcut) {
        this(mask, -1, -1, -1, -1, maskApplFun, maskAggrFun, shortcut);
    }

    public Masker(Mask mask, IntBFun maskApplFun, IntBFun maskAggrFun) {
        this(mask, -1, -1, -1, -1, maskApplFun, maskAggrFun,
                new IntBoolFun() {
                    @Override
                    public Boolean apply(Integer p1) {
                        return false;
                    }
                }
        );
    }

	public Masker(Mask mask, int x1, int x2, int y1, int y2, IntBFun maskApplFun, IntBFun maskAggrFun, IntBoolFun shortcut) {
		this.mask = mask;
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
		this.maskApplFun = maskApplFun;
		this.maskAggrFun = maskAggrFun;
        this.shortcut = shortcut;
	}

    public T compute(final T image) {
        final int lx1;
        final int lx2;
        final int ly1;
        final int ly2;

        if (x1 != -1)
            lx1 = x1;
        else
            lx1 = 0;
        if (x2 != -1)
            lx2 = x2;
        else
            lx2 = image.getWidth();
        if (y1 != -1)
            ly1 = y1;
        else
            ly1 = 0;
        if (y2 != -1)
            ly2 = y2;
        else
            ly2 = image.getHeight();

//        System.out.println(x2);
//        System.out.println(y2);

        final int maskWidth = mask.getWidth();
        final int maskHeight = mask.getHeight();

/*
        Mask mask = new Mask(new int[maskWidth][maskHeight]);
        for (int i = 0; i < maskWidth; i++)
            for (int j = 0; j < maskHeight; j++)
                mask.set(i, j, mask.get(i, j) * maskFactor);
*/

        final T masked = Image.createImageOfType(image, lx2-lx1, ly2-ly1);

        if (parallel) {
            parfor (new ProcessPower.ForLoop(lx1, lx2, new Fun<Integer, None>() { public None apply(Integer i) {
                for (int j = ly1; j < ly2; j++) {
                    boolean init = true;
                    int aggrValue = 0;
                    MaskLoop:
                    for (int k = 0; k < maskWidth; k++)
                        for (int l = 0; l < maskHeight; l++) {
                            int pixelValue;
                            try	{
                                pixelValue = image.get(
                                        i + k - maskWidth/2,
                                        j + l - maskHeight/2
                                );
                            } catch (RuntimeException e)	{
    //                            System.out.println("---");
    //                            System.out.println("x: " + i);
    //                            System.out.println("x2: " + lx2);
    //                            System.out.println("width: " + image.getWidth());
    //                            System.out.println("y: " + j);
    //                            System.out.println("y2: " + ly2);
    //                            System.out.println("height: " + image.getHeight());
                                pixelValue = image.get(i, j); //Copying to neighbors
                            }
                            final int maskValue = mask.get(k, l);
                            int currentValue = maskApplFun.apply(maskValue, pixelValue);
                            // Order of apply parameters should be maskValue and pixelValue
                            if (init) {
                                aggrValue = currentValue;
                                init = false;
                            } else {
                                aggrValue = maskAggrFun.apply(aggrValue, currentValue);
                            }
                            if (shortcut.apply(aggrValue))
                                break MaskLoop;
                        }
                    int value;
                    if (mask.isNormalizing())
                        value = aggrValue / mask.getCachedSum();
                    else
                        value = aggrValue;

                    masked.valueUncheckedSet(i - lx1, j - ly1, value);
                }
                return None.instance();
            }}));
        } else {
            for (int i = lx1; i < lx2; i++)
                for (int j = ly1; j < ly2; j++) {
                    boolean init = true;
                    int aggrValue = 0;
                    MaskLoop:
                    for (int k = 0; k < maskWidth; k++)
                        for (int l = 0; l < maskHeight; l++) {
                            int pixelValue;
                            try	{
                                pixelValue = image.get(
                                        i + k - maskWidth/2,
                                        j + l - maskHeight/2
                                );
                            } catch (RuntimeException e)	{
    //                            System.out.println("---");
    //                            System.out.println("x: " + i);
    //                            System.out.println("x2: " + lx2);
    //                            System.out.println("width: " + image.getWidth());
    //                            System.out.println("y: " + j);
    //                            System.out.println("y2: " + ly2);
    //                            System.out.println("height: " + image.getHeight());
                                pixelValue = image.get(i, j); //Copying to neighbors
                            }
                            final int maskValue = mask.get(k, l);
                            int currentValue = maskApplFun.apply(maskValue, pixelValue);
                            // Order of apply parameters should be maskValue and pixelValue
                            if (init) {
                                aggrValue = currentValue;
                                init = false;
                            } else {
                                aggrValue = maskAggrFun.apply(aggrValue, currentValue);
                            }
                            if (shortcut.apply(aggrValue))
                                break MaskLoop;
                        }
                    int value;
                    if (mask.isNormalizing())
                        value = aggrValue / mask.getCachedSum();
                    else
                        value = aggrValue;

                    masked.valueUncheckedSet(i - lx1, j - ly1, value);
                }
        }
        return masked;
    }

    public boolean isParallel() {
        return parallel;
    }

    public void setParallel(boolean parallel) {
        this.parallel = parallel;
    }

    public static <T extends Image> T process(T image, Mask mask) {
        return new Masker<T>(mask).compute(image);
    }

    public static <T extends Image> T parProcess(T image, Mask mask) {
        Masker<T> masker = new Masker<T>(mask);
        masker.setParallel(true);
        return masker.compute(image);    
    }
}

