function y = recEven(x)
	if (x == 0)
		y = 0;
	else
		y = recOdd(x - 1);
    end
