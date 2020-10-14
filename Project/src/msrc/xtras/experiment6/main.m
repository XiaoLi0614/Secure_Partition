m = [1 2 3,
     4 5 6,
     7 8 9];
println('m = ')
println(m)
hv = [1 2 3 4 5 6]
println('hv = ')
println(hv)
vv = [1, 2, 3, 4, 5, 6]
println('vv = ')
println(vv)
println('')

println('Simple indexing')
println('m(2,2) =')
println(m(2,2))
asser1 = (m(2,2) == 5)
check(asser1)

println('Multi indexing')
println('m([1 3], [1 3]) =')
println(m([1 3], [1 3]))
a2 = (
        m([1 3], [1 3]) ==
        [1 3,
         7 9]
     );
asser2 = allTrue(a2)
check(asser2)


println('Simple and Multi indexing 1')
println('m(1, [1 3]) =')
println(m(1, [1 3]))
a3 = (
        m(1, [1 3]) ==
        [1 3]
     );
asser3 = allTrue(a3)
check(asser3)


println('Simple and Multi indexing 2')
println('m([1 3], 1) =')
println(m([1 3], 1))
a4 = (
        m([1 3], 1) ==
        [1,
         7]
     );
asser4 = allTrue(a4)
check(asser4)


println('Colon - Test 1')
println('m(:, [1 3]) =')
println(m(:, [1 3]))
a5 = (
        m(:, [1 3]) ==
        [1 3,
         4 6,
         7 9]
     );
asser5 = allTrue(a5)
check(asser5)

println('Colon - Test 2')
println('m([1, 3], :) =')
println(m([1 3], :))
a6 = (
        m([1 3], :) ==
        [1 2 3,
         7 8 9]
     );
asser6 = allTrue(a6)
check(asser6)

println('End - Test 1')
println('m(end, [1 3]) =')
println(m(end, [1 3]))
a7 = (
        m(end, [1 3]) ==
        [7 9]
     );
asser7 = allTrue(a7)
check(asser7)

println('End - Test 2')
println('m([1, 3], end) =')
println(m([1 3], end))
a8 = (
        m([1 3], end) ==
        [3,
         9]
     );
asser8 = allTrue(a8)
check(asser8)


println('Linear Indexing 1 - Test 1')
println('hv([1 5])')
println(hv([1 5]))
a9 = (
        hv([1 5]) ==
        [1 5]
     );
asser9 = allTrue(a9)
check(asser9)

println('Linear Indexing 1 - Test 2')
println('hv([1, 5])')
println(hv([1, 5]))
a10 = (
        hv([1, 5]) ==
        [1 5]
     );
asser10 = allTrue(a10)
check(asser10)

println('Linear Indexing 1 - Test 3')
println('vv([1 5])')
println(vv([1 5]))
a11 = (
        vv([1 5]) ==
        [1, 5]
     );
asser11 = allTrue(a11)
check(asser11)


println('Linear Indexing 1 - Test 4')
println('vv([1, 5])')
println(vv([1, 5]))
a12 = (
        vv([1, 5]) ==
        [1, 5]
     );
asser12 = allTrue(a12)
check(asser12)

println('Linear Indexing 2 - Test 1')
println('m([1 5])')
println(m([1 5]))
a13 = (
        m([1 5]) ==
        [1 5]
     );
asser13 = allTrue(a13)
check(asser13)

println('Linear Indexing 2 - Test 2')
println('m([1, 5])')
println(m([1, 5]))
a14 = (
        m([1, 5]) ==
        [1, 5]
     );
asser14 = allTrue(a14)
check(asser14)

println('Linear Indexing 2 - Test 3')
println('hv([1 3, 2 5])')
println(hv([1 3, 2 5]))
a15 = (
        hv([1 3, 2 5]) ==
        [1 3, 2 5]
     );
asser15 = allTrue(a15)
check(asser15)

println('Linear Indexing 2 - Test 4')
println('vv([1 3, 2 5])')
println(vv([1 3, 2 5]))
a16 = (
        vv([1 3, 2 5]) ==
        [1 3, 2 5]
     );
asser16 = allTrue(a16)
check(asser16)

println('Linear Indexing 2 - Test 5')
println('m([1 3, 5 7])')
println(m([1 3, 5 7]))
a17 = (
        m([1 3, 5 7]) ==
        [1 3, 5 7]
     );
asser17 = allTrue(a17)
check(asser17)

