function [noisyImage, resultImage, time] = ricianDenoiseFun(originalImage)

    % Simulate Rician noise
    sigma = 0.05;
    noisyImage = addRicianNoise(originalImage, sigma);

    % Parameters for ricianDenoise
    % Smaller lambda implies stronger denoising
    lambda = 0.065;

    % Parameter Tol is the stopping tolerance
    tolerance = 2e-3;
    %tolerance = 5e-4*max(noisyImage(:));

    % Perform the denoising
    startTime = clock;
    resultImage = ricianDenoise(noisyImage, sigma, lambda, tolerance);
    stopTime = clock;
    time = etime(stopTime, startTime);

end









% Parameter FastApprox decides how to approximate I1(x)/I0(x):
% FastApprox = true; % Fast and moderately accurate
% FastApprox = false;  % Slow but very accurate
