%------------------------------------------------------------------------------
% Description: This is the main script of
% the CoSaMP algorithm for the Compressed Sensing (CS).
% Reconstruction of a synthetic vector x that we
% generate according to the parameters below. It is
% measured with matrix phi, from which we get vector
% y, which is used in turn to recover xHat, which
% should ideally be equal to x.


% INPUT PARAMETERS:
% n: Dimension of vector x that we want to recover
% m: Number of measurements; dimension of vector y, the measurement vector
% K: Sparsity of vector x that we want to recover

% OUTPUT:
% Recovered vector "xHat",
% which we compare with our original vector x to check errors

%------------------------------------------------------------------------------

% Parameters
n = 1024;
m = 200;
k = 40;
%n = 5;
%m = 3;
%k = 2;

maxIter = 500;
tolerance = 1e-4;
epsilon = 0.1;  % Only relevant in the noisy case

%------------------------------------------------------------------------------
% Constructing test data
% Measurement matrix that we create (will change for real CT data)
randomMat = randn(m, n);
%randomMat = [
%    0.5377,    0.8622,   -0.4336,    2.7694,    0.7254;
%    1.8339,    0.3188,    0.3426,   -1.3499,   -0.0631;
%   -2.2588,   -1.3077,    3.5784,    3.0349,    0.7147
%];
phi = (1 / sqrt(m)) * randomMat;

% Generate random sparse test signal
alpha = zeros(n, 1);

rp = randperm(n);
%rp =  [5,     3,     1,     2,     4];

alpha(rp(1:k)) = randn(k, 1);
%randomV = [
%    0.1419;
%    0.4218
%];
%alpha(rp(1:k)) = randomV;

x = alpha;
x = x / norm(x);  % Normalization not necessary

% Get measurements
y = phi * x;
%------------------------------------------------------------------------------

% Run CoSaMP
tic;
xHat = coSaMP(y, phi, k, maxIter, tolerance);
time = toc;
error1 = norm(x-xHat) / norm(x);

disp('CoSaMP error value:');
disp(error1);
disp('Time is seconds:');
disp(time);

% Noisy case
%n = randn(m, 1);
%n = epsilon*n/norm(n);
%yn = y+n;
%xHat = coSaMP(yn, phi, k, maxIter, tolerance);
%error2 = norm(x-xHat) / norm(x);
%disp('Reconstruction error (noisy case):');
%disp(error2)
%------------------------------------------------------------------------------



%------------------------------------------------------------------------------
% Notes:
% Functions used in this benchmark
% sqrt, randn, zeros, randperm, norm, sort, abs, union, pinv
%------------------------------------------------------------------------------

% Edited: Mohsen Lesani
