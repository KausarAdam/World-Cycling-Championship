import javax.swing.JOptionPane;

public class Manager 
{
	private CompetitorList allCompetitors;
	private CompetitorGUI gui;

	// Constructor
	public Manager()
	{
		allCompetitors = new CompetitorList();

		// To display the GUI
		gui = new CompetitorGUI();
	}

	// To get and check the competitor number entered by the user
	private void getAndCheckInput()
	{
		boolean invalid = false;
		int i = 0;

		// Repeat loop twice if the user enters invalid value
		while (invalid == false && i < 2)
		{
			// To display short details about the competitor by inputting competitor number
			String input = JOptionPane.showInputDialog(null, "Enter Competitor Number");

			// To confirm the user did not press 'cancel' or left it empty and that the input only has numbers
			if (input != null && input.matches("\\d+")) 
			{
				KMACompetitor c = allCompetitors.findByComNum(Integer.parseInt(input));

				// If competitor number exists, then display the details
				if (c != null) 
				{
					System.out.println(c.getShortDetails());
				}

				// If competitor number does not exist, display message
				else 
				{
					System.out.println("Competitor Number does not exist");
				}
				invalid = true;
			} 
			else 
			{
				System.out.println("Invalid Competitor Number");
				i++;
			} 
		}
	}

	// To run the program
	public void run()
	{
		// To read the contents of 3 csv files
		allCompetitors.readFile("InputRoad.csv");
		allCompetitors.readFile("InputCyclo.csv");
		allCompetitors.readFile("InputTrack.csv");

		/* To take input from the user and check whether a competitor with the same 
		competitor number exists */
		//getAndCheckInput();

		// To generate the report
		String report = allCompetitors.generateFinalReport();

		// To save the report as a text file
		allCompetitors.writeToFile("Competitor Report.txt", report);
	}
}
