abstract public class KMACompetitor implements Comparable<KMACompetitor>
{
	// Instance Variables
	private int comNumber;
	private Name comName;
	private String comLevel;
	private int age;
	private String country;
	private int[] scores;
	private static final int NUM_OF_SCORES = 5;

	// Constructor
	public KMACompetitor(int comNumber, Name comName, String comLevel, int age, String country, int[] scores) 
	{
		this.comNumber = comNumber;
		this.comName = comName;
		this.comLevel = comLevel;
		this.age = age;
		this.country = country;
		this.scores = scores;
	}

	// Getters
	public int getComNumber() 
	{
		return comNumber;
	}
	public Name getComName() 
	{
		return comName;
	}
	public String getComLevel() 
	{
		return comLevel;
	}
	public int getAge() 
	{
		return age;
	}
	public String getCountry() 
	{
		return country;
	}
	public int[] getScoreArray() 
	{
		return scores;
	}
	public int getNumOfScores() 
	{
		return NUM_OF_SCORES;
	}

	// Setters
	public void setComNumber(int comNumber) 
	{
		this.comNumber = comNumber;
	}
	public void setComName(Name comName) 
	{
		this.comName = comName;
	}
	public void setComLevel(String comLevel) 
	{
		this.comLevel = comLevel;
	}
	public void setAge(int age) 
	{
		this.age = age;
	}
	public void setCountry(String country) 
	{
		this.country = country;
	}
	public void setScores(int[] scores) 
	{
		this.scores = scores;
	}

	// To display the scores from the array
	public String displayScores() 
	{
		String allScores = scores[0] + ", ";
		try 
		{
			for (int i=1; i<NUM_OF_SCORES; i++) 
			{
				allScores += scores[i];

				// To avoid comma after the last score
				if (i != NUM_OF_SCORES - 1) 
				{
					allScores += ", ";
				}
			}
		} 
		// If there are less than 5 scores
		catch (ArrayIndexOutOfBoundsException aib) 
		{
			System.out.println("Report not created. Insufficient scores at index position: "+ aib.getMessage());
			System.exit(0);
		}
		return allScores;
	}

	// To get the overallscore
	abstract public double getOverallScore();

	// To get full details
	public String getFullDetails(KMACompetitor c1) 
	{
		return c1.toString();
	}

	// To get short details
	public String getShortDetails() 
	{
		return "CN " + comNumber + " " + comName.getInitials() + 
				" has overall score " + getOverallScore() + ".\n";
	}

	// To display full details of KMACompetitor objects
	public String toString()
	{
		return "Competitor number " + comNumber + ", name " + 
				getComName().getFullName() + ", country " + country
				+ ".\n" + getComName().getFirstName() + " is a/an " + 
				comLevel + " aged " + age + " and received these scores : " + 
				displayScores() + ".\n";
	}

	// To override the compareTo method for sorting
	public int compareTo(KMACompetitor other) 
	{
		return Integer.toString(comNumber).compareTo(Integer.toString(other.comNumber));
	}
}
