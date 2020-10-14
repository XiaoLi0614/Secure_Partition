function mat = readIntMatrix(filePathName)

    fid = fopen(filePathName, 'r', 'b');

    n = fread(fid, 1, 'int', 'b');
    m = fread(fid, 1, 'int', 'b');

    mat = zeros(n, m, 'int32');

%    for i = 1:n
%        for j = 1:m
%            mat(i, j) = fread(fid, 1, 'int', 'b');
%        end
%    end

    v = fread(fid, n*m, 'int', 'b');
    mat = reshape(v, n, m);

    fclose(fid);
