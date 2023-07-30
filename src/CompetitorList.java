import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/** 
 * This class uses the concept of ArrayList.
 * <p>
 * The CompetitorList class represents the ArrayList used for storing data 
 * about competitors. This class is a modified version of ArrayList with custom
 * variables and methods to meet the specification.
 * <p>
 * This class includes methods to read a CSV file, generate a final report, support 
 * methods to generate the report, write data to an external text file and support 
 * methods for GUI.
 * <p>
 * F21SF Software Engineering Foundations - Assignment 2 
 * @author Kawthar Mohammad Adam (km2065)
 */

public class CompetitorList
{
	/** 
	 * Creates an ArrayList named competitorList of KMACompetitor type.
	 */
	private ArrayList<KMACompetitor> competitorList;

	/** 
	 * Initialises the newly created ArrayList named competitorList.
	 * */
	public CompetitorList()
	{
		competitorList = new ArrayList<KMACompetitor>();
	}

	/** 
	 * Returns the size of the competitorList.
	 * @return The size of the competitorList.
	 */
	private int getSize() 
	{
		return competitorList.size();
	}

	/** 
	 * Returns a string as a table with details of all 
	 * competitors in the competitorList.
	 * @return The table of competitors.
	 */
	public String getTableOfCompetitors()
	{
		String report = "Competitor                            Level            Age     Country         Experience     Scores            Overall\n";
		for (KMACompetitor c : competitorList) 
		{
			report += String.format("%-5d", c.getComNumber());
			report += String.format("%-33s", c.getComName().getFullName());
			report += String.format("%-17s", c.getComLevel());
			report += String.format("%-8d", c.getAge());
			report += String.format("%-16s", c.getCountry());
			// To get the experience for cyclo-cross cyclists
			if (c instanceof CycloCrossCyclist)
			{
				CycloCrossCyclist c1 = (CycloCrossCyclist) c;
				report += c1.getYearsOfExperience();
				// To adjust the formatting since some experience values are one digit and some are two digits
				if (Integer.toString(c1.getYearsOfExperience()).length() == 2)
				{
					report += String.format("%-13s", " years");
				}
				else
				{
					report += String.format("%-14s", " years");
				}
			}
			// Empty value for other cyclists
			else
			{
				report += String.format("%-15s", "not required");
			}
			report += String.format("%-18s", c.displayScores());
			report += c.getOverallScore();
			report += "\n";
		}
		return report;
	}

	/** 
	 * Searches the competitorList to find the competitor using the 
	 * competitor number. If the competitor number is found then the 
	 * KMACompetitor is returned. If the competitor number is not found then 
	 * null is returned since it means that the competitor does not exist.
	 * @param comNum The unique competitor number to identify competitors.
	 * @return The object KMACompetitor if the competitor is found, null otherwise.
	 */
	public KMACompetitor findByComNum(int comNum)
	{
		for (KMACompetitor c : competitorList)
		{
			if (c.getComNumber() == comNum)
			{
				return c;
			}
		}
		return null;
	}

	/**
	 * Compares the overall score of all competitors and stores the 
	 * highest score and competitor number for further checks. It then 
	 * checks if there is a tie between 2 or more competitors using the 
	 * highest score and the competitor number. Returns a string containing 
	 * the full details of all competitors if there are 2 or more winners, 
	 * otherwise the full details of only one competitor.
	 * @return The full details of the winner(s).
	 */
	private String getHighestScoreDetails()
	{
		double highScore = 0;
		int comNum = 0;

		// To find the highest overall score
		for (KMACompetitor c : competitorList) 
		{
			double overallScore = c.getOverallScore();
			if (overallScore > highScore)
			{
				highScore = overallScore;
				comNum = c.getComNumber();
			}
		}

		// To find if there are 2 or more winners
		String report = "";
		ArrayList<Integer> comNumList = new ArrayList<Integer>();
		for (KMACompetitor c : competitorList) 
		{
			if ((comNum != c.getComNumber()) && (highScore == c.getOverallScore()))
			{
				comNumList.add(c.getComNumber());
			}
		}

		// If there are 2 or more winners then display their details
		if (comNumList.size() != 0)
		{
			KMACompetitor c = findByComNum(comNum);
			report += "There is a tie between competitors. The highest score is " 
					+ highScore + ".\nFull details for all the winners are:\n" + c.getFullDetails(c) + "\n";

			for (Integer i : comNumList) 
			{
				KMACompetitor c1 = findByComNum(i);
				report += c1.getFullDetails(c1) + "\n";
			}
			return report;
		}

		// If there is only one winner, then display his/her details
		else
		{
			KMACompetitor c1 = findByComNum(comNum);
			String name = c1.getComName().getFullName();
			return "The person with the highest score is " + name + 
					" with a score of " + highScore + ".\nFull details for " 
					+ comNum + ":\n"+ c1.getFullDetails(c1);
		}
	}

