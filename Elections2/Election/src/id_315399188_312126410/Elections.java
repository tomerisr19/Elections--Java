package id_315399188_312126410;

import java.io.Serializable;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import id_315399188_312126410.exeptions.BallotBoxAlreadyExcist;
import id_315399188_312126410.exeptions.BallotBoxTypeDontExicst;
import id_315399188_312126410.exeptions.BirthYearNotInRange;
import id_315399188_312126410.exeptions.CandidateAlreadyExcistInTheParty;
import id_315399188_312126410.exeptions.DontHaveProtectionExeption;
import id_315399188_312126410.exeptions.IDMismachExeption;
import id_315399188_312126410.exeptions.MonthNotInRange;
import id_315399188_312126410.exeptions.NameContainIllegalChars;
import id_315399188_312126410.exeptions.PartyAllreadyExist;
import id_315399188_312126410.exeptions.PartyDosentExcist;
import id_315399188_312126410.exeptions.PrimerisPositionAlreadyTaken;
import id_315399188_312126410.exeptions.SameIDExeption;
import id_315399188_312126410.exeptions.SickDaysExeption;
import id_315399188_312126410.exeptions.SoldierCantBeCandidate;
import id_315399188_312126410.exeptions.UnderAgeExeption;
import id_315399188_312126410.exeptions.YearNotInRange;

public class Elections implements Serializable {
	private int month;
	private int year;
	private MySet<Citizen> citizens;
	private ArrayList<Party> parties;
	private ArrayList<ArrayList<BallotBox>> ballotBoxes;

	public Elections(int month, int year) throws MonthNotInRange, YearNotInRange {
		setMonth(month);
		setYear(year);
		citizens = new MySet<Citizen>();
		parties = new ArrayList<Party>();
		ballotBoxes = new ArrayList<ArrayList<BallotBox>>();
		ballotBoxes.add(new ArrayList<BallotBox>());
		ballotBoxes.add(new ArrayList<BallotBox>());
		ballotBoxes.add(new ArrayList<BallotBox>());
		ballotBoxes.add(new ArrayList<BallotBox>());
		BallotBox.resetBallotBoxesSerialNumber();
	}
	/*
	 * Citizen ballotBox - 0 Corona Citizen ballotBox - 1 Soldiers ballotBox - 2
	 * Corona soldier ballotBox - 3
	 */

	public int getMonth() {
		return month;
	}

	public BallotBox getLowestCitizenBallotBox() {
		BallotBox b = ballotBoxes.get(0).get(0);
		int lowest = ballotBoxes.get(0).get(0).getNumOfCitizens();
		ArrayList<BallotBox> bList = ballotBoxes.get(0);
		for (BallotBox box : bList) {
			if (box.getNumOfCitizens() < lowest) {
				b = box;
				lowest = box.getNumOfCitizens();
			}
		}
		return b;
	}

	public BallotBox getLowestCoronaCitizenBallotBox() {
		BallotBox b = ballotBoxes.get(1).get(0);
		int lowest = ballotBoxes.get(1).get(0).getNumOfCitizens();
		ArrayList<BallotBox> bList = ballotBoxes.get(1);
		for (BallotBox box : bList) {
			if (box.getNumOfCitizens() < lowest) {
				b = box;
				lowest = box.getNumOfCitizens();
			}
		}
		return b;
	}

	public BallotBox getLowestSoldierBallotBox() {
		BallotBox b = ballotBoxes.get(2).get(0);
		int lowest = ballotBoxes.get(2).get(0).getNumOfCitizens();
		ArrayList<BallotBox> bList = ballotBoxes.get(2);
		for (BallotBox box : bList) {
			if (box.getNumOfCitizens() < lowest) {
				b = box;
				lowest = box.getNumOfCitizens();
			}
		}
		return b;
	}

	public BallotBox getLowestCoronaSoldierBallotBox() {
		BallotBox b = ballotBoxes.get(3).get(0);
		int lowest = ballotBoxes.get(3).get(0).getNumOfCitizens();
		ArrayList<BallotBox> bList = ballotBoxes.get(3);
		for (BallotBox box : bList) {
			if (box.getNumOfCitizens() < lowest) {
				b = box;
				lowest = box.getNumOfCitizens();
			}
		}
		return b;
	}

	public boolean addBallotBox(BallotBox b, int chosen) throws BallotBoxAlreadyExcist, NameContainIllegalChars {
		for (int i = 0; i < b.getAddress().length(); i++) {
			if (!(Character.isLetter(b.getAddress().charAt(i)) || b.getAddress().charAt(i) == ' ')) {
				throw new NameContainIllegalChars();
			}
		}
		for (ArrayList<BallotBox> bList : ballotBoxes) {
			for (BallotBox b1 : bList) {
				if (b1.equals(b)) {
					throw new BallotBoxAlreadyExcist();
				}
			}
		}
		switch (chosen) {
		case 0: {
			ballotBoxes.get(0).add(b);
			break;
		}
		case 1: {
			ballotBoxes.get(1).add(b);
			break;
		}
		case 2: {
			ballotBoxes.get(2).add(b);
			break;
		}
		case 3: {
			ballotBoxes.get(3).add(b);
			break;
		}
		default: {
			break;
		}
		}

		return true;
	}

