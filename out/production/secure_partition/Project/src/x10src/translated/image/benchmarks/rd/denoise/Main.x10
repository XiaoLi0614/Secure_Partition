
import x10.compiler.*;

public class Main {

	public static def main(args: Array[String]) {
		Lib.disp("Rician Denoising");
		var sigma: Double = 0.05;
		var lambda: Double = 0.065;
		var tolerance: Double = 2e-3;
		var inputPathName: String = "/media/MOHSENHD/1.Works/3.Research/0.Research/1.CDSC/2.Projects/0.Project/0.Project/src/msrc/benchmarks/rd/denoise/NoisyKneeMRI.format";
		var outputPathName: String = "/media/MOHSENHD/1.Works/3.Research/0.Research/1.CDSC/2.Projects/0.Project/0.Project/src/msrc/benchmarks/rd/denoise/DenoisedKneeMRI.format";
		var noisyImage: DoubleMatrix = Lib.double(Lib.readFormatImage(inputPathName));
		noisyImage = Lib.divide(noisyImage, 255);
		Lib.tic();
		var denoisedImage: DoubleMatrix = RicianDenoise.ricianDenoise(noisyImage.clone(), sigma, lambda, tolerance);
		var time: Double = Lib.toc();
		Lib.disp("Time in seconds:");
		Lib.disp(time);
		Lib.writeFormatImage(denoisedImage.clone(), outputPathName);
	}

}

