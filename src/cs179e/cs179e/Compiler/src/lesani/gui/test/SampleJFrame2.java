package lesani.gui.test;

import lesani.gui.frame.BaseJFrame;
import lesani.gui.layout.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SampleJFrame2 extends BaseJFrame
{
    public SampleJFrame2() throws HeadlessException {
        super("Sample Frame");
        setSize(300, 300);
        setLocation(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JTextField textField = new JTextField();
        final JButton click1 = new JButton("Click");
        final JButton click2 = new JButton("Click");
        final JButton click3 = new JButton("Click");
        final JButton click4 = new JButton("Click");
        final VPanel vPanel1 = new VPanel(Sizer.fixHeight(textField), Sizer.fixHeight(new JTextField()));
        final VPanel vPanel2 = new VPanel(Sizer.fixHeight(new JTextField()), Sizer.fixHeight(new JTextField()));
        final VPanel vPanel3 = new VPanel(Sizer.fixHeight(click1), Sizer.fixHeight(click2));

        final VPanel vPanel4 = new VPanel(Sizer.fixHeight(click3), Sizer.fixHeight(click4));
        HPanel panel = new HPanel(vPanel1, vPanel2, vPanel3);

        click1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField.setText("Clicked.");
                HPanel panel = new HPanel(vPanel1, vPanel2, vPanel3, vPanel4);
                setCenter(panel);
                SampleJFrame2.this.invalidate();
                SampleJFrame2.this.validate();
            }
        });


//        System.out.println(hPanel1.getMaximumSize());
//        System.out.println(Integer.MAX_VALUE);

/*
        HPanel hPanel1 = new HPanel(new JLabel("Are you well .... ..... ... .. .. ... ...... .... .... ....?"));
        HPanel hBox2 = new HPanel(new HSpring(), new JButton("Yes"), new JButton("No"));
        HPanel hBox3 = new HPanel(new HPanel(new JButton("Yes"), new JButton("Yes"), new JButton("No")));
        HPanel hBox4 = new HPanel(new HPanel(new JButton("Yes"), new JButton("Yes"), new JButton("No")));
*/
//        VPanel vPanel = new VPanel(hPanel1, hPanel2);
//        VPanel mainPanel = new VPanel(hPanel1, hBox2);
//        VPanel mainPanel = new VPanel(hPanel1, hBox2, hBox3, hBox4);

//        mainPanel.getMaximumSize();
//        System.out.println("Here");
        //, new JTextArea(10, 10)

        setCenter(new JScrollPane(panel));

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
        SampleJFrame2 frame = new SampleJFrame2();
        frame.setVisible(true);
    }

}
