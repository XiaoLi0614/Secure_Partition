function result = addRicianNoise(nu, sigma)
%   addRicianNoise
%   Random arrays from Rician distribution.
%   r = ricianNoise(nu, sigma) returns random numbers from the Rician
%   distribution with parameters nu and sigma.  If nu is an array, then result
%   is an array of the same shape.
%   Pascal Getreuer 2009
%   Edited: Mohsen Lesani

    result = nu;
    r = result(:)' / sqrt(2);
    twoRowR = r([1,1], :);
    twoRowRandom = randn(2, length(r));
    rp = (twoRowR + twoRowRandom * sigma) .^ 2;
    rpSum = sum(rp);
    result(:) = sqrt(rpSum);


% matrix(:) make a column vector from the columns of matrix one after the other one.
% (r([1,1],:) duplicates it into two rows
% randn(2, length(r)) gives a random matrix of 2 rows and length(r) columns.
% sum(r) summation for each column
% The result is a matrix:
% "matirx(:) = vector" Can see the matrix as a vector and do the assignment.




%{
    nu = [1.0 2.0 3.0;
          4.0 5.0 6.0];
    result = nu;
    disp(nu);
    r = result(:)' / sqrt(2);
disp(r);
    twoRowR = r([1,1], :);
disp(twoRowR);
%    twoRowRandom = randn(2, length(r));
    twoRowRandom = [
        -1.4410,   -0.3999,    0.8156,    1.2902,    1.1908,   -0.0198;
        0.5711,    0.6900,    0.7119,    0.6686,   -1.2025,   -0.1567];
disp(twoRowRandom);
    rp = (twoRowR + twoRowRandom * sigma) .^ 2;
disp(rp);
    rpSum = sum(rp);
disp(rpSum);
    result(:) = sqrt(rpSum);
disp(result);
%}


