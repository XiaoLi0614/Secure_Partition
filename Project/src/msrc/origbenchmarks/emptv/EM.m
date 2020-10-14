function x = EM(A, b, x0, max_iter)

%
% x = EMCT(A, g, x0, max_iter)
%
% EM algorithm for image reconstruction.
%
% We have to reconstruct x from
%
%  A*x = b
%
% x is the initial guess,
% maxiter is the maximum iterations,
% 
% 

% Commented. Not needed.
%[m, n] = size(A);


x = x0;
stopc = 0;
iter = 0;
sumA = sum(A)';
while ~stopc
    Ax = A*x;
    bAx = b./Ax;
    AtbAx = A' * bAx;
    x = x .* AtbAx ./ sumA;
    iter = iter + 1;
    
    if iter == max_iter
        stopc = 1;
% Just printing for testing.         
%        fprintf('total iteration %d:\n', iter);
    end
end

