clear all 

%%%%%%%%%%%%%%%%%%%%%%
%%%%%%% Image registration in two dimensions using L^2 similarity measure and
%%%%%%% the H^2 regularization (biharmonic regularization) 
%%%%%%%%%%%%%%%%%%%%%%


% Read in two images
R=double(imread('MRI_r207ax_nu_sc_xy_108.pgm'));
T=double(imread('MRI_r8731ax_nu_sc_xy_108.pgm'));

[M N]=size(T); 

% Parameters (defined by the users) 
alpha = 50000; 
dt = 0.0001;
itermax = 5000;


%------------------------------------------------------------------------
%    VISUALISATION OF THE TWO MRI IMAGES TO BE MATCHED 
%-------------------------------------------------------------------------
figure
imagesc(R); colormap(gray); axis equal; axis off; hold on; title('Reference R');

figure
imagesc(T); colormap(gray); axis equal; axis off; hold on; title('Template T');
%--------------------------------------------------------------------------

% compute gradient of T by central differences
Tx(2:M-1,1:N)=(T(3:M,1:N)-T(1:M-2,1:N))/2;
Tx(1,1:N)=(T(2,1:N)-T(1,1:N));
Tx(M,1:N)=(T(M,1:N)-T(M-1,1:N));

Ty(1:M,2:N-1)=(T(1:M,3:N)-T(1:M,1:N-2))/2;
Ty(1:M,1)=(T(1:M,2)-T(1:M,1));
Ty(1:M,N)=(T(1:M,N)-T(1:M,N-1));

% Rewrite coefficients   
a1=1+(16*alpha*dt);     
a=1/a1;
b1=alpha*dt;              
b=b1/a1;


% initialize displacement 
u1=zeros(M,N); u2=zeros(M,N);

% Define the identity transformation 
S1=(1:M)'*ones(1,N);
S2=ones(M,1)*(1:N);

% Main part of the code (repeated for each iteration)

for iter=1:itermax

    % Update Identity plus Displacement
    S11=S1+u1;S22=S2+u2;

    % compute T(x+u) using interpolation
    T1=interpn(T,S11,S22,'linear');
    Tx1=interpn(Tx,S11,S22,'linear');
    Ty1=interpn(Ty,S11,S22,'linear');

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

    for i=3:M-2
        for j=3:N-2
                u1(i,j)=a*(u1(i,j)-dt*Tx1(i,j)*(T1(i,j)-R(i,j)))-...
                        b*(u1(i+2,j)-6*u1(i+1,j)-6*u1(i-1,j)+u1(i-2,j)+...
                           u1(i,j+2)-6*u1(i,j+1)-6*u1(i,j-1)+u1(i,j-2)+...
                           u1(i+1,j+1)+u1(i-1,j+1)+u1(i+1,j-1)+u1(i-1,j-1));

                u2(i,j)=a*(u2(i,j)-dt*Ty1(i,j)*(T1(i,j)-R(i,j)))-...
                        b*(u2(i+2,j)-6*u2(i+1,j)-6*u2(i-1,j)+u2(i-2,j)+...
                           u2(i,j+2)-6*u2(i,j+1)-6*u2(i,j-1)+u2(i,j-2)+...
                           u2(i+1,j+1)+u2(i-1,j+1)+u2(i+1,j-1)+u2(i-1,j-1));

end
end

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% End iterations 
end

%--------------------------------------------------------------
%%%%  OUTPUT and VISUALIZATIONS 
%---------------------------------------------------------------

    % Calculate determinant of the jacobian
    u1x(2:M-1,2:N-1)=(0.5)*(u1(3:M,2:N-1)-u1(1:M-2,2:N-1));
    u1y(2:M-1,2:N-1)=(0.5)*(u1(2:M-1,3:N)-u1(2:M-1,1:N-2));
    u2x(2:M-1,2:N-1)=(0.5)*(u2(3:M,2:N-1)-u2(1:M-2,2:N-1));
    u2y(2:M-1,2:N-1)=(0.5)*(u2(2:M-1,3:N)-u2(2:M-1,1:N-2));
     
    determinant=(ones(M-2,N-2)+u1x(2:M-1,2:N-1)).*(ones(M-2,N-2)+u2y(2:M-1,2:N-1))-u1y(2:M-1,2:N-1).*u2x(2:M-1,2:N-1);

figure;imagesc(R);colormap(gray);axis equal; axis off; hold on; 
title('Deformation grid over reference R'); 
contour(S22,40,'r');hold on;
contour(S11,40,'r');

figure; imagesc(1./determinant); axis equal; axis off; title('Inverse Jacobian determinant'); colorbar; 

%--------------------------------------------------------------------


