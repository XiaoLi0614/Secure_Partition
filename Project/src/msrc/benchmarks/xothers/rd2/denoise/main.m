
% Read noisy image
noisyImage = double(readFormatImage('NoisyKneeMRI.format'));

% Scale the image to [0, 1]
noisyImage = noisyImage / 255;

% Denoise
[denoisedImage, time] = testRicianDenoise(noisyImage);
disp('Rician Denoising');
disp('Time in seconds:');
disp(time);

% Write reconstructed image
writeFormatImage(denoisedImage, 'DenoisedKneeMRI.format');


