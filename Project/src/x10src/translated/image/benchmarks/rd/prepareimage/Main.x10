
import x10.compiler.*;

public class Main {

	public static def main(args: Array[String]) {
		Lib.disp("Adding Rician Noise");
		var originalImagePathName: String = "/media/MOHSENHD/1.Works/3.Research/0.Research/1.CDSC/2.Projects/0.Project/0.Project/src/msrc/benchmarks/rd/prepareimage/KneeMRI.format";
		var noisyImagePathName: String = "/media/MOHSENHD/1.Works/3.Research/0.Research/1.CDSC/2.Projects/0.Project/0.Project/src/msrc/benchmarks/rd/prepareimage/NoisyKneeMRI.format";
		var originalImage: DoubleMatrix = Lib.double(Lib.readFormatImage(originalImagePathName));
		originalImage = Lib.divide(originalImage, 255);
		var sigma: Double = 0.05;
		Lib.tic();
		var noisyImage: DoubleMatrix = AddRicianNoise.addRicianNoise(originalImage.clone(), sigma);
		var time: Double = Lib.toc();
		Lib.disp("Time in seconds:");
		Lib.disp(time);
		Lib.writeFormatImage(noisyImage.clone(), noisyImagePathName);
	}

}

