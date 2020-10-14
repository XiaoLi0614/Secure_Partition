
load('rays30.mat','rays');
% rays is A
load('sino30.mat','sino');

fid = fopen('rays.format', 'w');

[n, m] = size(rays);

fprintf(fid, '%d', n);
fprintf(fid, '%d', m);

%rays=full(rays);

for i=1:n
    for j=1:m
        if (rays(i,j) > 0)
            r = full(rays(i,j));
            fprintf(fid, '%d', i);
            fprintf(fid, '%d', j);
            fprintf(fid, '%f', r);
        end
    end
end

fclose(fid);



fid = fopen('sino.format', 'w');

[n, m] = size(sino);

fprintf(fid, '%d', n);
fprintf(fid, '%d', m);


for i=1:n
    for j=1:m
        fprintf(fid, '%f', sino(i,j));
    end
end


fclose(fid);