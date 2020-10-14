function x = EMpTV(A, b)

    V = reshape(sum(A), 256, 256);
%    V = full(V);

    alpha = 5;

    x = ones(256*256, 1);

    for i=1:300
        x = EM(A, b, x, 3);

        % Reshape to 2D
        x = reshape(x, 256, 256);

        x(1, :) = zeros(1, 256);
        x(256, :) = zeros(1, 256);
        x(:, 1) = zeros(256, 1);
        x(:, 256) = zeros(256, 1);


        x = TV(x, V, alpha, 6);

        % Reshape to 1D
        x = reshape(x, 256*256, 1);

        % Shrink the image to be bounded by (0, 1).
        % This can be changed depending on the exact problem.
        x = max(x, 0);
        x = min(x, 1);

    end

