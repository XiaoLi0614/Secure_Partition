% ------------------------------------------------------------------
% Read and preprocess data:
%n = 64;
%n = 32;
n = 16;

% rays is A
% In 3D, the matrix "rays" can not be stored, so we have to define
% two functions, one is projection "rays X image", another is
% backprojection "rays^t X sino".

%load('rays30.mat','rays');
%rays = readMatrix('../data/Rays64.format');
%rays = readMatrix('../data/Rays32.format');
%rays = readMatrix('../data/Rays16.format');
rays = readMatrix('/media/MOHSENHD/1.Works/3.Research/0.Research/1.CDSC/2.Projects/0.Project/0.Project/src/msrc/benchmarks/data/Rays16.format');

% sinogram is b
%load('sino30.mat','sino');
%sino = readMatrix('../data/Sino64.format');
%sino = readMatrix('../data/Sino32.format');
%sino = readMatrix('../data/Sino16.format');
sino = readMatrix('/media/MOHSENHD/1.Works/3.Research/0.Research/1.CDSC/2.Projects/0.Project/0.Project/src/msrc/benchmarks/data/Sino16.format');

rowSum = sum(rays, 2);
%disp(rowSum);
index = find(rowSum > 0);
%disp(index);
% Sum over each row
% Find the indices of the rows for which the sum is non-zero

A = rays(index, :);
% choose only these rows.
% We have to delete all zero rows, because the measurement would be
% zero, no matter what the image is.

b = sino(index);
% Delete the same rows in b.


% ------------------------------------------------------------------
% EM + TV:

V = reshape(sum(A), n, n);

alpha = 5;

x = ones(n*n, 1);

for i=1:300
    x = em(A, b, x, 3);
    % Reshape to 2D
    x = reshape(x, n, n);

    x(1, :) = 0.0;
    x(n, :) = 0.0;
    x(:, 1) = 0.0;
    x(:, n) = 0.0;


    x = tv(x, V, alpha, 6);

    % Reshape to 1D
    x = reshape(x, n*n, 1);

    % Shrink the image to be bounded by (0, 1).
    % This can be changed depending on the exact problem.
    x = max(x, 0);
    x = min(x, 1);

end

% ------------------------------------------------------------------
% The result is an image.
image = reshape(x, n, n);
%dispImage(image);
writeFormatImage(image, '/media/MOHSENHD/1.Works/3.Research/0.Research/1.CDSC/2.Projects/0.Project/0.Project/src/msrc/benchmarks/emptv/EMpTVOut.format');
% ------------------------------------------------------------------


