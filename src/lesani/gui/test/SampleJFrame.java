package lesani.gui.test;

import lesani.gui.frame.BaseJFrame;
import lesani.gui.layout.*;

import javax.swing.*;
import java.awt.*;


public class SampleJFrame extends BaseJFrame
{
    public SampleJFrame() throws HeadlessException {
        super("Sample Frame");
        setSize(300, 300);
        setLocation(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        HPanel hPanel1 = new HPanel(new JLabel("Your Text:"), Sizer.fixHeight(new JTextArea(7, 7)));
//        System.out.println(hPanel1.getMaximumSize());
//        System.out.println(Integer.MAX_VALUE);
        HPanel hPanel2 = new HPanel(new HSpring(), new JButton("Yes"), new JButton("No"));

/*
        HPanel hPanel1 = new HPanel(new JLabel("Are you well .... ..... ... .. .. ... ...... .... .... ....?"));
        HPanel hBox2 = new HPanel(new HSpring(), new JButton("Yes"), new JButton("No"));
        HPanel hBox3 = new HPanel(new HPanel(new JButton("Yes"), new JButton("Yes"), new JButton("No")));
        HPanel hBox4 = new HPanel(new HPanel(new JButton("Yes"), new JButton("Yes"), new JButton("No")));
*/
        VPanel vPanel = new VPanel(hPanel1, hPanel2);
//        VPanel mainPanel = new VPanel(hPanel1, hBox2);
//        VPanel mainPanel = new VPanel(hPanel1, hBox2, hBox3, hBox4);

//        mainPanel.getMaximumSize();
//        System.out.println("Here");
        //, new JTextArea(10, 10)

        setCenter(vPanel);

/*
        GridPanel gridPanel = new GridPanel(new JComponent[][]
                {
                        {new JButton("Yes"), new JButton("No"), new HSpring(), new JLabel("////////////")},
                        {new JLabel("PPPPPPPPPPPPP"), new VSpring(), new JButton("Hahaha"), new VPanel(new JButton("1"), new JButton("2"))}
                }
        );
        setCenterPanel(gridPanel);
*/


    }

    public static void main(String[] args) {
        SampleJFrame frame = new SampleJFrame();
        frame.setVisible(true);
    }

}
