import java.util.Comparator;

public class ComScoreComparator implements Comparator<KMACompetitor>
{
	// To override the method for sorting by age
	public int compare(KMACompetitor other1, KMACompetitor other2) 
	{
		double score1 = other1.getOverallScore();
		double score2 = other2.getOverallScore();
		
		// If score1 is greater than score2, return 1
		if (score1 > score2)
		{
			return 1;
		}
		// If score1 is equal to score2, return 0
		else if (score1 == score2)
		{
			return 0;
		}
		// If score1 is smaller than score2, return -1
		else
		{
			return -1;
		}
	}
}
