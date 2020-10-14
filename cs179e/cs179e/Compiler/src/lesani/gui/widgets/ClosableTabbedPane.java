package lesani.gui.widgets;

import lesani.gui.layout.HPanel;
import lesani.gui.layout.HSpring;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by IntelliJ IDEA.
 * User: mohsen
 * Date: Mar 4, 2010
 * Time: 10:30:56 PM
 */

public class ClosableTabbedPane extends JTabbedPane {

    public ClosableTabbedPane(int tabPlacement) {
        super(tabPlacement);
    }

    @Override
    public void addTab(String title, Component component) {
        super.addTab(title, component);
//        setTabComponentAt(getTabCount() - 1, new TabTitlePanel(title));
        setTabComponentAt(getTabCount() - 1, makePanel(title));
    }

    private JPanel makePanel(String title) {
        final TabButton button = new TabButton();
        JPanel titlePanel = new HPanel(new JLabel(title), new HSpring(), button);
//        Component block = Box.createRigidArea(new Dimension(1, 2));
//        JPanel wrapper =
        titlePanel.setOpaque(false);
        titlePanel.setBorder(BorderFactory.createMatteBorder(3, 1, 1, 1, (Color) null));
//        wrapper.setOpaque(false);
        button.setTitlePanel(titlePanel);
        return titlePanel;
    }

//    private class TabTitlePanel extends HPanel {
//
//        public TabTitlePanel(String title) {
//            super(new JLabel(title), new HSpring(), new TabButton());
//        }

        private class TabButton extends JButton implements ActionListener {
            private JPanel titlePanel;

            //            TabTitlePanel tabTitlePanel;
            public TabButton() {
                int size = 15;
                final Dimension dimSize = new Dimension(size, size);
                setPreferredSize(dimSize);
                setMaximumSize(dimSize);
                setMinimumSize(dimSize);
                setToolTipText("close");
                //Make the button looks the same for all Laf's
                setUI(new BasicButtonUI());
                //Make it transparent
//                setContentAreaFilled(false);
                //No need to be focusable
//                setFocusable(false);
//                setBorder(BorderFactory.createEtchedBorder());
//                setBorderPainted(false);
                //Close the proper tab by clicking the button
                addActionListener(this);
            }
            public void setTitlePanel(JPanel titlePanel) {
                this.titlePanel = titlePanel;
            }

//            public TabTitlePanel getTabTitlePanel() {
//                return tabTitlePanel;
//            }
//
//            public void setTabTitlePanel(TabTitlePanel tabTitlePanel) {
//                this.tabTitlePanel = tabTitlePanel;
//            }

            public void actionPerformed(ActionEvent e) {

                int i = indexOfTabComponent(titlePanel);
//                System.out.println(getTabCount());
                if (i != -1) {
                    ClosableTabbedPane.this.remove(i);
                }
            }

            //we don't want to update UI for this button
/*
            public void updateUI() {
            }
*/

            //paint the cross
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                //shift the image for pressed buttons
                g2.setStroke(new BasicStroke(1));
                g2.setColor(Color.BLACK);
                int delta = 3;
                g2.drawLine(delta, delta, getWidth() - delta - 1, getHeight() - delta - 1);
                g2.drawLine(getWidth() - delta - 1, delta, delta, getHeight() - delta - 1);
                g2.dispose();
            }
//        }

        }

}