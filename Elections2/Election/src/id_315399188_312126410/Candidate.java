package id_315399188_312126410;

import id_315399188_312126410.exeptions.BirthYearNotInRange;
import id_315399188_312126410.exeptions.IDMismachExeption;
import id_315399188_312126410.exeptions.NotInRange;
import id_315399188_312126410.exeptions.YearNotInRange;

public class Candidate extends Citizen implements Comparable<Candidate> {
	private Party party;
	private int primerisPosition;
	private boolean isInParty = false;

	public Candidate(String name, String id, int year, BallotBox ballotBoxType, Party party, int primerisPosition)
			throws NotInRange, IDMismachExeption, BirthYearNotInRange {
		super(name, id, year, ballotBoxType);
		this.party = party;
		this.primerisPosition = primerisPosition;
		this.isInParty = true;
	}

	public Party getParty() {
		return party;
	}

	public int getPrimerisPosition() {
		return primerisPosition;
	}

	public boolean setPrimerisPosition(int primerisPosition) {
		this.primerisPosition = primerisPosition;
		return true;
	}

	public boolean setParty(Party p) {
		if (this.isInParty) {
			System.out.println("This candidate is already in party");
		} else {
			this.party = p;
			System.out.println("The candidate added susecfully");
		}
		return true;
	}

	public boolean IsInParty() {
		return isInParty;
	}

	@Override
	public int compareTo(Candidate cn) {
		if (this.primerisPosition < cn.primerisPosition) {
			return -1;
		} else if (this.primerisPosition > cn.primerisPosition) {
			return 1;
		} else {
			return 0;
		}

	}

	public boolean setInParty(boolean isInParty) {
		this.isInParty = isInParty;
		return true;
	}

	@Override
	public String toString() {
		return super.toString() + " ,Party: " + this.party.getName() + " ,Primeris position: " + this.primerisPosition;
	}

}
