package lesani.gui.layout;

import javax.swing.*;
import java.awt.*;

public class HPanel extends lesani.gui.layout.Box {

    public static final float CENTER = Component.CENTER_ALIGNMENT;
    public static final float TOP = Component.TOP_ALIGNMENT;
    public static final float BOTTOM = Component.BOTTOM_ALIGNMENT;

    private void init() {
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        setAlignment(CENTER);
    }

    public HPanel()
    {
        init();
    }

    public HPanel(JComponent[] components)
    {
        super(components);
        init();
    }


    public HPanel(JComponent c1)
    {
        super(c1);
        init();
    }
    public HPanel(JComponent c1, JComponent c2)
    {
        super(c1, c2);
        init();
    }
    public HPanel(JComponent c1, JComponent c2, JComponent c3)
    {
        super(c1, c2, c3);
        init();
    }
    public HPanel(JComponent c1, JComponent c2, JComponent c3, JComponent c4)
    {
        super(c1, c2, c3, c4);
        init();
    }

    protected Component getDevider()
    {
        return javax.swing.Box.createRigidArea(new Dimension(5, 0));
    }

    protected void setAlignment(JComponent component)
    {
        component.setAlignmentY(alignment);
//        component.setAlignmentX(alignment);
//        System.out.println(component);
//        System.out.println(alignment);
    }

    public Dimension getMaximumSize()
    {
        int sumWidth = 0;
        int maxHeight = Integer.MIN_VALUE;
        Component[] components = getComponents();
        for (Component component : components) {
            int width;
            int height;
            Dimension d = component.getMaximumSize();
            width = (int)d.getWidth();
            height = (int)d.getHeight();
            if (component instanceof VSpring)
                height = 0;
            if ((component instanceof VPanel) && (((VPanel) component).hasVSpring()))
            {
                VPanel vPanel = (VPanel) component;
                Dimension dim = vPanel.getNonEmptyMaximumSize();
                height = (int)dim.getHeight();
            }
//            System.out.println(width + ", " + height);
            if (height > maxHeight)
                maxHeight = height;

            if (width == Integer.MAX_VALUE)
                sumWidth = Integer.MAX_VALUE;
            else if (sumWidth != Integer.MAX_VALUE)
                sumWidth += width;

        }
//        System.out.println("\t" + sumWidth + "\t" + maxHeight);
        if (sumWidth != Integer.MAX_VALUE)
                sumWidth += 2;
        if (maxHeight != Integer.MAX_VALUE)
                maxHeight += 2;
        return new Dimension(sumWidth, maxHeight);
    }

    public Dimension getNonEmptyMaximumSize()
    {
        Component[] components = getComponents();
        int maxHeight = Integer.MIN_VALUE;
        int sumWidth = 0;
        for (Component component : components) {
            int width = 0;
            int height = 0;

            if (!(component instanceof HSpring))
            {
                if (component instanceof lesani.gui.layout.Box)
                    width = (int)((lesani.gui.layout.Box)component).getNonEmptyMaximumSize().getWidth();
                else
                    width = (int)component.getMaximumSize().getWidth();
            }
            if (!(component instanceof VSpring))
            {
                if (component instanceof lesani.gui.layout.Box)
                    height = (int)((lesani.gui.layout.Box)component).getNonEmptyMaximumSize().getHeight();
                else
                    height = (int)component.getMaximumSize().getHeight();
            }
            if (height > maxHeight)
                maxHeight = height;

            if (width == Integer.MAX_VALUE)
                sumWidth = Integer.MAX_VALUE;
            else if (sumWidth != Integer.MAX_VALUE)
                sumWidth += width;
        }
        if (sumWidth != Integer.MAX_VALUE)
                sumWidth += 2;
        if (maxHeight != Integer.MAX_VALUE)
                maxHeight += 2;
//        System.out.println(new Dimension(width, height));
        return new Dimension(sumWidth, maxHeight);
    }


/*
    public static void main(String[] args) {
        new BaseJFrame("HPanel") {
            {

            }
        }.setVisible(true);
    }
*/
}