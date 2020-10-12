package lesani.image.core.op.core.masking;

import lesani.image.core.image.GSImage;
import lesani.image.core.op.core.Abs;
import lesani.image.core.op.core.Scaler;
import lesani.image.core.op.core.base.UOp;
import lesani.image.util.ImageUtil;

/**
 * Created by IntelliJ IDEA.
 * User: mohsen
 * Date: Mar 1, 2010
 * Time: 11:01:28 PM
 */

public class EdgeDetector extends UOp<GSImage> {
    private Mask mask;

    public EdgeDetector(Mask mask) {
        this.mask = mask;
    }

    @Override
    public GSImage compute(GSImage image) {
        GSImage edgeImage =
            Masker.process(image, mask);
        return edgeImage;
    }

    public static GSImage process(GSImage image, Mask mask) {
        return new EdgeDetector(mask).compute(image);
    }

    public static class Parallel extends UOp<GSImage> {
        private Mask mask;

        public Parallel(Mask mask) {
            this.mask = mask;
        }

        @Override
        public GSImage compute(GSImage image) {
            Masker<GSImage> masker = new Masker<GSImage>(mask);
            masker.setParallel(true);
            return masker.compute(image);
        }

        public static GSImage process(GSImage image, Mask mask) {
            return new Parallel(mask).compute(image);
        }
    }

    // Unscaled (normal) ---------------------------------------
    /**
     * Created by IntelliJ IDEA.
     * User: mohsen
     * Date: Mar 1, 2010
     * Time: 11:01:28 PM
     */
    public static class Sobel {
        public static class RightDiagonal extends UOp<GSImage> {

            public GSImage compute(GSImage image) {
                return EdgeDetector.process(image, Mask.Sharpening.FirstDerivative.rightDiagonalSobelMask);
            }

            public static GSImage process(GSImage image) {
                return new RightDiagonal().compute(image);
            }

            // can change this to parprocess method.
            public static class Parallel extends UOp<GSImage> {

                public GSImage compute(GSImage image) {
                    return EdgeDetector.Parallel.process(image, Mask.Sharpening.FirstDerivative.rightDiagonalSobelMask);
                }

                public static GSImage process(GSImage image) {
                    return new Parallel().compute(image);
                }
            }
        }
    }

    // -----------------------------------------------------------

    public static class Scaled {

         public static class Sobel extends UOp<GSImage> {
            private Mask mask;

            public Sobel(Mask mask) {
                this.mask = mask;
            }

            @Override
            public GSImage compute(GSImage image) {
                GSImage edgeImage =
                    Scaler.instance().compute(
                    Abs.instance().compute(
                    Masker.process(image, mask)
                    ));
                return edgeImage;
            }

            public static GSImage process(GSImage image, Mask mask) {
                return new Sobel(mask).compute(image);
            }

            private static class Parallel extends UOp<GSImage>{
               private Mask mask;

               public Parallel(Mask mask) {
                   this.mask = mask;
               }

               @Override
               public GSImage compute(GSImage image) {
                   GSImage edgeImage =
                       Scaler.instance().parCompute(
                       Abs.instance().parCompute(
                       Masker.parProcess(image, mask)
                       ));
                   return edgeImage;
               }

               public static GSImage process(GSImage image, Mask mask) {
                   return new Parallel(mask).compute(image);
               }
            }

            /**
             * Created by IntelliJ IDEA.
             * User: mohsen
             * Date: Mar 1, 2010
             * Time: 11:01:28 PM
             */

            public static class Horizontal extends UOp<GSImage> {

                public GSImage compute(GSImage image) {
                    return Sobel.process(image, Mask.Sharpening.FirstDerivative.horizontalSobelMask);
                }

                public static GSImage process(GSImage image) {
                    return new Horizontal().compute(image);
                }
            }

            /**
             * Created by IntelliJ IDEA.
             * User: mohsen
             * Date: Mar 1, 2010
             * Time: 11:01:28 PM
             */
            public static class Vertical extends UOp<GSImage> {

                public GSImage compute(GSImage image) {
                    return Sobel.process(image, Mask.Sharpening.FirstDerivative.verticalSobelMask);
                }

                public static GSImage process(GSImage image) {
                    return new Vertical().compute(image);
                }
            }

            /**
             * Created by IntelliJ IDEA.
             * User: mohsen
             * Date: Mar 1, 2010
             * Time: 11:01:28 PM
             */
            public static class Right extends UOp<GSImage> {

                public GSImage compute(GSImage image) {
                    return Sobel.process(image, Mask.Sharpening.FirstDerivative.rightDiagonalSobelMask);
                }

                public static GSImage process(GSImage image) {
                    return new Right().compute(image);
                }
            }

            public static class Left extends UOp<GSImage> {

                public GSImage compute(GSImage image) {
                    return Sobel.process(image, Mask.Sharpening.FirstDerivative.rightDiagonalSobelMask);
                }

                public static GSImage process(GSImage image) {
                    return new Left().compute(image);
                }
            }

            /**
             * Created by IntelliJ IDEA.
             * User: mohsen
             * Date: Mar 1, 2010
             * Time: 11:01:28 PM
             */
            public static class LeftDiagonal extends UOp<GSImage> {

                public GSImage compute(GSImage image) {
                    return Sobel.process(image, Mask.Sharpening.FirstDerivative.leftDiagonalSobelMask);
                }

                public static GSImage process(GSImage image) {
                    return new LeftDiagonal().compute(image);
                }
            }

            public static class RightDiagonal extends UOp<GSImage> {

                public GSImage compute(GSImage image) {
                    return Sobel.process(image, Mask.Sharpening.FirstDerivative.rightDiagonalSobelMask);
                }

                public static GSImage process(GSImage image) {
                    return new RightDiagonal().compute(image);
                }

                public static class Parallel extends UOp<GSImage> {

                    public GSImage compute(GSImage image) {
                        return Sobel.Parallel.process(image, Mask.Sharpening.FirstDerivative.rightDiagonalSobelMask);
                    }

                    public static GSImage process(GSImage image) {
                        return new Parallel().compute(image);
                    }
                }
            }
            /**
             * Created by IntelliJ IDEA.
             * User: mohsen
             * Date: Mar 1, 2010
             * Time: 11:01:28 PM
             */

            public static class Full extends UOp<GSImage> {

                @Override
                public GSImage compute(GSImage image) {
                    GSImage verticalEdegImage =
                            Sobel.process(
                                    image,
                                    Mask.Sharpening.FirstDerivative.verticalSobelMask);
                    GSImage horizontalEdegImage =
                            Sobel.process(
                                    image,
                                    Mask.Sharpening.FirstDerivative.horizontalSobelMask);
                    GSImage rightDiagonalEdegImage =
                            Sobel.process(
                                    image,
                                    Mask.Sharpening.FirstDerivative.rightDiagonalSobelMask);
                    GSImage leftDiagonalEdegImage =
                            Sobel.process(
                                    image,
                                    Mask.Sharpening.FirstDerivative.leftDiagonalSobelMask);
                    GSImage[] edgeImages = {
                        verticalEdegImage,
                        horizontalEdegImage,
                        rightDiagonalEdegImage,
                        leftDiagonalEdegImage
                    };

                    final GSImage edgeImagesScaled = ImageUtil.getAddedAndScaled(edgeImages);
                    return edgeImagesScaled;
                }

                public static GSImage process(GSImage image) {
                    return new Full().compute(image);
                }
            }
        }
    }
}
