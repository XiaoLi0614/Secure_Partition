% Tests array assignment

m = [1 2 3;
     4 5 6;
     7 8 9];
disp('m = ');
disp(m);
disp(' ');

%{
b = logical([0 1 0; 1 0 1;0 0 0])
disp('b = ');
disp(b);
disp(' ');
%}

disp('---------------------------')
disp('Simple indexing');
disp('m(2,2) = 9');
m(2,2) = 9;
asser1 = (m(2,2) == 9);
check(asser1);


disp('---------------------------')
disp('Multi indexing 1');
disp('m([1 3], [1 3]) = 9');
m([1 3], [1 3]) = 9;
a2 = ( ...
        m([1 3], [1 3]) == ...
        [9 9;
         9 9] ...
     );
asser2 = allTrue(a2);
check(asser2);

disp('---------------------------')
disp('Multi indexing 2');
disp('m([1 3], [1 3]) = [5 5; 5 5]');
m([1 3], [1 3]) = [5 5;
                   5 5];
a3 = ( ...
        m([1 3], [1 3]) == ...
        [5 5;
         5 5] ...
     );
asser3 = allTrue(a3);
check(asser3);


disp('---------------------------')
disp('Single and Multi indexing 1 - 1');
disp('m(1, [1 3]) = 3');
m(1, [1 3]) = 3;
a4 = (                  ...
        m(1, [1 3]) ==  ...
        [3 3]           ...
     );
asser4 = allTrue(a4);
check(asser4);

disp('---------------------------')
disp('Single and Multi indexing 1 - 2');
disp('m(1, [1 3]) = [4 4]');
m(1, [1 3]) = [4 4];
a5 = (                  ...
        m(1, [1 3]) ==  ...
        [4 4]           ...
     );
asser5 = allTrue(a5);
check(asser5);

disp('---------------------------')
disp('Single and Multi indexing 2 - 1');
disp('m([1 3], 1) = 5');
m([1 3], 1) = 5;
a6 = (                  ...
        m([1 3], 1) ==  ...
        [5; 5]          ...
     );
asser6 = allTrue(a6);
check(asser6);

disp('---------------------------')
disp('Single and Multi indexing 2 - 2');
disp('m([1 3], 1) = [6 6]');
m([1 3], 1) = [6; 6];
a7 = (                  ...
        m([1 3], 1) ==  ...
        [6; 6]          ...
     );
asser7 = allTrue(a7);
check(asser7);

disp('---------------------------')
disp('Linear Indexing 1');
disp('m([3 4; 6 7]) = 5');
m([3 4; 6 7]) = 5;
a8 = (                      ...
        m([3 4; 6 7]) ==    ...
        [5 5; 5 5]          ...
     );
asser8 = allTrue(a8);
check(asser8);

disp('---------------------------')
disp('Linear Indexing 1');
disp('m([3 4; 6 7]) = 5');
m([3 4; 6 7]) = [1 2; 3 4];
a9 = (                      ...
        m([3 4; 6 7]) ==    ...
        [1 2; 3 4]          ...
     );
asser9 = allTrue(a9);
check(asser9);

disp('---------------------------')
disp('Logical Indexing 1');
disp('m(logical([1 0 1; 0 0 0; 0 1 0])) = 5');
m = [1 2 3;
     4 5 6;
     7 8 9];
i10 = logical([1 0 1; 0 0 0; 0 1 0])
m(i10) = 5;
a10 = (             ...
        m(i10) ==   ...
        [5; 5; 5]   ...
     );
asser10 = allTrue(a10);
check(asser10);

disp('---------------------------')
disp('Logical Indexing 1');
disp('m(logical([1 0 1; 0 0 0; 0 1 0])) = [1 2 3]');
m = [1 2 3;
     4 5 6;
     7 8 9];
i11 = logical([1 0 1; 0 0 0; 0 1 0])
m(i11) = [1 2 3];
a11 = (             ...
        m(i11) ==   ...
        [1; 2; 3]   ...
     );
asser11 = allTrue(a11);
check(asser11);

disp('---------------------------')
disp('Colon Assignment 1');
disp('m(:) = 5');
m = [1 2 3;
     4 5 6;
     7 8 9];
m(:) = 5;
a12 = (             ...
        m ==        ...
        [5 5 5;     ...
         5 5 5;     ...
         5 5 5]     ...
     );
asser12 = allTrue(a12);
check(asser12);

disp('---------------------------')
disp('Colon Assignment 2');
disp('m(:) = [9 8 7; 6 5 4;3 2 1];');
m = [1 2 3;
     4 5 6;
     7 8 9];
m(:) = [9 8 7;
        6 5 4;
        3 2 1];
a13 = (             ...
        m ==        ...
        [9 8 7;     ...
         6 5 4;     ...
         3 2 1]     ...
     );
asser13 = allTrue(a13);
check(asser13);


disp('---------------------------')

asser = asser1 && asser2 && asser3 && asser4 && asser5 && asser6 && asser7 && asser8 && asser9 && ...
        asser10 && asser11 && asser12 && asser13;

if (asser)
    disp('Success.');
else
    disp('Failure.');
end





