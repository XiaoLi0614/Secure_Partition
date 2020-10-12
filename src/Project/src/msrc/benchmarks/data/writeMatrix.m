function writeMatrix(mat, filePathName)
    [n, m] = size(mat);

    fid = fopen(filePathName, 'w', 'b');

    isInt = logical(1);
    for i = 1:n
        for j = 1:m
            if (floor(mat(i, j)) ~= mat(i,j))
                isInt = logical(0);

            end
        end
    end

    if isInt

    %    for i = 1:n
    %        for j = 1:m
    %            fwrite(fid, mat(i, j), 'double', 'b');
    %        end
    %    end

        fwrite(fid, 1, 'int', 'b');

        fwrite(fid, n, 'int', 'b');
        fwrite(fid, m, 'int', 'b');


        v = reshape(mat, n*m, 1);
        fwrite(fid, v, 'int', 'b');

    else

        fwrite(fid, 2, 'int', 'b');

        fwrite(fid, n, 'int', 'b');
        fwrite(fid, m, 'int', 'b');

        v = reshape(mat, n*m, 1);
        fwrite(fid, v, 'double', 'b');

    end


    fclose(fid);
