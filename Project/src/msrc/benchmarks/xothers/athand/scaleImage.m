function intImage = scaleImage(image)
% To write a function that scales an image in double type to int type
    intImage = zeros([n, m], 'uint8');
    maxV = 0;
    for i = 1:n
        for j = 1:m
            v = image(i, j);
            if (v > maxV)
                maxV = v;
            end
        end
    end
    for i = 1:m
        for j = 1:n
            intImage(j, i) = cast((image(j, i) / maxV) * 255, 'uint8');
        end
    end
