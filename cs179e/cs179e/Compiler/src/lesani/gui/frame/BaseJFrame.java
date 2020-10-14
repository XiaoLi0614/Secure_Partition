package lesani.gui.frame;

import lesani.gui.layout.HPanel;
import lesani.gui.layout.HSpring;
import lesani.gui.layout.VPanel;
import lesani.gui.layout.VSpring;

import javax.swing.*;
import java.awt.*;


public class BaseJFrame extends JFrame
{
    VPanel mainPanel;
    protected Component centerPanel;
    private boolean isSizeSet = false;
    private JMenuBar leftMenu;

    static
    {
        JFrame.setDefaultLookAndFeelDecorated(true);
    }

    public BaseJFrame(String title) throws HeadlessException
    {
        super(title);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setSize(Dimension d) {
        isSizeSet = true;
        super.setSize(d);
    }

    public void setSize(int width, int height) {
        isSizeSet = true;
        super.setSize(width, height);
    }
    public void setSize() {
	//            System.out.println(centerPanel.getMaximumSize());
	//            System.out.println(centerPanel.getPreferredSize());
	//            System.out.println(centerPanel.getNonEmptyMaximumSize());

		Dimension d = mainPanel.getNonEmptyMaximumSize();
	//            System.out.println(d);
		int width = (int)d.getWidth();
		int height = (int)d.getHeight();
		if (width != Integer.MAX_VALUE)
			width += (2 + 15);
		else
			width = 600;
		if (height != Integer.MAX_VALUE)
			height += (19 + 15);
		else
			width = 600;
		setSize(width, height);
	}

    public void setVisible(boolean b) {
        if (b && !isSizeSet)
			setSize();
//		setLocation(200, 200);
        super.setVisible(b);
    }

    public void setLeftMenu(JMenuBar menuBar) {
        leftMenu = menuBar;
        buildMainPanel();
    }

    public void setCenter(Component component)
    {
        this.centerPanel = component;
        buildMainPanel();
    }

    private void buildMainPanel() {
        if (leftMenu == null) {
            HPanel ccenterPanel = new HPanel();
//        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));
//        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
//        panel.setAlignmentY(Component.CENTER_ALIGNMENT);
            ccenterPanel.add(centerPanel);
//        centerPanel.setBorder(BorderFactory.createEtchedBorder());
            ccenterPanel.setBorder(BorderFactory.createEtchedBorder());
//        centerPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
//        centerPanel.setBorder(BorderFactory.createRaisedBevelBorder());

//        HPanel hPanel = new HPanel(new HSpring(), new JButton("Button"), new JButton("Button"));
//        centerPanel.add(new JButton("Button"));
            HPanel hPanel = new HPanel(new HSpring(), ccenterPanel, new HSpring());
//        VPanel mainPanel = new VPanel()
//        centerPanel.add();
//        centerPanel.add();
//        centerPanel = hPanel;

            mainPanel = new VPanel(new VSpring(), hPanel, new VSpring());
//        HPanel hPanel = new HPanel(new HSpring(), mainPanel, new HSpring());
            setContentPane(mainPanel);
/*
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());
        panel1.add(centerPanel, BorderLayout.SOUTH);
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());
        panel2.add(panel1, BorderLayout.CENTER);
        setContentPane(panel2);
*/

        } else {
            Container contentPane = getContentPane();
            add(leftMenu, BorderLayout.LINE_START);
            if (centerPanel != null)
                contentPane.add(centerPanel, BorderLayout.CENTER);
        }

    }
}

