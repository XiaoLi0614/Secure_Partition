disp('Rician Denoising');
% --------------------------------------
% Parameters for ricianDenoise

sigma = 0.05;

% Smaller lambda implies stronger denoising
lambda = 0.065;

% stopping tolerance
tolerance = 2e-3;
%tolerance = 5e-4*max(noisyImage(:));

inputPathName = '/media/MOHSENHD/1.Works/3.Research/0.Research/1.CDSC/2.Projects/0.Project/0.Project/src/msrc/benchmarks/rd/denoise/NoisyKneeMRI.format';
outputPathName = '/media/MOHSENHD/1.Works/3.Research/0.Research/1.CDSC/2.Projects/0.Project/0.Project/src/msrc/benchmarks/rd/denoise/DenoisedKneeMRI.format';

% --------------------------------------

% Read noisy image
noisyImage = double(readFormatImage(inputPathName));

% Scale the image to [0, 1]
noisyImage = noisyImage / 255;

% Denoise
tic;
denoisedImage = ricianDenoise(noisyImage, sigma, lambda, tolerance);
time = toc;


disp('Time in seconds:');
disp(time);

% Write reconstructed image
writeFormatImage(denoisedImage, outputPathName);

% --------------------------------------

