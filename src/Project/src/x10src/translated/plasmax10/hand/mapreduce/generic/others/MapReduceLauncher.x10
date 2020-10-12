import x10.util.Set;
import x10.util.Pair;


public class MapReduceLauncher {
    static class HashSet[T] extends x10.util.HashSet[T] {
        public def iterator():Iterator[T] {
            return null;
        }
    }

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
		val mapReducer = new MyMapReducer();
		mapReducer.compute();
	    timer.stop();
		Console.OUT.println("MapReduce\n" +
		                    "Wall-clock time: " +
		                    timer.readTimer() +
		                    " secs.");
	}
}

