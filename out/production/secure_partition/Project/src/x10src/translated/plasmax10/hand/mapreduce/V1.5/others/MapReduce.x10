
public class MapReduce {
    public static void main(String[] args) { 
	{	
		Timer tmr = new Timer();
		int count = 0;
	    tmr.start(count);
		new MR().run(); 
	    tmr.stop(count);
		System.out.println("Wall-clock time for mapreduce: " + tmr.readTimer(count) + " secs");
	}
	}
}

