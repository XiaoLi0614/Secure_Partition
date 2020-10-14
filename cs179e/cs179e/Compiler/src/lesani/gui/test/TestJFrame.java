package lesani.gui.test;

import lesani.gui.frame.BaseJFrame;
import lesani.gui.layout.HPanel;
import lesani.gui.layout.HSpring;

import javax.swing.*;


public class TestJFrame extends BaseJFrame
{
    public TestJFrame() {
        super("Test");

/*
        HPanel hPanel = new HPanel(new JLabel("This:"), new JTextField() {
            public Dimension getMaximumSize() {
                System.out.println(getPreferredSize());
                return getPreferredSize();
            }
        });
*/
//        HPanel hPanel = new HPanel(new JLabel("This:"), new JTextField(20));
//        HPanel hPanel = new HPanel(new JLabel("This:"), Sizer.fixSize(new JTextField(10)));
        HPanel hPanel = new HPanel(new HPanel(new HSpring(), new JLabel("Yes")));

//        setContentPane(hPanel);
        setCenter(hPanel);
    }

    public static void main(String[] args) {
        new TestJFrame().setVisible(true);
    }
}
