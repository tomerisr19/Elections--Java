package id_315399188_312126410;

import id_315399188_312126410.exeptions.BirthYearNotInRange;
import id_315399188_312126410.exeptions.IDMismachExeption;
import id_315399188_312126410.exeptions.NotInRange;
import id_315399188_312126410.exeptions.YearNotInRange;

public class CoronaSoldier extends Soldier implements SickAble {
	private boolean protectionGear;
	private int sickDays;

	public CoronaSoldier(String name, String id, int year, BallotBox ballotBoxType, boolean carryWeapon,
			boolean protectionGear, int sickDays) throws NotInRange, IDMismachExeption, BirthYearNotInRange {
		super(name, id, year, ballotBoxType, carryWeapon);
		this.protectionGear = protectionGear;
		this.sickDays = sickDays;
	}

	public boolean isProtectionGear() {
		return protectionGear;
	}

	public void setProtectionGear(boolean protectionGear) {
		this.protectionGear = protectionGear;
	}

	public int getSickDays() {
		return sickDays;
	}

	public void setSickDays(int sickDays) {
		this.sickDays = sickDays;
	}

	public boolean Sick() {
		return protectionGear;
	}

	@Override
	public String toString() {
		return super.toString() + ", Has protection Gear ? " + this.protectionGear + ", How many days he is sick ? "
				+ this.sickDays;
	}

}
