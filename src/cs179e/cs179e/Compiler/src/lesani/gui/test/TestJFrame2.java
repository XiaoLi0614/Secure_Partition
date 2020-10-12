package lesani.gui.test;

import lesani.gui.frame.BaseJFrame;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: Mohsen's Desktop
 * Date: Apr 18, 2006
 * Time: 6:39:02 PM
 */

public class TestJFrame2 {
    public static void main(String[] args) {
        BaseJFrame frame = new BaseJFrame("Base Frame");
        frame.setSize(100, 100);
        System.out.println(frame.getHeight());
        frame.setLocation(200, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JPanel panel = new JPanel();
        panel.add(new JButton("Yes"));
        panel.add(new JButton("No"));
//        JPanel panel = new VPanel(new VSpring(), new JButton("Yes"), new JButton("No"), new VSpring());
//        frame.setCenterPanel(panel);
        frame.setVisible(true);
    }

}

