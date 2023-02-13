package id_315399188_312126410;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import id_315399188_312126410.exeptions.NameContainIllegalChars;

public class BallotBox<T extends Citizen> implements Serializable {
	private static int serialNumber = 1;
	private int serial;
	private String address;
	private ArrayList<T> citizens;
	private double votingPrecent;

	public BallotBox(String address) throws NameContainIllegalChars {
		setAddress(address);
		citizens = new ArrayList<T>();
		votingPrecent = 0;
		this.serial = serialNumber++;

	}

	public static void resetBallotBoxesSerialNumber() {
		BallotBox.serialNumber = 1;
	}

	public boolean addVoter(T c) {
		citizens.add(c);
		return true;

	}

	public void calculateVotingPrecentage() {
		int didntVoteCounter = 0;
		for (Citizen c1 : citizens) {
			if (!(c1.isHasVoted())) {
				didntVoteCounter++;
			}
		}
		if (citizens.size() != 0) {
			double result = (didntVoteCounter * 100) / citizens.size();
			votingPrecent = 100 - result;
		}

	}

	public String countVotes(ArrayList<Party> partyList) {
		StringBuffer sb = new StringBuffer();
		for (Party p1 : partyList) {
			int counter = 0;
			for (Citizen c1 : citizens) {
				if (c1.getVote() != null) {

					if (p1.getName().equals(c1.getVote().getName())) {
						counter++;
					}
				}
			}

			sb.append("The party " + p1.getName() + " won: " + counter + " votes \n");
		}

		return sb.toString();
	}

	public static int getSerialNumber() {
		return serialNumber;
	}

	public static void setSerialNumber(int serialNumber) {
		BallotBox.serialNumber = serialNumber;
	}

	public int getSerial() {
		return serial;
	}

	public boolean setSerial(int serial) {
		this.serial = serial;
		return true;
	}

	public String getAddress() {
		return address;
	}

	public boolean setAddress(String address) throws NameContainIllegalChars {

		for (int i = 0; i < address.length(); i++) {
			if (!(Character.isLetter(address.charAt(i)) || address.charAt(i) == ' ')) {
				throw new NameContainIllegalChars();
			}
		}
		this.address = address;
		return true;
	}

	public ArrayList<T> getCitizens() {
		return citizens;
	}

	public void setCitizens(ArrayList<T> citizens) {
		this.citizens = citizens;
	}

	public double getVotingPrecent() {
		return votingPrecent;
	}

	public boolean setVotingPrecent(double votingPrecent) {
		this.votingPrecent = votingPrecent;
		return true;
	}

	public int getNumOfCitizens() {
		return citizens.size();
	}

	public boolean equals(Object other) {
		if (!(other instanceof BallotBox)) {
			return false;
		} else {
			BallotBox p = (BallotBox) other;
			return this.getSerial() == p.getSerial();
		}
	}

	@Override
	public String toString() {
		return "Serial number: " + this.serial + " ,Location: " + this.address;
	}

}
