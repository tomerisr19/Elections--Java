package id_315399188_312126410.exeptions;


public class BirthYearNotInRange extends Exception{
	
	public BirthYearNotInRange (int year)
	{
		super ("The year must be in range between 1880 - " + year);
	}
}
