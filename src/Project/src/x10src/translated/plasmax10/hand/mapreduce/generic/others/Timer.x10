
public class Timer {

    private val startTimes: Rail[Double]!;
    private val elapsedTimes: Rail[Double]!;
    private val totalTimes: Rail[Double]!;


    public def this(n: Int) {
        startTimes = Rail.makeVar[Double](n, (Int) => 0.0);
        elapsedTimes = Rail.makeVar[Double](n, (Int) => 0.0);
        totalTimes = Rail.makeVar[Double](n, (Int) => 0.0);
    }


    public def this() {
        this(0);
    }

    public def start(n: Int): Void {
        startTimes(n) = System.currentTimeMillis();
    }

    public def start(): Void {
        start(0);       
    }

    public def stop(n: Int): Void {
        elapsedTimes(n) = System.currentTimeMillis() - startTimes(n);
        elapsedTimes(n) /= 1000;
        totalTimes(n) += elapsedTimes(n);
    }

    public def stop(): Void {
        stop(0);
    }

    public def readTimer(n: Int): Double {
        return totalTimes(n);
    }

    public def readTimer(): Double {
        return readTimer(0);
    }

    public def resetTimer(n: Int): Void {
        totalTimes(n) = 0;
        startTimes(n) = 0;
        elapsedTimes(n) = 0;
    }

    public def resetTimer(): Void {
        resetTimer(0);
    }

    public def resetAllTimers(): Void{
        var i: Int = 0;
        while (i < startTimes.length) {
            resetTimer(i);
            i++;
        }
    }
}


