/*========================================================================
 *
 * RICIANDENOIESMX.C  Total variation minimization for Rician denoising
 *
 * u = riciandenoisemx(f,sigma,lambda,Tol) performs denoising on image f
 * with Rician noise with parameter sigma.  The denoised image u is found
 * as the minimizer of 
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
static void riciandenoise(double *u, const double *f, mwSize M, mwSize N,
double sigma, double lambda, double Tol)
{
    double *g;       /* Array storing 1/|grad u| approximation */
    double sigma2, gamma, r, ulast;
    bool Converged;
    mwSize m, n;
    int Iter;    
    
    /* Initializations */
    sigma2 = SQR(sigma);
    gamma = lambda/sigma2;
    Converged = false;
    memcpy(u, f, sizeof(double)*M*N);  /* Initialize u = f */
    g = mxCalloc(M*N, sizeof(double)); /* Allocate temporary work array */        
        
    /*** Main gradient descent loop ***/
    for(Iter = 1; Iter <= MAXITER; Iter++)
    {
        /* Macros for referring to pixel neighbors */
        #define CENTER   (m+n*M)
        #define RIGHT    (m+n*M+M)
        #define LEFT     (m+n*M-M)
        #define DOWN     (m+n*M+1)
        #define UP       (m+n*M-1)        
        
        /* Approximate g = 1/|grad u| */
        for(n = 1; n < N-1; n++)
            for(m = 1; m < M-1; m++)
                g[CENTER] = 1.0/sqrt( EPSILON
                   + SQR(u[CENTER] - u[RIGHT])
                   + SQR(u[CENTER] - u[LEFT])
                   + SQR(u[CENTER] - u[DOWN])
                   + SQR(u[CENTER] - u[UP]) );        
        
        /* Update u by a sem-implict step */
        Converged = true;
        
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
                   + gamma*f[CENTER]*r) ) /
                   (1.0 + DT*(g[RIGHT] + g[LEFT] + g[DOWN] + g[UP] + gamma));
                
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
    mwSize M,N;     
       
    /* Input checking */
    if(nrhs != 4)
        mexErrMsgTxt("Four input arguments required.");
    else if(nlhs > 1)
        mexErrMsgTxt("Too many output arguments.");
    if(!mxIsDouble(F_IN) || mxIsComplex(F_IN) || mxIsSparse(F_IN)) 
        mexErrMsgTxt("Image f must be a real double array.");
    if(!ISSCALAR(SIGMA_IN) || !ISSCALAR(LAMBDA_IN) || !ISSCALAR(TOL_IN))
        mexErrMsgTxt("Invalid input.");
    
    sigma = mxGetScalar(SIGMA_IN);
    lambda = mxGetScalar(LAMBDA_IN);
    Tol = mxGetScalar(TOL_IN);
    
    if(sigma <= 0 || lambda < 0 || Tol <= 0)
        mexErrMsgTxt("Invalid input.");
    
    M = mxGetM(F_IN); 
    N = mxGetN(F_IN);
    U_OUT = mxCreateDoubleMatrix(M, N, mxREAL); /* Create output matrix */ 
    
    /* Call the main denoising routine */
    riciandenoise(mxGetPr(U_OUT),mxGetPr(F_IN),M,N,sigma,lambda,Tol);
    return;
}
