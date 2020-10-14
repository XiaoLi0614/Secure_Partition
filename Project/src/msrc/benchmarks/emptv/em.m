function x = em(A, b, x0, maxIter)

%
% x = EM(A, g, x0, maxIter)
%
% EM algorithm for image reconstruction.
%
% We have to reconstruct x from
%
%  A*x = b
%
% x0 is the initial guess,
% maxIter is the maximum iterations,
%


    x = x0;
    done = 0;
    iter = 0;
    sumA = sum(A)';
    while ~done
        Ax = A * x;
        Ax = max(Ax, 1e-8);
        bAx = b ./ Ax;
        AtbAx = A' * bAx;
        x = x .* AtbAx ./ sumA;
        iter = iter + 1;

        if iter == maxIter
            done = 1;
        end
    end

