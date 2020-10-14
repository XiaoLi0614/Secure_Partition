package lesani.image.gui.environment;

import lesani.gui.widgets.ClosableTabbedPane;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: lesani
 * Date: Mar 5, 2010
 * Time: 2:35:17 PM
 */

public class ImageTabbedPane extends ClosableTabbedPane {

    public ImageTabbedPane() {
        super(JTabbedPane.LEFT);

    }

    public void addTab(String title, BufferedImage image, File file) {
        addTab(title, new ImagePanel(image, file));
        setSelectedIndex(getTabCount() - 1);
    }
    public void addTab(String title, BufferedImage image) {
        addTab(title, image, null);
    }

    public ImagePanel getImagePanel() {
        return (ImagePanel) getComponentAt(getSelectedIndex());
    }

}
