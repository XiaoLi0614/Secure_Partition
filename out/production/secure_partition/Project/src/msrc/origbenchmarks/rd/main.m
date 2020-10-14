%%% Demo script for riciandenoise.m %%%

% Get a clean input image
originalImage = imread('knee-mri.png');

% Average RGB
originalImage = mean(double(originalImage)/255, 3);

[noisyImage, resultImage, time] = ricianDenoiseFun(originalImage);


% Plot input
figure(1);
imagesc(noisyImage);
axis image
axis off
noiseMeasure = -10*log10(mean((originalImage(:) - noisyImage(:)).^2));
colormap(gray(256));
title(sprintf('Noisy input image (PSNR %.2f dB)', noiseMeasure));
drawnow;
shg;


% Plot output
figure(2);
imagesc(resultImage);
axis image
axis off
colormap(gray(256));
title(sprintf('Denoised (PSNR %.2f dB, CPU time %.2f s)', ...
    -10*log10(mean((originalImage(:) - resultImage(:)).^2)), ...
    time));


