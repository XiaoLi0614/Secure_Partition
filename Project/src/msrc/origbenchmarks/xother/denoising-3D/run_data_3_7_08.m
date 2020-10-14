clear all
close all

% dx=1.33;
% dy=1.33;
% dz=2;
dx=1;
dy=1;
dz=1;


scale=true;

lambda = 15;

% generate synthetic data
S=zeros(30,30,30,7);
S(:,:,:,1)=691;
S(:,:,:,2)=78;
S(:,:,:,3)=76;
S(:,:,:,4)=56;
S(:,:,:,5)=71;
S(:,:,:,6)=66;
S(:,:,:,7)=106;

gradient=[1 0 1;-1 0 1;0 1 1;0 1 -1;1 1 0;-1 1 0];

[T T_posDef scaleFactor] = makeTensor(S,gradient,scale);
    
[TT g] = tvtensor(T_posDef,T,lambda,1e-2,dx,dy,dz,200);
TT=TT/scaleFactor;