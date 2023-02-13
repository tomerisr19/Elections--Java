package id_315399188_312126410;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class ElectionsManager implements Serializable {

	private ArrayList<Elections> electionsList;

	public ElectionsManager() {
		electionsList = new ArrayList<Elections>();
	}

	public void addElections(Elections e) {
		electionsList.add(e);
	}

	public ArrayList<Elections> getElections() {
		return electionsList;
	}

	public void showResultOfOneRound(int index) {
		electionsList.get(index).toString();
	}
}
