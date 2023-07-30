public class Name implements Comparable<Name>
{
	//Instance Variables
	private String firstName;
	private String middleName;
	private String lastName;

	//Constructors
	public Name(String firstName, String middleName, String lastName)
	{
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
	}

	public Name(String firstName, String lastName)
	{
		this.firstName = firstName;
		this.middleName = "";
		this.lastName = lastName;
	}

	public Name(String fullName)
	{
		//Storing the index of first space
		int position1 = fullName.indexOf(' ');
		//Storing the index of last space
		int position2 = fullName.lastIndexOf(' ');

		if (position1 < 0)
		{
			// To capitalise the first letter of the frstName for sortng purposes
			this.firstName = fullName.substring(0, 1).toUpperCase() + fullName.substring(1);;
			this.middleName = "";
			this.lastName = "";
		}
		else
		{
			// To capitalise the first letter of the frstName for sortng purposes
			this.firstName = fullName.substring(0, 1).toUpperCase() + fullName.substring(1, position1);

			//To check whether middle name is empty or not
			if (position1 != position2)
			{
				this.middleName = fullName.substring(position1+1, position2);
			}
			else
			{
				this.middleName = "";
			}

			this.lastName = fullName.substring(position2+1);
		}
	}

	//Getters
	public String getFirstName()
	{
		return firstName;
	}
	public String getMiddleName()
	{
		return middleName;
	}
	public String getLastName()
	{
		return lastName;
	}

	//Setters
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	public void setMiddleName(String middleName)
	{
		this.middleName = middleName;
	}
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	//To get the full name in the format firstName middleName lastName
	public String getFullName()
	{
		//For checking whether the middleName is empty or not
		if (middleName.length() != 0)
		{
			return firstName + " " + middleName + " " + lastName;
		}
		else
		{
			return firstName + " " + lastName;
		}
	}

	//To get the capitalised initials
	public String getInitials()
	{
		String initial = "";
		if (middleName.length() != 0)
		{
			initial = "(" + firstName.charAt(0) + middleName.charAt(0) + lastName.charAt(0) + ")";
			return initial.toUpperCase();
		}
		else if (firstName.length() != 0 && lastName.length() == 0)
		{
			initial = "(" + firstName.charAt(0) + ")";
			return initial.toUpperCase();
		}
		else if (firstName.length() != 0 && lastName.length() != 0)
		{
			initial =  "(" + firstName.charAt(0) + lastName.charAt(0) + ")";
			return initial.toUpperCase();
		}
		else
		{
			return "(Name is Missing)";
		}
	}

	// To override the compareTo method for sorting
	public int compareTo(Name other) 
	{
		return getFirstName().compareTo(other.getFirstName());
	}
}
