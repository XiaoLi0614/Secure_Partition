function [T,g] = tvtensor(X,Xnoisy,lambda,dt,dx,dy,dz,iter)

scale = 1;

L = tensorchol(X);
T = llt(L);

rr=rl(T,dx,dy,dz);

Lnew=zeros(size(L));

for i = 1:iter 
    i
    pt11=(1/rr)*p(squeeze(T(:,:,:,1,1)),dx,dy,dz);
    pt21=(1/rr)*p(squeeze(T(:,:,:,2,1)),dx,dy,dz);
    pt22=(1/rr)*p(squeeze(T(:,:,:,2,2)),dx,dy,dz);
    pt31=(1/rr)*p(squeeze(T(:,:,:,3,1)),dx,dy,dz);
    pt32=(1/rr)*p(squeeze(T(:,:,:,3,2)),dx,dy,dz);
    pt33=(1/rr)*p(squeeze(T(:,:,:,3,3)),dx,dy,dz);
   
    drdl11=squeeze(L(:,:,:,1,1)).*pt11 + squeeze(L(:,:,:,2,1)).*pt21 + squeeze(L(:,:,:,3,1)).*pt31;
    drdl21=squeeze(L(:,:,:,1,1)).*pt21 + squeeze(L(:,:,:,2,1)).*pt22 + squeeze(L(:,:,:,3,1)).*pt32;
    drdl22=squeeze(L(:,:,:,2,2)).*pt22 + squeeze(L(:,:,:,3,2)).*pt32;
    drdl31=squeeze(L(:,:,:,1,1)).*pt31 + squeeze(L(:,:,:,2,1)).*pt32 + squeeze(L(:,:,:,3,1)).*pt33;
    drdl32=squeeze(L(:,:,:,2,2)).*pt32 + squeeze(L(:,:,:,3,2)).*pt33;
    drdl33=squeeze(L(:,:,:,3,3)).*pt33;

    dgdl11 = 2*lambda*squeeze( scale*(T(:,:,:,1,1)-Xnoisy(:,:,:,1,1)).*L(:,:,:,1,1) + ...
                               (T(:,:,:,2,1)-Xnoisy(:,:,:,2,1)).*L(:,:,:,2,1) + ...
                               (T(:,:,:,3,1)-Xnoisy(:,:,:,3,1)).*L(:,:,:,3,1))- ...
                               2*drdl11;
   
    dgdl21 = 2*lambda*squeeze( (T(:,:,:,2,1)-Xnoisy(:,:,:,2,1)).*L(:,:,:,1,1) + ...
                               scale*(T(:,:,:,2,2)-Xnoisy(:,:,:,2,2)).*L(:,:,:,2,1) + ...
                               (T(:,:,:,3,2)-Xnoisy(:,:,:,3,2)).*L(:,:,:,3,1))- ...
                               2*drdl21; 
    
    dgdl22 = 2*lambda*squeeze( scale*(T(:,:,:,2,2)-Xnoisy(:,:,:,2,2)).*L(:,:,:,2,2) + ...
                               (T(:,:,:,3,2)-Xnoisy(:,:,:,3,2)).*L(:,:,:,3,2))- ...
                               2*drdl22;   
                           
    dgdl31 = 2*lambda*squeeze( (T(:,:,:,3,1)-Xnoisy(:,:,:,3,1)).*L(:,:,:,1,1) + ...
                               (T(:,:,:,3,2)-Xnoisy(:,:,:,3,2)).*L(:,:,:,2,1) + ...
                               scale*(T(:,:,:,3,3)-Xnoisy(:,:,:,3,3)).*L(:,:,:,3,1))- ...
                               2*drdl31; 
                           
    dgdl32 = 2*lambda*squeeze( (T(:,:,:,3,2)-Xnoisy(:,:,:,3,2)).*L(:,:,:,2,2) + ...
                               scale*(T(:,:,:,3,3)-Xnoisy(:,:,:,3,3)).*L(:,:,:,3,2))- ...
                               2*drdl32; 
    
    dgdl33 = 2*lambda*squeeze( scale*(T(:,:,:,3,3)-Xnoisy(:,:,:,3,3)).*L(:,:,:,3,3))- ...
                               2*drdl33; 

                           
    Lnew(:,:,:,1,1) = L(:,:,:,1,1)  - dt*dgdl11;
    Lnew(:,:,:,2,1) = L(:,:,:,2,1)  - dt*dgdl21;
    Lnew(:,:,:,2,2) = L(:,:,:,2,2)  - dt*dgdl22;
    Lnew(:,:,:,3,1) = L(:,:,:,3,1)  - dt*dgdl31;
    Lnew(:,:,:,3,2) = L(:,:,:,3,2)  - dt*dgdl32;
    Lnew(:,:,:,3,3) = L(:,:,:,3,3)  - dt*dgdl33;
   
    L = Lnew;
    T = llt(L);
    
    rr=rl(T,dx,dy,dz);

    g(i) = minim_func(llt(L),lambda,Xnoisy,dx,dy,dz);
