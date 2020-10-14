disp('Adding Rician Noise');

originalImagePathName = '/media/MOHSENHD/1.Works/3.Research/0.Research/1.CDSC/2.Projects/0.Project/0.Project/src/msrc/benchmarks/rd/prepareimage/KneeMRI.format';
noisyImagePathName = '/media/MOHSENHD/1.Works/3.Research/0.Research/1.CDSC/2.Projects/0.Project/0.Project/src/msrc/benchmarks/rd/prepareimage/NoisyKneeMRI.format';


% Read image
originalImage = double(readFormatImage(originalImagePathName));

% Scale the image to [0, 1]
originalImage = originalImage / 255;

% Simulate Rician noise
sigma = 0.05;


tic;
noisyImage = addRicianNoise(originalImage, sigma);
time = toc;
disp('Time in seconds:');
disp(time);

% write noisy image
writeFormatImage(noisyImage, noisyImagePathName);





