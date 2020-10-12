% Tests relational and logical operators

d1 = 1.1
d2 = 2.2
da1 = [1.1 2.2 3.3;
       4.4 5.5 6.6]
da2 = [1.2 2.2 3.4;
       4.4 5.6 6.6]

disp('Relational - Primitives');
asser1 = (d2 >= d1);
check(asser1);

disp('Relational - Arrays');
ba2 = (da2 > da1);
asser2 = allTrue(ba2 == logical([1 0 1;
                                 0 1 0]));
check(asser2);

disp('Relational - Array and primitive - Test 1');
ba3 = (da2 > 2)
asser3 = allTrue(ba3 == logical([0 1 1;
                                 1 1 1]));
check(asser3);

disp('Relational - Array and primitive - Test 2');
ba4 = (2 > da2)
asser4 = allTrue(ba4 == logical([1 0 0;
                                 0 0 0]));
check(asser4);

disp('Short circuit and, or and not');
l51 = ~(logical(0) && logical(0));
l52 = (logical(0) || logical(1));
l53 = ~(logical(1) && logical(0));
l54 = (logical(1) || logical(1));
asser5 = l51 && l52 && l53 && l54;
check(asser5);

disp('Elementwise and, or and not - primitives');
l61 = (0 | 5);
l62 = (0 | 5);
l63 = (6 | 5);
l64 = (6 & 5);
l65 = ~(0 & 5);
asser6 = l61 && l62 && l63 && l64;
check(asser6);

disp('Elementwise and, or and not - arrays');
m71 = [1 2 0 0];
m72 = [1 0 1 0];
l71 = allTrue((m71 & m72) == logical([1 0 0 0]));
l72 = allTrue((m71 | m72) == logical([1 1 1 0]));
asser7 = l71 && l72;
check(asser7);

disp('Elementwise and, or and not - primitive and array 1');
m81 = [1 2 0 0];
l81 = allTrue((m81 & 5) == logical([1 1 0 0]));
l82 = allTrue((~(m81 | 5)) == logical([0 0 0 0]));
asser8 = l81 && l82;
check(asser8);

disp('Elementwise and, or and not - primitive and array 2');
m91 = [1 2 0 0];
l91 = allTrue((0 & m91) == logical([0 0 0 0]));
l92 = allTrue((~(5 | m91)) == logical([0 0 0 0]));
asser9 = l91 && l92;
check(asser9);


asser = asser1 && asser2 && asser3 && asser4 && asser5 && asser6 && asser7 && asser8 && asser9;

if (asser)
    disp('Success.');
else
    disp('Failure.');
end

