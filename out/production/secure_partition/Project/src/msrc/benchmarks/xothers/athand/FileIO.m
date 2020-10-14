
load('rays30.mat','rays');
load('sino30.mat','sino');

fid = fopen('rays.format', 'w');

[n, m] = size(rays);

fprintf(fid, '%d', n);
fprintf(fid, '%d', m);

%rays=full(rays);

for i=1:n
    for j=1:m
        if (rays(i,j) > 0)
            r = full(rays(i,j));
            fprintf(fid, '%d', i);
            fprintf(fid, '%d', j);
            fprintf(fid, '%f', r);
        end
    end
end

fclose(fid);



fid = fopen('sino.format', 'w');

[n, m] = size(sino);

fprintf(fid, '%d', n);
fprintf(fid, '%d', m);


for i=1:n
    for j=1:m
        fprintf(fid, '%f', sino(i,j));
    end
end


fclose(fid);


%{
public static def writeImageToFormat(
    image: IntMatrix,
    filePathName: String)
        /*throws IOException*/ {

    val output = new File(filePathName);
    val writer = output.openWrite();

    val width = image.n;
    val height = image.m;

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
            val value = image(i, j);
            val bValue = value as Byte;
            writer.write(bValue);
        }
    }
    writer.close();
}

public static def readImageFromFormat(filePathName: String): IntMatrix {
    val input = new File(filePathName);
    val reader = input.openRead();

    val lWidth = reader.read();
    val hWidth = reader.read();
    val lHeight = reader.read();
    val hHeight = reader.read();

    val width = buildInt(lWidth, hWidth);
    val height = buildInt(lHeight, hHeight);


    val region = (0..(width-1)) * (0..(height-1));
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
    return new IntMatrix(data, width, height);
}


private static def high(i: Int): Byte {
    return (i / 256) as Byte;
}
private static def low(i: Int): Byte {
    return (i % 256) as Byte;
}

private static def buildInt(low: Byte, high: Byte): Int {
    val uLow = toUnsigned(low);
    val uHigh = toUnsigned(high);
    return uHigh*256 + uLow;
}

private static def toUnsigned(theByte: Byte): Int {
    var intValue: Int = theByte;
    if (intValue < 0) {
        intValue = ((theByte & 0x7F) as Int) + 128;
    }
    return intValue;
}
%}
