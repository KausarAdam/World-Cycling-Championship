import java.util.Arrays;

// This class is a subclass of KMACompetitor
public class RoadCyclist extends KMACompetitor implements Comparable<KMACompetitor>
{
	// Constructor
	public RoadCyclist (int comNumber, Name comName, String comLevel, int age, String country, int[] scores)
	{
		super (comNumber, comName, comLevel, age, country, scores);
	}

	// To get the average of scores that are odd (not even)
	public double getOverallScore()
	{
		double total = 0;
		int counter = 0;

		// To copy values of scores array to prevent original scores from sorting
		int[] scoresCopy = Arrays.copyOf(getScoreArray(), getNumOfScores());

		// To calculate the sum of odd scores
		for (int i=0; i<scoresCopy.length; i++)
		{
			// To find out whether the score is odd
			if (scoresCopy[i] % 2 == 1)
			{
				total += scoresCopy[i];
				counter++;
			}
		}

		// To find the average by dividing the total with the number of odd scores
		return Double.parseDouble(String.format("%.1f", total/counter));
	}

	// To display the full details of RoadCyclist objects
	public String toString()
	{
		return "Competitor number " + getComNumber() + ", name " + 
				getComName().getFullName() + ", country " + getCountry()
				+ ".\n" + getComName().getFirstName() + " is a Road Cyclist " + 
				getComLevel() + " aged " + getAge() + " and received these scores : " + 
				displayScores() + ".\nThis gives the competitor an overall score of " + 
				String.format("%.1f", getOverallScore()) + ".";
	}

	// To override the compareTo method for sorting
	public int compareTo(KMACompetitor other) 
	{
		return Integer.toString(getComNumber()).compareTo(Integer.toString(other.getComNumber()));
	}
}
