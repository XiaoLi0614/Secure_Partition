package lesani.gui.layout;

import javax.swing.*;
import java.awt.*;


public abstract class Box extends JPanel {
    protected float alignment;

    public Box()
    {
        super.add(getDevider());
//        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    public Box(JComponent[] components)
    {
        this();
        add(components);
    }

    public Box(JComponent c1)
    {
        this(new JComponent[] {c1});
    }
    public Box(JComponent c1, JComponent c2)
    {
        this(new JComponent[] {c1, c2});
    }
    public Box(JComponent c1, JComponent c2, JComponent c3)
    {
        this(new JComponent[] {c1, c2, c3});
    }
    public Box(JComponent c1, JComponent c2, JComponent c3, JComponent c4)
    {
        this(new JComponent[] {c1, c2, c3, c4});
    }

    public void add(JComponent[] components)
    {
        for (JComponent component : components) {
            setAlignment(component);
            super.add(component);
            Component c = getDevider();
            //setAlignment(c);
            super.add(c);

        }
    }

    public void add(JComponent c1)
    {
        add(new JComponent[] {c1});
    }

    public void add(JComponent c1, JComponent c2)
    {
        add(new JComponent[] {c1, c2});
    }

    public void add(JComponent c1, JComponent c2, JComponent c3)
    {
        add(new JComponent[] {c1, c2, c3});
    }

    public void add(JComponent c1, JComponent c2, JComponent c3, JComponent c4)
    {
        add(new JComponent[] {c1, c2, c3, c4});
    }

    protected abstract Component getDevider();
    protected abstract void setAlignment(JComponent component);

    public float getAlignment() {
        return alignment;
    }

    public void setAlignment(float alignment) {
        this.alignment = alignment;
        Component[] components = getComponents();
        for (Component component : components) {
            if (component instanceof JComponent)
                setAlignment((JComponent) component);
        }
    }


    public abstract Dimension getNonEmptyMaximumSize();
/*
    {
        int width = 0;
        int height = 0;
        Component[] components = getComponents();
        for (Component component : components) {
//            System.out.println("\t" + component.getMaximumSize().getWidth());
//            System.out.println(component);
            if (!(component instanceof HSpring))
            {
                if (component instanceof Box)
                    width += ((Box)component).getNonEmptyMaximumSize().getWidth();
                else
                    width += component.getMaximumSize().getWidth();
            }
            if (!(component instanceof VSpring))
            {
                if (component instanceof Box)
                    height += ((Box)component).getNonEmptyMaximumSize().getHeight();
                else
                    height += component.getMaximumSize().getHeight();
            }
//            System.out.println("");
        }
        if (width != Integer.MAX_VALUE)
                width += 2;
        if (height != Integer.MAX_VALUE)
                height += 2;
//        System.out.println(new Dimension(width, height));
        return new Dimension(width, height);
    }
*/

    public boolean hasVSpring()
    {
        Component[] components = getComponents();
        for (Component component : components) {
            if (component instanceof VSpring)
                return true;
            if (component instanceof VPanel)
            {
                Box box = (Box) component;
                if (box.hasVSpring())
                    return true;
            }
        }
        return false;
    }

    public boolean hasHSpring()
    {
        Component[] components = getComponents();
        for (Component component : components) {
            if (component instanceof HSpring)
                return true;
            if (component instanceof HPanel)
            {
                Box box = (Box) component;
                if (box.hasHSpring())
                    return true;
            }
        }
        return false;
    }



}
