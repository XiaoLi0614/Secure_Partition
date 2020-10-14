public class Test2 {

	public static def main(args: Array[String]) {
        val c = 9; // (1)
        var a: Int = 3;
        async at (here.next()) { // (2)
//            a = 5;
            assert(c == 9); // (3)
        }
        assert(c == 9); // (6)
    }
}