function result = CoSaMPFun()
    % Description: This is the main script for CoSaMP.m,
    % the CoSaMP algorithm for the Compressed Sensing (CS)
    % reconstruction of a synthetic vector $x$ that we
    % generate according to the parameters below. It is
    % "measured" with matrix $Phi$, from which we get vector
    % $y$, which is used in turn to recover $xhat$, which
    % ideally should = $x$



    % INPUT PARAMETERS:
    % N (dimension of vector x that we want to recover)
    % K (sparsity of vector x that we want to recover)
    % M (number of measurements; dimension of vector y -- the measurement vector)


    %
    % OUTPUT:
    % Recovered vector "xhat", which we compare with our original vector
    % "x" to check errors
    %
    %
    %%%%%%%%%%%%%%


    % Parameters
    N = 1024;
    M = 200;
    K = 40;
    maxiterval = 500;
    haltval = 1e-4;
    epsi = .1; %only relevant in the noisy case


    % Measurement matrix that we create (will change for real CT data)
    Phi = (1/sqrt(M))*randn(M,N);

    % Generate random sparse test signal
    alpha = zeros(N,1);
    rp = randperm(N);
    alpha(rp(1:K)) = randn(K,1);
    x = alpha;
    x = x/norm(x);  %normalization not necessary

    % Get measurements
    y = Phi*x;


    % run CoSaMP
    [xhat] = CoSaMP(y, Phi, K, maxiterval,haltval);
    result = norm(x-xhat)/norm(x);

% NOISY CASE
%n = randn(M,1);
%n = epsi*n/norm(n);
%yn = y+n;
%[xhat] = CoSaMP(yn, Phi, K, maxiterval, haltval);
%fprintf('(noise) CoSaMP error: %d\n', norm(x-xhat)/norm(x));
    
end
