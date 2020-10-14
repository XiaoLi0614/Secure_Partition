
public class Check {

	public static def check(asser: Boolean): Void {
		if (asser) 
			Lib.disp("Passed");
		else 
			Lib.disp("Failed");
		Lib.disp(" ");
		return;
	}

}

