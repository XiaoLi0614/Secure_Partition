function xt = thresh(x, k)
    % This function just thresholds a vector x, keeping the k largest components

    [trash, indices] = sort(abs(x), 'descend');
    kIndices = indices(1:k);
    xt = 0 * x;
    xt(kIndices) = x(kIndices);

% Edited: Mohsen Lesani
