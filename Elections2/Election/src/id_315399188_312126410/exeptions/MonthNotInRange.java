package id_315399188_312126410.exeptions;


public class MonthNotInRange extends Exception{
	
	public MonthNotInRange ()
	{
		super ("The month must between 1 - 12 !");
	}
}
