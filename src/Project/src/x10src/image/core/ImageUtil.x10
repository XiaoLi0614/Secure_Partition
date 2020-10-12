import x10.io.File;
import x10.io.IOException;


public class ImageUtil {

    public static def readFile(filePathName: String): GrayScaleImage/*{self.home==here} throws IOException*/ {
        return readFile(filePathName, here); 
    }

    public static def readFile(filePathName: String, place: Place): GrayScaleImage/*{self.home==place} throws IOException*/ {
//        return runFunAtLocations(() => {
                val input = new File(filePathName);
                val reader = input.openRead();

                val lWidth = reader.read();
                val hWidth = reader.read();
                val lHeight = reader.read();
                val hHeight = reader.read();

                val width = buildInt(lWidth, hWidth);
                val height = buildInt(lHeight, hHeight);


//                val region = Region.make([0..(width-1), 0..(height-1)]);
                val region = (0..(width-1)) * (0..(height-1));
//                val data = Array.make[Int](region);
//                for (var i: Int = 0; i < width; i++) {
//                    for (var j: Int = 0; j < height; j++) {
//                        val bValue = reader.read();
//                        val value = toUnsigned(bValue);
//                        data(i, j) = value
//                    }
//                }

                val data = new Array[Int](
                    region, (
                        (p:Point(region.rank)) => {
                            val i = p(0);
                            val j = p(1);
                            val bValue = reader.read();
                            val value = toUnsigned(bValue);
                            return value as Int;
                        }
                    )
                );

                reader.close();
                return new GrayScaleImage(data);
//            },
//            place);


/*
        val input = new File(filePathName);
        val reader = input.openRead();

        val width = reader.read();
        val height = reader.read();
        val region = Region.make([0..(width-1), 0..(height-1)]);

        val data = at(place)
            Array.make[Byte](region);

        async(place) {
            for (var i: Int = 0; i < width; i++)
                for (var j: Int = 0; j < height; j++)
                    data(i, j) = reader.read();
            reader.close();
        }
        return at(place) new Image(data);
*/
    }

    public static def writeFile(
        image: GrayScaleImage/*{self.home==here}*/,
        filePathName: String)
            /*throws IOException*/ {

        val output = new File(filePathName);
        val writer = output.openWrite();

        val width = image.width();
        val height = image.height();

        val lWidth = low(width);
        val hWidth = high(width);
        val lHeight = low(height);
        val hHeight = high(height);
        // We use little endian.
        writer.write(lWidth);
        writer.write(hWidth);
        writer.write(lHeight);
        writer.write(hHeight);

        for (var i: Int = 0; i < width; i++) {
            for (var j: Int = 0; j < height; j++) {
                val value = image.get(i, j);
                val bValue = value as Byte;
                writer.write(bValue);
            }
        }
        writer.close();
    }

    // It is assumed that the function returns an object that is located in that location!
//    public static def runFunAtLocations[T](f: () => T, place: Place): T{self.home==place} {
//        return at(place)
//            f()
//            as T{self.home==place};
//    }

    public static def high(i: Int): Byte {
        return (i / 256) as Byte;
    }
    public static def low(i: Int): Byte {
        return (i % 256) as Byte;
    }

    public static def buildInt(low: Byte, high: Byte): Int {
        val uLow = toUnsigned(low);
        val uHigh = toUnsigned(high);
        return uHigh*256 + uLow;
    }

    public static def toUnsigned(theByte: Byte): Int {
        var intValue: Int = theByte;
        if (intValue < 0) {
            intValue = ((theByte & 0x7F) as Int) + 128;
        }
        return intValue;
    }
}

