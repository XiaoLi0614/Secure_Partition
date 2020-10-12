package lesani.image.gui.environment;

import lesani.image.core.image.GSImage;

/**
 * Created by IntelliJ IDEA.
 * User: lesani
 * Date: Mar 15, 2010
 * Time: 4:19:51 PM
 */
public class DefiniteStepCountProcessor implements MultiStepProcessor {
    private ImageProcessor processor;
    private GSImage current;
    private int totalSteps;

    private int stepCount = 0;

    public DefiniteStepCountProcessor(ImageProcessor processor, int totalSteps) {
        this.processor = processor;
        this.totalSteps = totalSteps;
    }

    public DefiniteStepCountProcessor(ImageProcessor processor, int totalSteps, GSImage image) {
        this.processor = processor;
        this.totalSteps = totalSteps;
        this.current = image;
    }

    public void setImage(GSImage image) {
        this.current = image;
    }

    public void step() {
        current = processor.process(current);
        stepCount++;
        System.out.println(stepCount);
    }

    public boolean isFinished() {
        return stepCount == totalSteps;
    }

    public GSImage current() {
        return current;
    }

}
