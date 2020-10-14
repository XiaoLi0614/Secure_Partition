package lesani.math.Jama.add;

import lesani.math.Jama.Matrix;
import lesani.math.Jama.SingularValueDecomposition;

public class Matrices {
 /**
  * The difference between 1 and the smallest exactly representable number
  * greater than one. Gives an upper bound on the relative error due to
  * rounding of floating point numbers.
  */
 public static double MACHEPS = 2E-16;

 /**
  * Updates MACHEPS for the executing machine.
  */
 public static void updateMacheps() {
  MACHEPS = 1;
  do
   MACHEPS /= 2;
  while (1 + MACHEPS / 2 != 1);
 }

 /**
  * Computes Moore–Penrose pseudoinverse using the SVD method.
  *
  * Modified version of the original implementation by Kim van der Linde.
  */
 public static Matrix pinv(Matrix x) {
  if (x.rank() < 1)
   return null;
  if (x.getColumnDimension() > x.getRowDimension())
      return pinv(x.transpose()).transpose();
  SingularValueDecomposition svdX = new SingularValueDecomposition(x);
  double[] singularValues = svdX.getSingularValues();
  double[][] u = svdX.getU().getArray();
  double[][] v = svdX.getV().getArray();
//    double maxS = singularValues[0];
//    for (int i = 0; i < singularValues.length; i++)
//        maxS = Math.max(singularValues[i], maxS);
     double tol = Math.max(x.getColumnDimension(), x.getRowDimension()) * /*maxS*/singularValues[0] * MACHEPS;
     double[] singularValueReciprocals = new double[singularValues.length];
     for (int i = 0; i < singularValues.length; i++)
   singularValueReciprocals[i] = Math.abs(singularValues[i]) < tol ? 0 : (1.0 / singularValues[i]);
  int min = Math.min(x.getColumnDimension(), u[0].length);
  double[][] inverse = new double[x.getColumnDimension()][x.getRowDimension()];
  for (int i = 0; i < x.getColumnDimension(); i++)
   for (int j = 0; j < u.length; j++)
    for (int k = 0; k < min; k++)
     inverse[i][j] += v[i][k] * singularValueReciprocals[k] * u[j][k];
  return new Matrix(inverse);
 }
/*
    function X = pinv(A,varargin)
    %PINV   Pseudoinverse.
    %   X = PINV(A) produces a matrix X of the same dimensions
    %   as A' so that A*X*A = A, X*A*X = X and A*X and X*A
    %   are Hermitian. The computation is based on SVD(A) and any
    %   singular values less than a tolerance are treated as zero.
    %   The default tolerance is MAX(SIZE(A)) * NORM(A) * EPS(class(A)).
    %
    %   PINV(A,TOL) uses the tolerance TOL instead of the default.
    %
    %   Class support for input A:
    %      float: double, single
    %
    %   See also RANK.

    %   Copyright 1984-2004 The MathWorks, Inc.
    %   $Revision: 5.12.4.2 $  $Date: 2004/12/06 16:35:27 $

    if isempty(A)     % quick return
      X = zeros(size(A'),class(A));
      return
    end

    [m,n] = size(A);

    if n > m
       X = pinv(A',varargin{:})';
    else
       [U,S,V] = svd(A,0);
       if m > 1, s = diag(S);
          elseif m == 1, s = S(1);
          else s = 0;
       end
       if nargin == 2
          tol = varargin{1};
       else
          tol = max(m,n) * eps(max(s));
       end
       r = sum(s > tol);
       if (r == 0)
          X = zeros(size(A'),class(A));
       else
          s = diag(ones(r,1)./s(1:r));
          X = V(:,1:r)*s*U(:,1:r)';
       end
    end
*/


    public static void main(String[] args) {
        updateMacheps();
        Matrix matrix = new Matrix(new double[][] {{1, 2, 3}, {4, 5, 6}});
        Matrix matrix2 = new Matrix(new double[][] {{1, 2}, {3, 4}, {5, 6}});
//        System.out.println(pinv(matrix.p));
        pinv(matrix).print(4, 4);
        pinv(matrix2).print(4, 4);
        System.out.println();

//        matrix.inverse().print(4, 4);
    }
}