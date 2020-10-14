package lesani.image.segmentation.deformable.serial;

import lesani.image.core.image.GSImage;
import lesani.image.core.op.core.Inverter;
import lesani.image.core.op.core.SizeScaler;
import lesani.image.core.op.core.masking.EdgeDetector;
import lesani.image.core.op.core.masking.Mask;
import lesani.image.core.op.core.masking.Masker;
import lesani.image.segmentation.deformable.serial.base.MultiStepMeshProcessor;
import lesani.image.segmentation.deformable.serial.mesh.Node;
import lesani.image.segmentation.deformable.serial.mesh.NodePointer;

/**
 * Created by IntelliJ IDEA.
 * User: lesani
 * Date: Mar 5, 2010
 * Time: 6:25:56 PM
 */


/*
Image
Edge
Invert = edges in black
Gaussian = spreading edges in black
Invert = spread of edges in white
compute gradient
*/


public class Segmenter extends MultiStepMeshProcessor {

    public static final int INIT_NODE_COUNT = 30;

    public static final int PYRAMID_LEVEL_COUNT = 3;
    public static final int SCALE_FACTOR = 2;
    private static final int FILTER_APPL_PER_LEVEL = 40;//20;
    
    public static final int MIN_EDGE_LENGTH = 6;
    public static final int MAX_EDGE_LENGTH = 10;

    public static final int POINT_RADIUS = 1;

    private GSImage[] pyramid;
    private int currentImageIndex;

    private LevelProcessor currentLevelProcessor;

//    enum Stage {DEFLATION, MIN_SEARCH}
//    private Stage stage = Stage.DEFLATION;

    //----------------------------------------------------

    public Segmenter() {
    }

    public Segmenter(GSImage image) {
        init(image);
    }

    public void setImage(GSImage image) {
        init(image);
    }

    private void init(GSImage inputImage) {
        buildPyramid(inputImage);
        this.image = pyramid[pyramid.length - 1];
        
        currentImageIndex = pyramid.length;

        setNextProcessor();
    }

    private void buildPyramid(GSImage inputImage) {
        
        GSImage edgeImage = EdgeDetector.Scaled.Sobel.Full.process(inputImage);
        GSImage invertedEdgeImage = Inverter.process(edgeImage);

        Masker<GSImage> masker = new Masker<GSImage>(Mask.Smoothing.gaussian3);

        pyramid = new GSImage[PYRAMID_LEVEL_COUNT];

        pyramid[0] = invertedEdgeImage;
        for (int i = 1; i < pyramid.length; i++) {
            pyramid[i] = SizeScaler.process(pyramid[i-1], SCALE_FACTOR);
        }
        //GSImage deflateImage = pyramid[pyramid.length - 1];
        for (int i = 0; i < pyramid.length; i++) {
            for (int j = 0; j < FILTER_APPL_PER_LEVEL; j++)
                pyramid[i] = masker.compute(pyramid[i]);
            pyramid[i] = Inverter.process(pyramid[i]);
        }
    }


    @Override
    public void step() {
//        System.out.println("Step");
        if (currentLevelProcessor.isFinished())
            setNextProcessor();
        currentLevelProcessor.step();
    }

    @Override
    public boolean isFinished() {
//        if (isLastProcessor() && (currentLevelProcessor.isFinished()))
//            System.out.println("IsFinished");
        return (isLastProcessor() && (currentLevelProcessor.isFinished()));
    }

    private void setNextProcessor() {
        currentImageIndex--;

        if (currentImageIndex == pyramid.length - 1)
            initMesh();
        else
            remesh();

        GSImage currentImage = pyramid[currentImageIndex];
        int minLength = Segmenter.MIN_EDGE_LENGTH;// / (int) (Math.pow(SCALE_FACTOR, currentImageIndex));
        if (minLength < 5)
            minLength = 5;
        int maxLength = Segmenter.MAX_EDGE_LENGTH;// / (int) (Math.pow(SCALE_FACTOR, currentImageIndex));
        if (maxLength < 6)
            minLength = 6;
        currentLevelProcessor = new LevelProcessor(
                currentImage,
                first,
                minLength,
                maxLength);
    }

    private void initMesh() {
        Node current = null;
        Node previous = null;
        Node first;
        first = null;
        int nodeCount = INIT_NODE_COUNT / (int) (Math.pow(SCALE_FACTOR, pyramid.length - 1));
        for (int k = 0; k < nodeCount; k++) {
            double angle = k* ((2*Math.PI) / nodeCount);
            final int width = image.getWidth();
            final int height = image.getHeight();
            int radius = Math.min(width/2, height/2) - POINT_RADIUS;
            int i = width/2 + (int)(radius*Math.cos(angle));
            int j = height/2 + (int)(radius*Math.sin(angle));

            current = new Node(i, j);
//            nodes.add(current);
            if (previous != null) {
                previous.setNext(current);
                current.setPrevious(previous);
            } else
                first = current;
            previous = current;
        }
        first.setPrevious(current);
        current.setNext(first);

        this.first = new NodePointer(first);
    }

    private void remesh() {
        Node node = first.node;
        do {
            node.setX(node.getX()*SCALE_FACTOR);
            node.setY(node.getY()*SCALE_FACTOR);
            node.metEdge = false;
            node = node.next;
        } while (node != first.node);
    }

    private boolean isLastProcessor() {
        return currentImageIndex == 0;
    }

    @Override
    public GSImage current() {
        return currentLevelProcessor.current();
    }
}