	public boolean addCandidate(Candidate cn) throws CandidateAlreadyExcistInTheParty, UnderAgeExeption, SameIDExeption,
			DontHaveProtectionExeption, PartyDosentExcist, PrimerisPositionAlreadyTaken, BallotBoxTypeDontExicst,
			NameContainIllegalChars, SoldierCantBeCandidate, IDMismachExeption, BirthYearNotInRange, SickDaysExeption {
		if (cn.getParty() == null) {
			throw new PartyDosentExcist();
		}
		if (year - cn.getYear() >= 18 && year - cn.getYear() <= 21) {
			throw new SoldierCantBeCandidate();
		}

		ArrayList<Candidate> cnList = cn.getParty().getAllCandidates();
		for (Candidate cn1 : cnList) {
			if (cn.getPrimerisPosition() == cn1.getPrimerisPosition()) {
				throw new PrimerisPositionAlreadyTaken();
			} else if (cn1.equals(cn)) {
				throw new CandidateAlreadyExcistInTheParty();
			}
		}

		addCitizen(cn);
		cn.getParty().addCandidate(cn);
		return true;

	}

	public boolean addCitizen(Citizen c) throws UnderAgeExeption, SameIDExeption, DontHaveProtectionExeption,
			BallotBoxTypeDontExicst, NameContainIllegalChars, IDMismachExeption, BirthYearNotInRange, SickDaysExeption {
		if (c.getBallotBoxType() == null) {
			throw new BallotBoxTypeDontExicst();
		}
		if (c.getYear() < 1880 || c.getYear() > year) {
			throw new BirthYearNotInRange(year);
		}
		if ((this.year - c.getYear()) < 18) {
			throw new UnderAgeExeption();
		}
		if (c instanceof SickAble) {
			int sickDays = 0;
			if (c instanceof CoronaCitizen) {
				sickDays = ((CoronaCitizen) c).getSickDays();
			}
			if (c instanceof CoronaSoldier) {
				sickDays = ((CoronaSoldier) c).getSickDays();
			}
			if (c instanceof CoronaCandidate) {
				sickDays = ((CoronaCandidate) c).getSickDays();
			}
			if (sickDays <= 0) {
				throw new SickDaysExeption();
			}
		}
		for (int i = 0; i < c.getName().length(); i++) {
			if (!(Character.isLetter(c.getName().charAt(i)) || c.getName().charAt(i) == ' ')) {
				throw new NameContainIllegalChars();
			}
		}
		if (!(citizens.add(c))) {
			throw new SameIDExeption();
		}
		c.getBallotBoxType().addVoter(c);
		return false;

	}

	public boolean addParty(Party p)
			throws PartyAllreadyExist, DateTimeException, NameContainIllegalChars, YearNotInRange {
		if (p.getDateOfCreation().getYear() < 1948 || p.getDateOfCreation().getYear() > this.year) {
			throw new YearNotInRange(year);
		}
		for (Party p1 : parties) {
			if (p1.equals(p)) {
				throw new PartyAllreadyExist();
			}
		}
		for (int i = 0; i < p.getName().length(); i++) {
			if (!(Character.isLetter(p.getName().charAt(i)) || p.getName().charAt(i) == ' ')) {
				throw new NameContainIllegalChars();
			}
		}
		parties.add(p);
		return true;
	}

	public void setMonth(int month) throws MonthNotInRange {

		if (month > 12 || month < 1) {
			throw new MonthNotInRange();
		} else {
			this.month = month;
		}
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) throws YearNotInRange {
		int counter = 0;
		int num = year;
		while (num != 0) {
			num = num / 10;
			counter++;
		}
		if (year < 1948 || year > 2200) {
			throw new YearNotInRange(2200);
		} else {
			this.year = year;
		}

	}

	public MySet<Citizen> getCitizens() {
		return citizens;
	}

	public ArrayList<Party> getParties() {
		return parties;
	}

	public ArrayList<ArrayList<BallotBox>> getBallotBoxes() {
		return ballotBoxes;
	}

	public StringBuffer electionsResults() {
		StringBuffer stringBuffer = new StringBuffer();
		for (ArrayList<BallotBox> bList : ballotBoxes) {
			for (BallotBox b : bList) {
				b.calculateVotingPrecentage();
				System.out.println("Serial number: " + b.getSerial() + " ,Location: " + b.getAddress()
						+ " ,Voting precent: " + b.getVotingPrecent() + "%");
				System.out.println(b.countVotes(parties));
			}
		}
		for (Party p : parties) {
			int sumOfVotes = 0;
			for (int i = 0; i < citizens.getCurrentSize(); i++) {
				if (citizens.get(i).getVote() != null) {
					if (citizens.get(i).getVote().getName().equals(citizens.get(i).getName())) {
						sumOfVotes++;
					}
				}
			}
			stringBuffer.append(p.getName() + " won: " + sumOfVotes + " in the elections");
		}
		return stringBuffer;
	}

	public boolean equals(Object other) {
		if (!(other instanceof Elections)) {
			return false;
		} else {
			Elections e = (Elections) other;
			return this.month == e.month && this.year == e.year;
		}
	}

	@Override
	public String toString() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("Election month: " + this.month + " ,Election year: " + this.year + "\n");
		stringBuffer.append(electionsResults());
		return stringBuffer.toString();

	}

}
