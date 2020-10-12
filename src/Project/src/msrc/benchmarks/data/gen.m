
theta = 10;


% C / n
image = phantom(16);
D = 15.5;
d = 8;
q = 15;
ss = 8.0;
[rays, sino] = fbeam2d(image, D, q, ss, d, theta);
rays = full(rays);
writeMatrix(rays, 'Rays16.format');
writeMatrix(sino, 'Sino16.format');

image = phantom(32);
D = 31.25;
d = 16;
q = 31;
ss = 4.0;
[rays, sino] = fbeam2d(image, D, q, ss, d, theta);
rays = full(rays);
writeMatrix(rays, 'Rays32.format');
writeMatrix(sino, 'Sino32.format');


%image = phantom(64);
%D = 62.5;
%d = 32;
%q = 62;
%ss = 2.0;
%[rays, sino] = fbeam2d(image, D, q, ss, d, theta);
%rays = full(rays);
%writeMatrix(rays, 'Rays64.format');
%writeMatrix(sino, 'Sino64.format');


%image = phantom(128);
%D = 125;
%d = 64;
%q = 125;
%ss = 1.0;
%[rays, sino] = fbeam2d(image, D, q, ss, d, theta);


%image = phantom(256);
% C / n
%D = 250;
% C / n
%d = 128;
% C / n keep int
%q = 150;
% C * n
%ss = .5;
%[rays, sino] = fbeam2d(image, D, q, ss, d, theta);



