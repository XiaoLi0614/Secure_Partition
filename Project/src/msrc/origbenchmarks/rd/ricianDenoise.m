function u = ricianDenoise(inImage, sigma, lambda, tolerance)

    %   ricianDenoise
    %   Total variation minimization for Rician image denoising
    %   u = RICIANDENOISE(inImage,sigma,lambda) performs denoising on image inImage with
    %   Rician noise with parameter sigma.  The denoised image u is found as
    %   the minimizer of
    %   let f = inImage
    %
    %          /                      / [ u^2 + f^2            u f    ]
    %     min  | |grad u| dx + lambda | [ --------- - log I0( ----- ) ] dx.
    %      u   /                      / [ 2 sigma^2          sigma^2  ]
    %
    %   Parameter lambda >= 0 determines the strength of the denoising: smaller
    %   lambda implies stronger denoising.
    %
    %   RICIANDENOISE(inImage,sigma,lambda,tolerance) specifies the stopping tolerance, the
    %   method stops when ||u^Iter - u^Iter-1||_inf < tolerance.
    %
    %   Pascal Getreuer 2009

    % Parameter sigma must be positive.
    % Parameter lambda must be non-negative.
    % Image inImage must be non-negative real-valued two-dimensional array.

    %%% Method parameters %%%
    % Maximum allowed number of iterations
    maxIter = 100;
    % Gradient descent time step size
    dt = 5;
    % Small parameter to avoid divide-by-zero
    epsilon = 1e-20;

    %%% Setup %%%
    [n,m] = size(inImage);

    ir = [2:m,m];
    il = [1,1:m-1];
    id = [2:n,n];
    iu = [1,1:n-1];

    sigma2 = sigma^2;
    gamma = lambda/sigma2;

    % Initialize u as the input image inImage
    u = inImage;

    %%% Main gradient descent loop %%%
    for Iter = 1:maxIter
        ulast = u;

        % Create shifted versions of the image
        ur = u(:,ir);   % Shifted one pixel right
        ul = u(:,il);   % left
        ud = u(id,:);   % down
        uu = u(iu,:);   % up

        % Approximate 1/|grad u|
        g = 1./sqrt(epsilon + (ur - u).*(ur - u) ...
            + (ul - u).*(ul - u) ...
            + (ud - u).*(ud - u) ...
            + (uu - u).*(uu - u));

        % Evaluate I1(inImage.*u/sigma^2) ./ I0(inImage.*u/sigma^2)
        r = u .* inImage/sigma2;

        % Rational approximation of I1(r)./I0(r): Approximation is L^inf
        % optimal with error less than 8e-4 for all x >= 0.
        r = ( r.*(2.38944 + r.*(0.950037 + r)) ) ...
            ./ ( 4.65314 + r.*(2.57541 + r.*(1.48937 + r)) );

        % Update u by a semi-implicit step
        ug = u.*g;
        u = ( u + dt*(ug(:,ir) + ug(:,il) + ug(id,:) + ug(iu,:) + gamma*inImage.*r) ) ...
           ./ ( 1 + dt*(g(:,ir) + g(:,il) + g(id,:) + g(iu,:) + gamma) );

        % Check for convergence
        if norm(u(:) - ulast(:), inf) < tolerance
            break;
        end
    end
end












%---------------------------------------------------------------------------------------------------
%%% Done, show exiting message %%%
%if norm(u(:) - ulast(:),inf) >= tolerance
%    fprintf('Maximum iterations exceeded (maxIter=%d).\n',maxIter);
%else
%    fprintf('Converged in %d iterations with tolerance %g.\n',Iter,tolerance);
%end


%    if FastApprox
%        Rational approximation of I1(r)./I0(r): Approximation is L^inf
%        optimal with error less than 8e-4 for all x >= 0.
%        r = ( r.*(2.38944 + r.*(0.950037 + r)) ) ...
%            ./ ( 4.65314 + r.*(2.57541 + r.*(1.48937 + r)) );
%    else
%        Evaluate I1(r)./I0(r) with besseli
%        r = besseli(1,r,1)./besseli(0,r,1);
%    end

%   RICIANDENOISE(..., FastApprox) specifies how to evaluate I1(x)/I0(x), an
%   expression encountered in solving the minimization.  If FastApprox is
%   true (default), then I1(x)/I0(x) is evaluated with a cubic rational
%   approximation accurate to 8e-4 for all x >= 0.  Otherwise, MATLAB's
%   very accurate (but more time-consuming) besseli function is used.


%%% Input checking %%%
%if nargin < 5
%    FastApprox = true;
%
%    if nargin < 4
%        tolerance = 5e-4*max(inImage(:));
%
%        if nargin < 3
%            error('Not enough input arguments.');
%        end
%    end
%end
%
%if sigma <= 0
%    error('Parameter sigma must be positive.');
%elseif lambda < 0
%    error('Parameter lambda must be nonnegative.');
%elseif any(~isfinite(inImage(:)) | inImage(:) < 0)
%    error('Image inImage must be nonnegative real-valued image.');
%elseif ndims(inImage) ~= 2
%    error('Image inImage must be a two-dimensional array.');
%end

