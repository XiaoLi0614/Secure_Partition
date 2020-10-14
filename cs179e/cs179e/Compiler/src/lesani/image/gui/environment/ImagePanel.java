package lesani.image.gui.environment;

import lesani.collection.option.None;
import lesani.collection.option.Option;
import lesani.collection.option.Some;
import lesani.gui.frame.CenteredPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: lesani
 * Date: Mar 5, 2010
 * Time: 11:11:47 AM
 */

public class ImagePanel extends CenteredPanel {

    private BufferedImage image;
    private Option<File> file = None.instance();

    public ImagePanel(BufferedImage image) {
        this.image = image;
        init();
    }

    public ImagePanel(BufferedImage image, File file) {
        this.image = image;
        if (file==null)
            this.file = None.instance();
        else
            this.file = new Some<File>(file);
        init();
    }

    public void init() {
        JPanel centerPanel = new InnerImagePanel();
        setCenter(centerPanel);
    }

    public BufferedImage getImage() {
        return image;
    }

    public Option<File> getFile() {
        return file;
    }

    public void setImage(final BufferedImage image) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ImagePanel.this.image = image;

                JPanel centerPanel = new InnerImagePanel();
                setCenter(centerPanel);
                repaint();
            }
        });
    }

    class InnerImagePanel extends JPanel {

        public void paint(Graphics g)
        {
            g.drawImage(image, 0, 0, null);
        }

        public Dimension getMinimumSize()
        {
            return new Dimension(image.getWidth() , image.getHeight());
        }

        public Dimension getPreferredSize()
        {
            return new Dimension(image.getWidth(), image.getHeight());
        }

        public Dimension getMaximumSize()
        {
            return new Dimension(image.getWidth(), image.getHeight());
        }
    }
}
