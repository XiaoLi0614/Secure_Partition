package lesani.gui.widgets;

import lesani.gui.layout.HPanel;
import lesani.gui.layout.HSpring;

import javax.swing.*;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: lesani
 * Date: Mar 29, 2010
 * Time: 11:52:39 AM
 */

public class StatusBar extends HPanel {
    private JLabel label;

    public StatusBar() {
        setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.GRAY));
        label = new JLabel();
        add(label);
        add(new HSpring());
    }

    public void set(String s) {
        label.setText(s);
    }

    public String get() {
        return label.toString();
    }
}
