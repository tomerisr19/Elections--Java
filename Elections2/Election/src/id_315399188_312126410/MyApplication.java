package id_315399188_312126410;

import java.time.DateTimeException;
import java.util.ArrayList;

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

public interface MyApplication {

	boolean endProgram(boolean endProgram);

	void electionsResults(Elections elections);

	void elections(Elections elections) throws PartyDosentExcist, DontHaveProtectionExeption;

	void printParties(Elections elections);

	void printCitizens(Elections elections);

	void printBallotBoxes(Elections elections);

	void addCandidate(Elections elections)
			throws NotInRange, CandidateAlreadyExcistInTheParty, UnderAgeExeption, SameIDExeption,
			DontHaveProtectionExeption, PartyDosentExcist, PrimerisPositionAlreadyTaken, SickDaysExeption;

	void addParty(Elections elections);

	void addCitizen(Elections elections)
			throws NotInRange, IDMismachExeption, DontHaveProtectionExeption, SickDaysExeption;

	void addBallotBox(Elections e);

}
