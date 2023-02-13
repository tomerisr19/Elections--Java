package id_315399188_312126410;

import java.io.Serializable;

import id_315399188_312126410.exeptions.BirthYearNotInRange;
import id_315399188_312126410.exeptions.IDMismachExeption;
import id_315399188_312126410.exeptions.NotInRange;
import id_315399188_312126410.exeptions.PartyDosentExcist;
import id_315399188_312126410.exeptions.YearNotInRange;

public class Citizen implements Serializable {
	protected String name;
	protected String id;
	protected int year;
	protected BallotBox ballotBoxType;
	protected boolean hasVoted;
	protected Party vote;

	public Citizen(String name, String id, int year, BallotBox ballotBoxType)
			throws NotInRange, IDMismachExeption, BirthYearNotInRange {
		this.name = name;
		setId(id);
		setYear(year);
		this.ballotBoxType = ballotBoxType;
		hasVoted = false;
		vote = null;
	}

	public String getName() {
		return name;
	}

	public boolean setName(String name) {
		this.name = name;
		return true;
	}

	public String getId() {
		return id;
	}

	public boolean setId(String id) throws IDMismachExeption {

		if (id.length() != 9) {
			throw new IDMismachExeption();
		} else {
			for (int i = 0; i < id.length(); i++) {
				if (id.charAt(i) < '0' || id.charAt(i) > '9') {
					throw new IDMismachExeption();
				}
			}
		}
		this.id = id;
		return true;

	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public BallotBox getBallotBoxType() {
		return ballotBoxType;
	}

	public boolean setBallotBoxType(BallotBox ballotBoxType) {
		this.ballotBoxType = ballotBoxType;
		return true;
	}

	public boolean isHasVoted() {
		return hasVoted;
	}

	public boolean setHasVoted(boolean hasVoted) {
		this.hasVoted = hasVoted;
		return true;
	}

	public Party getVote() {
		return vote;
	}

	public boolean setVote(Party vote) throws PartyDosentExcist {
		if (vote == null && this.hasVoted == true) {
			throw new PartyDosentExcist();
		} else {
			this.vote = vote;
		}
		return true;

	}

	public boolean equals(Object other) {
		if (!(other instanceof Citizen)) {
			return false;
		} else {
			Citizen c = (Citizen) other;
			return this.id.equals(c.id);
		}
	}

	@Override
	public String toString() {
		return "Name: " + this.name + " ,ID: " + this.id + " ,Birth year: " + this.year + " ,BallotBox: "
				+ this.ballotBoxType;
	}

}
