import java.util.Arrays;

// This class is a subclass of KMACompetitor
public class CycloCrossCyclist extends KMACompetitor implements Comparable<KMACompetitor>
{
	// Instance variable
	// All cyclo-cross cyclist will be experts and have to specify their years of experience
	private int yearsOfExperience;

	// Constructor
	public CycloCrossCyclist (int comNumber, Name comName, String comLevel, int age, String country, int[] scores, int yearsOfExperience)
	{
		super (comNumber, comName, comLevel, age, country, scores);
		this.yearsOfExperience = yearsOfExperience;
	}

	// Getter
	public int getYearsOfExperience()
	{
		return yearsOfExperience;
	}

	// To get the average of top 3 scores
	public double getOverallScore()
	{
		double total = 0;

		// To copy values of scores array to prevent original scores from sorting
		int[] scoresCopy = Arrays.copyOf(getScoreArray(), getNumOfScores());

		// To sort the array
		Arrays.sort(scoresCopy);

		// To calculate the highest 3 scores
		for (int i=scoresCopy.length-3; i<scoresCopy.length; i++)
		{
			total += scoresCopy[i];
		}

		// To find the average by dividing total by 3
		return Double.parseDouble(String.format("%.1f", total/3));
	}

	// To display the full details of CycloCrossCyclist objects
	public String toString()
	{
		return "Competitor number " + getComNumber() + ", name " + 
				getComName().getFullName() + ", country " + getCountry() + ".\n" + 
				getComName().getFirstName() + " is a Cyclo-Cross Cyclist Expert aged " 
				+ getAge() + ", with an experience of " + getYearsOfExperience() + 
				" years and received these scores : " + displayScores() + 
				".\nThis gives the competitor an overall score of " + 
				String.format("%.1f", getOverallScore()) + ".";
	}

	// To override the compareTo method for sorting
	public int compareTo(KMACompetitor other) 
	{
		return Integer.toString(getComNumber()).compareTo(Integer.toString(other.getComNumber()));
	}
}
