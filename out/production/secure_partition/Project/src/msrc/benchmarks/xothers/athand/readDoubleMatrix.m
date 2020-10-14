function mat = readDoubleMatrix(filePathName)

    fid = fopen(filePathName, 'r', 'b');

    n = fread(fid, 1, 'int', 'b');
    m = fread(fid, 1, 'int', 'b');

    mat = zeros(n, m, 'double');

%    for j = 1:m
%        for i = 1:n
%            mat(i, j) = fread(fid, 1, 'double', 'b');
%        end
%    end

    v = fread(fid, n*m, 'double', 'b');
    mat = reshape(v, n, m);
    fclose(fid);
