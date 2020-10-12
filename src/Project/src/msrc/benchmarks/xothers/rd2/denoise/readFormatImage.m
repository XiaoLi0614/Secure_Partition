function image = readFormatImage(filePathName)

    fid = fopen(filePathName, 'r');

    m = fread(fid, 1, 'int16');
    n = fread(fid, 1, 'int16');

    image = zeros(n, m, 'uint8');

    for i = 1:m
        for j = 1:n
            image(j, i) = fread(fid, 1, 'uint8');
        end
    end

    fclose(fid);
