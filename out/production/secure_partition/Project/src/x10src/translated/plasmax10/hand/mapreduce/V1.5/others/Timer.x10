/*
!-------------------------------------------------------------------------!
!									  !
!	 N  A  S     P A R A L L E L	 B E N C H M A R K S  3.0	  !
!									  !
!			J A V A 	V E R S I O N			  !
!									  !
!                               T I M E R                                 !
!                                                                         !
!-------------------------------------------------------------------------!
!                                                                         !
!    This benchmark is a serial version of the NPB3_0_JAV  Timer code.    !      !									  !
!    Permission to use, copy, distribute and modify this software	  !
!    for any purpose with or without fee is hereby granted.  We 	  !
!    request, however, that all derived work reference the NAS  	  !
!    Parallel Benchmarks 3.0. This software is provided "as is" 	  !
!    without express or implied warranty.				  !
!									  !
!    Information on NPB 3.0, including the Technical Report NAS-02-008	  !
!    "Implementation of the NAS Parallel Benchmarks in Java",		  !
!    original specifications, source code, results and information	  !
!    on how to submit new results, is available at:			  !
!									  !
!	    http://www.nas.nasa.gov/Software/NPB/			  !
!									  !
!    Send comments or suggestions to  npb@nas.nasa.gov  		  !
!									  !
!	   NAS Parallel Benchmarks Group				  !
!	   NASA Ames Research Center					  !
!	   Mail Stop: T27A-1						  !
!	   Moffett Field, CA   94035-1000				  !
!									  !
!	   E-mail:  npb@nas.nasa.gov					  !
!	   Fax:     (650) 604-3957					  !
!									  !
!-------------------------------------------------------------------------!
!     Translation to Java and to MultiThreaded Code:			  !
!     Michael A. Frumkin					          !
!     Mathew Schultz	   					          !
!-------------------------------------------------------------------------!
*/

public class Timer{
  public static final int max_counters=64;
  // private static final dist(:rank==1) d = [0:max_counters]->here;
  private static final dist(:rank==1) d = [0:max_counters]->place.FIRST_PLACE;
  private static final double[:rank==1] start_time   = new double[d];
  private static final double[:rank==1] elapsed_time = new double[d];
  private static final double[:rank==1] total_time   = new double[d];

  public Timer(){
    int i=0;
    while (i<max_counters) {
      start_time[i]=0;
      elapsed_time[i]=0;
      total_time[i]=0;
      i++;
    }
  }

  public void start(int n){  
    start_time[n]=System.currentTimeMillis();
  }
  
  public void stop(int n){
    elapsed_time[n]=System.currentTimeMillis()-start_time[n];
    elapsed_time[n]/=1000;
    total_time[n] += elapsed_time[n];
  }
  
  public double readTimer(int n){
    return total_time[n];
  }
  
  public void resetTimer(int n){
    total_time[n]=0;
    start_time[n]=0;
    elapsed_time[n]=0;
  }
  
  public void resetAllTimers(){
    int i=0;
    while (i<max_counters) {
      resetTimer(i);
      i++;
    }
  }
}


