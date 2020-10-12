package lesani.gui.menu;

import lesani.gui.frame.BaseJFrame;
import lesani.gui.widgets.ClosableTabbedPane;

import java.awt.*;
import javax.swing.*;


public class MenuLayoutDemo {
    JMenuBar menuBar;
    public JMenuBar createMenuBar()
    {
        menuBar = new JMenuBar();
        menuBar.setLayout(new BoxLayout(menuBar, BoxLayout.PAGE_AXIS));

        menuBar.add(createMenu("Menu 1"));
        menuBar.add(createMenu("Menu 2"));
        menuBar.add(createMenu("Menu 3"));

        menuBar.setBorder(BorderFactory.createMatteBorder(0,0,0,1,
                Color.BLACK));
        return menuBar;
    }

    // used by createMenuBar
    public JMenu createMenu(String title) {
        JMenu m = new HorizontalMenu(/*menuBar, */title);
        m.add("Menu item #1 in " + title);
        m.add("Menu item #2 in " + title);
        m.add("Menu item #3 in " + title);

        JMenu submenu = new HorizontalMenu(/*menuBar, */"Submenu");
        submenu.add("Submenu item #1");
        submenu.add("Submenu item #2");
        m.add(submenu);

        return m;
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {

        //Create and set up the window.
        BaseJFrame frame = new BaseJFrame("MenuLayoutDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        MenuLayoutDemo demo = new MenuLayoutDemo();
//        frame.setContentPane(new HPanel(demo.createMenuBar(), new HSpring()));
//        Container contentPane = frame.getContentPane();
//        contentPane.setBackground(Color.WHITE); //contrasting bg

        frame.setLeftMenu(demo.createMenuBar());
//        final JTabbedPane tabbedPane = new JTabbedPane();
        final JTabbedPane tabbedPane = new ClosableTabbedPane(JTabbedPane.LEFT);
        frame.setCenter(tabbedPane);
        tabbedPane.addTab("Tab 1", new Panel());
        tabbedPane.addTab("Tab 2", new Panel());

//        frame.setJMenuBar(demo.createMenuBar());

//		frame.setJMenuBar(demo.createMenuBar());

        //Display the window.
        frame.setSize(300, 150);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}

