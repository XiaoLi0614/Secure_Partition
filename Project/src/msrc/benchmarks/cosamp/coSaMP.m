%------------------------------------------------------------------------------
% Original code By Jason Laska
%
% From 
% "CoSaMP: Iterative signal recovery from incomplete and innaccurate samples"
% D. Needell and J. A. Tropp

% Edited: Mohsen Lesani
%------------------------------------------------------------------------------

% Inputs
% y: Measurement vector (CT fourier samples, for instance)
% phi: Sampling matrix (will be a partial Fourier matrix when working with real CT data)
% k: Sparsity of x
% maxIter: Number of iterations in the algorithm
% tolerance: Error tolerance

% Notation:
% n: Dimension of vectorized x that we want to recover
% m: Number of measurements; dimension of vector y, the measurement vector
% x: Original CT image we want to reconstruct

% Output:
% Recovered vector "xhat", which we compare with our original vector "x" to check errors
%------------------------------------------------------------------------------

% y:            VVector[Double]
% phi:          Matrix[Double]
% k:            Int
% maxIter:      Int
% tolerance:    Double

function xHat = coSaMP(y, phi, k, maxIter, tolerance)
    n = size(phi, 2);
    m = size(phi, 1);

    xHat = zeros(n, 1);  % Estimate

    res = y;	         % Residual
    i = 1;

    prevOmega = [];

    while ((norm(res) > tolerance) && (i-1 < maxIter))
        i = i + 1;

        % Match
        match = phi' * res;
        % match: Matrix[Double]

        % 2K Support of match
        [dummy, supp] = sort(abs(match), 'descend');
        omega = supp(1:2*k);
        % omega: Matrix[Int]

        % Merge Support with previous
        T = union(omega, prevOmega);
        % T: Matrix[Int]

        % Least Squares Estimate
        phi_T = phi(:, T);
        a_T = pinv(phi_T) * y;  % This is a matrix inverse, and then multiply
        xHat = zeros(n, 1);
        xHat(T) = a_T;

        % Prune estimate
        [dummy, supp] = sort(abs(xHat), 'descend');
        %xHat(supp(k+1:end)) = 0;
        xHat(supp(k+1:end)) = 0.0;

        % Update residual
        res = y - phi*xHat;

        % Store this as prev support
        prevOmega = supp(1:k);  % Top k terms from xHat
    end



