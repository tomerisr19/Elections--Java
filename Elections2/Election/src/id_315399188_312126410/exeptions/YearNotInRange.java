package id_315399188_312126410.exeptions;


public class YearNotInRange extends Exception{
	
	public YearNotInRange (int year)
	{
		super ("The year must be in range between 1948 - " + year);
	}
}
