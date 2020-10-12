
% Read image
grayImage = double(readFormatImage('KneeMRI.format'));

% Scale the image to [0, 1]
grayImage = grayImage / 255;

% Add noise and denoise
[noisyImage, denoisedImage, time] = testRicianDenoise(grayImage);

% write images
writeFormatImage(noisyImage, 'NoisyKneeMRI.format');
writeFormatImage(denoisedImage, 'DenoisedKneeMRI.format');



