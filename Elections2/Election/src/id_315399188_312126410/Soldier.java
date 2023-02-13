package id_315399188_312126410;

import id_315399188_312126410.exeptions.BirthYearNotInRange;
import id_315399188_312126410.exeptions.IDMismachExeption;
import id_315399188_312126410.exeptions.NotInRange;
import id_315399188_312126410.exeptions.YearNotInRange;

public class Soldier extends Citizen {
	private boolean carryWeapon;

	public Soldier(String name, String id, int year, BallotBox ballotBoxType, boolean carryWeapon)
			throws NotInRange, IDMismachExeption, BirthYearNotInRange {
		super(name, id, year, ballotBoxType);
		this.carryWeapon = carryWeapon;
	}

	public void isCarryWeapon() {
		System.out.println("I'm carring weapon !");
	}

	public void carryWeapon() {
		this.carryWeapon = true;
	}

	@Override
	public String toString() {
		return super.toString() + ", Is the soldier carring a weapon ? " + this.carryWeapon;
	}

}
