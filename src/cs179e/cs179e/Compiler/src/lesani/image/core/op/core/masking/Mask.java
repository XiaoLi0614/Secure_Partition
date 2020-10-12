package lesani.image.core.op.core.masking;

/**
 * Created by IntelliJ IDEA.
 * User: Mohsen's Desktop
 * Date: Apr 19, 2007
 * Time: 1:10:29 PM
 */

public class Mask
{
	private int[][] mask;
    private boolean normalizing = false;
    private int cachedSum;

    public abstract static class Smoothing {
        // The result should be divided by the size of the mask.
        public static Mask gaussian3 = new Mask(
                    new int[][] {{1, 2, 1},
                                 {2, 4, 2},
                                 {1, 2, 1}},
                                true);

        public static Mask gaussian5 = new Mask(
                    new int[][] {{2, 4, 5, 4, 2},
                                 {4, 9, 12, 9, 4},
                                 {5, 12, 15, 12, 5},
                                 {4, 9, 12, 9, 4},
                                 {2, 4, 5, 4, 2}},
                                true);
    }

    public abstract static class Sharpening {
		public abstract static class FirstDerivative
		{
			public static Mask horizontalSobelMask = new Mask(
						new int[][] {{-1, -2, -1},
									 {0, 0, 0},
									 {1, 2, 1}});   

			public static Mask leftDiagonalSobelMask = new Mask(
						new int[][] {{2, 1, 0},
									 {1, 0, -1},
									 {0, -1, -2}});

			public static Mask verticalSobelMask = new Mask(
                        new int[][] {{-1, 0, 1},
                                     {-2, 0, 2},
                                     {-1, 0, 1}});

			public static Mask rightDiagonalSobelMask = new Mask(
                        new int[][] {{0, -1, -2},
                                     {1, 0, -1},
                                     {2, 1, 0}});
        }

        public abstract static class SecondDerivative {

            public static Mask laplacianMask = new Mask(
						new int[][] {{0, -1, 0},
									 {-1, 4, -1},
									 {0, -1, 0}});

		}
		public static Mask laplacianIncludingDiagonalsMask = new Mask(
				new int[][] {{-1, -1, -1},
							 {-1, 8, -1},
							 {-1, -1, -1}});
	}

	public Mask(int[][] mask) {
		this.mask = mask;
	}

    public Mask(int[][] mask, boolean normalizing) {
        this.mask = mask;
        this.normalizing = normalizing;
        if (normalizing)
            cachedSum = sum();
    }

    public int[][] getMask()
	{
		return mask.clone();
	}

	public void setMask(int[][] mask)
	{
		this.mask = mask;
	}

    public boolean isNormalizing() {
        return normalizing;
    }

    public void setNormalizing(boolean normalizing) {
        this.normalizing = normalizing;
    }

    public Mask reflection() {
        int[][] reflectedMask = new int[mask.length][mask[0].length];
        for (int i = 0; i < reflectedMask.length; i++)
            for (int j = 0; j < reflectedMask.length; j++)
                reflectedMask[i][j] = mask[2 * (mask.length/2) - i][2 * (mask[0].length/2) - j];
        return new Mask(reflectedMask);
    }

	public int getWidth()
	{
		if (mask == null)
			return 0;
		return mask.length;
	}

	public int getHeight()
	{
		if (mask == null)
			return 0;
		return mask[0].length;
	}

	public void set(int i, int j, int value)
	{
		mask[i][j] = value;
	}

	public int get(int i, int j)
	{
		return mask[i][j];
	}

    public static Mask newFull(int width, int height) {
        int[][] array = new int[width][height];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = 1;
            }
        }
        return new Mask(array);
    }

    public int sum() {
        int sum = 0;
        for (int i = 0; i < mask.length; i++) {
            for (int j = 0; j < mask[0].length; j++) {
                sum += mask[i][j];
            }
        }
        return sum;
    }

    public void cacheSum() {
        cachedSum = sum();
    }

    public int getCachedSum() {
        return cachedSum;
    }
}
