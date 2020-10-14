function b = doubleEq(mat1, mat2)
    [n, m] = size(mat1);
    tresh = 0.000001;
    for i = 1:n
        for j = 1:m
            defer = mat1(i, j) - mat2(i, j);
            if (defer < 0)
                defer = -defer;
            end
            if (defer > tresh)
                b = false;
                return;
            end
        end
    end
    b = logical(1);



