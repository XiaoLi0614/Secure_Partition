function b = fun(a)
    b = logical(1)
    b = a(1, 1)
    c = b && b
%    c = (b == b)

%    b = b && a(1, 1)

%[1, 10, 1, 1, 1, 3]
%						fun.m at 3:9
%						Indexed array - Elements of the two matrices Matrix[Bool](T6, T7), Matrix[Int](T28, T29)
%							Bool and
%							Int
%OrigConst
%Bool == Int at 0-3:9
%Subst
%T1->T2
%T2->T3
%T4->T5
%T6->T7
%T8->T9
%T10->T11
%T12->T13
%T11->T0
%T13->T3
%T7->Matrix[Int](T6, T7)
%T9->Matrix[Int](T6, T7)
%T0->Matrix[Bool](T6, T7)
%T3->Bool
%T5->Bool

%	    Alt no. 6-3
%		T0 == Matrix[Bool](T28, T29) at 0-3:9
%		Int == Int at 0-3:9
%		Int == Int at 0-3:9
%		T3 == Bool at 0-3:9




%T10 == T0 at 1-25:6
%T13 == T1 at 1-25:6
%T6 == Double at 1-10:6
%T8 == Double at 1-10:6
%T11 == Bool at 1-10:6
%Int == Int at 1-5:6
%Int == Int at 1-5:6
%Int == Int at 1-5:6
%Int == Int at 1-5:6
%Int == Int at 1-5:6
%Int == Int at 1-5:6
%Int == Int at 1-5:6
%Int == Int at 1-5:6
%Int == Int at 1-5:6
%T9 == Matrix[Int](3, 3) at 1-5:6
