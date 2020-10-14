% Tests a function definition, function call, assignment, multi assignment and return statements.

a = 2;
b = 4;

c = maxFun(a, b);
println('Single return value');
asser1 = (c == 4);
check(asser1);

println('Multi return value: Test 1');
[r1, r2] = minAndMaxFun(a, b);
asser2 = (r1 == 2) && (r2 == 4)
check(asser2);

println('Multi return value: Test 2');
r = minAndMaxFun(a, b);
asser3 = allTrue(r == [2 4])
check(asser3);

asser = asser1 && asser2 && asser3;

if (asser)
    disp('Success.');
else
    disp('Failure.');
end

