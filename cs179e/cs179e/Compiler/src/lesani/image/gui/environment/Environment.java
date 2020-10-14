package lesani.image.gui.environment;

import lesani.file.Address;
import lesani.image.core.image.GSImage;
import lesani.image.core.op.core.Inverter;
import lesani.image.core.op.core.masking.EdgeDetector;
import lesani.image.core.op.core.masking.Mask;
import lesani.image.core.op.core.masking.Masker;
import lesani.image.util.ImageFormatConverter;
import lesani.image.util.ImageUtil;
import lesani.image.util.exception.NotAnImageFileException;
import lesani.collection.option.None;
import lesani.collection.option.Option;
import lesani.collection.option.Some;
import lesani.file.Util;
import lesani.gui.frame.BaseJFrame;
import lesani.gui.layout.VPanel;
import lesani.gui.menu.HorizontalMenu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//import j.segmentation.deformable.serial.Segmenter;
import lesani.image.segmentation.deformable.parallel.Segmenter;
import lesani.gui.widgets.StatusBar;

/**
 * Created by IntelliJ IDEA.
 * User: mohsen
 * Date: Mar 4, 2010
 * Time: 8:05:38 PM
 */

public class Environment extends BaseJFrame {

    private JFileChooser fileChooser = new JFileChooser();
    private JMenuBar menuBar;
    private final ImageTabbedPane tabbedPane;
    private StatusBar statusBar;


    public Environment() {
        super("Image Processing Environment");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLeftMenu(createMenuBar());

        statusBar = new StatusBar();
        statusBar.set("Environment loaded.");
        tabbedPane = new ImageTabbedPane();
        VPanel vPanel = new VPanel();
        vPanel.add(tabbedPane);
        vPanel.add(statusBar);
//        setCenter(tabbedPane);
        setCenter(vPanel);

//        tabbedPane.addTab("Tab 1", new Panel());
//        tabbedPane.addTab("Tab 2", new Panel());

        init();
    }

    private void init() {

//        openFile(new File("/media/MOHSENHD/1.Works/3.Research/0.Research/1.CDSC/1.ImageProcessing/2.Projects/Project/Images/Registration/1.Original.jpg"));
//        openFile(new File("/media/MOHSENHD/1.Works/3.Research/0.Research/1.CDSC/1.ImageProcessing/2.Projects/Project/Images/Registration/2.RotatedLena.jpg"));
//        openFile(new File("/media/MOHSENHD/1.Works/3.Research/0.Research/1.CDSC/1.ImageProcessing/2.Projects/Project/Images/Registration/3.OriginalDotted.jpg"));
//        openFile(new File("/media/MOHSENHD/1.Works/3.Research/0.Research/1.CDSC/1.ImageProcessing/2.Projects/Project/Images/Registration/4.Mapped.jpg"));
//        openFile(new File("/media/MOHSENHD/1.Works/3.Research/0.Research/1.CDSC/1.ImageProcessing/2.Projects/Project/Images/Denoising/NoisyEye.jpg"));
//        openFile(new File("/media/MOHSENHD/1.Works/3.Research/0.Research/1.CDSC/1.ImageProcessing/2.Projects/Project/Images/Denoising/GrayGirl.jpg"));
//        openFile(new File("/media/MOHSENHD/1.Works/3.Research/0.Research/1.CDSC/1.ImageProcessing/2.Projects/Project/Images/Medical/Brain.jpg"));
//        openFile(new File("/media/MOHSENHD/1.Works/3.Research/0.Research/1.CDSC/1.ImageProcessing/2.Projects/Project/Images/Medical/TheSample3.jpeg"));
//        openFile(new File("/media/MOHSENHD/1.Works/3.Research/0.Research/1.CDSC/1.ImageProcessing/2.Projects/Project/Images/Medical/TheSample4.bmp"));
//        openFile(new File("/media/MOHSENHD/1.Works/3.Research/0.Research/1.CDSC/1.ImageProcessing/2.Projects/Project/Images/Medical/TheSample6.bmp"));
//        openFile(new File("/media/MOHSENHD/1.Works/3.Research/0.Research/1.CDSC/1.ImageProcessing/2.Projects/Project/Images/Medical/TheSample7.bmp"));
//        openFile(new File("/media/MOHSENHD/1.Works/3.Research/0.Research/1.CDSC/1.ImageProcessing/2.Projects/Project/Images/Medical/TheSample8.bmp"));
//        openFile(new File("/media/MOHSENHD/1.Works/3.Research/0.Research/1.CDSC/1.ImageProcessing/2.Projects/Project/Images/Medical/TheSample9.bmp"));
//        openFile(new File("/media/MOHSENHD/1.Works/3.Research/0.Research/1.CDSC/1.ImageProcessing/2.Projects/Project/Images/Medical/TheSample10.bmp"));
//        openFile(new File("F:\\1.Works\\3.Research\\0.Research\\1.CDSC\\1.ImageProcessing\\2.Projects\\Project\\Images\\Medical\\isolate_A.jpg"));
    }

