import java.util.Comparator;

public class ComNameComparator implements Comparator<KMACompetitor>
{
	// To override the method for sorting by name
	public int compare(KMACompetitor other1, KMACompetitor other2) 
	{
		Name n1 = other1.getComName();
		Name n2 = other2.getComName();
		return n1.compareTo(n2);
	}
}
