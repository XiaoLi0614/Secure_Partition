function [m1, m2] = minAndMaxFun(x, y)
   if (x >= y)
      m1 = y;
      m2 = x
      return;
   else
      m1 = x
      m2 = y;
   end

