function mat = readMatrix(filePathName)

    fid = fopen(filePathName, 'r', 'b');

    isInt = fread(fid, 1, 'int', 'b');

    n = fread(fid, 1, 'int', 'b');
    m = fread(fid, 1, 'int', 'b');

    if isInt == 1

        mat = zeros(n, m, 'int32');
        v = fread(fid, n*m, 'int', 'b');
        mat = reshape(v, n, m);

    else

        mat = zeros(n, m, 'double');

    %    for j = 1:m
    %        for i = 1:n
    %            mat(i, j) = fread(fid, 1, 'double', 'b');
    %        end
    %    end

        v = fread(fid, n*m, 'double', 'b');
        mat = reshape(v, n, m);
    end

    fclose(fid);

