


load('rays30.mat','rays')
% rays is A
load('sino30.mat','sino');

% sinogram is b

% In 3D, the matrix "rays" can not be stored, so we have to define
% two functions, one is projection "rays X image", another is
% backprojection "rays^t X sino".


index = find(sum(rays,2)>0);
% Sum over each row
% Find the indices of the rows for which the sum is non-zero

A = rays(index,:);
% choose only these rows
% We have to delete all zero rows, because the measurement would be
% zero, no matter what the image is.

b = sino(index);
% Delete the same rows in b

result = EMpTV(A, b)
result
% The result is an image.

