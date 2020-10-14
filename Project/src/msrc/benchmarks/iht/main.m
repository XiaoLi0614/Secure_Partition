
n = 512;
m = 230;
k = 13;
%n = 5;
%m = 4;
%k = 3;

iters = 1000;
tic;
result = ihtFun(n, m, k, iters);
time = toc;
disp('IHT error value:');
disp(result);
disp('Time in seconds:');
disp(time);

% Edited: Mohsen Lesani