    private JMenuBar createMenuBar() {
        menuBar = new JMenuBar();
        menuBar.setLayout(new BoxLayout(menuBar, BoxLayout.PAGE_AXIS));

        JMenu fileMenu = createFileMenu();
        fileMenu.setAlignmentX(JMenu.LEFT_ALIGNMENT);
        menuBar.add(fileMenu);

        JMenu baseOpMenu = createBaseOpMenu();
        baseOpMenu.setAlignmentX(JMenu.LEFT_ALIGNMENT);
        menuBar.add(baseOpMenu);

        JMenu segmentMenu = createSegmentMenu();
        segmentMenu.setAlignmentX(JMenu.LEFT_ALIGNMENT);
        menuBar.add(segmentMenu);


//        JMenu c2 = createMenu("Menu 2");
//        c2.setAlignmentX(JMenu.LEFT_ALIGNMENT);
//        menuBar.add(c2);
//
//        JMenu c3 = createMenu("Menu 2");
//        c3.setAlignmentX(JMenu.LEFT_ALIGNMENT);
//        menuBar.add(c3);

/*
        JComponent c1 = createFileMenu();
        JComponent c2 = createMenu("Menu 2");
        JComponent c3 = createMenu("Menu 3");

        VPanel container = new VPanel(c1, c2, c3);
        menuBar.add(container);
*/

        menuBar.setBorder(BorderFactory.createMatteBorder(0,0,0,1, Color.GRAY));
        return menuBar;
    }
//---------------------------------------------
    private JMenu createFileMenu() {
        JMenu menu = new HorizontalMenu(/*menuBar, */"File");
        JMenuItem openMenuItem = new JMenuItem("Open ...");
        openMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int returnVal = fileChooser.showOpenDialog(Environment.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    final File selectedFile = fileChooser.getSelectedFile();
                    openFile(selectedFile);
                }
            }
        });
        menu.add(openMenuItem);

        JMenuItem saveAsMenuItem = new JMenuItem("Save as ...");
        final ActionListener saveAsAction = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int returnVal = fileChooser.showSaveDialog(Environment.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    ImagePanel imagePanel = tabbedPane.getImagePanel();
                    BufferedImage image = imagePanel.getImage();
                    saveFile(image, file);
                }
            }
        };
        saveAsMenuItem.addActionListener(saveAsAction);

        JMenuItem saveMenuItem = new JMenuItem("Save");
        saveMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ImagePanel imagePanel = tabbedPane.getImagePanel();
                if (imagePanel.getFile() == None.instance())
                    saveAsAction.actionPerformed(e);
                else {
                    BufferedImage image = imagePanel.getImage();
                    File file = ((Some<File>)imagePanel.getFile()).get();
                    saveFile(image, file);
                }
            }
        });

        menu.add(saveMenuItem);
        menu.add(saveAsMenuItem);

        JMenuItem openFromFormat = new JMenuItem("Open from format");
        openFromFormat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int returnVal = fileChooser.showOpenDialog(Environment.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    final File file = fileChooser.getSelectedFile();
                    launchOutOfSwingThread(new Runnable() {

                        public void run() {
                            try {
                                statusBar.set("Opening file ..." + " (" + file.getPath() + ")");
                                GSImage image =
                                        ImageFormatConverter.fromFormat(file.getPath());
                                BufferedImage bufferedImage = image.getBufferedImage();
                                tabbedPane.addTab(Address.getNameNExtension(file), bufferedImage, file);
                                statusBar.set("File opened." + " (" + file.getPath() + ")");
                            } catch (IOException e1) {

                            }
                        }
                    });
                }
            }
        });
        menu.add(openFromFormat);

        JMenuItem saveToFormat = new JMenuItem("Save to format");
        saveToFormat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                ImagePanel imagePanel = tabbedPane.getImagePanel();
                final File file;
                if (imagePanel.getFile() == None.instance()) {
                    int returnVal = fileChooser.showSaveDialog(Environment.this);
                    if (returnVal != JFileChooser.APPROVE_OPTION)
                        return;
                    file = fileChooser.getSelectedFile();
                } else
                    file = ((Some<File>)imagePanel.getFile()).get();
                launchOutOfSwingThread(new Runnable() {

                    public void run() {

                        try {
                            statusBar.set("Saving file ..." + " (" + file.getPath() + ")");
                            ImageFormatConverter.toFormat(file.getPath(), "format");
                            statusBar.set("File saved." + " (" + file.getPath() + ")");
                        } catch (NotAnImageFileException e1) {
                            statusBar.set("File could not be read.");
                        } catch (IOException e1) {
                            statusBar.set("File io error.");
                        }
                    }
                });
            }
        });
        menu.add(saveToFormat);

        return menu;
    }

    private void openFile(final File file) {
        launchOutOfSwingThread(new Runnable() {
            public void run() {
                String filePathName = file.getPath();
                String name = file.getName();
                try {
                    BufferedImage image = ImageUtil.getImage(filePathName);

//            GSImage gsImage = new GSImage(image);
//            gsImage.print();

                    //ImageUtil.showImage("Image", image);
                    tabbedPane.addTab(name, image, file);

                } catch (NotAnImageFileException ioe) {
                    JOptionPane.showMessageDialog(Environment.this,
                            "The file is not of a recognizable format.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void saveFile(final BufferedImage image, final File file) {
        launchOutOfSwingThread(new Runnable() {
            public void run() {
                try {
                    ImageIO.write(image, "BMP", file);
                } catch (IOException ioe) {
                    JOptionPane.showMessageDialog(Environment.this,
                            "The file could not be saved.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }



//---------------------------------------------

    private JMenu createBaseOpMenu() {
        JMenu menu = new HorizontalMenu(/*menuBar, */"BaseOp");

        HorizontalMenu basicMenu = createBasicMenu();
        menu.add(basicMenu);

        HorizontalMenu maskingMenu = createMaskingMenu();
        menu.add(maskingMenu);

        return menu;
    }

    private HorizontalMenu createBasicMenu() {
        HorizontalMenu menu = new HorizontalMenu(/*menuBar, */"Basic");
        JMenuItem invertMenuItem = new JMenuItem("Invert");
        invertMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ImagePanel imagePanel = tabbedPane.getImagePanel();
                BufferedImage bufferedImage = imagePanel.getImage();
                final GSImage image = new GSImage(bufferedImage);
                GSImage invertedImage = Inverter.process(image);
                tabbedPane.addTab("Inverted", invertedImage.getBufferedImage());
            }
        });
        menu.add(invertMenuItem);

        return menu;
    }

    private HorizontalMenu createMaskingMenu() {
        HorizontalMenu menu = new HorizontalMenu("Masking");

        HorizontalMenu smoothingMenu = createSmoothingMenu();
        menu.add(smoothingMenu);

        HorizontalMenu sharpeningMenu = createSharpeningMenu();
        menu.add(sharpeningMenu);

        return menu;
    }

    private HorizontalMenu createSmoothingMenu() {
        HorizontalMenu menu = new HorizontalMenu("Smoothing");

        JMenuItem gaussian3 = new JMenuItem("Gaussian 3");
        gaussian3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                applyMask(Mask.Smoothing.gaussian3, "Gaussian 3");
            }
        });
        menu.add(gaussian3);
        
        JMenuItem multistepGaussian3 = new JMenuItem("Continuous Gaussian 3");
        multistepGaussian3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                multistepProcessCurrentImage(
                        new DefiniteStepCountProcessor(
                                new ImageProcessor() {
                                    public GSImage process(GSImage image) {
                                        return Masker.process(image, Mask.Smoothing.gaussian3);
                                    }
                                },
                                100
                        ),
                        "Continuous Gaussian 3"
                        );
            }
        });
        menu.add(multistepGaussian3);

        JMenuItem gaussian5 = new JMenuItem("Gaussian 5");
        gaussian5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                applyMask(Mask.Smoothing.gaussian5, "Gaussian 5");
            }
        });
        menu.add(gaussian5);


        return menu;
    }

    private HorizontalMenu createSharpeningMenu() {
        HorizontalMenu menu = new HorizontalMenu("Sharpening");

        JMenuItem horizontalMenu = new JMenuItem("Sobel (Horizontal)");
        horizontalMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                processCurrentImage("Sobel (Horizontal)",
                        new ImageProcessor() {
                        public GSImage process(GSImage image) {
                            return EdgeDetector.Scaled.Sobel.Horizontal.process(image);
                        }
                    }
                );
            }
        });
        menu.add(horizontalMenu);

        JMenuItem verticalSobelMenu = new JMenuItem("Sobel (Vertical)");
        verticalSobelMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                processCurrentImage("Sobel (Vertical)",
                        new ImageProcessor() {
                        public GSImage process(GSImage image) {
                            return EdgeDetector.Scaled.Sobel.Vertical.process(image);
                        }
                    }
                );
            }
        });
        menu.add(verticalSobelMenu);
        JMenuItem leftDiagonalSobelMenu = new JMenuItem("Sobel (Left Diagonal)");
        leftDiagonalSobelMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                processCurrentImage("Sobel (Left Diagonal)",
                        new ImageProcessor() {
                        public GSImage process(GSImage image) {
                            return EdgeDetector.Scaled.Sobel.LeftDiagonal.process(image);
                        }
                    }
                );
            }
        });
        menu.add(leftDiagonalSobelMenu);
        JMenuItem rightDiagonalSobelMenu = new JMenuItem("Sobel (Right Diagonal)");
        rightDiagonalSobelMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                processCurrentImage("Sobel (Right Diagonal)",
                        new ImageProcessor() {
                        public GSImage process(GSImage image) {
                            return EdgeDetector.Scaled.Sobel.RightDiagonal.process(image);
                        }
                    }
                );
            }
        });
        menu.add(rightDiagonalSobelMenu);

        JMenuItem sobelAllMenu = new JMenuItem("Sobel (All)");
        sobelAllMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                processCurrentImage("Sobel (All)",
                        new ImageProcessor() {
                        public GSImage process(GSImage image) {
                            return EdgeDetector.Scaled.Sobel.Full.process(image);
                        }
                    }
                );
            }
        });
        menu.add(sobelAllMenu);

        return menu;
    }

