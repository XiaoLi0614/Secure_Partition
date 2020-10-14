function [x numSC] = makePosDef(x,scale)

numSC=1;
maxE=0;
num=1;
for i=1:size(x,1)
    for j=1:size(x,2)
        for k=1:size(x,3)
            xx = [x(i,j,k,1) x(i,j,k,2) x(i,j,k,3) 0 x(i,j,k,4) x(i,j,k,5) 0 0 x(i,j,k,6)];
            T = reshape(xx,3,3);
            T = T+triu(T',1);
            [V,D] = eig(T);
            if(sum(diag(D<0.00001))>0)
                D = diag(diag(D)<=0.00001)*0.01+diag(diag(D>0.00001).*diag(D));
                T = V*D*V';
                x(i,j,k,:) = [T(1,1) T(1,2) T(1,3) T(2,2) T(2,3) T(3,3)];
                num = num+1;
            end
            maxE = max(max(max(D)),maxE);
        end        
    end
end

if(scale)
    numSC = 1/maxE;
    x = x*numSC;
end