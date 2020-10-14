
import x10.compiler.*;

public class Tv {

	public static def tv(inputImage: DoubleMatrix, V: DoubleMatrix, alpha: Int, maxIter: Int): DoubleMatrix {
		var newImage: DoubleMatrix;
		var xId1: Tuple2[Int, Int] = Lib.size(inputImage.clone());
		var m: Int = xId1._1;
		var n: Int = xId1._2;
		var epsilon: Double = 1e-8;
		var image1: DoubleMatrix = inputImage.clone();
		var image2: DoubleMatrix = Lib.dzeros(m, n);
		var c1: DoubleMatrix = Lib.dzeros(m, n);
		var c2: DoubleMatrix = Lib.dzeros(m, n);
		var c3: DoubleMatrix = Lib.dzeros(m, n);
		var c4: DoubleMatrix = Lib.dzeros(m, n);
		for (k in new IntMatrix(1, 1, maxIter).values()) {
			for (i in new IntMatrix(2, 1, (m - 1)).values()) 
				for (j in new IntMatrix(2, 1, (n - 1)).values()) 
					c1(i, j) = ((image1(i, j) / Lib.sqrt(((epsilon + Lib.dotPower((image1((i + 1), j) - image1(i, j)), 2)) + Lib.dotPower((image1(i, (j + 1)) - image1(i, j)), 2)))) / V(i, j));
			for (i in new IntMatrix(2, 1, (m - 1)).values()) 
				for (j in new IntMatrix(2, 1, (n - 1)).values()) 
					c2(i, j) = ((image1(i, j) / Lib.sqrt(((epsilon + Lib.dotPower((image1(i, j) - image1((i - 1), j)), 2)) + Lib.dotPower((image1((i - 1), (j + 1)) - image1((i - 1), j)), 2)))) / V(i, j));
			for (i in new IntMatrix(2, 1, (m - 1)).values()) 
				for (j in new IntMatrix(2, 1, (n - 1)).values()) 
					c3(i, j) = ((image1(i, j) / Lib.sqrt(((epsilon + Lib.dotPower((image1((i + 1), j) - image1(i, j)), 2)) + Lib.dotPower((image1(i, (j + 1)) - image1(i, j)), 2)))) / V(i, j));
			for (i in new IntMatrix(2, 1, (m - 1)).values()) 
				for (j in new IntMatrix(2, 1, (n - 1)).values()) 
					c4(i, j) = ((image1(i, j) / Lib.sqrt(((epsilon + Lib.dotPower((image1((i + 1), (j - 1)) - image1(i, (j - 1))), 2)) + Lib.dotPower((image1(i, j) - image1(i, (j - 1))), 2)))) / V(i, j));
			for (i in new IntMatrix(2, 1, (m - 1)).values()) 
				for (j in new IntMatrix(2, 1, (n - 1)).values()) 
					if ((image1(i, j) < epsilon)) {

							image2(i, j) = image1(i, j);
					}					else 
						image2(i, j) = ((1 / ((((alpha + c1(i, j)) + c2(i, j)) + c3(i, j)) + c4(i, j))) * (((((alpha * inputImage(i, j)) + (c1(i, j) * image1((i + 1), j))) + (c2(i, j) * image1((i - 1), j))) + (c3(i, j) * image1(i, (j + 1)))) + (c4(i, j) * image1(i, (j - 1)))));
			image1 = image2.clone();
		}
		newImage = image1.clone();
		return newImage;
	}

}

