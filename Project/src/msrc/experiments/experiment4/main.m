% Tests array indexing
asser0 = logical(1);
asser1 = logical(1);
asser2 = logical(1);
asser3 = logical(1);
asser4 = logical(1);
asser5 = logical(1);
asser6 = logical(1);
asser7 = logical(1);
asser8 = logical(1);
asser9 = logical(1);
asser10 = logical(1);
asser11 = logical(1);
asser12 = logical(1);
asser13 = logical(1);
asser14 = logical(1);
asser15 = logical(1);
asser16 = logical(1);
asser17 = logical(1);
asser18 = logical(1);
asser19 = logical(1);
asser20 = logical(1);

m = [1 2 3;
     4 5 6;
     7 8 9];
disp('m = ');
disp(m);
disp(' ');
hv = [1 2 3 4 5 6];
disp('hv = ');
disp(hv);
disp(' ');
vv = [1; 2; 3; 4; 5; 6];
disp('vv = ');
disp(vv);
disp(' ');


disp('---------------------------')
disp('Simple indexing');
disp('m(2,2) =');
disp(m(2,2));
asser1 = (m(2,2) == 5);
check(asser1);


disp('---------------------------')
disp('Multi indexing');
disp('m([1 3], [1 3]) =');
disp(m([1 3], [1 3]));
a2 = ( ...
        m([1 3], [1 3]) == ...
        [1 3;
         7 9] ...
     );
asser2 = allTrue(a2);
check(asser2);


disp('---------------------------')
disp('Simple and Multi indexing 1');
disp('m(1, [1 3]) =');
disp(m(1, [1 3]));
a3 = ( ...
        m(1, [1 3]) == ...
        [1 3] ...
     );
asser3 = allTrue(a3);
check(asser3);

disp('---------------------------')
disp('Simple and Multi indexing 2');
disp('m([1 3], 1) =');
disp(m([1 3], 1));
a4 = ( ...
        m([1 3], 1) == ...
        [1;
         7] ...
     );
asser4 = allTrue(a4);
check(asser4);
disp('---------------------------')


disp('Colon - Test 1');
disp('m(:, [1 3]) =');
disp(m(:, [1 3]));
a5 = ( ...
        m(:, [1 3]) == ...
        [1 3;
         4 6;
         7 9] ...
     );
asser5 = allTrue(a5);
check(asser5);
disp('---------------------------')

disp('Colon - Test 2');
disp('m([1, 3], :) =');
disp(m([1 3], :));
a6 = ( ...
        m([1 3], :) == ...
        [1 2 3;
         7 8 9] ...
     );
asser6 = allTrue(a6);
check(asser6);
disp('---------------------------')

disp('End - Test 1');
disp('m(end, [1 3]) =');
disp(m(end, [1 3]));
a7 = ( ...
        m(end, [1 3]) == ...
        [7 9] ...
     );
asser7 = allTrue(a7);
check(asser7);

disp('---------------------------')
disp('End - Test 2');
disp('m([1, 3], end) =');
disp(m([1 3], end));
a8 = ( ...
        m([1 3], end) == ...
        [3;
         9] ...
     );
asser8 = allTrue(a8);
check(asser8);

disp('---------------------------')
disp('Linear Indexing 1 - Test 1');
disp('hv([1 5])');
disp(hv([1 5]));
a9 = ( ...
        hv([1 5]) == ...
        [1 5] ...
     );
asser9 = allTrue(a9);
check(asser9);

disp('---------------------------')
disp('Linear Indexing 1 - Test 2');
disp('hv([1, 5])');
disp(hv([1; 5]));
a10 = ( ...
        hv([1; 5]) == ...
        [1 5] ...
     );
asser10 = allTrue(a10);
check(asser10);


disp('---------------------------')
disp('Linear Indexing 1 - Test 3');
disp('vv([1 5])');
disp(vv([1 5]));
a11 = ( ...
        vv([1 5]) == ...
        [1; 5] ...
     );
asser11 = allTrue(a11);
check(asser11);


disp('---------------------------')
disp('Linear Indexing 1 - Test 4');
disp('vv([1; 5])');
disp(vv([1; 5]));
a12 = ( ...
        vv([1; 5]) == ...
        [1; 5] ...
     );
asser12 = allTrue(a12);
check(asser12);


disp('---------------------------')
disp('Linear Indexing 2 - Test 1');
disp('m([1 5])');
disp(m([1 5]));
a13 = ( ...
        m([1 5]) == ...
        [1 5] ...
     );
asser13 = allTrue(a13);
check(asser13);

disp('---------------------------')
disp('Linear Indexing 2 - Test 2');
disp('m([1; 5])');
disp(m([1; 5]));
a14 = ( ...
        m([1; 5]) == ...
        [1; 5] ...
     );
asser14 = allTrue(a14);
check(asser14);


disp('---------------------------')
disp('Linear Indexing 2 - Test 3');
disp('hv([1 3; 2 5])');
disp(hv([1 3; 2 5]));
a15 = ( ...
        hv([1 3; 2 5]) == ...
        [1 3; 2 5] ...
     );
asser15 = allTrue(a15);
check(asser15);

disp('---------------------------')
disp('Linear Indexing 2 - Test 4');
disp('vv([1 3; 2 5])');
disp(vv([1 3; 2 5]));
a16 = ( ...
        vv([1 3; 2 5]) == ...
        [1 3; 2 5] ...
     );
asser16 = allTrue(a16);
check(asser16);


disp('---------------------------')
disp('Linear Indexing 2 - Test 5');
disp('m([1 3; 5 7])');
disp(m([1 3; 5 7]));
a17 = ( ...
        m([1 3; 5 7]) == ...
        [1 7; 5 3] ...
     );
asser17 = allTrue(a17);
check(asser17);

disp('---------------------------')
disp('Linear Indexing, Single index')
disp('m(6) =')
disp(m(6))
asser18 = (     ...
        m(6) == ...
        8       ...
      );

check(asser18)


disp('---------------------------')
disp('Single Colon')
disp('m(:) =')
disp(m(:))
a19 = (                             ...
        m(:) ==                     ...
        [1; 4; 7; 2; 5; 8; 3; 6; 9] ...
      );
asser19 = allTrue(a19)
check(asser19)

disp('---------------------------')
disp('Single End')
disp('m(end) =')
disp(m(end))
asser20 = (         ...
        m(end) ==   ...
        9           ...
      );

check(asser20)


b = logical([0 1 0; 1 0 1;0 0 0])
disp('b = ');
disp(b);
disp(' ');



disp('---------------------------')
disp('Logical Indexing')
disp('m(b) =')
disp(m(b))
a21 = (             ...
        m(b) ==     ...
        [4;         ...
         2;         ...
         6]         ...
      );
asser21 = allTrue(a21)
check(asser21)
disp('---------------------------')


asser = asser1 && asser2 && asser3 && asser4 && asser5 && asser6 && asser7 && asser8 && asser9 && ...
        asser10 && asser11 && asser12 && asser13 && asser14 && asser15 && asser16 && asser17 && ...
        asser18 && asser19 && asser20 && asser21;

if (asser)
    disp('Success.');
else
    disp('Failure.');
end





















%d = [1.1 2.3 3.3;
%     4.4 5.5 6.6;
%     7.6 8.4 9.8];

%e = [1   2.3 3.3;
%     4.4 5.5 6.6;
%     7.6 8.4 9.8];

