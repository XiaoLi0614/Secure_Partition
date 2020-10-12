

public class Experiment {

    public static def main(args: Array[String]) {

        at (here)
            Console.OUT.println(here);

        at (here.next())
            Console.OUT.println(here);

/*
        val i = 2;
        if (i==3)
            if (true)
                Console.OUT.println("Hello");
            else
                Console.OUT.println("world");
*/


/*
        val p = Point.make(10);
        val i = inc(p);
        Console.OUT.println(i);
*/



        //val region: Region(2) = Region.make([0..9, 0..9]);
/*
        val region = Region.make([0..9, 0..9]);
        val dist = region -> here;
        val data = Array.make[Int](dist);

        for (var i: Int = 0; i < 10; i++)
            for (var j: Int = 0; j < 10; j++) {
                val p = Point.make([i, j]);
                data(p) = 10;
            }
*/
/*
        val width: Byte = 3;
        val height: Byte = 3;
        Console.OUT.print("1");
        val region = Region.make([0..(width-1), 0..(height-1)]);
*/

    }

}

