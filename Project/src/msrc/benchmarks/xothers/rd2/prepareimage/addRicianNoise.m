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
    rp = (r([1,1], :) + randn(2, length(r)) * sigma) .^ 2;
    result(:) = sqrt(sum(rp));



% matrix(:) make a column vector from the columns of matrix one after the other one.
% (r([1,1],:) duplicates it into two rows
% randn(2, length(r)) gives a random matrix of 2 rows and length(r) columns.
% sum(r) summation for each column
% The result is a matrix:
% "matirx(:) = vector" Can see the matrix as a vector and do the assignment.




