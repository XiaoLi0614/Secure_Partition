
function xhat = IHT(y, phi, K, iterCount, x)

% This is the actual script that does the recovery of xhat from y, using phi

[M,N] = size(phi);
xhat = zeros(N,1);

for i=1:iterCount
   xhatnew = thresh(xhat + phi' * (y - phi*xhat), K);

   if norm(xhat-xhatnew) < 1e-6*norm(xhat)
      break;
   end
   xhat = xhatnew;
end



