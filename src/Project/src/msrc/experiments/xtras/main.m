%inImage = [1.0 2.0 3.0; 4.0 5.0 6.0];
inImage = double(readFormatImage('NoisyKneeMRI.format'));
noisyImage = noisyImage / 255;

sigma = 0.05;
lambda = 0.065;
tolerance = 2e-3;

tic;
denoisedImage = ricianDenoise(inImage, sigma, lambda, tolerance);
time = toc;


disp('Reconstruction time in seconds:');
disp(time);

% Write reconstructed image
writeFormatImage(denoisedImage, 'DenoisedKneeMRI.format');
