package id_315399188_312126410;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

import id_315399188_312126410.Party.eType;
import id_315399188_312126410.exeptions.BallotBoxAlreadyExcist;
import id_315399188_312126410.exeptions.BallotBoxTypeDontExicst;
import id_315399188_312126410.exeptions.BirthYearNotInRange;
import id_315399188_312126410.exeptions.CandidateAlreadyExcistInTheParty;
import id_315399188_312126410.exeptions.DontHaveProtectionExeption;
import id_315399188_312126410.exeptions.IDMismachExeption;
import id_315399188_312126410.exeptions.MonthNotInRange;
import id_315399188_312126410.exeptions.NameContainIllegalChars;
import id_315399188_312126410.exeptions.NotInRange;
import id_315399188_312126410.exeptions.PartyAllreadyExist;
import id_315399188_312126410.exeptions.PartyDosentExcist;
import id_315399188_312126410.exeptions.PrimerisPositionAlreadyTaken;
import id_315399188_312126410.exeptions.SameIDExeption;
import id_315399188_312126410.exeptions.SickDaysExeption;
import id_315399188_312126410.exeptions.SoldierCantBeCandidate;
import id_315399188_312126410.exeptions.UnderAgeExeption;
import id_315399188_312126410.exeptions.YearNotInRange;

public class Main implements MyApplication {

	public static void main(String[] args) throws NotInRange, IDMismachExeption, DontHaveProtectionExeption,
			CandidateAlreadyExcistInTheParty, UnderAgeExeption, SameIDExeption, PartyDosentExcist,
			PrimerisPositionAlreadyTaken, SickDaysExeption, FileNotFoundException, IOException, ClassNotFoundException {
		Main eMain = new Main();
		Scanner sc = new Scanner(System.in);
		ElectionsManager e = new ElectionsManager();
		Elections elections = null;
		boolean endProgram = true;
		elections = eMain.loadFile(e, elections);
		boolean readFile;
		if (elections != null) {
			readFile = true;
		} else {
			readFile = false;
		}

		do {
			if (!(readFile)) {
				int month, year;
				System.out.println("Please enter the election month");
				boolean monthCheck = true;
				month = 0;
				while (monthCheck) {
					try {
						month = sc.nextInt();
						monthCheck = false;
					} catch (InputMismatchException e1) {
						System.out.println("The month must contain only digits !");
						System.out.println("Please enter a new month:");
						sc.nextLine();
					}
				}
				System.out.println("Please enter the election year");
				boolean yearCheck = true;
				year = 0;
				while (yearCheck) {
					try {
						year = sc.nextInt();
						yearCheck = false;
					} catch (InputMismatchException e1) {
						System.out.println("The year must contain only digits !");
						System.out.println("Please enter a new year:");
						sc.nextLine();
					}
				}
				boolean error = true;
				while (error) {
					try {
						elections = new Elections(month, year);
						e.addElections(elections);
						error = false;

					} catch (MonthNotInRange e1) {
						System.out.println(e1.getMessage());
						System.out.println("Please enter a correct month");
						month = sc.nextInt();
					} catch (YearNotInRange e1) {
						System.out.println(e1.getMessage());
						System.out.println("Please enter a correct year");
						year = sc.nextInt();
					}
				}
			}

			int menu = 0;
			if (!(readFile)) {
				HardCoded(elections);
			}
			while (menu != 10) {
				System.out.println("Please select an option:");
				System.out.println("1 - Add ballot box");
				System.out.println("2 - Add citizen");
				System.out.println("3 - Add party");
				System.out.println("4 - Add candidate");
				System.out.println("5 - Show all ballot boxes");
				System.out.println("6 - Show all citizens");
				System.out.println("7 - Show all parties");
				System.out.println("8 - Start the elections");
				System.out.println("9 - Show election's results");
				System.out.println("10 - Exit");
				menu = sc.nextInt();

				switch (menu) {
				case (1): {
					eMain.addBallotBox(elections);
					break;
				}
				case (2): {
					eMain.addCitizen(elections);
					break;
				}
				case (3): {
					eMain.addParty(elections);
					break;
				}
				case (4): {
					eMain.addCandidate(elections);
					break;
				}
				case (5): {
					eMain.printBallotBoxes(elections);
					break;
				}
				case (6): {
					eMain.printCitizens(elections);
					break;
				}
				case (7): {
					eMain.printParties(elections);
					break;
				}
				case (8): {
					eMain.elections(elections);
					break;
				}
				case (9): {
					eMain.electionsResults(elections);
					break;
				}
				case (10): {
					endProgram = eMain.endProgram(endProgram);
					readFile = false;
					break;
				}
				default: {
					System.out.println("Wrong number, please choose number from the options");
					break;
				}

				}
			}

		} while (endProgram);
		boolean answer1 = true;
		do {
			System.out.println("Would you like to print the resluts of one of the election rounds ? (true / false)");
			boolean answer2 = true;
			while (answer2) {
				try {
					answer1 = sc.nextBoolean();
					answer2 = false;
				} catch (InputMismatchException e1) {
					System.out.println("Please enter true or false: ");
					sc.nextLine();
				}
			}
			if (answer1) {
				System.out.println("Please choose from this options: (enter a number)");
				int index = 0;
				for (Elections e1 : e.getElections()) {

					System.out.println((index + 1) + ") " + e1.getMonth() + "/" + e1.getYear());
					index++;
				}
				boolean printCheck = true;
				int choose = 0;
				while (printCheck) {
					try {
						choose = sc.nextInt();
						printCheck = false;
					} catch (InputMismatchException e1) {
						System.out.println("Please enter a number: ");
						sc.nextLine();
					}
				}
				while (choose > e.getElections().size() || choose < 1) {
					System.out.println("Please enter a number from the list: ");
					choose = sc.nextInt();
				}
				e.showResultOfOneRound((choose - 1));
			}
		} while (answer1);
		ObjectOutputStream outFile = new ObjectOutputStream(new FileOutputStream("elections.dat"));
		outFile.writeObject(e);
		outFile.close();

		System.out.println("Thank you and goodbye");
	}

