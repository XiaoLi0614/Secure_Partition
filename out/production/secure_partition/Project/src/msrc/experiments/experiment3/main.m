% Tests math operations

disp('Check 1');
n11 = 2;
n12 = 3.1;
n13 = n11 + n12;
asser1 = (n13 == 5.1);
check(asser1);

disp('Check 2');
a21 = [1 2 3];
a22 = [1.1 2.2 3.3];
a23 = a21 + a22;
asser2 = allTrue(a23 == [2.1 4.2 6.3]);
check(asser2);

disp('Check 3');
a31 = logical([1 0 3]);
a32 = a31 .* 5;
%disp(a32);
asser3 = allTrue(a32 == [5 0 5]);
check(asser3);

disp('Check 4');
a41 = logical([1 2 0]);
a42 = 3 .* a41;
%disp(a42);
asser4 = allTrue(a42 == [3 3 0]);
check(asser4);

disp('Check 5');
a51 = 4;
a52 = -a51;
asser5 = (a52 == -4);
check(asser5);

disp('Check 6');
a61 = [1 2 3];
a62 = -a61;
asser6 = allTrue(a62 == [-1, -2, -3]);
check(asser6);

disp('Check 7');
a71 = [1 2 3];
a72 = [2 3 2];
a73 = a71 .^ a72;
asser7 = allTrue(a73 == [1 8 9]);
check(asser7);

disp('Check 8');
a81 = [1 2 3;
       2 3 4];
a82 = [2 3;
       1 2;
       3 2];
a83 = a81 * a82;
asser8 = allTrue(a83 == [13 13;
                         19 20]);
check(asser8);

disp('Check 9');
a91 = [1 2;
       2 3];
a92 = a91 * 2;
asser9 = allTrue(a92 == [2 4;
                         4 6]);
check(asser9);


disp('Check 10');
a101 = [1.0 2.0 3.0;
        4.0 5.0 6.0;
        7.0 8.0 9.0];
a102 = [2.0 3.0 4.0;
        1.0 2.0 3.0;
        6.0 7.0 4.0];
a103 = a101 / a102;
asser10 = doubleEq(a103, [0.0, 1.0, 0.0;
                          3.0, -2.0, 0.0;
                          6.0, -5.0, 0.0]);
check(asser10);

disp('Check 11');
a111 = [8 2 6;
        7 5 2];
a112 = a111 / 2;
disp(a112);
asser11 = doubleEq(a112, [4.0 1.0 3.0;
                          3.5 2.5 1.0]);
check(asser11);

disp('Check 12');
a121 = [1 2 3;
        4 5 6];
a122 = a121'
asser12 = allTrue(a122 == [1 4;
                           2 5;
                           3 6]);
check(asser12);


disp('Check 13');
a131 = [1 2 3;
        4 5 6];
a132 = a131 .^ 2
asser13 = allTrue(a132 == [1 4 9;
                           16 25 36]);
check(asser13);

disp('Check 14');
a141 = [2 3 4;
        1 2 3;
        6 7 4];
a142 = a141 ^ 3
asser14 = allTrue(a142 == [300   404   376;
                           204   276   260;
                           512   676   596]);
check(asser14);



asser = asser1 && asser2 && asser3 && asser4 && asser5 && asser6 && asser7 && asser8 && asser9 && ...
        asser10 && asser11 && asser12 && asser13 && asser14;

if (asser)
    disp('Success.');
else
    disp('Failure.');
end


