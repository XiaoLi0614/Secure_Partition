package lesani.image.segmentation.deformable.parallel;

import lesani.image.core.image.GSImage;
import lesani.image.core.op.core.Inverter;
import lesani.image.core.op.core.SizeScaler;
import lesani.image.core.op.core.masking.EdgeDetector;
import lesani.image.core.op.core.masking.Mask;
import lesani.image.core.op.core.masking.Masker;
import lesani.image.segmentation.deformable.parallel.base.MultiStepMeshProcessor;
import lesani.image.segmentation.deformable.parallel.mesh.DistModel;
import lesani.image.segmentation.deformable.parallel.mesh.ModelPart;
import lesani.image.segmentation.deformable.parallel.mesh.Node;
import lesani.image.segmentation.deformable.parallel.mesh.Place;

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

    public static final int PYRAMID_LEVEL_COUNT = 2;//3;
    public static final int SCALE_FACTOR = 2;
    private static final int FILTER_APPL_PER_LEVEL = 20;
    
    public static final int MIN_EDGE_LENGTH = 6;
    public static final int MAX_EDGE_LENGTH = 10;

    public static final int POINT_RADIUS = 1;

    private Place[] places;

    private GSImage[] pyramid;
    private int currentImageIndex;

    private int splitCount = 4;
    private DistModel model;
    private LevelProcessor currentLevelProcessor;

//    enum Stage {DEFLATION, MIN_SEARCH}
//    private Stage stage = Stage.DEFLATION;

    //----------------------------------------------------

    public Segmenter() {
    }


    public Segmenter(int splitCount) {
        this.splitCount = splitCount;
    }

    public Segmenter(GSImage image, int splitCount) {
        init(image);
        this.splitCount = splitCount;
    }

    public void setImage(GSImage image) {
        init(image);
    }

    private void init(GSImage inputImage) {
        places = new Place[splitCount];
        for (int i = 0; i < places.length; i++) {
            places[i] = new Place();
        }

        buildPyramid(inputImage);
        this.image = pyramid[pyramid.length - 1];
        
        currentImageIndex = pyramid.length;
        setNextProcessor();

    }

    private void buildPyramid(GSImage inputImage) {
        
        GSImage edgeImage = EdgeDetector.Scaled.Sobel.Full.process(inputImage);
        GSImage invertedEdgeImage = Inverter.process(edgeImage);

        // ParallelMaker in the package j.core.parallel
        // It can be used here.
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
        final boolean finished = isLastProcessor() && (currentLevelProcessor.isFinished());
        if (finished)
            for (Place place : places) place.finish();
        return finished;
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
                model,
                minLength,
                maxLength);
    }

    private void initMesh() {

        final int width = image.getWidth();
        final int height = image.getHeight();

        int centerX = width/2;
        int centerY = height/2;

        int radius = Math.min(width/2, height/2) - POINT_RADIUS;

        int nodeCount = INIT_NODE_COUNT;// / (int) (Math.pow(SCALE_FACTOR, pyramid.length - 1));

        model = createDistCircle(centerX, centerY, radius, nodeCount, places);

        this.first = model.part(0).getFirst();

    }

    private DistModel createDistCircle(
            int centerX,
            int centerY,
            int radius,
            int nodeCount,
            Place[] places) {

        int splitCount = places.length;
        ModelPart[] modelParts = new ModelPart[splitCount];
        for (int i = 0; i < modelParts.length; i++)
            modelParts[i] = new ModelPart(places[i]);


        Node current = null;
        Node previous = null;
        Node first = null;

        int nodePerProcessor = nodeCount / splitCount;
        int currentProcessorIndex = 0;
        int currentProcessorNodeCount = 0;

        for (int k = 0; k < nodeCount; k++) {
            double angle = k* ((2*Math.PI) / nodeCount);

            int i = centerX + (int)(radius*Math.cos(angle));
            int j = centerY + (int)(radius*Math.sin(angle));

            current = new Node(i, j);
            currentProcessorNodeCount++;
            if (currentProcessorNodeCount == 1) {
                if (currentProcessorIndex < modelParts.length)
                    modelParts[currentProcessorIndex].setFirst(current);
            }
            if (currentProcessorNodeCount == nodePerProcessor) {
                modelParts[currentProcessorIndex].setLast(current);
                currentProcessorIndex++;
                currentProcessorNodeCount = 0;
            }

            if (previous != null) {
                previous.setNext(current);
                current.setPrevious(previous);
            } else
                first = current;

            previous = current;
        }
        first.setPrevious(current);
        current.setNext(first);
        // current is the last node now.
        modelParts[modelParts.length - 1].setLast(current);

        return new DistModel(modelParts);
    }

    private void remesh() {
        Node node = first.node;
        do {
            node.setX(node.getX()*SCALE_FACTOR);
            node.setY(node.getY()*SCALE_FACTOR);
            node.metEdge = false;
            node = node.getNext();
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


