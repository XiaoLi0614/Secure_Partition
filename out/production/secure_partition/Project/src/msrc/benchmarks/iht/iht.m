
function xHat = iht(y, phi, k, iterCount, x)

    % This is the actual script that does the recovery of xhat from y, using phi

    [m,n] = size(phi);
    xHat = zeros(n, 1);

    for i=1:iterCount
       xHatNew = thresh(xHat + phi' * (y - phi*xHat), k);

       if norm(xHat-xHatNew) < 1e-6*norm(xHat)
          break;
       end

       xHat = xHatNew;
    end



% Edited: Mohsen Lesani
