import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CompetitorGUI extends JFrame implements ActionListener
{
	// GUI components
	JTextArea displayList;
	JTextField searchNum, searchUpdate, searchMessage, num, name, 
	level, age, country, overall, shortDetails, updateMessage;
	JTextField [] score;
	JButton search, ok, clear, searchUpdateButton, updateButton, close;
	JScrollPane scrollList;
	JRadioButton rNumber, rName, rScore;
	ButtonGroup bg;
	JCheckBox beginner, intermediate, expert;

	// CompetitorLists
	private CompetitorList comList;
	private CompetitorList comList2; // For sort and filter

	// Constructor
	public CompetitorGUI()
	{	
		// To read and process the text files
		comList = new CompetitorList();
		comList.readFile("InputRoad.csv");
		comList.readFile("InputCyclo.csv");
		comList.readFile("InputTrack.csv");

		// To set the size of the frame
		setSize(1500, 670);
		// To position the frame in the centre
		setLocationRelativeTo(null);

		// To set the title and icon of the window
		setTitle(" World Cycling Championship");
		ImageIcon img = new ImageIcon("icon.jpg");
		setIconImage(img.getImage());

		// Calling the panels of the frame
		setupEastPanel();
		setupWestPanel();
		setupSouthPanel();

		// To disable the standard close button
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		// To display the splash screen
		splash();

		// To set the window visible
		setVisible(true);
	}

	// To set up the splash screen
	private void splash()
	{
		// To create a new window
		JWindow window = new JWindow();

		// To create a new label and to add an image into it
		JLabel img = new JLabel();
		img.setIcon(new ImageIcon("splash.png"));

		// To add the image label into the window
		window.getContentPane().add(img);

		// To set the size, location and visibility
		window.setSize(752, 563);
		window.setLocationRelativeTo(null);
		window.setVisible(true);

		// To pause the screen for specified time
		try
		{
			Thread.sleep(3000);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}

		// To make the window disappear after the thread resumes
		window.setVisible(false);
	}

	// create labels with different strings and alignment
	private JLabel createOneLabel(String s, int align)
	{
		JLabel label = new JLabel(s, align);
		label.setForeground(Color.BLACK);
		Font f = new Font(Font.SANS_SERIF, Font.PLAIN, 14);
		label.setFont(f);
		return label;
	}

	// To create the west panel
	private void setupWestPanel()
	{
		//To create the display section
		displayList = new JTextArea(15,20);
		displayList.setFont(new Font (Font.MONOSPACED, Font.PLAIN, 14));
		displayList.setEditable(false);
		displayList.setText(comList.getTableOfCompetitors());

		// To add the scrollbar to the display area
		scrollList = new JScrollPane(displayList);
		this.add(scrollList, BorderLayout.CENTER);
	}

	// To create the east panel
	private void setupEastPanel()
	{	
		// To create the sort section
		JPanel sort = new JPanel(new GridLayout(1, 3));
		rNumber = new JRadioButton();
		rNumber.setText("comNum");
		sort.add(rNumber);
		rName = new JRadioButton();
		rName.setText("Name");
		sort.add(rName);
		rScore = new JRadioButton();
		rScore.setText("Overall Score");
		sort.add(rScore);
		bg = new ButtonGroup();
		bg.add(rNumber);
		bg.add(rName);
		bg.add(rScore);

		// To create the filter section
		JPanel filter = new JPanel(new GridLayout(1, 3));
		beginner = new JCheckBox("Beginner");
		filter.add(beginner);
		intermediate = new JCheckBox("Intermediate");
		filter.add(intermediate);
		expert = new JCheckBox("Expert");
		filter.add(expert);

		// To add the ok and clear buttons
		JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 0, 0));
		ok = new JButton("OK");
		buttonPanel.add(ok);
		// To specify the action when button is pressed
		ok.addActionListener(this);
		clear = new JButton("Clear");
		buttonPanel.add(clear);
		// To specify the action when button is pressed
		clear.addActionListener(this);

		// To combine the sort and filter section
		JPanel sortAndFilter = new JPanel(new GridLayout(7, 1));
		sortAndFilter.add(createOneLabel("Sort and Filter", JLabel.CENTER));
		sortAndFilter.add(createOneLabel("Sort by (ascending order):", JLabel.LEFT));
		sortAndFilter.add(sort);
		sortAndFilter.add(createOneLabel("Filter on:", JLabel.LEFT));
		sortAndFilter.add(filter);
		sortAndFilter.add(buttonPanel);
		sortAndFilter.add(createOneLabel("Search and Update", JLabel.CENTER));

		// To create the search section
		JPanel search = new JPanel(new GridLayout(2, 2));
		search.add(createOneLabel("comNum : ", JLabel.LEFT));
		searchUpdate = new JTextField(3);
		search.add(searchUpdate);
		searchMessage = new JTextField(20);
		searchMessage.setEditable(false);
		searchMessage.setForeground(Color.RED);
		search.add(searchMessage);
		searchUpdateButton = new JButton("Search");
		search.add(searchUpdateButton);
		// To specify the action when button is pressed
		searchUpdateButton.addActionListener(this);

		// To create the update section
		JPanel update = new JPanel(new GridLayout(7, 2));
		update.add(createOneLabel("comNum : ", JLabel.LEFT));
		num = new JTextField(3);
		num.setEditable(false);
		update.add(num);
		update.add(createOneLabel("Name : ", JLabel.LEFT));
		name = new JTextField(20);
		name.setEditable(false);
		update.add(name);
		update.add(createOneLabel("Level : ", JLabel.LEFT));
		level = new JTextField(15);
		level.setEditable(false);
		update.add(level);
		update.add(createOneLabel("Age : ", JLabel.LEFT));
		age = new JTextField(2);
		age.setEditable(false);
		update.add(age);
		update.add(createOneLabel("Country : ", JLabel.LEFT));
		country = new JTextField(20);
		country.setEditable(false);
		update.add(country);
		update.add(createOneLabel("Overall Score : ", JLabel.LEFT));
		overall = new JTextField(20);
		overall.setEditable(false);
		update.add(overall);
		update.add(createOneLabel("Scores : ", JLabel.LEFT));
		// For scores
		JPanel scorePanel = new JPanel();
		score = new JTextField[5];
		for (int i = 0; i < 5; i++) 
		{
			score[i] = new JTextField(2);
			score[i].setEditable(false);
			scorePanel.add((score[i]));
		}
		update.add(scorePanel);

		// To insert the update button in the centre
		JPanel update2 = new JPanel(new GridLayout(1, 3));
		update2.add(createOneLabel("", JLabel.LEFT));
		updateButton = new JButton("Update");
		updateButton.setEnabled(false);
		update2.add(updateButton);
		// To specify the action when button is pressed
		updateButton.addActionListener(this);
		update2.add(createOneLabel("", JLabel.LEFT));

		// To create the final update section
		JPanel update3 = new JPanel(new BorderLayout());
		update3.add(search, BorderLayout.NORTH);
		update3.add(update, BorderLayout.CENTER);
		update3.add(update2, BorderLayout.SOUTH);

		// To create the east panel
		JPanel eastPanel = new JPanel(new BorderLayout());
		eastPanel.add(sortAndFilter, BorderLayout.NORTH);
		eastPanel.add(update3, BorderLayout.CENTER);
		updateMessage = new JTextField(20);
		updateMessage.setEditable(false);
		updateMessage.setForeground(Color.RED);
		updateMessage.setHorizontalAlignment(JTextField.CENTER);
		eastPanel.add(updateMessage, BorderLayout.SOUTH);

		// To add the east panel to the content pane
		this.add(eastPanel, BorderLayout.EAST); 
	}

	// To create the south panel
	private void setupSouthPanel()
	{
		// To create the search section
		JPanel searchPanel = new JPanel(new GridLayout(2, 3, 2, 0));
		// To enter a blank line
		searchPanel.add(createOneLabel("", JLabel.LEFT));
		searchPanel.add(createOneLabel("Search for Short Details", JLabel.CENTER));
		searchPanel.add(createOneLabel("", JLabel.LEFT));
		searchPanel.add(createOneLabel("Enter Competitor Number: ", JLabel.LEFT));
		searchNum = new JTextField(3);
		searchPanel.add(searchNum);
		search = new JButton("Search");  
		searchPanel.add(search);    
		// To specify the action when button is pressed
		search.addActionListener(this);

		// To create the result section
		JPanel resultPanel = new JPanel(new GridLayout(1, 1));
		shortDetails = new JTextField(40);
		shortDetails.setEditable(false);
		shortDetails.setForeground(Color.RED);
		shortDetails.setHorizontalAlignment(JTextField.CENTER);
		resultPanel.add(shortDetails);

		// To create the south panel
		JPanel southPanel = new JPanel(new BorderLayout(2, 2));
		southPanel.add(searchPanel, BorderLayout.NORTH);
		resultPanel.setPreferredSize(new Dimension(0, 28));
		southPanel.add(resultPanel, BorderLayout.CENTER);
		close = new JButton("Close");
		close.setPreferredSize(new Dimension(0, 35));
		southPanel.add(close, BorderLayout.SOUTH);
		// To specify the action when button is pressed
		close.addActionListener(this);

		// To add the south panel to the content pane
		this.add(southPanel, BorderLayout.SOUTH); 
	}

	/* Override method for specifying the actions carried out 
	   when a button is clicked */
	public void actionPerformed(ActionEvent e) 
	{
		// If close is clicked, exit the system
		if (e.getSource() == close)
		{
			// To save the original list with updated score as .txt
			comList.writeToFile("UpdatedScoreReport.txt", comList.getTableOfCompetitors());

			/* If comList2 is null (sort and filter was not used), then exit
			   Without this code, the user will not be able to close the application until
			   he/she clicks on the 'ok' button because the files needs to be written */
			if (comList2 == null)
			{
				System.exit(0);
			}

			// To save the sorted/filtered list with updated score as .txt
			comList2.writeToFile("FIlter&SortUpdatedReport.txt", comList2.getTableOfCompetitors());

			// Exit
			System.exit(0);
		}
		// If search in update section is clicked
		else if (e.getSource() == searchUpdateButton)
		{
			searchUpdate();
		}
		// If update is clicked
		else if (e.getSource() == updateButton)
		{
			// To check all the checkboxes so that the updated score is displayed in the text area
			beginner.setSelected(true);
			intermediate.setSelected(true);
			expert.setSelected(true);

			// To update the scores
			update();
			// To update the TextArea
			sort();
		}
		// If search is clicked
		else if (e.getSource() == search)
		{
			search();
		}
		// If ok is clicked
		else if (e.getSource() == ok)
		{
			sort();
		}
		// If clear is clicked
		else if (e.getSource() == clear)
		{
			// Clear all selections 
			bg.clearSelection();
			beginner.setSelected(false);
			intermediate.setSelected(false);
			expert.setSelected(false);
		}
	}

	// When the search button is clicked in the update section
	private void searchUpdate()
	{
		// To remove any previous message
		updateMessage.setText("");

		// To get the user input and remove spaces
		String stringNum = searchUpdate.getText().trim();

		// If the field is not empty and contains only numbers
		if (stringNum.length() != 0 && stringNum.matches("\\d+"))
		{
			// To search for the competitor
			int intNum = Integer.parseInt(stringNum);
			KMACompetitor c = comList.findByComNum(intNum);

			// If competitor exists, then display details in textfields
			if (c != null)
			{
				searchMessage.setText("");
				num.setText(Integer.toString(c.getComNumber()));
				name.setText(c.getComName().getFirstName());
				level.setText(c.getComLevel());
				age.setText(Integer.toString(c.getAge()));
				country.setText(c.getCountry());
				overall.setText(Double.toString(c.getOverallScore()));
				int [] actionScore = c.getScoreArray();
				for (int i = 0; i < actionScore.length; i++) 
				{
					score[i].setText(Integer.toString(actionScore[i]));
					// To make the scores field editable
					score[i].setEditable(true);
				}
				// To enable the update button
				updateButton.setEnabled(true);
			}
			// If the copetitor is not found, then display message and clear field
			else
			{
				searchMessage.setText("comNum " + intNum + " does not exist");
				clear();
			}
		}
		// If the field does not contain comNum, then display message and clear field
		else
		{
			searchMessage.setText("Please enter a comNum");
			clear();
		}
	}

	// When the Update button is clicked
	private void update()
	{
		// To remove any previous error message
		updateMessage.setText("");

		// To get the user input and remove spaces
		String stringNum = searchUpdate.getText().trim();

		// To convert comNum to int
		int intNum = Integer.parseInt(stringNum);

		// To search for the comNum
		KMACompetitor c = comList.findByComNum(intNum);

		// To hold the new scores entered by the user
		int[] newScore = new int[5];
		for (int i = 0; i < 5; i++)
		{
			// If the score is not empty and a number
			String ss = score[i].getText();
			if (ss.length() != 0 && ss.matches("\\d+")) 
			{
				// If the score is between 1 and 5, then store it
				int s = Integer.parseInt(score[i].getText());
				if ((s > 0) && (s < 6)) 
				{
					newScore[i] = s;

					// If the last index is being read, then update the scores and display message
					if (i == 4) 
					{
						c.setScores(newScore);
						updateMessage.setText("Updated!");
					}
				}
				// If the score is not between 1 and 5, then do not update the score and display error message
				else 
				{
					System.out.println("The score entered at index " + i + " - is not between 1 and 5. \n");

					// To display the original scores and discard the new ones
					for (int j = 0; j < 5; j++) 
					{
						score[j].setText(Integer.toString(c.getScoreArray()[j]));
					}
					updateMessage.setText("Update Failed - Score should be between 1 and 5");
					break;
				} 
			}
			// If the field does not contain comNum, then display message and clear field
			else
			{
				System.out.println("The score entered at index " + i + " - is not a number. \n");

				// To display the original scores and discard the new ones
				for (int j = 0; j < 5; j++) 
				{
					score[j].setText(Integer.toString(c.getScoreArray()[j]));
				}
				updateMessage.setText("Please enter a score number between 1 and 5");
				break;
			}
		}
	}

	// When the search button for short details is clicked
	private void search()
	{
		// To clear any previous message
		shortDetails.setText("");

		// To get the user input and remove spaces
		String stringNum = searchNum.getText().trim();

		// If the field is not empty and contains only numbers
		if (stringNum.length() != 0 && stringNum.matches("\\d+"))
		{
			// To search for the competitor
			int intNum = Integer.parseInt(stringNum);
			KMACompetitor c = comList.findByComNum(intNum);

			// If competitor exists, then display short details
			if (c != null)
			{
				shortDetails.setText(c.getShortDetails());
			}
			// If the copetitor is not found, then display message and clear field
			else
			{
				shortDetails.setText("comNum " + intNum + " does not exist");
				searchNum.setText("");
			}
		}
		// If the field does not contain comNum, then display message and clear field
		else
		{
			shortDetails.setText("Please enter a comNum");
			searchNum.setText("");
		}
	}

	// To filter the objects
	private ArrayList<KMACompetitor> filter()
	{
		/* An empty list for situations when there are less than 3 ArrayLists 
		for the addAll() method */
		ArrayList<KMACompetitor> empty = new ArrayList<>();

		// If all 3 checkboxes are selected, display all levels
		if (beginner.isSelected() && intermediate.isSelected() && expert.isSelected())
		{
			// Merges 3 lists together
			return comList.addAll(comList.filterBeginner(), comList.filterIntermediate(), comList.filterExpert());
		}
		// If beginner is selected
		else if (beginner.isSelected())
		{
			// If beginner and intermediate are selected
			if (intermediate.isSelected())
			{
				return comList.addAll(comList.filterBeginner(), comList.filterIntermediate(), empty);
			}
			// If beginner and expert are selected
			else if (expert.isSelected())
			{
				return comList.addAll(comList.filterBeginner(), comList.filterExpert(), empty);
			}
			// If only beginner is selected
			else
			{
				return comList.addAll(comList.filterBeginner(), empty, empty);
			}
		}
		// If intermediate is selected
		else if (intermediate.isSelected())
		{
			// If intermediate and expert are selected
			if (expert.isSelected())
			{
				return comList.addAll(comList.filterIntermediate(), comList.filterExpert(), empty);
			}
			// If only intermediate is selected
			else
			{
				return comList.addAll(comList.filterIntermediate(), empty, empty);
			}
		}
		// If only expert is selected
		else if (expert.isSelected())
		{
			return comList.addAll(comList.filterExpert(), empty, empty);
		}
		// If none of the checkboxes are selected
		else
		{
			return comList.addAll(empty, empty, empty);
		}
	}

	// To sort the filtered list
	private void sort()
	{
		comList2 = new CompetitorList();

		// New ArrayList for storing the filtered list
		ArrayList<KMACompetitor> newList = filter();

		// Adding objects of the ArrayList to the competitorList
		for (KMACompetitor c : newList) 
		{
			comList2.addOneCompetitor(c);
		}

		// Sort by competitor number
		if (rNumber.isSelected())
		{
			displayList.setText(comList2.listByNum());
		}
		// Sort by name
		else if (rName.isSelected())
		{
			displayList.setText(comList2.listByName());
		}
		// Sort by overall score
		else if (rScore.isSelected())
		{
			displayList.setText(comList2.listByScore());
		}
		// Display the filtered order if nothing is selected
		else
		{
			displayList.setText(comList2.getTableOfCompetitors());
		}
	}

	// To clear the text fields in the update section and to disable the score fields and the update button
	private void clear()
	{
		num.setText("");
		name.setText("");
		level.setText("");
		age.setText("");
		country.setText("");
		overall.setText("");
		for (int i = 0; i < 5; i++) 
		{
			score[i].setText("");
			score[i].setEditable(false);
		}
		updateButton.setEnabled(false);
	}
}
