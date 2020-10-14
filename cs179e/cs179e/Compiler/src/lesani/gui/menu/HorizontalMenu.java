package lesani.gui.menu;

import javax.swing.*;
import java.awt.*;

/**
 * User: Mohsen's Desktop
* Date: Aug 26, 2007
* Time: 10:11:33 PM
*/
public class HorizontalMenu extends JMenu {
//    JMenuBar menuBar;


    public HorizontalMenu(/*JMenuBar menuBar, */String label) {
        super(label);
//        this.menuBar = menuBar;
//            JPopupMenu pm = getPopupMenu();
//            pm.setLayout(new BoxLayout(pm, BoxLayout.LINE_AXIS));
    }

    public Dimension getMinimumSize() {
        return getPreferredSize();
    }

    public Dimension getMaximumSize() {
        return getPreferredSize();
    }

    public void setPopupMenuVisible(boolean b) {
        boolean isVisible = isPopupMenuVisible();
        if (b != isVisible) {
            if ((b) && isShowing()) {
                //Set location of popupMenu (pulldown or pullright).
                //Perhaps this should be dictated by L&F.
                Container parent = getParent();
                if (parent instanceof JPopupMenu) {
//                    x = parent.getWidth();
//                    y = 0;
                    super.setPopupMenuVisible(b);
                }
                else
                {
                    int x = 0;
                    int y = 0;
                    x = getWidth();
//                    x = menuBar.getWidth();
                    y = 0;
                    getPopupMenu().show(this, x, y);
                }

            } else {
                getPopupMenu().setVisible(false);
            }
        }
    }
}
