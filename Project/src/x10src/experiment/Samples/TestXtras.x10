
//---------------------------------------------------------------------------
/*
		val iter: Iterator[Region.Scanner]! =
			(r.scanners() as (Iterator[Region.Scanner]!)); 
		while (iter.hasNext()) {
			var s: Region.Scanner! = (iter.next() as Region.Scanner!);
			val min0 = s.min(0);
			val max0 = s.max(0);
			Console.OUT.println(min0);
			Console.OUT.println(max0);
			for (var i0:int = min0; i0<=max0; i0++) {
				s.set(0,i0);
				val min1 = s.min(1);
				val max1 = s.max(1);
				for (var i1:int=min1; i1<=max1; i1++) {
					Console.OUT.println(i0 + ", " + i1); 
				}
			}
		} 		

/*
Here's the general scheme for the information used in scanning, illustrated for a region of rank r=4. Each axis Xi is bounded by two sets of halfspaces, min[i] and max[i], obtained from the region halfspaces by FME (resulting in the 0 coefficients as shown). Computing the bounds for Xi requires substituting X0 up to Xi-1 into each halfspace (as shown for the min[i]s) and taking mins and maxes. 0 1 2 r-1 min[0] A0 0 0 0 B X0 bounded by B / A0 A0 0 0 0 B X0 bounded by B / A0 ... ... min[1] A0 A1 0 0 B X1 bounded by (B+A0 X0) / A1 A0 A1 0 0 B X1 bounded by (B+A0 X0) / A1 ... ... min[2] A0 A1 A2 0 B X2 bounded by (B+A0 X0+A1 X1) / A2 A0 A1 A2 0 B X2 bounded by (B+A0 X0+A1 X1) / A2 ... ... min[3] A0 A1 A2 A3 B X3 bounded by (B+A0 X0+A1 X1+A2 X2) / A3 A0 A1 A2 A3 B X3 bounded by (B+A0 X0+A1 X1+A2 X2) / A3 ... ... In the innermost loop the bounds for X3 could be computed by substituting the known values of X0 through X2 into each halfspace. However, part of that computation can be pulled out of the inner loop by keeping track for each halfspace in in min[k] and each constraint in max[k] a set of partial sums of the form minSum[0] = B minSum[1] = B+A0 X0 ... minSum[k] = B+A0 X0+A1 X1+...+Ak-1 Xk-1 z (and similiarly for maxSum) and updating each partial sum minSum[i+1] (and similarly for maxSum[i+1]) every time Xi changes by minSum[i+1] := sum[i] + Ai Xi The loop bounds for Xk are then obtained by computing mins and maxes over the sum[k]/Ak for the halfspaces in elim[k]. 
*/
/*
class SQueue {

    private var isSent: Boolean = false;
    private var element: Int = 0;

    private var isReceived: Boolean = false;

    public def send(i: Int): Void {
        atomic {
            element = i;
            isSent = true;
        }
        when (isReceived) {
        }
    }

    public def receive(): Int = {
        var value: Int = 0;
        when(isSent) {
            value = element;   
        }
        atomic {
            isReceived = true;
        }
        return value;
    }
}

public class Test {
	public static def main(Rail[String]) {
		val queue = new SQueue();
		async {
			Console.OUT.println("Sending 1");
			atomic {
				queue.send(1);
			}
		}
		async {
			val value = queue.receive();
			Console.OUT.println("Received " + value);
		}
	}
}
*/
/*
class AtomicTest {
	var c: Int = 0;
	public def run() {
		for (var i: Int = 0; i < 1000; i++) {
			async {
				atomic {
					c += 1;
				}
			}
		}
	}
	public def run2() {
		val theClock = Clock.make(); 
		atomic {
			theClock.resume();
			c += 1;
		}
	}
}
*/
/*
	atomic {
		theClock.resume();
		c += 1;
	}
	method abstract global public x10.lang.Clock.resume(): x10.lang.Void: Only nonblocking methods can be called from nonblocking code.
*/

/*
	atomic {
		next;
		c += 1;
	}
	The next statement cannot be used in nonblocking code.
*/

/*
	class A {
			public val r = 1..10;	
			public val d: Dist = r -> here;
	}
	val a = new A();
	val p = a.d(5);
	
*/
/*
public class Test {
	public static def main(Rail[String])  {
		val a = new A(1, 1);
		Program.A_f(a, 5);
	}
}

class Program {
	public static def A_f(a: A, k: Int): Void {
		a.i = k;
	}
}

class A {
	public global var i: Int = 0;
	public global var j: Int = 0;
	
	public def this(i: Int, j: Int) {
		this.i = i;
		this.j = j;
	}
}

class Program {
	public static def A_f(a: A, k: Int): Void {
		a.i = k;
	}
}

class A {
	public global var i: Int = 0;
	public global var j: Int = 0;
	
	public def this(i: Int, j: Int) {
		this.i = i;
		this.j = j;
	}
}
*/

/*
		val r = (1..10) * (1..10);
		val d = r -> here;
		
		val place = d([1, 1]);
*/
/*
public class Test {
	public static def main(Rail[String])  {
		val r = 1..10;
		val d = r -> here; 
		
		val array = Array.make[Int](r, (point:Point) => 1);
		
		val intArray = Array.make[Int](r, (point:Point) => {
			return array(point);
		});

		val p = [5];
		val t = intArray(p);
	}
}


public class Test {
	public static def main(Rail[String])  {
		val r = 1..10;
		val d = r -> here; 
		
		val array: Array[Int](1) = Array.make[Int](r, (point:Point) => 1);
		
		val intArray: Array[Int](1) = Array.make[Int](r, (point:Point(1)) => {
			return array(point);
		});

		val p = [5];
		val t = intArray(p);
	}
}
*/
