package lesani.gui.layout;

import java.awt.*;

public class VSpring extends javax.swing.Box.Filler
{
	public VSpring()
	{
		super(new Dimension(0,0), new Dimension(0,0), new Dimension(0, Short.MAX_VALUE));

    }

/*
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.drawLine(0, 0, getWidth(), getHeight());

    }
*/
}
