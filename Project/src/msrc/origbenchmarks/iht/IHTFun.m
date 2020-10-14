function result = IHTFun()
    % Description: This is the main script for the IHT
    % (Iterative Hard Thresholding) algorithm for the
    % Compressed Sensing (CS) reconstruction of a synthetic
    % vector $x$ that we generate according to the %parameters below.
    % It is "measured" with matrix $Phi$, from which we get vector
    % $y$, which is used in turn to recover $xhat$, which ideally should = $x$
    %
    % Original scripts from Chinmay Hegde
    %
    %  PSEUDOCODE:
    %  1) Generate x according to M,N,K
    %  2) Generate measurement matrix Phi; normalize Phi
    %  3) Generate y from y = Phi*x
    %  4) Recover x from y using IHT.m
    %
    %
    %
    % INPUT PARAMETERS:
    % N (dimension of vector x that we want to recover)
    % K (sparsity of vector x that we want to recover)
    % M (number of measurements; dimension of vector y -- the measurement vector)
    % Its (number of iterations in the algorithm IHT.m)
    %
    % This script also calls:  IHT.m (which calls thresh.m)
    %
    % OUTPUT:
    % Recovered vector "xhat", which we compare with our original vector "x"
    % to check errors

    % This is just for cleaning.
    % Not needed for computation.
    % clear, close all, clc

    N = 512;
    K = 13;
    M = 230;
    Its = 1000;

    %  1) Generate x according to M,N,K
    x = zeros(N,1);
    q = randperm(N);
    x(q(1:K)) = randn(K,1);

    %  2) Generate measurement matrix Phi
    Phi = randn(M,N);
    Phi = Phi/norm(Phi);

    %  3) Generate y from y = Phi*x
    % sample the signal
    y = Phi*x;
    y = y + 0.00*randn(size(y));

    %  4) Recover x from y using IHT.m
    xhat = IHT(y, Phi, K, Its, x);

    result = norm(x-xhat)/norm(x);
end


