function x = EMpTV(A, b)

%%%%% add poison noise on the measurement %%%%%%
%
% b1 = b;
% b1 = b1*3e-10;
% b1 = imnoise(b1,'poisson');
% b1 = b1/(3e-10);
% 
% %% b1 is the noise data, while b is the noisefree data
% 
% b = b1;


V = reshape(sum(A),256,256);
V = full(V);

alpha = 5;

x0 = ones(256*256,1);

for i=1:300
% This is only to print the time for each iteration.
%    tic

    x0 = EM(A, b, x0, 3);

    % reshape to 2d
    x0 = reshape(x0,256,256);

    x0(1,:) = zeros(1,256);
    x0(256,:) = zeros(1,256);
    x0(:,1) = zeros(256,1);
    x0(:,256) = zeros(256,1);


    x0 = TV(x0, V, alpha, 6);

    % reshape to 1d
    x0 = reshape(x0, 256*256,1);

    x0 = max(x0, 0);
    x0 = min(x0, 1);
    % shrink the image to be bounded by (0,1), this can be changed
    % depending on the exact problem.

% This is only to see the image while testing.
% Not needed for the computation. 
%    if mod(i,100) == 0
%       figure;
%       subplot(1,2,1)
%       imshow(reshape(x0,256,256),[]);
%       subplot(1,2,2)
%       imshow(reshape(x0,256,256)-phantom(256),[])
%    end

%    toc
end


%---------------------------------------------------
% Extras:

% figure
% subplot(3,1,1)
% plot(b);

% subplot(3,1,2)
% plot(b1);
% subplot(3,1,3)
% plot(b1-b);
%
