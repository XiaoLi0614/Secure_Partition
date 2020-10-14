function result = ihtFun(n, m, k, iters)
    % Description: This is the main script for the IHT
    % (Iterative Hard Thresholding) algorithm for the
    % Compressed Sensing (CS) reconstruction of a synthetic
    % vector $x$ that we generate according to the %parameters below.
    % It is "measured" with matrix $phi$, from which we get vector
    % $y$, which is used in turn to recover $xhat$, which ideally should = $x$
    %
    % Original scripts from Chinmay Hegde
    %
    %  PSEUDOCODE:
    %  1) Generate x according to m, n, k
    %  2) Generate measurement matrix phi; normalize phi
    %  3) Generate y from y = phi*x
    %  4) Recover x from y using iht.m
    %
    %
    %
    % INPUT PARAMETERS:
    % N (dimension of vector x that we want to recover)
    % K (sparsity of vector x that we want to recover)
    % M (number of measurements; dimension of vector y -- the measurement vector)
    % iters (number of iterations in the algorithm iht.m)
    %
    % This script calls:  iht.m (which calls thresh.m)
    %
    % OUTPUT:
    % Recovered vector "xhat", which we compare with our original vector "x"
    % to check errors

    % This is just for cleaning.
    % Not needed for computation.
    % clear, close all, clc

    %  1) Generate x according to m, n, k

    x = zeros(n, 1);
    q = randperm(n);
%    q = [3     4     5     1     2];
    randV = randn(k, 1);
%    randV = [
%        -2.0656;
%        -0.0340;
%        -0.9630];
    x(q(1:k)) = randV;

    %  2) Generate measurement matrix phi
    phi = randn(m,n);
%    phi = [
%        0.8131,   -0.0895,    1.8941,   -0.5665,    0.1949;
%        1.1002,    1.1289,   -0.6612,   -0.1307,    0.1823;
%       -1.3343,   -0.5953,   -0.0599,    0.8012,    0.7805;
%        0.3509,    0.1195,    1.3071,    1.5503,    0.6616];
    phi = phi / norm(phi);

    %  3) Generate y from y = phi*x
    % sample the signal
    y = phi * x;
    randy = randn(size(y));
%    randy = [
%        0.2716;
%        0.4160;
%        0.3962;
%        0.8266
%    ];
    y = y + 0.00 * randy;

    %  4) Recover x from y using iht.m
    xhat = iht(y, phi, k, iters, x);

    result = norm(x-xhat) / norm(x);



% Edited: Mohsen Lesani
