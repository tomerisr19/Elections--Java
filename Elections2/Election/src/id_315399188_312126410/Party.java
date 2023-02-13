package id_315399188_312126410;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Party implements Serializable {

	public enum eType {
		LEFT, RIGHT, CENTER
	};

	private String name;
	private LocalDate dateOfCreation;
	private eType ePelegType;
	private ArrayList<Candidate> allCandidates;
	protected int sumOfVotes;

	public Party(String name, int year, int month, int day, eType ePelegType) {
		this.name = name;
		this.dateOfCreation = LocalDate.of(year, month, day);
		this.ePelegType = ePelegType;
		this.allCandidates = new ArrayList<Candidate>();
		sumOfVotes = 0;
	}

	public String getName() {
		return name;
	}

	public boolean setName(String name) {
		this.name = name;
		return true;
	}

	public boolean addCandidate(Candidate cn) {
		allCandidates.add(cn);
		Collections.sort(allCandidates);
		;
		return true;
	}

	public eType getePelegType() {
		return ePelegType;
	}

	public boolean setePelegType(eType ePelegType) {
		this.ePelegType = ePelegType;
		return true;
	}

	public ArrayList<Candidate> getAllCandidates() {
		return allCandidates;
	}

	public void setAllCandidates(ArrayList<Candidate> allCandidates) {
		this.allCandidates = allCandidates;
	}

	public int getSumOfVotes() {
		return sumOfVotes;
	}

	public void setSumOfVotes(int sumOfVotes) {
		this.sumOfVotes = sumOfVotes;
	}

	public LocalDate getDateOfCreation() {
		return dateOfCreation;
	}

	public boolean setDateOfCreation(LocalDate dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
		return true;
	}

	public boolean equals(Object other) {
		if (!(other instanceof Party)) {
			return false;
		} else {
			Party p = (Party) other;
			return this.name.equalsIgnoreCase(p.name);
		}
	}

	@Override
	public String toString() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("Name: " + this.name + " ,Creation year: " + this.dateOfCreation + " ,Peleg: "
				+ this.ePelegType + "\n");
		stringBuffer.append("All candidates: \n");
		for (int i = 0; i < allCandidates.size(); i++) {
			stringBuffer.append((i + 1) + ")" + allCandidates.get(i).getName() + "\n");
			System.out.println();
		}
		return stringBuffer.toString();
	}

}
