import x10.util.Set;
import x10.util.Pair;

//-----------------------------------------------------------------------------------------
/*public */
class Timer {

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

//-----------------------------------------------------------------------------------------

/*public */
interface Mapper[T1, KI, TI] {
    public def map(x: T1): Set[Pair[KI, TI]];
}

//-----------------------------------------------------------------------------------------

/*public */
interface Reducer[TI, T2] {
    public def reduce(Set[TI]): T2;
}

//-----------------------------------------------------------------------------------------

/*public */
abstract class MapReducer[T1, KI, TI, T2]
    implements
    Mapper[T1, KI, TI], Reducer[TI, T2] {

    static class HashSet[T] extends x10.util.HashSet[T] {
        public def iterator():Iterator[T] {
            return null;
        }
    }

    private const REDUCER_COUNT = Place.MAX_PLACES;

    private var inputs: Set[T1]!;
    private var inputArray: Array[T1](1)!;
    private var mapPartitionArray: Array[Rail[Set[Pair[KI, TI]]]](1)!;

    public def this(inputs: Set[T1]) {
        this.inputs = inputs;
    }

    public def this() {
    }

    public def compute(inputs: Set[T1]) {
        this.inputs = inputs;
        phases();
    }

    public def compute() {
        if (inputs == null)
            throw new RuntimeException("Input data in needed.");
        phases();
    }

    private def phases() {
        distributePhase();
        mapPhase();
        redistributePhase();
        reducePhase();
    }

    private def distributePhase() {
        val inputRail = inputs.toRail() as Rail[T1]!;
        val inputDist = Dist.makeBlock(0..(inputRail.length - 1));
        inputArray = Array.makeVal[T1](
            inputDist,
            ((i):Point) => inputRail(i)
        );

        // Each place has any array of size REDUCER_COUNT.
        // Each place divides its intermediate data to REDUCER_COUNT parts
        // according to the intermediate key hashcodes.
        val intermediateDist = Dist.makeUnique() as Dist(1);
        mapPartitionArray = Array.makeVal[Rail[Set[Pair[KI, TI]]]] (
            intermediateDist,
            (
                (p:Point) => {
                    //[T, T1, KI, TI, T2]
                    val fun = (j:nat) => new HashSet[Pair[KI, TI]]();
                    val rail = Rail.makeVal[Set[Pair[KI, TI]]](REDUCER_COUNT, fun);
                    return rail as Rail[Set[Pair[KI, TI]]];
                }
            )
        )
        /*as Array[Rail[Set[Pair[KI, TI]]]]{rank == 1}*/;
    }


    private def mapPhase(): Void {
        finish
            ateach (p in inputArray) {
                val hereRail = mapPartitionArray([here.id] as Point(1)) as Rail[Set[Pair[KI, TI]]]!;

                val interms = map(inputArray(p as Point(1)));
                // We made a Rail from the HashMap because
                // there is no iterator() for HashMap in the current release!!!
                val intermsRail = interms.toRail() as Rail[Pair[KI, TI]]!;
                for(var i: Int = 0; i < intermsRail.length; i++) {
                    val interm = intermsRail(i);
                    val index = interm.first.hashCode() % REDUCER_COUNT;
                    hereRail(index).add(interm);
                }
            }
    }

    private def redistributePhase() {

    }

    private def reducePhase(): Void {

    }
}

//-----------------------------------------------------------------------------------------

public class MapReduceLauncher {
    static class HashSet[T] extends x10.util.HashSet[T] {
        public def iterator():Iterator[T] {
            return null;
        }
    }
    //!-
    abstract
    static class MyMapReducer extends MapReducer[String, String, Int, Int] {
                                      //MapReducer[T1, KI, TI, T2]

        //public def map(T1): Set[Pair[KI, TI]];
        public def map(line: String): Set[Pair[String, Int]] {
            var currentLine: String = line;
            val set = new HashSet[Pair[String, Int]]();
            currentLine += " ";
            while (line.length() > 0) {
                val index = currentLine.indexOf(" ");
                if (index <= 0)
                    break;
                val word = currentLine.substring(0, index + 1);
                set.add(Pair[String, Int](word, 1));
                currentLine = currentLine.substring(index + 1, currentLine.length());
            }
            return set;
        }

        //public def reduce(Set[TI]): T2;
        public def reduce(interms: Set[Int]): Int {
            return interms.size();
        }
    }

	public static def main(Rail[String])  {

		val timer = new Timer();
	    timer.start();
		//!+
		//val mapReducer = new MyMapReducer();
		//!+
		//mapReducer.compute();
	    timer.stop();
		Console.OUT.println("MapReduce\n" +
		                    "Wall-clock time: " +
		                    timer.readTimer() +
		                    " secs.");
	}
}

//-----------------------------------------------------------------------------------------

/*
Rail[Pair[KI, T1]] intermsRail = interms.toRail();
for(var i = 0; i < intermsRail.length(); i++) {

}
*/
