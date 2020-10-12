package lesani.image.gui.environment;

import lesani.image.core.image.GSImage;

/**
* Created by IntelliJ IDEA.
* User: lesani
* Date: Mar 15, 2010
* Time: 3:39:37 PM
*/

public interface MultiStepProcessor {
    void setImage(GSImage image);

    public abstract void step();
    public abstract boolean isFinished();
    
    public abstract GSImage current();

}