	/**
	 * Generates the frequency table of scores ranging from 1 to 5.
	 * Returns the formatted string as a table containing scores and
	 * their frequency.
	 * @return The frequency table of scores.
	 */
	private String getFrequencyTable()
	{
		int [] freqScores = new int [5];
		for (KMACompetitor c : competitorList) 
		{
			for (int i=0; i<5; i++)
			{
				// To increment the value of score on y-1 index
				int y = c.getScoreArray()[i];
				freqScores[y-1]++;
			}
		} 

		// To create the report
		String report = "The following individual scores were awarded:\nScores:     ";
		for (int i=0; i<freqScores.length; i++) 
		{
			report += String.format("%-5d", i+1);
		}
		report += "\nFrequency:  ";
		for (int i=0; i<freqScores.length; i++) 
		{
			report += String.format("%-5d", freqScores[i]);
		}
		return report;
	}

	/**
	 * Calculates the average of overall scores for all competitors 
	 * in the competitorList and returns the average value.
	 * @return The average of overall scores.
	 */
	private double getAverage()
	{
		double total = 0;
		for (KMACompetitor c : competitorList) 
		{
			total += c.getOverallScore();
		}
		return total/getSize();
	}

	/**
	 * Determines the value of the highest overall score and returns it.
	 * @return The maximum overall score.
	 */
	private double getMax()
	{
		double max = 0;
		for (KMACompetitor c : competitorList) 
		{
			if (max < c.getOverallScore())
			{
				max = c.getOverallScore();
			}
		}
		return max;
	}

	/**
	 * Determines the value of the lowest overall score and returns it.
	 * @return The minimum overall score.
	 */
	private double getMin()
	{
		double min = getMax();
		for (KMACompetitor c : competitorList) 
		{
			if (min > c.getOverallScore())
			{
				min = c.getOverallScore();
			}
		}
		return min;
	}

	/**
	 * Creates a summary table with average, maximum and minimum values.
	 * @return The summary table. 
	 */
	private String getSummaryStats()
	{
		String report = "SUMMARY STATISTICS\nAverage        Maximum        Minimum\n";
		report += String.format("%-15s", String.format("%.2f", getAverage()));
		report += String.format("%-15s",getMax());
		report += getMin() + "\n";
		return report;
	}

	/**
	 * Generates two types of reports. The report not required 
	 * can be commented out.
	 * <p>
	 * First report: Contains the table of competitors, 
	 * full details of winners, frequency table and summary table 
	 * (this report is commented out).
	 * <p>
	 * Second report: Contains short details of all competitors and
	 * full details of winners.
	 * @return The final report.
	 */
	public String generateFinalReport()
	{
		// Assignment 1 code:
		/* String report = getTableOfCompetitors() + "\nSTATISTICAL\nThere are " + 
				getSize() + " competitors.\n\n" + getHighestScoreDetails() 
				+ "\n\n" + getFrequencyTable()+ "\n\n" + getSummaryStats();
		return report;*/

		String report = "";

		for (KMACompetitor c : competitorList) 
		{
			report += c.getShortDetails();
		}

		return report + "\n" + getHighestScoreDetails() + "\n\n";
	}