//---------------------------------------------

    private JMenu createSegmentMenu() {
        JMenu menu = new HorizontalMenu(/*menuBar, */"Segmentation");
        JMenuItem deformableMenuItem = new JMenuItem("Deformable");
        deformableMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Segmenter segmenter = new Segmenter();
                multistepProcessCurrentImage(segmenter, "Deformable");
            }
        });
        menu.add(deformableMenuItem);
        return menu;
    }

//---------------------------------------------
    ExecutorService executor = Executors.newSingleThreadExecutor();

    private void launchOutOfSwingThread(Runnable runnable) {
//        new Thread(runnable).start();
        executor.execute(runnable);
    }
    private void processCurrentImage(final String tabTitle, final ImageProcessor processor) {

        ImagePanel imagePanel = tabbedPane.getImagePanel();

        final BufferedImage bufferedImage = imagePanel.getImage();
        final Option<File> file = imagePanel.getFile();

        // before this is run in swing thread.
        // But the computation is done in a new thread.
        launchOutOfSwingThread(new Runnable() {
            public void run() {
                GSImage image = new GSImage(bufferedImage);
                GSImage result = processor.process(image);
                final BufferedImage resultBufferedImage = result.getBufferedImage();
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        tabbedPane.addTab(tabTitle, resultBufferedImage);
                    }
                });
            }
        });
    }

    private void multistepProcessCurrentImage(
            final MultiStepProcessor processor,
            final String tabTitle) {

        final int STEP_PER_INTERVAL = 5;
        final double INTERVAL_IN_SECS = 1;
//        final double INTERVAL_IN_SECS = 0;

        ImagePanel imagePanel = tabbedPane.getImagePanel();
        final BufferedImage bufferedImage = imagePanel.getImage();

        tabbedPane.addTab(tabTitle, bufferedImage);
        final ImagePanel ProcessImagePanel = tabbedPane.getImagePanel();


        launchOutOfSwingThread(new Runnable() {
            public void run() {
                GSImage image = new GSImage(bufferedImage);
                processor.setImage(image);
                while (!processor.isFinished()) {
//                    System.out.println("Thread, inside loop");
                    long t1 = System.currentTimeMillis();
                    for (int i = 0; i < STEP_PER_INTERVAL; i++) {
                        processor.step();
                        if (processor.isFinished())
                            break;
                    }
                    long t2 = System.currentTimeMillis();
                    long passedMillies = t2 - t1;
                    long sleepMillies = (long)(1000 * INTERVAL_IN_SECS) - passedMillies;
                    if (sleepMillies > 0)
                        try {
                            Thread.sleep(sleepMillies);
                        } catch (InterruptedException e1) {}

                    GSImage current = processor.current();
                    ProcessImagePanel.setImage(current.getBufferedImage());
                }
                System.out.println("Process finished.");
            }
        });
    }

    private void applyMask(final Mask mask, String tabTitle) {
        processCurrentImage(tabTitle, new ImageProcessor() {
                public GSImage process(GSImage image) {
                    return Masker.process(image, mask);
                }
            }
        );
    }












    public void showImage(String title, BufferedImage image, File file) {
        tabbedPane.addTab(title, image, file);
    }
    public void showImage(String title, BufferedImage image) {
        tabbedPane.addTab(title, image);    
    }
    public void showImage(BufferedImage image) {
        tabbedPane.addTab("", image);    
    }

//---------------------------------------------
    // used by createMenuBar
    private JMenu createMenu(String title) {
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

//-------------------------------------------------------------------------

    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Environment frame = new Environment();

                frame.setSize(1000, 600);
                frame.setLocation(200, 50);
                frame.setVisible(true);
            }
        });
    }


}

