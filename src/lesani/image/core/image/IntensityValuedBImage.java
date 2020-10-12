package lesani.image.core.image;

import java.awt.image.*;

/**
 * Created by IntelliJ IDEA.
 * User: mohsen
 * Date: Feb 27, 2010
 * Time: 1:28:32 AM
 */

public class IntensityValuedBImage extends BImage {

    BImage bImage;

    public IntensityValuedBImage(BImage bImage) {
        super(0, 0);
        this.bImage = bImage;
    }

    @Override
    public int get(int x, int y) {
        return bImage.getIntensity(x, y);
    }

    public void set(int x, int y, int value) {
        bImage.setIntensity(x, y, value);
    }

    public int getIntensity(int x, int y) {
        return bImage.getIntensity(x, y);
    }

    public void setIntensity(int x, int y, int value) {
        bImage.setIntensity(x, y, value);
    }

    public int maxIntensity() {
        return bImage.maxIntensity();
    }

    public void set(int x, int y) {
        bImage.set(x, y);
    }

    public void unset(int x, int y) {
        bImage.unset(x, y);
    }

    public BImage getBlock(int x, int y, int windowWidth, int windowHeight) {
        return bImage.getBlock(x, y, windowWidth, windowHeight);
    }

    public int bufferedImageGet(BufferedImage bufferedImage, int x, int y) {
        return bImage.bufferedImageGet(bufferedImage, x, y);
    }

    public BImage intensityValued() {
        return bImage.intensityValued();
    }

    public BufferedImage getBufferedImage() {
        return bImage.getBufferedImage();
    }

    public int getWidth() {
        return bImage.getWidth();
    }

    public int getHeight() {
        return bImage.getHeight();
    }

    public void thresholdSet(int x, int y, int value) {
        bImage.thresholdSet(x, y, value);
    }

    public void valueUncheckedSet(int x, int y, int value) {
        bImage.valueUncheckedSet(x, y, value);
    }

    public int[][] getArray() {
        return bImage.getArray();
    }

    public void setBlock(int x, int y, lesani.image.core.image.Image blockImage) {
        bImage.setBlock(x, y, blockImage);
    }

    @Override
    public Object clone() {
        return bImage.clone();
    }

    public void checkIndexBoundary(int x, int y) throws RuntimeException {
        bImage.checkIndexBoundary(x, y);
    }

    public void checkValueRange(int value) {
        bImage.checkValueRange(value);
    }

    public void bufferedImageSet(BufferedImage bufferedImage, int x, int y, int intensity) {
        bImage.bufferedImageSet(bufferedImage, x, y, intensity);
    }
}
