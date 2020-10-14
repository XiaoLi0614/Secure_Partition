%%% Demo script for riciandenoise3mx %%%
% For size, I did not include the 3D test data in this package.  You need
% to download the test data from online.  Follow these steps:
%
% 1. Go to the website
%       http://www.bic.mni.mcgill.ca/cgi/brainweb1
%    "BrainWeb: Simulated MRI Volumes for Normal Brain."
%
% 2. Select "Noise: 0%," then press the "Download" button.  On the
%    following page, select that "raw short (12-bit)" format, and then
%    "Start download" to download the file.
%
% 3. Uncompress the downloaded file to obtain a file called
%       t1_icbm_normal_1mm_pn0_rf20.raws
%    and put it in the same directory as this script.


% Parameter for the Rician noie
sigma = 0.05;
% Parameters for riciandenoise
% Smaller lambda implies stronger denoising
lambda = 0.065;
% Parameter Tol is the stopping tolerance
Tol = 2e-3;

% Load the test data
fid = fopen('t1_icbm_normal_1mm_pn0_rf20.raws','rb');

if fid == -1
    help(mfilename);
    return;
end

uexact = fread(fid,inf,'uint16=>double',0,'ieee-be');
fclose(fid);
uexact = uexact/4095;
uexact = permute(reshape(uexact,[181,217,181]),[3,2,1]);
uexact = uexact(end:-1:1,end:-1:1,end:-1:1);

fprintf('Loaded uexact of size %s\n',mat2str(size(uexact)));

% Plot uexact
figure(1);
volslice(uexact);
title('Exact data (PSNR +\infty dB)');
colormap(hot(256));
drawnow;
shg;

% Save axes handles for linkaxes (for panning and zooming)
for k = 1:3
    ax1(k) = subplot(2,2,k);
end

% Simulate Rician noise
fprintf('Simulating noisy data...\n');
f = ricianrnd(uexact,sigma);

% Plot input
figure(2);
volslice(f);
title(sprintf('Noisy input data (PSNR %.2f dB)',...
    -10*log10(mean((uexact(:) - f(:)).^2))));
colormap(hot(256));
drawnow;
shg;

% Save axes handles
for k = 1:3
    ax2(k) = subplot(2,2,k);
end

% Link corresponding axes (for panning and zooming)
for k = 1:3
    linkaxes([ax1(k),ax2(k)],'xy');
end

fprintf('\nPress enter to proceed with denoising.');
pause;
fprintf('\nRunning riciandenoise3mx...\n');
figure(2);
shg;
drawnow;

% Perform the denoising
StartTime = clock;
u = riciandenoise3mx(f,sigma,lambda,Tol);
StopTime = clock;

% Plot output
figure(3);
volslice(u);
title(sprintf('Denoised (PSNR %.2f dB, CPU time %.2f s)', ...
    -10*log10(mean((uexact(:) - u(:)).^2)), ...
    etime(StopTime,StartTime)));
colormap(hot(256));
shg;

% Save axes handles
for k = 1:3
    ax3(k) = subplot(2,2,k);
end

% Link corresponding axes (for panning and zooming)
for k = 1:3
    linkaxes([ax1(k),ax2(k),ax3(k)],'xy');
end

