package id_315399188_312126410.exeptions;

public class SickDaysExeption extends Exception {
	public SickDaysExeption ()
	{
		super ("The number of sick days must to be larger than '0'");
	}
}
