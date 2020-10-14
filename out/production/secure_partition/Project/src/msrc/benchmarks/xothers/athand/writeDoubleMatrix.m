function writeDoubleMatrix(mat, filePathName)
    [n, m] = size(mat);

    fid = fopen(filePathName, 'w', 'b');

    fwrite(fid, n, 'int', 'b');
    fwrite(fid, m, 'int', 'b');

%    for i = 1:n
%        for j = 1:m
%            fwrite(fid, mat(i, j), 'double', 'b');
%        end
%    end
    v = reshape(mat, n*m, 1);
    fwrite(fid, v, 'double', 'b');

    fclose(fid);