	/**
	 * Adds the new competitor to the competitorList by checking 
	 * whether it exists already in the list. If the competitor 
	 * number of the competitor does not exist, then the competitor
	 * will be added to the competitorList, otherwise, it will return
	 * false.
	 * @param c The KMACompetitor object that needs to be added 
	 * to the competitorList.
	 * @return true if the competitor does not exist already, 
	 * otherwise false.
	 */
	public boolean addOneCompetitor(KMACompetitor c)
	{
		// To find the competitor number of the competitor
		int comNum = c.getComNumber();

		// To find the comNum in the list
		KMACompetitor check = findByComNum(comNum);

		// Check for the existence of comNum
		if (check == null)
		{
			competitorList.add(c);
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Reads data from the CSV file line-by-line. Lines are processed 
	 * if they are not empty, otherwise, they are ignored. 
	 * <p>
	 * An error message is displayed and the system exits normally 
	 * if the file is not found.
	 * @param fileName The name of the file that needs to be read.
	 */
	public void readFile(String fileName) 
	{
		try
		{
			File file = new File(fileName);
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) 
			{
				// To read the current line and process it
				String line = scanner.nextLine(); 

				// If the line is not empty
				if (line.length() != 0) 
				{
					processLine(line, fileName);
				}
			}
			scanner.close();
		}
		// If the csv file is not found, normal exit the system
		catch (FileNotFoundException fnf)
		{
			System.out.println( fileName + " not found.");
			System.out.println("Cannot process the data from the file. Please rename the files to "
					+ "InputRoad.csv , InputTrack.csv or InputCyclo.csv");
			System.exit(0);
		}
	}

	/**
	 * Writes data to a text file. 
	 * <p>
	 * The stack trace will be printed and the system will exit urgently 
	 * if an exception occurs.
	 * @param fileName The name of the file to which the data will
	 * be written to.
	 * @param report The string that needs to be written to the file.
	 */
	public void writeToFile(String fileName, String report) 
	{
		try
		{
			FileWriter fw;
			fw = new FileWriter(fileName);
			fw.write("Competitor Report\n\n");
			fw.write(report);
			fw.close();
		}

		// Stack trace will be printed here because this is unlikely
		catch (IOException ioe)
		{
			ioe.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * Processes the data being read from the CSV file line-by-line. 
	 * It splits the line into parts when it finds a ',' and stores the 
	 * string into an array. It then converts the data in the array to 
	 * the required data type so that it can be used when creating a 
	 * competitor. 
	 * <p>
	 * If the competitor is a cyclo-cross cyclist, then the years of experience
	 * should be between 10 and 50 years.
	 * <p>
	 * The scores at the end of the line are stored in a string array, converted 
	 * to an integer, checked to ensure the values are between 1-5 and then added 
	 * to the final integer array. 
	 * <p>
	 * The competitor is added to the competitorList if it does not exist already.
	 * <p>
	 * The fileName is checked to find out whether the object will be created for
	 * RoadCyclist, TrackCyclist or CycloCrossCyclist. 
	 * <p>
	 * Error messages are displayed if the expected data type is not entered 
	 * (ex: age is entered as 'Twenty' instead of 20), if the cyclo-cross cyclists don't
	 * have an experience of 10 to 50 years, if the values for the competitor are missing, 
	 * if the fileName does not match and if the comLevel for CycloCross is not 'Expert'.
	 * @param line The line read by the scanner from the .txt file.
	 * @param fileName The name of the file which is being read.
	 */
	private void processLine(String line, String fileName) 
	{
		try 
		{
			String parts [] = line.split(",");

			String snum = parts[0];
			// To remove spaces
			snum = snum.trim();
			// To convert String to int
			int num = Integer.parseInt(snum);

			Name name = new Name(parts[1]);

			String level = parts[2];

			String country = parts[3];

			String sage = parts[4];
			sage = sage.trim();
			int age = Integer.parseInt(sage);

			int experience = 0;
			int scoresLength = 0;
			String sscores[];

			// To check if the file read is for cyclo-cross
			if (fileName.equals("InputCyclo.csv"))
			{
				// To process experience years
				String string_exp = parts[5];
				string_exp = string_exp.trim();
				experience = Integer.parseInt(string_exp);

				// If experience is below 10 or more than 50
				if ((experience < 10) || (experience > 50))
				{
					System.out.println("The experience - " + experience + " - for " + num 
							+ " is not between 10 and 50. \n");
					System.exit(0);
				}

				// The scores are at the end of the line
				scoresLength = parts.length - 6;
				sscores = new String[scoresLength]; 
				System.arraycopy(parts, 6, sscores, 0, scoresLength);
			}
			// If the file read is not for cyclo-cross
			else
			{
				// The scores are at the end of the line
				scoresLength = parts.length - 5;
				sscores = new String[scoresLength];
				System.arraycopy(parts, 5, sscores, 0, scoresLength);
			}

			// To convert the scores from String to int
			int scores[] = new int[scoresLength];
			for (int i=0; i<sscores.length; i++) 
			{
				int score = Integer.parseInt(sscores[i]);

				// To check score is between 1 and 5
				if ((score > 0) && (score < 6)) 
				{
					scores[i] = score;
				} 
				else 
				{
					System.out.println("The score entered at index " + i + " in line - " + line
							+ " - is not between 1 and 5. \n");
					System.exit(0);
				} 
			}

			//To check the fileName for creating an object
			boolean ok = false;
			if (fileName.equals("InputRoad.csv"))
			{
				// To create RoadCyclist object and then add it to the list if it does not exist already
				RoadCyclist c = new RoadCyclist(num, name, level, age, country, scores);
				ok = addOneCompetitor(c);
			}
			else if (fileName.equals("InputTrack.csv"))
			{
				// To create TrackCyclist object and then add it to the list if it does not exist already
				TrackCyclist c = new TrackCyclist(num, name, level, age, country, scores);
				ok = addOneCompetitor(c);
			}
			else if (fileName.equals("InputCyclo.csv"))
			{
				// To ensure that only Expert competitors have participated
				if (level.equals("Expert")) 
				{
					// To create CycloCrossCyclist object and then add it to the list if it does not exist already
					CycloCrossCyclist c = new CycloCrossCyclist(num, name, level, age, country, scores, experience);
					ok = addOneCompetitor(c);
				}
				// If the competitor is not Expert, print message
				else
				{
					System.out.println("The level for " + num + " is not Expert.");
					ok = true;
				}
			}
			// If the competitor number already exists, print message
			if (ok == false)
			{
				System.out.println("The competitor with Competitor Number " + snum + " already exists.");
			}
		}
		catch (NumberFormatException nfe) 
		{
			System.out.println("Number conversion error in the line: " + line + " - " 
					+ nfe.getMessage());
		}
		catch (ArrayIndexOutOfBoundsException aib) 
		{
			System.out.println("Not enough items in the line: " + line + 
					" - at index position: " + aib.getMessage());
		}
	}

	/**
	 * Sorts the KMACompetitor objects by competitor number
	 * @return Table of competitors sorted by competitor number
	 */
	public String listByNum()
	{
		Collections.sort(competitorList);
		return getTableOfCompetitors();
	}

	/**
	 * Sorts the KMACompetitor objects by name.
	 * @return Table of competitors sorted by name.
	 */
	public String listByName() 
	{
		Collections.sort(competitorList, new ComNameComparator());
		return getTableOfCompetitors();
	}

	/**
	 * Sorts the KMACompetitor objects by overall score.
	 * @return Table of competitors sorted by overall score.
	 */
	public String listByScore() 
	{
		Collections.sort(competitorList, new ComScoreComparator());
		return getTableOfCompetitors();
	}

	/**
	 * Filters the competitorList on the comLevel "Beginner" and adds those 
	 * objects to a new ArrayList.
	 * @return The filtered ArrayList.
	 */
	public ArrayList<KMACompetitor> filterBeginner()
	{	
		ArrayList<KMACompetitor> filterList = new ArrayList<>();
		for (KMACompetitor c : competitorList) 
		{
			if (c.getComLevel().equals("Beginner"))
			{
				filterList.add(c);
			}
		}
		return filterList;
	}

	/**
	 * Filters the competitorList on the comLevel "Intermediate" and adds those 
	 * objects to a new ArrayList.
	 * @return The filtered ArrayList.
	 */
	public ArrayList<KMACompetitor> filterIntermediate()
	{	
		ArrayList<KMACompetitor> filterList = new ArrayList<>();
		for (KMACompetitor c : competitorList) 
		{
			if (c.getComLevel().equals("Intermediate"))
			{
				filterList.add(c);
			}
		}
		return filterList;
	}

	/**
	 * Filters the competitorList on the comLevel "Expert" and adds those 
	 * objects to a new ArrayList.
	 * @return The filtered ArrayList.
	 */
	public ArrayList<KMACompetitor> filterExpert()
	{	
		ArrayList<KMACompetitor> filterList = new ArrayList<>();
		for (KMACompetitor c : competitorList) 
		{
			if (c.getComLevel().equals("Expert"))
			{
				filterList.add(c);
			}
		}
		return filterList;
	}

	/**
	 * Appends 3 ArrayLists into one.
	 * @param c1 The first ArrayList for merging.
	 * @param c2 The second ArrayList for merging.
	 * @param c3 The third ArrayList for merging.
	 * @return The final ArrayList.
	 */
	public ArrayList<KMACompetitor> addAll(ArrayList<KMACompetitor> c1, ArrayList<KMACompetitor> c2, ArrayList<KMACompetitor> c3)
	{
		ArrayList<KMACompetitor> combined = new ArrayList<>();
		combined.addAll(c1);
		combined.addAll(c2);
		combined.addAll(c3);
		return combined;
	}
}
