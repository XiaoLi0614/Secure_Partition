package lesani.gui.frame;

import lesani.gui.layout.HPanel;
import lesani.gui.layout.HSpring;
import lesani.gui.layout.VSpring;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by IntelliJ IDEA.
 * User: lesani
 * Date: Mar 5, 2010
 * Time: 2:22:32 PM
 */
public class CenteredPanel extends JPanel {
    private JPanel center;

    public void setCenter(JPanel center) {
        this.center = center;
        init();
    }

    public CenteredPanel() {
    }

    public CenteredPanel(JPanel center) {
        this.center = center;
        init();
    }

    private void init() {
        setLayout(new BorderLayout());
        HPanel hCenterer = new HPanel(new HSpring(), center, new HSpring());
        HPanel vCenterer = new HPanel(new VSpring(), hCenterer, new VSpring());
        add(vCenterer, BorderLayout.CENTER);
    }

    public JPanel getCenter() {
        return center;
    }
}