	public Elections loadFile(ElectionsManager e, Elections elections) throws IOException, ClassNotFoundException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Would you like to load an election from the file ? (true / false)");
		boolean readFile = true;
		boolean readCheck = true;
		boolean chooseCheck = true;
		while (readCheck) {
			try {
				readFile = sc.nextBoolean();
				readCheck = false;
			} catch (InputMismatchException e1) {
				System.out.println("Please enter true or false: ");
				sc.nextLine();
			}
		}
		if (readFile) {
			int chooseElections = 0;
			try {
				ObjectInputStream inFile = new ObjectInputStream(new FileInputStream("elections.dat"));
				ElectionsManager eFile = (ElectionsManager) inFile.readObject();
				System.out.println("Witch elections would you like to choose ? (please enter a number)");
				int index = 0;
				for (Elections eRead : eFile.getElections()) {
					System.out.println((index + 1) + ") " + eRead.getMonth() + "/" + eRead.getYear());
					e.addElections(eFile.getElections().get(index));
					index++;

				}
				while (chooseCheck) {
					try {
						chooseElections = sc.nextInt();
						chooseCheck = false;
					} catch (InputMismatchException e1) {
						System.out.println("Please enter a number: ");
						sc.nextLine();
					}
				}

				while (chooseElections > eFile.getElections().size() || chooseElections < 1) {
					System.out.println("Please enter a number from the list: ");
					chooseElections = sc.nextInt();
				}
				elections = eFile.getElections().get(chooseElections - 1);
				inFile.close();
			} catch (FileNotFoundException e1) {
				System.out.println("The file is empty, please start elections by yourself");
				readFile = false;
			}

			return elections;
		} else {
			try {
				ObjectInputStream inFile = new ObjectInputStream(new FileInputStream("elections.dat"));
				ElectionsManager eFile = (ElectionsManager) inFile.readObject();
				int index = 0;
				for (Elections eRead : eFile.getElections()) {
					e.addElections(eFile.getElections().get(index));
					index++;
				}
				inFile.close();
			} catch (FileNotFoundException e1) {
				readFile = false;
			}
		}
		return elections;
	}

	public boolean endProgram(boolean endProgram) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Do you want to start another round of elections ? (true / false)");
		boolean answer = true;
		while (answer) {
			try {
				endProgram = sc.nextBoolean();
				answer = false;
			} catch (InputMismatchException e1) {
				System.out.println("Please enter true or false: ");
				sc.nextLine();
			}
		}
		return endProgram;
	}

	public void electionsResults(Elections elections) {
		elections.toString();
	}

	public void elections(Elections elections) throws PartyDosentExcist, DontHaveProtectionExeption {
		Scanner sc = new Scanner(System.in);

		for (int i = 0; i < elections.getCitizens().getCurrentSize(); i++) {
			Party p = null;

			System.out.println("Do " + elections.getCitizens().get(i).getName() + " want to vote ? (true / false)");
			boolean boolCheck = true;
			boolean wantVote = false;
			while (boolCheck) {
				try {
					wantVote = sc.nextBoolean();
					boolCheck = false;
				} catch (InputMismatchException e1) {
					System.out.println("Please enter true or false: ");
					sc.nextLine();
				}
			}
			if (wantVote) {
				if ((elections.getCitizens().get(i) instanceof SickAble)) {

					if (!((SickAble) elections.getCitizens().get(i)).Sick()) {
						System.out.println("This citizen is in quarantined but have no protection ! can't vote !");
					} else {
						System.out.println("Please choose a party from the options: ");
						for (Party p1 : elections.getParties()) {
							System.out.println(p1.getName());
						}

						sc.nextLine();
						String nameChosenParty = sc.nextLine();
						boolean error = true;
						while (error) {
							for (Party p1 : elections.getParties()) {
								if (p1.getName().equalsIgnoreCase(nameChosenParty)) {
									p = p1;
								}
							}
							elections.getCitizens().get(i).setHasVoted(true);

							try {
								elections.getCitizens().get(i).setVote(p);
								System.out.println("The vote has saved in the ballot box");
								error = false;
							} catch (PartyDosentExcist e) {

								System.out.println(e.getMessage());
								System.out.println("Please enter a party from the options:");
								nameChosenParty = sc.nextLine();
							}
						}
					}

				} else {
					System.out.println("Please choose a party from the options: ");
					for (Party p1 : elections.getParties()) {
						System.out.println(p1.getName());
					}

					sc.nextLine();
					String nameChosenParty = sc.nextLine();
					boolean error = true;
					while (error) {
						for (Party p1 : elections.getParties()) {
							if (p1.getName().equalsIgnoreCase(nameChosenParty)) {
								p = p1;
							}
						}
						elections.getCitizens().get(i).setHasVoted(true);

						try {
							elections.getCitizens().get(i).setVote(p);
							System.out.println("The vote has saved in the ballot box");
							error = false;
						} catch (PartyDosentExcist e) {

							System.out.println(e.getMessage());
							System.out.println("Please enter a party from the options:");
							nameChosenParty = sc.nextLine();
						}
					}
				}
			} else {
				System.out.println("OK, maybe in the next elections");
				elections.getCitizens().get(i).setHasVoted(false);
				elections.getCitizens().get(i).setVote(null);
			}
		}
	}

	public void printParties(Elections elections) {
		for (Party p1 : elections.getParties()) {
			System.out.println(p1.toString());
		}
	}

	public void printCitizens(Elections elections) {
		for (int i = 0; i < elections.getCitizens().getCurrentSize(); i++) {

			System.out.println(elections.getCitizens().get(i).toString());
		}
		System.out.println();
	}

	public void printBallotBoxes(Elections elections) {
		int index = 0;
		for (ArrayList<BallotBox> bList : elections.getBallotBoxes()) {
			switch (index++) {
			case 0: {
				System.out.println("Citizens Ballotboxes: ");
				for (BallotBox b1 : bList) {
					System.out.println(b1.toString());
				}
				System.out.println();
				break;
			}
			case 1: {
				System.out.println("Corona Citizens Ballotboxes: ");
				for (BallotBox b1 : bList) {
					System.out.println(b1.toString());
				}
				System.out.println();
				break;
			}
			case 2: {
				System.out.println("Soldiers Ballotboxes: ");
				for (BallotBox b1 : bList) {
					System.out.println(b1.toString());
				}
				System.out.println();
				break;
			}
			case 3: {
				System.out.println("Corona soldiers Ballotboxes: ");
				for (BallotBox b1 : bList) {
					System.out.println(b1.toString());
				}
				System.out.println();
				break;
			}
			default: {
				break;
			}
			}

		}
	}

	public void addCandidate(Elections elections)
			throws NotInRange, CandidateAlreadyExcistInTheParty, UnderAgeExeption, SameIDExeption,
			DontHaveProtectionExeption, PartyDosentExcist, PrimerisPositionAlreadyTaken, SickDaysExeption {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter a name:");
		String name = sc.nextLine();
		System.out.println("Please enter ID:");
		String id = sc.nextLine();
		System.out.println("Please enter his year of birth:");
		boolean cYearCheck = true;
		int cYear = 0;
		while (cYearCheck) {
			try {
				cYear = sc.nextInt();
				cYearCheck = false;
			} catch (InputMismatchException e1) {
				System.out.println("The year must contain only digits !");
				System.out.println("Please enter a new year:");
				sc.nextLine();
			}
		}
		System.out.println("Please enter the name of the party from the options:");
		Party party = null;
		for (Party p1 : elections.getParties()) {
			System.out.println(p1.getName());
		}
		sc.nextLine();
		String partyName = sc.nextLine();
		for (Party p1 : elections.getParties()) {
			if (p1.getName().equalsIgnoreCase(partyName)) {
				party = p1;
				break;
			}
		}

		System.out.println("Please enter his primeris position:");
		boolean primerisPositionCheck = true;
		int primerisPosition = 0;
		while (primerisPositionCheck) {
			try {
				primerisPosition = sc.nextInt();
				primerisPositionCheck = false;
			} catch (InputMismatchException e1) {
				System.out.println("The primeris position must contain only digits !");
				System.out.println("Please enter a new primeris position:");
				sc.nextLine();
			}
		}
		System.out.println("Is the citizen in quarantine ? (true / false)");
		boolean boolCheck = true;
		boolean isQuarantine = false;
		while (boolCheck) {
			try {
				isQuarantine = sc.nextBoolean();
				boolCheck = false;
			} catch (InputMismatchException e1) {
				System.out.println("Please enter true or false: ");
				sc.nextLine();
			}
		}

		BallotBox b = null;
		boolean error = true;
		if (isQuarantine) {
			b = elections.getLowestCoronaCitizenBallotBox();

			System.out.println("How many days the candidate is sick ?");
			boolean sickDaysCheck = true;
			int sickDays = 0;
			while (sickDaysCheck) {
				try {
					sickDays = sc.nextInt();
					sickDaysCheck = false;
				} catch (InputMismatchException e1) {
					System.out.println("The sick days must contain only digits !");
					System.out.println("Please enter a new number of sick days:");
					sc.nextLine();
				}
			}
			System.out.println("Does he have a protection suit ? (true / false)");
			boolean boolCheck1 = true;
			boolean isMask = false;
			while (boolCheck1) {
				try {
					isMask = sc.nextBoolean();
					boolCheck1 = false;
				} catch (InputMismatchException e1) {
					System.out.println("Please enter true or false: ");
					sc.nextLine();
				}
			}
			sc.nextLine();
			while (error) {
				try {
					Candidate cn = new CoronaCandidate(name, id, cYear, b, party, primerisPosition, isMask, sickDays);
					elections.addCandidate(cn);
					System.out.println("The candidate added successfully !");
					error = false;
				} catch (BirthYearNotInRange e) {
					System.out.println(e.getMessage());
					System.out.println("Please enter a new year:");
					cYearCheck = true;
					cYear = 0;
					while (cYearCheck) {
						try {
							cYear = sc.nextInt();
							cYearCheck = false;
						} catch (InputMismatchException e1) {
							System.out.println("The year must contain only digits !");
							System.out.println("Please enter a new year:");
							sc.nextLine();
						}
					}
				} catch (UnderAgeExeption e) {
					System.out.println(e.getMessage());
					error = false;

				} catch (SameIDExeption e) {
					System.out.println(e.getMessage());
					System.out.println("This ID already excist in the system, please enter a new ID: ");
					id = sc.nextLine();

				} catch (IDMismachExeption e) {
					System.out.println(e.getMessage());
					System.out.println("The ID must contain 9 digits, please enter a new ID: ");
					id = sc.nextLine();

				} catch (BallotBoxTypeDontExicst e) {
					System.out.println(e.getMessage());
					error = false;

				} catch (CandidateAlreadyExcistInTheParty e) {
					System.out.println(e.getMessage());
					error = false;

				} catch (PartyDosentExcist e) {
					System.out.println(e.getMessage());
					System.out.println("Please enter a new party name: ");
					partyName = sc.nextLine();
					for (Party p1 : elections.getParties()) {
						if (p1.getName().equalsIgnoreCase(partyName)) {
							party = p1;
							break;
						}
					}

				}

				catch (PrimerisPositionAlreadyTaken e) {
					System.out.println(e.getMessage());
					System.out.println("Please enter a new primeris position: ");
					primerisPosition = sc.nextInt();
					sc.nextLine();

				} catch (NameContainIllegalChars e) {
					System.out.println(e.getMessage());
					name = sc.nextLine();
				} catch (SoldierCantBeCandidate e) {
					System.out.println(e.getMessage());
					error = false;

				} catch (SickDaysExeption e) {
					System.out.println(e.getMessage());
					System.out.println("Please enter a new number of sick days:");
					sickDays = sc.nextInt();

				}
			}

		} else {
			b = elections.getLowestCitizenBallotBox();
			sc.nextLine();
			while (error) {
				try {
					Candidate cn = new Candidate(name, id, cYear, b, party, primerisPosition);
					elections.addCandidate(cn);
					System.out.println("The candidate added successfully !");
					error = false;
				} catch (BirthYearNotInRange e) {
					System.out.println(e.getMessage());
					System.out.println("Please enter a new year:");
					cYearCheck = true;
					cYear = 0;
					while (cYearCheck) {
						try {
							cYear = sc.nextInt();
							cYearCheck = false;
						} catch (InputMismatchException e1) {
							System.out.println("The year must contain only digits !");
							System.out.println("Please enter a new year:");
							sc.nextLine();
						}
					}

				} catch (UnderAgeExeption e) {
					System.out.println(e.getMessage());
					error = false;

				} catch (SameIDExeption e) {
					System.out.println(e.getMessage());
					System.out.println("please enter a new ID: ");
					id = sc.nextLine();
				} catch (IDMismachExeption e) {
					System.out.println(e.getMessage());
					System.out.println("please enter a new ID: ");
					id = sc.nextLine();

				} catch (BallotBoxTypeDontExicst e) {
					System.out.println(e.getMessage());
					error = false;

				} catch (CandidateAlreadyExcistInTheParty e) {
					System.out.println(e.getMessage());
					error = false;

				} catch (PartyDosentExcist e) {
					System.out.println(e.getMessage());
					System.out.println("Please enter a new party name: ");
					partyName = sc.nextLine();
					for (Party p1 : elections.getParties()) {
						if (p1.getName().equalsIgnoreCase(partyName)) {
							party = p1;
							break;
						}
					}

				} catch (PrimerisPositionAlreadyTaken e) {
					System.out.println(e.getMessage());
					System.out.println("Please enter a new primeris position: ");
					primerisPosition = sc.nextInt();
					sc.nextLine();

				} catch (NameContainIllegalChars e) {
					System.out.println(e.getMessage());
					name = sc.nextLine();
				} catch (SoldierCantBeCandidate e) {
					System.out.println(e.getMessage());
					error = false;

				}

			}
		}
	}

	public void addParty(Elections elections) {
		Scanner sc = new Scanner(System.in);
		Party p = null;
		System.out.println("Please enter the name of the party:");
		String name = sc.nextLine();
		System.out.println("Please enter the year of the party's creation:");
		int year = sc.nextInt();
		System.out.println("Please enter the month of the party's creation:");
		int month = sc.nextInt();
		System.out.println("Please enter the day of the party's creation:");
		int day = sc.nextInt();
		System.out.println("Please enter the identity of the party:");

		boolean menu = true;
		eType p1 = null;
		while (menu) {
			System.out.println("1 -- RIGHT");
			System.out.println("2 -- CENTER");
			System.out.println("3 -- LEFT");
			int answer = sc.nextInt();
			switch (answer) {
			case 1: {
				p1 = Party.eType.RIGHT;
				menu = false;
				break;
			}
			case 2: {
				p1 = Party.eType.CENTER;
				menu = false;
				break;
			}
			case 3: {
				p1 = Party.eType.LEFT;
				menu = false;
				break;
			}
			default: {
				System.out.println("Please choose a number from the options:");
				break;
			}
			}
		}
		boolean error = true;
		sc.nextLine();
		while (error) {
			try {
				p = new Party(name, year, month, day, p1);
				elections.addParty(p);
				error = false;
				System.out.println("The party added successfully !");
			} catch (PartyAllreadyExist e) {
				System.out.println(e.getMessage());
				error = false;
			} catch (DateTimeException e) {
				System.out.println(e.getMessage());
				System.out.println("Please enter a new month: ");
				month = sc.nextInt();
				System.out.println("Please enter a new day: ");
				day = sc.nextInt();
				sc.nextLine();
			} catch (YearNotInRange e) {
				System.out.println(e.getMessage());
				System.out.println("Please enter a new year: ");
				year = sc.nextInt();
				sc.nextLine();
			} catch (NameContainIllegalChars e) {
				System.out.println(e.getMessage());
				name = sc.nextLine();
			}

		}

	}

	public void addCitizen(Elections elections)
			throws NotInRange, IDMismachExeption, DontHaveProtectionExeption, SickDaysExeption {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter a name:");
		String name = sc.nextLine();
		System.out.println("Please enter ID:");
		String id = sc.nextLine();
		System.out.println("Please enter his year of birth:");
		boolean cYearCheck = true;
		int cYear = 0;
		while (cYearCheck) {
			try {
				cYear = sc.nextInt();
				cYearCheck = false;
			} catch (InputMismatchException e1) {
				System.out.println("The year must contain only digits !");
				System.out.println("Please enter a new year:");
				sc.nextLine();
			}
		}
		System.out.println("Is the citizen in quarantine ? (true / false)");
		boolean boolCheck = true;
		boolean isQuarantine = false;
		while (boolCheck) {
			try {
				isQuarantine = sc.nextBoolean();
				boolCheck = false;
			} catch (InputMismatchException e1) {
				System.out.println("Please enter true or false: ");
				sc.nextLine();
			}
		}
		BallotBox b = null;
		boolean error = true;
		if (isQuarantine) {
			if (!(elections.getYear() - cYear >= 18 && elections.getYear() - cYear <= 21)) {
				b = elections.getLowestCoronaCitizenBallotBox();
				System.out.println("How many days the citizen are sick ?");
				boolean sickDaysCheck = true;
				int sickDays = 0;
				while (sickDaysCheck) {
					try {
						sickDays = sc.nextInt();
						sickDaysCheck = false;
					} catch (InputMismatchException e1) {
						System.out.println("The sick days must contain only digits !");
						System.out.println("Please enter a new number of sick days:");
						sc.nextLine();
					}
				}
				System.out.println("Does he have a protection suit ? (true / false)");
				boolean boolCheck1 = true;
				boolean isMask = false;
				while (boolCheck1) {
					try {
						isMask = sc.nextBoolean();
						boolCheck1 = false;
					} catch (InputMismatchException e1) {
						System.out.println("Please enter true or false: ");
						sc.nextLine();
					}
				}

				sc.nextLine();
				while (error) {
					try {
						Citizen c = new CoronaCitizen(name, id, cYear, b, isMask, sickDays);
						elections.addCitizen(c);
						System.out.println("The citizen added successfully !");
						error = false;
					} catch (BirthYearNotInRange e) {
						System.out.println(e.getMessage());
						System.out.println("Please enter a new year:");
						cYearCheck = true;
						cYear = 0;
						while (cYearCheck) {
							try {
								cYear = sc.nextInt();
								cYearCheck = false;
							} catch (InputMismatchException e1) {
								System.out.println("The year must contain only digits !");
								System.out.println("Please enter a new year:");
								sc.nextLine();
							}
						}
					} catch (UnderAgeExeption e) {
						System.out.println(e.getMessage());
						error = false;

					} catch (SameIDExeption e) {
						System.out.println(e.getMessage());
						System.out.println("This ID already excist in the system, please enter a new ID:");
						id = sc.nextLine();

					} catch (IDMismachExeption e) {
						System.out.println(e.getMessage());
						System.out.println("please enter a new ID:");
						id = sc.nextLine();

					} catch (DontHaveProtectionExeption e) {
						System.out.println(e.getMessage());
						error = false;

					} catch (BallotBoxTypeDontExicst e) {
						System.out.println(e.getMessage());
						error = false;

					} catch (NameContainIllegalChars e) {
						System.out.println(e.getMessage());
						name = sc.nextLine();
					} catch (SickDaysExeption e) {
						System.out.println(e.getMessage());
						System.out.println("Please enter a new number of sick days:");
						sickDays = sc.nextInt();

					}
				}

			} else {
				b = elections.getLowestCoronaSoldierBallotBox();
				System.out.println("How many days the citizen are sick ?");
				boolean sickDaysCheck = true;
				int sickDays = 0;
				while (sickDaysCheck) {
					try {
						sickDays = sc.nextInt();
						sickDaysCheck = false;
					} catch (InputMismatchException e1) {
						System.out.println("The sick days must contain only digits !");
						System.out.println("Please enter a new number of sick days:");
						sc.nextLine();
					}
				}
				sc.nextLine();
				System.out.println("Does he have a protection suit ? (true / false)");
				boolean boolCheck1 = true;
				boolean isMask = false;
				while (boolCheck1) {
					try {
						isMask = sc.nextBoolean();
						boolCheck1 = false;
					} catch (InputMismatchException e1) {
						System.out.println("Please enter true or false: ");
						sc.nextLine();
					}
				}

				System.out.println("Is the soldier carry weapon (true / false)");
				boolean boolCheck2 = true;
				boolean isCarryWeapon = false;
				while (boolCheck2) {
					try {
						isCarryWeapon = sc.nextBoolean();
						boolCheck2 = false;
					} catch (InputMismatchException e1) {
						System.out.println("Please enter true or false: ");
						sc.nextLine();
					}
				}

				sc.nextLine();
				while (error) {
					try {
						Soldier c = new CoronaSoldier(name, id, cYear, b, isCarryWeapon, isMask, sickDays);
						elections.addCitizen(c);
						System.out.println("The corona soldier added successfully !");
						error = false;
					} catch (BirthYearNotInRange e) {
						System.out.println(e.getMessage());
						System.out.println("Please enter a new year:");
						cYearCheck = true;
						cYear = 0;
						while (cYearCheck) {
							try {
								cYear = sc.nextInt();
								cYearCheck = false;
							} catch (InputMismatchException e1) {
								System.out.println("The year must contain only digits !");
								System.out.println("Please enter a new year:");
								sc.nextLine();
							}
						}
					} catch (UnderAgeExeption e) {
						System.out.println(e.getMessage());
						error = false;

					} catch (SameIDExeption e) {
						System.out.println(e.getMessage());
						System.out.println("This ID already excist in the system, please enter a new ID:");
						id = sc.nextLine();

					} catch (IDMismachExeption e) {
						System.out.println(e.getMessage());
						System.out.println("please enter a new ID:");
						id = sc.nextLine();

					} catch (DontHaveProtectionExeption e) {
						System.out.println(e.getMessage());
						error = false;

					} catch (BallotBoxTypeDontExicst e) {
						System.out.println(e.getMessage());
						error = false;

					} catch (NameContainIllegalChars e) {
						System.out.println(e.getMessage());
						name = sc.nextLine();
					} catch (SickDaysExeption e) {
						System.out.println(e.getMessage());
						System.out.println("Please enter a new number of sick days:");
						sickDays = sc.nextInt();

					}
				}
			}

		} else if (elections.getYear() - cYear >= 18 && elections.getYear() - cYear <= 21) {
			b = elections.getLowestSoldierBallotBox();

			System.out.println("Is the soldier carry weapon (true / false)");
			boolean boolCheck2 = true;
			boolean isCarryWeapon = false;
			while (boolCheck2) {
				try {
					isCarryWeapon = sc.nextBoolean();
					boolCheck2 = false;
				} catch (InputMismatchException e1) {
					System.out.println("Please enter true or false: ");
					sc.nextLine();
				}
			}
			sc.nextLine();
			while (error) {
				try {
					Soldier c = new Soldier(name, id, cYear, b, isCarryWeapon);
					elections.addCitizen(c);
					System.out.println("The soldier added successfully !");
					error = false;
				} catch (BirthYearNotInRange e) {
					System.out.println(e.getMessage());
					System.out.println("Please enter a new year:");
					cYearCheck = true;
					cYear = 0;
					while (cYearCheck) {
						try {
							cYear = sc.nextInt();
							cYearCheck = false;
						} catch (InputMismatchException e1) {
							System.out.println("The year must contain only digits !");
							System.out.println("Please enter a new year:");
							sc.nextLine();
						}
					}
				} catch (UnderAgeExeption e) {
					System.out.println(e.getMessage());
					error = false;

				} catch (SameIDExeption e) {
					System.out.println(e.getMessage());
					System.out.println("please enter a new ID: ");
					id = sc.nextLine();

				} catch (IDMismachExeption e) {
					System.out.println(e.getMessage());
					System.out.println("please enter a new ID: ");
					id = sc.nextLine();

				} catch (BallotBoxTypeDontExicst e) {
					System.out.println(e.getMessage());
					error = false;

				} catch (NameContainIllegalChars e) {
					System.out.println(e.getMessage());
					name = sc.nextLine();
				}
			}
		} else {
			b = elections.getLowestCitizenBallotBox();
			sc.nextLine();
			while (error) {
				try {
					Citizen c = new Citizen(name, id, cYear, b);
					elections.addCitizen(c);
					System.out.println("The citizen added successfully !");
					error = false;
				} catch (BirthYearNotInRange e) {
					System.out.println(e.getMessage());
					System.out.println("Please enter a new year:");
					cYearCheck = true;
					cYear = 0;
					while (cYearCheck) {
						try {
							cYear = sc.nextInt();
							cYearCheck = false;
						} catch (InputMismatchException e1) {
							System.out.println("The year must contain only digits !");
							System.out.println("Please enter a new year:");
							sc.nextLine();
						}
					}
				} catch (UnderAgeExeption e) {
					System.out.println(e.getMessage());
					error = false;

				} catch (SameIDExeption e) {
					System.out.println(e.getMessage());
					System.out.println("please enter a new ID: ");
					id = sc.nextLine();
				} catch (IDMismachExeption e) {
					System.out.println(e.getMessage());
					System.out.println("please enter a new ID: ");
					id = sc.nextLine();

				} catch (BallotBoxTypeDontExicst e) {
					System.out.println(e.getMessage());
					error = false;

				} catch (NameContainIllegalChars e) {
					System.out.println(e.getMessage());
					name = sc.nextLine();
				}

			}
		}

	}

	public void addBallotBox(Elections e) {
		Scanner sc = new Scanner(System.in);
		int menu;
		System.out.println("Please enter the ballot box city: ");
		String address = sc.nextLine();
		System.out.println("Please select the type of the ballot box");
		System.out.println("1 -- Citizens ballot box");
		System.out.println("2 -- Corona Citizens ballot box");
		System.out.println("3 -- Soldiers ballot box");
		System.out.println("4 -- Corona Soldiers ballot box");

		menu = sc.nextInt();
		while (!(menu >= 1 && menu <= 4)) {
			System.out.println("Please choose a number between 1 - 4");
			menu = sc.nextInt();
		}
		sc.nextLine();

		switch (menu) {
		case 1: {
			boolean error = true;
			while (error) {
				try {
					BallotBox<Citizen> b = new BallotBox<Citizen>(address);
					e.addBallotBox(b, 0);
					System.out.println("Ballot box (Citizens) added successfully");
					error = false;
				} catch (BallotBoxAlreadyExcist e1) {
					System.out.println(e1.getMessage());
					error = false;
				} catch (NameContainIllegalChars e1) {
					System.out.println(e1.getMessage());
					address = sc.nextLine();
				}
			}
			break;
		}
		case 2: {

			boolean error = true;
			while (error) {
				try {
					BallotBox<CoronaCitizen> b = new BallotBox<CoronaCitizen>(address);
					e.addBallotBox(b, 1);
					System.out.println("Ballot box (Corona citizens) added successfully");
					error = false;
				} catch (BallotBoxAlreadyExcist e1) {
					System.out.println(e1.getMessage());
					error = false;
				} catch (NameContainIllegalChars e1) {
					System.out.println(e1.getMessage());
					address = sc.nextLine();
				}
			}
			break;
		}
		case 3: {
			boolean error = true;
			while (error == true) {
				try {
					BallotBox<Soldier> b = new BallotBox<Soldier>(address);
					e.addBallotBox(b, 2);
					System.out.println("Ballot box (Soldiers) added successfully");
					error = false;
				} catch (BallotBoxAlreadyExcist e1) {
					System.out.println(e1.getMessage());
					error = false;
				} catch (NameContainIllegalChars e1) {
					System.out.println(e1.getMessage());
					address = sc.nextLine();
				}
			}

			break;
		}
		case 4: {
			boolean error = true;
			while (error == true) {
				try {
					BallotBox<CoronaSoldier> b = new BallotBox<CoronaSoldier>(address);
					e.addBallotBox(b, 3);
					System.out.println("Ballot box (Corona soldiers) added successfully");
					error = false;
				} catch (BallotBoxAlreadyExcist e1) {
					System.out.println(e1.getMessage());
					error = false;
				} catch (NameContainIllegalChars e1) {
					System.out.println(e1.getMessage());
					address = sc.nextLine();
				}
			}

			break;
		}

		}
	}

	public static void HardCoded(Elections elections) {
		try {
			BallotBox<Citizen> b1 = new BallotBox<Citizen>("Haifa");
			elections.addBallotBox(b1, 0);
			BallotBox<CoronaCitizen> b2 = new BallotBox<CoronaCitizen>("Ramat Gan");
			elections.addBallotBox(b2, 1);
			BallotBox<Soldier> b3 = new BallotBox<Soldier>("Bisla");
			elections.addBallotBox(b3, 2);
			BallotBox<CoronaSoldier> b4 = new BallotBox<CoronaSoldier>("Tel Aviv");
			elections.addBallotBox(b4, 3);
			Citizen c1 = new Citizen("Yuval", "121212121", elections.getYear() - 30, b1);
			elections.addCitizen(c1);
			Citizen c2 = new Citizen("Kobi", "131313131", elections.getYear() - 40, b2);
			elections.addCitizen(c2);
			Citizen c3 = new CoronaCitizen("Rami", "141414141", elections.getYear() - 50, b1, true, 10);
			elections.addCitizen(c3);
			Citizen c4 = new Soldier("Roni", "151515151", elections.getYear() - 20, b3, true);
			elections.addCitizen(c4);
			Citizen c5 = new CoronaSoldier("Michal", "161616161", elections.getYear() - 60, b4, false, false, 20);
			elections.addCitizen(c5);
			Party p1 = new Party("Likud", 1960, 12, 1, Party.eType.RIGHT);
			elections.addParty(p1);
			Party p2 = new Party("Yesh Atid", 1970, 10, 12, Party.eType.CENTER);
			elections.addParty(p2);
			Party p3 = new Party("Merech", 1975, 12, 1, Party.eType.LEFT);
			elections.addParty(p3);
			Candidate cn1 = new Candidate("Binyamin", "232323232", elections.getYear() - 62, b1, p1, 1);
			elections.addCandidate(cn1);
			Candidate cn2 = new Candidate("Nir", "343434343", elections.getYear() - 70, b2, p1, 2);
			elections.addCandidate(cn2);
			Candidate cn3 = new Candidate("Yair", "191919191", elections.getYear() - 58, b1, p2, 1);
			elections.addCandidate(cn3);
			Candidate cn4 = new CoronaCandidate("Dor", "383838383", elections.getYear() - 40, b2, p2, 2, true, 4);
			elections.addCandidate(cn4);
			Candidate cn5 = new CoronaCandidate("Yoel", "181818181", elections.getYear() - 32, b1, p3, 1, true, 10);
			elections.addCandidate(cn5);
			Candidate cn6 = new CoronaCandidate("Ruti", "292929292", elections.getYear() - 38, b2, p3, 2, false, 14);
			elections.addCandidate(cn6);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}
