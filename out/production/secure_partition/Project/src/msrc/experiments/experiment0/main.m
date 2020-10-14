% Tests if, for, while, switch and break statements and also range vector constructors.

disp('if and for statements');
count = 0;
for i = 1:10
    if (mod(i, 2) == 1)
        count = count + 1;
    end
end
asser1 = (count == 5);
check(asser1);


disp('while and switch statements');
count = 0;
i = 3
while (i ~= 0)
    switch (i)
        case 1
            count = count + 1;
        case 2
            count = count + 1;
        otherwise
            count = count + 1;
    end
    i = i - 1;
    if (i == 1)
        break;
    end
end
asser2 = (count == 2);
check(asser2);

disp('Range vector creator');
a3 = 3:6;
asser3 = allTrue(a3 == [3 4 5 6]);
check(asser3);

disp('Range vector creator - with step');
a4 = 3:2:12;
asser4 = allTrue(a4 == [3 5 7 9 11]);
check(asser4);


asser = asser1 && asser2 && asser3 && asser4;

if (asser)
    disp('Success.');
else
    disp('Failure.');
end

