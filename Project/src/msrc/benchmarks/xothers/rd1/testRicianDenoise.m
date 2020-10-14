function [noisyImage, resultImage, time] = testRicianDenoise(originalImage)

    % Simulate Rician noise
    sigma = 0.05;
    noisyImage = addRicianNoise(originalImage, sigma);

    % Parameters for ricianDenoise
    % Smaller lambda implies stronger denoising
    lambda = 0.065;

    % stopping tolerance
    tolerance = 2e-3;
    %tolerance = 5e-4*max(noisyImage(:));

    % Perform the denoising

    tic
    resultImage = ricianDenoise(noisyImage, sigma, lambda, tolerance);
%    resultImage = originalImage;
    time = toc


    %stopTime = clock;
    %time = etime(stopTime, startTime);
    %startTime = clock;






% Parameter FastApprox decides how to approximate I1(x)/I0(x):
% FastApprox = true; % Fast and moderately accurate
% FastApprox = false;  % Slow but very accurate
