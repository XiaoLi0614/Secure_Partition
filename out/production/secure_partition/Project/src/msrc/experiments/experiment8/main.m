

%a = [1 2 3];
%b = a;
%disp(a);
%disp(b);
%
%a(1,1) = 10
%disp(a);
%disp(b);
%
%myf(a);
%disp(a);
%disp(b);


%a = [1 2 3];
%b = a([1, 1], :);
%disp(b);
%a(:) = 5;
%disp(a);
%a(:) = [4 5 6];
%disp(a);


%a = [1 2 3; 4 5 6];
%b = [10 20 30 40 50 60];
%a(:) = b;
%disp(a);
%a = [1 2 3; 4 5 6];
%a(:) = b'
%disp(a);


a = [1 0 3; 4 5 0];
s = sum(a, 2);
disp(s);
nzero = find(a);
disp(nzero);
r = reshape(a, 3, 2);
disp(r);
disp(max(a));
disp(min(a));

%s =
%     4
%     9

%nzero =
%     1
%     2
%     4
%     5

%r =
%     1     5
%     4     3
%     0     0

