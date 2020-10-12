function newImage = TV(inputImage, V, alpha, maxIter)

% this is the iteration used to solve the TV problem.

[M, N] = size(inputImage);

epsilon = 1e-8;
image1 = inputImage;
image2 = zeros(M,N);


for k = 1:1:maxIter
    for i=2:1:M-1
        for j=2:1:N-1
            c1(i,j)=image1(i,j)/sqrt(epsilon+(image1(i+1,j)-image1(i,j))^2+(image1(i,j+1)-image1(i,j))^2)/V(i,j);
        end
    end
    for i=2:1:M-1
        for j=2:1:N-1
            c2(i,j)=image1(i,j)/sqrt(epsilon+(image1(i,j)-image1(i-1,j))^2+(image1(i-1,j+1)-image1(i-1,j))^2)/V(i,j);
        end
    end
    for i=2:1:M-1
        for j=2:1:N-1
            c3(i,j)=image1(i,j)/sqrt(epsilon+(image1(i+1,j)-image1(i,j))^2+(image1(i,j+1)-image1(i,j))^2)/V(i,j);
        end
    end
    for i=2:1:M-1
        for j=2:1:N-1
            c4(i,j)=image1(i,j)/sqrt(epsilon+(image1(i+1,j-1)-image1(i,j-1))^2+(image1(i,j)-image1(i,j-1))^2)/V(i,j);
        end
    end


    for i=2:1:M-1
        for j=2:1:N-1
            if image1(i,j) < epsilon
                image2(i,j) = image1(i,j);
            else
            image2(i,j)=1/(alpha+c1(i,j)+c2(i,j)+c3(i,j)+c4(i,j))*(alpha*inputImage(i,j)+c1(i,j)*image1(i+1,j)+c2(i,j)*image1(i-1,j)+c3(i,j)*image1(i,j+1)+c4(i,j)*image1(i,j-1));
            end
        end
    end

    image1 = image2;
end
newImage = image1;


