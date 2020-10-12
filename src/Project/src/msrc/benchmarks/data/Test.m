function Test()

    mat1 = [1.2 2.2 3.2;
            4.2 5.2 6.2];
%    writeDoubleMatrix(mat1, 'Test.format');
%    mat2 = readDoubleMatrix('Test.format');
    writeMatrix(mat1, 'DoubleTest.format');
    mat2 = readMatrix('DoubleTest.format');

    for i=1:2
        for j=1:3
            if (mat1(i,j) ~= mat2(i,j))
                disp('Error');
                disp(mat1);
                disp(mat2);
                return;
            end
        end
    end
    disp('Passed.');

    mat3 = [1 2 3;
            4 5 6];
%    writeIntMatrix(mat3, 'Test.format');
%    mat4 = readIntMatrix('Test.format');

    writeMatrix(mat3, 'IntTest.format');
    mat4 = readMatrix('IntTest.format');

    for i=1:2
        for j=1:3
            if (mat3(i,j) ~= mat4(i,j))
                disp('Error');
                disp(mat3);
                disp(mat4);
                return;
            end
        end
    end
    disp('Passed.');
