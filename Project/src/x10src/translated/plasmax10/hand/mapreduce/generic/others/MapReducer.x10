import x10.util.Set;
//import x10.util.HashSet;
import x10.util.Pair;

public abstract class MapReducer[T1, KI, TI, T2]
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
        val intermediateDist = Dist.makeUnique() as Dist(1)!;
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

