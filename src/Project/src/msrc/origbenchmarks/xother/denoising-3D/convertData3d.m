function [T] = convertData3d(x)

for i=1:size(x,1)
    for j=1:size(x,2)
        for k=1:size(x,3);
            xx = [x(i,j,k,1) x(i,j,k,2) x(i,j,k,3) 0 x(i,j,k,4) x(i,j,k,5) 0 0 x(i,j,k,6)];
            TT = reshape(xx,3,3);
            T(i,j,k,:,:) = TT+triu(TT',1);
        end
    end
end
         