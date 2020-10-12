/*
public class Test {
	public static def main(Rail[String]) {
		new Test();
	}
	
	val intArray = Array.make[Int](10, (point(i): Point) => i*i);
	public def this() {
		intArray(0) = 2;	
	}
}
*/





/*
class A {
	def f() {
	}
}

class PlaceCast {
	static def cast[T](t: T): T{self.location==here} = {
		return (t as T{self.location==here});
	}
}

public class Test {
	public static def main(Rail[String]) {
		val a: A = at(here.next()) new A();
		async (here.next()) {
			(PlaceCast.cast(a)).f();
		}
	}
}

class A {
	def f() {
	}
}

class Transfer {
	static final def transfer[T](t: T): T{self.location==here} = {
		return (t as T{self.location==here});
	}
}

public class Test {
	public static def main(Rail[String]) {
		val a: A = at(here.next()) new A();
		async (here.next()) {
			//(a as A!).f();
			(Transfer.transfer(a)).f();
		}
	}
}
*/



/*
public class Test[KI, TI] {
	public static def main(Rail[String]) {
		//val i = new AClass[A[B[Int]]]();
		
	}
	public def f() {
		val fun = (i:nat) => new HashSet[Pair[KI, TI]]();
		val REDUCER_COUNT = 5;
		val intermediateDist = Dist.makeUnique() as Dist!;
		val mapPartitionArray = Array.makeVal[Rail[Set[Pair[KI, TI]]]] (
            intermediateDist, (p:Point) => null
        );

	}
}
*/

/*
            (
                ((i):Point) => {
                    //[T, T1, KI, TI, T2]
                    val fun = (j:nat) => new HashSet[Pair[KI, TI]]();
                    val rail = Rail.makeVal[Set[Pair[KI, TI]]](REDUCER_COUNT, fun);
                    return rail;
                }
            )
*/
/*
class HashSet[T] extends x10.util.HashSet[T] {
	public def iterator():Iterator[T] {
		return null;
	}
}
class A[T] {}
class B[T] {}
*/
/*
class A[T] {
	val n = new Name[T]();
	
}

class Name[T] {
	//val t = new HashSet[Int]();
}
*/
/*
class A {
	def this() {
		val i = f(2);
	}
	
	proto def f(i: Int): Int {
		return g(i * 2);
	}
	
	proto def g(i: Int): Int {
		return i * 5;
	}
}


public class Test {
	public static def main(Rail[String])  {
		val a =  new A();
	}
}
*/

/*
class LocalTable {

    val a: Rail[long]{self.at(this)};
    val mask: int;
    
    def this(size:int) {
        mask = size-1;
        a = Rail.makeVar[long](size, (i:nat)=>i as long);
    }
    
    public def update(ran:long) {
        //a(ran&mask as int) ^= ran;
        val index = ran&mask as int;
        a(index) = a(index) ^ ran;
    }
}


class A {}
public class Test {
	val a: Int{self.at(this)};
	
	public def this() {
		a = at (this) new A();
	}
	
	public static def main(Rail[String])  {
		
	}
}
*/





/*
class CircularList[T] {
  val datum:T;
  val tail: CircularList[T];
   def this(d:T): CircularList[T] {
      this.datum = d;
      this.tail = this;
    }
    def this(ds:List[T]{length >= 1}):CircularList[T] {
        this.datum = ds(0);
        this.tail = ds.length == 1 ? this : new CircularList[T](ds.tail(), this);
    }
    private def this(ds:List[T]{length >= 1}, t:proto CircularList[T]):proto CircularList[T] {
         this.datum = ds(0);
         this.tail = ds.length == 1 ? t : new CircularList[T](ds.tail(), t);
    }
}

public class Test {
	public static def main(Rail[String])  {
		
	}
}
class C {
	public def this() {
		val d1 = new D(this);
		val this1 = d1.c;
		val d2 = D.f(this) as D!;
		val this2 = d2.c;
	}
}

class D {
	public val c: C;
	public def this(c: proto C) {
		this.c = c;
	}
	public static def f(c: proto C): D {
		return new D(c);
	}
}

*/
