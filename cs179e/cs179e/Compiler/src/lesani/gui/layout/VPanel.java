package lesani.gui.layout;

import javax.swing.*;
import java.awt.*;

public class VPanel extends lesani.gui.layout.Box {

    public static final float CENTER = Component.CENTER_ALIGNMENT;
    public static final float LEFT = Component.LEFT_ALIGNMENT;
    public static final float RIGHT = Component.RIGHT_ALIGNMENT;

    private void init() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setAlignment(LEFT);
    }

    public VPanel()
    {
        init();
    }

    public VPanel(JComponent[] components)
    {
        super(components);
        init();
    }


    public VPanel(JComponent c1)
    {
        super(c1);
        init();
    }
    public VPanel(JComponent c1, JComponent c2)
    {
        super(c1, c2);
        init();
    }
    public VPanel(JComponent c1, JComponent c2, JComponent c3)
    {
        super(c1, c2, c3);
        init();
    }
    public VPanel(JComponent c1, JComponent c2, JComponent c3, JComponent c4)
    {
        super(c1, c2, c3, c4);
        init();
    }

    protected Component getDevider()
    {
        return javax.swing.Box.createRigidArea(new Dimension(0, 5));
    }

    protected void setAlignment(JComponent component)
    {
        component.setAlignmentX(alignment);
    }

    public Dimension getMaximumSize()
    {
        int maxWidth = Integer.MIN_VALUE;
        int sumHeight = 0;
        Component[] components = getComponents();
        for (Component component : components) {

            int width;
            int height;
            Dimension d = component.getMaximumSize();
            width = (int)d.getWidth();
            height = (int)d.getHeight();
            if (component instanceof HSpring)
                width = 0;

            if ((component instanceof HPanel) && (((HPanel) component).hasHSpring()))
            {
                HPanel hPanel = (HPanel) component;
                Dimension dim = hPanel.getNonEmptyMaximumSize();
                width = (int)dim.getWidth();
            }
//            System.out.println(width + ", " + height);
            if (width > maxWidth)
                maxWidth = width;
            if (height == Integer.MAX_VALUE)
                sumHeight = Integer.MAX_VALUE;
            else if (sumHeight != Integer.MAX_VALUE)
                sumHeight += height;
        }
//        System.out.println(super.getMaximumSize());
//        System.out.println(new Dimension(maxWidth, sumHeight + 2));
//        System.out.println(getPreferredSize());
//        System.out.println("");
        if (maxWidth != Integer.MAX_VALUE)
                maxWidth += 2;
        if (sumHeight != Integer.MAX_VALUE)
                sumHeight += 2;
        return new Dimension(maxWidth, sumHeight);
    }

    public Dimension getNonEmptyMaximumSize()
    {
        Component[] components = getComponents();
        int sumHeight = 0;
        int maxWidth = Integer.MIN_VALUE;
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
            if (width > maxWidth)
                maxWidth = width;

            if (height == Integer.MAX_VALUE)
                sumHeight = Integer.MAX_VALUE;
            else if (sumHeight != Integer.MAX_VALUE)
                sumHeight += height;
        }
        if (maxWidth != Integer.MAX_VALUE)
                maxWidth += 2;
        if (sumHeight != Integer.MAX_VALUE)
                sumHeight += 2;
//        System.out.println(new Dimension(width, height));
        return new Dimension(maxWidth, sumHeight);
    }

}