import java.util.Arrays;

// This class is a subclass of KMACompetitor
public class TrackCyclist extends KMACompetitor implements Comparable<KMACompetitor>
{
	// Constructor
	public TrackCyclist (int comNumber, Name comName, String comLevel, int age, String country, int[] scores)
	{
		super (comNumber, comName, comLevel, age, country, scores);
	}

	// To get the median
	public double getOverallScore()
	{
		// To copy values of scores array to prevent original scores from sorting
		int[] scoresCopy = Arrays.copyOf(getScoreArray(), getNumOfScores());

		// To sort the array
		Arrays.sort(scoresCopy);

		// To check whether the array size is odd and then return the median
		if (scoresCopy.length % 2 == 1)
		{
			return (scoresCopy[((scoresCopy.length - 1)/2)]);
		}
		// To check whether the array size is even and then return the median (for future use)
		else
		{
			return (scoresCopy[(scoresCopy.length / 2)] 
					+ scoresCopy[((scoresCopy.length / 2) - 1)])/2;
		}
	}

	// To display the full details of TrackCyclist objects
	public String toString()
	{
		return "Competitor number " + getComNumber() + ", name " + 
				getComName().getFullName() + ", country " + getCountry()
				+ ".\n" + getComName().getFirstName() + " is a Track Cyclist " + 
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
