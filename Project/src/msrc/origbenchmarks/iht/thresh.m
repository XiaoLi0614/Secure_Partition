function xt = thresh(x, K)

% This function just thresholds a vector x, keeping the K largest components

[trash, ind] = sort(abs(x), 'descend');
indk = ind(1:K);
xt = 0*x; xt(indk) = x(indk);

