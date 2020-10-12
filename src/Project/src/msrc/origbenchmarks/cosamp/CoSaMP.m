%
% original code By Jason Laska
%
% From 
% "CoSaMP: Iterative signal recovery from incomplete and innaccurate samples"
% D. Needell and J. A. Tropp
% COSAMP: Iterative signal recovery
%	  from incomplete and inaccurate samples
%
%%%%%%%%%%%%%%

% NOTATION:
% y = measurement vector (CT fourier samples, for instance)
% x = original CT image we want to reconstruct 
% Phi = sampling matrix (will be a partial Fourier matrix when working with real CT data)
% K = sparsity of x
% N (dimension of vectorized x that we want to recover) 
% M (number of measurements; dimension of vector y -- the measurement vector)
% maxiter (number of iterations in the algorithm cosamp.m)
% halt = error tolerance

%
% OUTPUT:
% Recovered vector "xhat", which we compare with our original vector "x" to check errors
%
%%%%%%%%%%%%%%


function [alpha] = CoSaMP(y, phi, K, maxIter, halt);

N = size(phi, 2);
M = size(phi, 1);

alpha = zeros(N,1); %estimate
res = y;	    %residual
i = 1;

prevOmega = [];

while ((norm(res) > halt) && (i-1 < maxIter))
    i = i+1;
    
    % Match
    match = phi' * res;
    
    % 2K Support of match
    [dummy, supp] = sort(abs(match), 'descend');
    omega = supp(1:2*K);
    
    
    % Merge Support with previous
    T = union(omega, prevOmega);
    
    % Least Squares Estimate
    phi_T = phi(:, T);
    a_T = pinv(phi_T)*y;  % This is a matrix inverse, and then multiply
    alpha = zeros(N,1);
    alpha(T) = a_T;
    
    % Prune estimate
    [dummy, supp] = sort(abs(alpha), 'descend');
    alpha(supp(K+1:end)) = 0;
    
    % Update residual
    res = y - phi*alpha;
    
    % Store this as prev support
    prevOmega = supp(1:K); %top K terms from alpha
    
end

