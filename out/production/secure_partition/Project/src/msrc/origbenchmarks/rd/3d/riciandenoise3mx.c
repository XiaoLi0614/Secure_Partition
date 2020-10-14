/*========================================================================
 *
 * RICIANDENOIES3MX.C  3D TV minimization for Rician denoising
 *
 * u = riciandenoise3mx(f,sigma,lambda,Tol) performs denoising on a 3D 
 * volume f with Rician noise with parameter sigma.  The denoised image u
 * is found as the minimizer of 
 *
 *         /                      / [ u^2 + f^2            u f    ]
 *    min  | |grad u| dx + lambda | [ --------- - log I0( ----- ) ] dx.
 *     u   /                      / [ 2 sigma^2          sigma^2  ]
 *
 * Parameter lambda >= 0 determines the strength of the denoising: smaller
 * lambda implies stronger denoising.  Tol specifies the stopping 
 * tolerance, the method stops when ||u^Iter - u^Iter-1||_inf < Tol.
 *
 *======================================================================*/
#include <math.h>
#include <string.h>
#include "mex.h"

/* Method Parameters */
#define DT          5.0
#define EPSILON     1.0E-20
#define MAXITER     500

/* Macro functions */
#define	ISSCALAR(P)	(mxIsDouble(P) && mxGetM(P) == 1 && mxGetN(P) == 1)
#define SQR(x) ((x)*(x))

/* Total variation minimization for rician denoising */
static void riciandenoise3(double *u, const double *f, 
int M, int N, int P,
double sigma, double lambda, double Tol)
{
    double *g;       /* Array storing 1/|grad u| approximation */
    double sigma2, gamma, r, ulast;
    bool Converged;
    int m, n, p;
    int Iter;    
    
    /* Initializations */
    sigma2 = SQR(sigma);
    gamma = lambda/sigma2;
    Converged = false;
    memcpy(u, f, sizeof(double)*M*N*P);  /* Initialize u = f */
    g = mxCalloc(M*N*P, sizeof(double)); /* Allocate temporary work array */        
        
    /*** Main gradient descent loop ***/
    for(Iter = 1; Iter <= MAXITER; Iter++)
    {
        /* Macros for referring to pixel neighbors */
        #define CENTER   (m+M*(n+N*p))
        #define RIGHT    (m+M*(n+N*p)+M)
        #define LEFT     (m+M*(n+N*p)-M)
        #define DOWN     (m+M*(n+N*p)+1)
        #define UP       (m+M*(n+N*p)-1)
        #define ZOUT     (m+M*(n+N*p+N))
        #define ZIN      (m+M*(n+N*p-N))        
        
        /* Approximate g = 1/|grad u| */
        for(p = 1; p < P-1; p++)
            for(n = 1; n < N-1; n++)
                for(m = 1; m < M-1; m++)
                    g[CENTER] = 1.0/sqrt( EPSILON
                       + SQR(u[CENTER] - u[RIGHT])
                       + SQR(u[CENTER] - u[LEFT])
                       + SQR(u[CENTER] - u[DOWN])
                       + SQR(u[CENTER] - u[UP])
                       + SQR(u[CENTER] - u[ZOUT])
                       + SQR(u[CENTER] - u[ZIN]));        
        
        /* Update u by a sem-implict step */
        Converged = true;
        
        for(p = 1; p < P-1; p++)
            for(n = 1; n < N-1; n++)
                for(m = 1; m < M-1; m++)
                {
                /* Evaluate r = I1(u*f/sigma^2) / I0(u*f/sigma^2) with
                 a cubic rational approximation. */
                    r = u[CENTER]*f[CENTER]/sigma2;
                    r = ( r*(2.38944 + r*(0.950037 + r)) )
                    / ( 4.65314 + r*(2.57541 + r*(1.48937 + r)) );
                /* Update u */
                    ulast = u[CENTER];
                    u[CENTER] = ( u[CENTER] + DT*(u[RIGHT]*g[RIGHT]
                       + u[LEFT]*g[LEFT] + u[DOWN]*g[DOWN] + u[UP]*g[UP]
                       + u[ZOUT]*g[ZOUT] + u[ZIN]*g[ZIN] 
                       + gamma*f[CENTER]*r) ) /
                    (1.0 + DT*(g[RIGHT] + g[LEFT] 
                    + g[DOWN] + g[UP] 
                    + g[ZOUT] + g[ZIN] + gamma));
                    
                /* Test for convergence */
                    if(fabs(ulast - u[CENTER]) > Tol)
                        Converged = false;
                }
        
        if(Converged)
            break;
    }
    
    /* Done, show exiting message */
    if(Converged)
        mexPrintf("Converged in %d iterations with tolerance %g.\n",
           Iter, Tol);
    else
        mexPrintf("Maximum iterations exceeded (MaxIter=%d).\n", MAXITER);
        
    mxFree(g);  /* Free temporary array */    
    return;
}

/* MEX gateway function, interface between C and MATLAB */
void mexFunction(int nlhs, mxArray *plhs[], int nrhs, const mxArray*prhs[])
{ 
    /* Input and ouput arguments */
    #define	F_IN	    prhs[0]
    #define	SIGMA_IN	prhs[1]
    #define	LAMBDA_IN	prhs[2]
    #define	TOL_IN	    prhs[3]
    #define	U_OUT	    plhs[0]
    double sigma, lambda, Tol;
    const int *Size;
    
       
    /* Input checking */
    if(nrhs != 4)
        mexErrMsgTxt("Four input arguments required.");
    else if(nlhs > 1)
        mexErrMsgTxt("Too many output arguments.");
    if(!mxIsDouble(F_IN) || mxIsComplex(F_IN) || 
    mxGetNumberOfDimensions(F_IN) !=3 || mxIsSparse(F_IN)) 
        mexErrMsgTxt("Image f must be a 3D real double array.");
    if(!ISSCALAR(SIGMA_IN) || !ISSCALAR(LAMBDA_IN) || !ISSCALAR(TOL_IN))
        mexErrMsgTxt("Invalid input.");
    
    sigma = mxGetScalar(SIGMA_IN);
    lambda = mxGetScalar(LAMBDA_IN);
    Tol = mxGetScalar(TOL_IN);
    
    if(sigma <= 0 || lambda < 0 || Tol <= 0)
        mexErrMsgTxt("Invalid input.");
    
    Size = mxGetDimensions(F_IN);
    /* Create output matrix */ 
    U_OUT = mxCreateNumericArray(3, Size, mxDOUBLE_CLASS, mxREAL);
    
    /* Call the main denoising routine */
    riciandenoise3(mxGetPr(U_OUT), mxGetPr(F_IN),
       Size[0], Size[1], Size[2], sigma, lambda, Tol);
    return;
}
