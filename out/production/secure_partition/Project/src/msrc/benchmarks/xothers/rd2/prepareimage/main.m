
% Read image
grayImage = double(readFormatImage('KneeMRI.format'));

% Scale the image to [0, 1]
grayImage = grayImage / 255;

% Simulate Rician noise
sigma = 0.05;
noisyImage = addRicianNoise(originalImage, sigma);

% write noisy image
writeFormatImage(noisyImage, 'NoisyKneeMRI.format');