end


function ipuv = ip(u,v,w,dx,dy,dz)
    ipuv = (dx*dy*dx)*sum(sum(sum(u.*v.*w)));

    
function nrm = l2norm(u,dx,dy,dz)
    nrm = sqrt(ip(u,u,u,dx,dy,dz));

    
function tv=tvnorm(u,dx,dy,dz)
    ep=1e-4;
    tv=(dx*dy*dz)*sum(sum(sum(sqrt(derx(u,dx).^2 + dery(u,dy).^2 + derz(u,dz).^2 + ep))));

    
%function x = rl(L,dx,dy,dz)
%x = sqrt(  tvnorm(squeeze(L(:,:,:,1,1)).^2,dx,dy,dz)^2 ... 
%         + 2*tvnorm(squeeze(L(:,:,:,1,1).*L(:,:,:,2,1)),dx,dy,dz)^2 ...
%         + tvnorm(squeeze(L(:,:,:,2,1).^2+L(:,:,:,2,2)).^2,dx,dy,dz)^2 ...
%         + 2*tvnorm(squeeze(L(:,:,:,1,1).*L(:,:,:,3,1)),dx,dy,dz)^2 ...
%         + 2*tvnorm(squeeze(L(:,:,:,2,1).*L(:,:,:,3,1) + L(:,:,:,2,2).*L(:,:,:,3,2)),dx,dy,dz)^2 ...
%         + tvnorm(squeeze(L(:,:,:,3,1).^2+L(:,:,:,3,2).^2+L(:,:,:,3,3).^2),dx,dy,dz)^2);
     
     
function x = rl(T,dx,dy,dz)

x = sqrt( tvnorm(squeeze(T(:,:,:,1,1)),dx,dy,dz).^2 + ... 
        2*tvnorm(squeeze(T(:,:,:,2,1)),dx,dy,dz).^2 + ...
        tvnorm(squeeze(T(:,:,:,2,2)),dx,dy,dz).^2 + ...
        2*tvnorm(squeeze(T(:,:,:,3,1)),dx,dy,dz).^2 + ...
        2*tvnorm(squeeze(T(:,:,:,3,2)),dx,dy,dz).^2 + ...
        tvnorm(squeeze(T(:,:,:,3,3)),dx,dy,dz).^2);    
     
function pu_tv=p(u,dx,dy,dz)
pu=curventure3d(u,dx,dy,dz);
pu_tv=pu*tvnorm(u,dx,dy,dz);
          

function f = minim_func(T,lambda,Xnoisy,dx,dy,dz)   
f = sqrt(tvnorm(squeeze(T(:,:,:,1,1)),dx,dy,dz)^2 + ...
         2*tvnorm(squeeze(T(:,:,:,2,1)),dx,dy,dz)^2 + ...
         tvnorm(squeeze(T(:,:,:,2,2)),dx,dy,dz)^2 + ...
         2*tvnorm(squeeze(T(:,:,:,3,1)),dx,dy,dz)^2 + ...
         2*tvnorm(squeeze(T(:,:,:,3,2)),dx,dy,dz)^2 + ...
         tvnorm(squeeze(T(:,:,:,3,3)),dx,dy,dz)^2);% + ...     
         (lambda/2)*(l2norm(squeeze( T(:,:,:,1,1)-Xnoisy(:,:,:,1,1)),dx,dy,dz)^2 + ...
         2*l2norm(squeeze( T(:,:,:,2,1))-squeeze(Xnoisy(:,:,:,2,1)),dx,dy,dz)^2 + ...
         l2norm(squeeze( T(:,:,:,2,2))-squeeze(Xnoisy(:,:,:,2,2)),dx,dy,dz)^2 + ...
         2*l2norm(squeeze( T(:,:,:,3,1))-squeeze(Xnoisy(:,:,:,3,1)),dx,dy,dz)^2 + ...
         2*l2norm(squeeze( T(:,:,:,3,2))-squeeze(Xnoisy(:,:,:,3,2)),dx,dy,dz)^2 + ...
         l2norm(squeeze( T(:,:,:,3,3))-squeeze(Xnoisy(:,:,:,3,3)),dx,dy,dz)^2);           
     
         

function [D] = curventure3d(u,dx,dy,dz)

