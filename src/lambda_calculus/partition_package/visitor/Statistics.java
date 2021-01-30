package lambda_calculus.partition_package.visitor;

import java.util.Date;

public class Statistics {
    public long ACheckTime;
    public long ACheckTimeS;
    //public long ACheckTimeE;

    public long CIntegrityCheckTime;
    public long CIntegrityCheckTimeS;
    //public long CIntegrityCheckTimeE;

    public long SIntegrityCheckTime;
    public long SIntegrityCheckTimeS;
    //public long SIntegrityCheckTimeE;

    public long totalCheckTime;
    public long totalCheckTimeS;
    //public long totalCheckTimeE;

    public int totalConstraints;

    public Statistics(){
        this.ACheckTime = 0;
        this.ACheckTimeS = 0;
        //this.ACheckTimeE = 0;
        this.CIntegrityCheckTime = 0;
        this.CIntegrityCheckTimeS = 0;
        //this.CIntegrityCheckTimeE = 0;
        this.SIntegrityCheckTime = 0;
        this.SIntegrityCheckTimeS = 0;
        //this.SIntegrityCheckTimeE = 0;
        this.totalCheckTime = 0;
        this.totalCheckTimeS = 0;
        //this.totalCheckTimeE = 0;
        this.totalConstraints = 0;
    }

    public void startACheck() {
        Date date = new Date();
        this.ACheckTimeS = date.getTime();
        return;
    }

    public void endACheck(){
        Date date = new Date();
        this.ACheckTime += (date.getTime() - this.ACheckTimeS);
        return;
    }

    public void startCIntegrityCheck() {
        Date date = new Date();
        this.CIntegrityCheckTimeS = date.getTime();
        return;
    }

    public void endCIntegrityCheck(){
        Date date = new Date();
        this.CIntegrityCheckTime += (date.getTime() - this.CIntegrityCheckTimeS);
        return;
    }

    public void startSIntegrityCheck() {
        Date date = new Date();
        this.SIntegrityCheckTimeS = date.getTime();
        return;
    }

    public void endSIntegrityCheck(){
        Date date = new Date();
        this.SIntegrityCheckTime += (date.getTime() - this.SIntegrityCheckTimeS);
        return;
    }

    public void startCheck(){
        Date date = new Date();
        this.totalCheckTimeS = date.getTime();
        return;
    }

    public void endCheck(){
        Date date = new Date();
        this.totalCheckTime += (date.getTime() - this.totalCheckTimeS);
        return;
    }

    public void addCons(){
        this.totalConstraints++;
        return;
    }

    public void printStatistics(){
        System.out.println("The total number of constraints checked is: " + this.totalConstraints
                + " withine " + this.totalCheckTime + " ms.");
        System.out.println("The total availability check time is: " + this.ACheckTime + " ms");
        System.out.println("The total CIntegrity check time is: " + this.CIntegrityCheckTime + " ms");
        System.out.println("The total SIntegrity check time is: " + this.SIntegrityCheckTime + " ms");
    }
}
