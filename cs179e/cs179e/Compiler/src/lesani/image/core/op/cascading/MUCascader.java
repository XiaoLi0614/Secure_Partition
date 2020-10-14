package lesani.image.core.op.cascading;

import lesani.image.core.image.Image;
import lesani.image.core.op.core.base.MOp;
import lesani.image.core.op.core.base.UOp;

/**
 * Created by IntelliJ IDEA.
 * User: mohsen
 * Date: Feb 27, 2010
 * Time: 4:24:33 PM
 */

public class MUCascader<T extends Image> extends MOp<T> {

    private MOp<T> mOperation;
    private UOp<T>[] operations;


    public MUCascader(MOp<T> mOperation, UOp<T>[] operations) {
        this.operations = operations;
        this.mOperation = mOperation;
    }

    public void setMOperation(MOp<T> mOperation) {
        this.mOperation = mOperation;
    }

    public T compute(T[] images) {
        T currentImage = mOperation.compute(images);
        for (UOp<T> operation : operations) {
            currentImage = operation.compute(currentImage);
        }
        return currentImage;
    }
}