u = addsam(u);

[m n z] = size(u);

tiny=1e-4;

%%%% Finn uxx %%%%

uy=(u([2:end],:,:)-u([1:end-1],:,:))/dy;
ux=(u(:,[2:end],:)-u(:,[1:end-1],:))/dx;
uz=(u(:,:,[2:end])-u(:,:,[1:end-1]))/dz;

Ly=(uy(1:end-1,1:end-1,:)+uy(1:end-1,2:end,:) ...
   +uy(2:end,1:end-1,:)+uy(2:end,2:end,:))/4;

Lz=(uz(:,1:end-1,1:end-1)+uz(:,1:end-1,2:end) ...
   +uz(:,2:end,1:end-1,:)+uz(:,2:end,2:end))/4;

uy_ny=zeros(m,n-1,z);uy_ny(2:end-1,:,:)=Ly;
uz_ny=zeros(m,n-1,z);uz_ny(:,:,2:end-1)=Lz;


normx=ux./sqrt(ux.^2+uy_ny.^2+uz_ny.^2+tiny);

uxx = (normx(:,[2:end],:)-normx(:,[1:end-1],:))/dx;


%%%% Finn uyy %%%%

Lx=(ux(1:end-1,1:end-1,:)+ux(1:end-1,2:end,:) ...
   +ux(2:end,1:end-1,:)+ux(2:end,2:end,:))/4;

Lz=(uz(1:end-1,:,1:end-1)+uz(1:end-1,:,2:end) ...
   +uz(2:end,:,1:end-1)+uz(2:end,:,2:end))/4;

ux_ny=zeros(m-1,n,z);ux_ny(:,2:end-1,:)=Lx;
uz_ny=zeros(m-1,n,z);uz_ny(:,:,2:end-1)=Lz;

normy=uy./sqrt(ux_ny.^2+uy.^2+uz_ny.^2+tiny);

uyy = (normy([2:end],:,:)-normy([1:end-1],:,:))/dy;


%%%% Finn uzz %%%%

Lx=(ux(:,1:end-1,1:end-1)+ux(:,1:end-1,2:end,:) ... 
   +ux(:,2:end,1:end-1)+ux(:,2:end,2:end))/4;
    
Ly=(uy(1:end-1,:,1:end-1)+uy(1:end-1,:,2:end) ... 
   +uy(2:end,:,1:end-1)+uy(2:end,:,2:end))/4;

ux_ny=zeros(m,n,z-1);ux_ny(:,2:end-1,:)=Lx;
uy_ny=zeros(m,n,z-1);uy_ny(2:end-1,:,:)=Ly;

normz=uz./sqrt(ux_ny.^2+uy_ny.^2+uz.^2+tiny);

uzz = (normz(:,:,[2:end])-normz(:,:,[1:end-1]))/dz;

D = uxx(2:end-1,:,2:end-1) + uyy(:,2:end-1,2:end-1) + uzz(2:end-1,2:end-1,:);    


function  u = addsam(u)

u = cat(1,u(1,:,:),u,u(end,:,:));
u = cat(2,u(:,1,:),u,u(:,end,:));
u = cat(3,u(:,:,1),u,u(:,:,end));


function du = derx(u,dx)

du = zeros(size(u));

du(:,2:end-1,:)=(u(:,3:end,:)-u(:,1:end-2,:))/(2*dx);
du(:,1,:) = du(:,2,:);
du(:,end,:)=du(:,end-1,:);


function du = dery(u,dy)

du = zeros(size(u));

du(2:end-1,:,:)=(u(3:end,:,:)-u(1:end-2,:,:))/(2*dy);
du(1,:,:) = du(2,:,:);
du(end,:,:)=du(end-1,:,:);


function du = derz(u,dz)

du = zeros(size(u));

du(:,:,2:end-1)=(u(:,:,3:end)-u(:,:,1:end-2))/(2*dz);
du(:,:,1) = du(:,:,2);
du(:,:,end)=du(:,:,end-1);


function L = tensorchol(D)

% performs cholesky factorization of the matrix D
[m,n,l,dummy1,dummy2]=size(D);
L=zeros(size(D));
for i=1:m
    for j = 1:n
        for k=1:l
            L(i,j,k,:,:) = chol(squeeze(D(i,j,k,:,:)))'; %matlab returns U: upper triangular matrix
        end
    end
end


function x = llt(L)

[m,n,l,dummy,dummy2]= size(L);
for i = 1:m
    for j = 1:n
        for k=1:l
            x(i,j,k,:,:) = squeeze(L(i,j,k,:,:))*squeeze(L(i,j,k,:,:))';
        end
    end
end    