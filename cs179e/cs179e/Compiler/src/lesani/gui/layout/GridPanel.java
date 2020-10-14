package lesani.gui.layout;

import javax.swing.Box;
import javax.swing.*;
import java.awt.*;


public class GridPanel extends VPanel
{
	public GridPanel() {}
	
    public GridPanel(JComponent[][] componentsGrid)
    {
		setComponents(componentsGrid);
	}

	public void setComponents(JComponent[][] componentsGrid) {
//        setLayout(new GridLayout(componentsGrid.length, componentsGrid[0].length));
        int[] widths = new int[componentsGrid[0].length];
        int[] heights = new int[componentsGrid.length];

        for (int i = 0; i < widths.length; i++)
            widths[i] = Integer.MIN_VALUE;

        for (int i = 0; i < heights.length; i++)
            heights[i] = Integer.MIN_VALUE;

        for (int i = 0; i < componentsGrid.length; i++) {
            JComponent[] components = componentsGrid[i];
            for (int j = 0; j < components.length; j++) {
                JComponent component = components[j];
                Dimension prefferedSize = component.getPreferredSize();
                if (prefferedSize.getWidth() > widths[j])
                    widths[j] = (int)prefferedSize.getWidth();
                if (prefferedSize.getHeight() > heights[i])
                    heights[i] = (int)prefferedSize.getHeight();
            }
        }

/*
        System.out.println("-----------------");
        for (int i = 0; i < heights.length; i++) {
            for (int j = 0; j < widths.length; j++) {
                Dimension dimension = new Dimension(widths[j], heights[i]);
                componentsGrid[i][j].setPreferredSize(dimension);
                System.out.println(componentsGrid[i][j].getPreferredSize());
            }
            System.out.println("");
        }
*/


        for (int i = 0; i < componentsGrid.length; i++) {
            JComponent[] components = componentsGrid[i];
            HPanel hPanel = new HPanel();
            hPanel.setAlignment(CENTER);
            for (int j = 0; j < components.length; j++) {
                JComponent component = components[j];
//                System.out.println(component);
                hPanel.add(component);
                Dimension preferredSize = component.getPreferredSize();
                if (!(component instanceof HPanel) || !(((HPanel)component).hasHSpring()))
                {
                    int width = (int) (widths[j] - preferredSize.getWidth());
//                    System.out.print(width + "\t");
                    Component c = Box.createRigidArea(new Dimension(width, 0));
                    hPanel.add(c);
                }
/*
                if (!(component instanceof VPanel) || !(((VPanel)component).hasVSpring()))
                {
                    int height = (int) (heights[i]);// - preferredSize.getHeight());
                    System.out.print(height);
                    Component c = Box.createRigidArea(new Dimension(0, height));
                    hPanel.add(c);
                }
*/
//                System.out.println("");
            }
            add(hPanel);
        }
/*
    }

    public Dimension getMaximumSize() {
*/
/*
        Component[] components = getComponents();
        int maxWidth = Integer.MIN_VALUE;
        int heightSum = 0;

        for (Component component : components) {
            Dimension d;
            if (component instanceof lesani.gui.layout.Box)
            {
                lesani.gui.layout.Box box = (lesani.gui.layout.Box)component;
                d = box.getNonEmptyMaximumSize();
//                System.out.print("*");
            }
            else
                d = component.getMaximumSize();
            int width = (int)d.getWidth();
//            System.out.println("W: " + width);
            int height = (int)d.getHeight();

            if (width > maxWidth)
                maxWidth = width;
            heightSum += height;
        }

//        System.out.println("MaxWidth: " + maxWidth);

        setMaximumSize(new Dimension(maxWidth, heightSum));
//        System.out.println("Set: " + getMaximumSize().getWidth());
*/

    }
}



