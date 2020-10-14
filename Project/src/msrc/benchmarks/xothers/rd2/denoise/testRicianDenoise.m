function [denoisedImage, time] = testRicianDenoise(noisyImage)

    % Parameters for ricianDenoise
    sigma = 0.05;

    % Smaller lambda implies stronger denoising
    lambda = 0.065;

    % stopping tolerance
    tolerance = 2e-3;
    %tolerance = 5e-4*max(noisyImage(:));

    % Perform the denoising
    tic;
    denoisedImage = ricianDenoise(noisyImage, sigma, lambda, tolerance);
    time = toc;


