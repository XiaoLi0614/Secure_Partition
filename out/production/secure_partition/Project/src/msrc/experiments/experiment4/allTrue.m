function b = allTrue(mat)
    [n, m] = size(mat);
    b = logical(1);
    for i = 1:n
        for j = 1:m
            b = (b && (mat(i, j)));
%            b = (b && (mat(1, 1)))
        end
    end


