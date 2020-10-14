function [rays, sino] = fbeam2d(image, D, q, ss, d, dtheta)
% [rays, sino] = fbeam2d(image, D, q, ss, d, dtheta)
% Conmputes the Radon transform of image using the geomerty data specified by pairs SOURCES, DETECTORS.
% @input:
% image: image to transform
% D: the distance from the source to the center of the image
% q: 2q+1 number of bin on a detector
% ss: the space of the sensor in angle.
% d: the bound of the image
% dtheta: the increasement of angle for the source
%
% @output:
% rays - forward matrix
% sino - sinogram, but without the exponential

%Determine the number of voxels(pixels)
%For 2D we always use M=N
[M N] = size(image);

%Generate the sources equally distributed on the circle and detectors on the circle too   
%Sampling: increased by dtheta
%%%%% we can also choose the angle to be in 360 degrees 
thetas = (0:dtheta:359)/180*pi;
%%%%%NS is number of the sources
NS = length(thetas);


% translate from the degree to pi
ss = ss/180*pi;
% the source start from the right
source = D;
detector = -D*exp(i*(-ss*q:ss:ss*q));

j = 1;
for theta = thetas
    sources(j) = source*exp(i*theta);
    detectors(j,:) = detector*exp(i*theta);
    j = j+1;
end;

%Initialization
sino = zeros(2*q+1,NS);
rays = sparse(NS*(2*q+1),M*N);

%Compute the sinogram looping through all the sources and detectors pairs
%for j = 3*NS/4+1:2:NS    
for j = 1:NS    

        x_s = real(sources(j));
        y_s = imag(sources(j));

    for k = 1:(2*q+1)

        x_d = real(detectors(j,k));
        y_d = imag(detectors(j,k));
      
        ray = ray_tracer_2d(x_s,y_s,x_d,y_d, M, N, d);

        rays((j-1)*(2*q+1)+k,:) = ray(:);
        sino(k,j) = sum(sum(image.*ray));
   end;
end;

      
