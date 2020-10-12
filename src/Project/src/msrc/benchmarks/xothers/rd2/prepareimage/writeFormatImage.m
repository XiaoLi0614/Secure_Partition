
function writeFormatImage(image, filePathName)
    [n, m] = size(image);

%    intImage = scaleImage(image)
    intImage = zeros([n, m], 'uint8');
    maxV = 0;
    for i = 1:n
        for j = 1:m
            v = image(i, j);
            if (v > maxV)
                maxV = v;
            end
        end
    end
    for i = 1:m
        for j = 1:n
            intImage(j, i) = cast((image(j, i) / maxV) * 255, 'uint8');
        end
    end

    fid = fopen(filePathName, 'w');

    fwrite(fid, m, 'int16');
    fwrite(fid, n, 'int16');

    for i = 1:m
        for j = 1:n
            fwrite(fid, intImage(j, i), 'uint8');
        end
    end
    fclose(fid);