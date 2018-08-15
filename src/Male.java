package hw1;

public class Male extends Person
{
//	Constructors
	
	public Male(String firstName, String lastName, int age)
	{
		this.setName(firstName, lastName);
		this.setGender("Male");
		this.setAge(age);
		this.setLifespan(Util.randGuassian(76, 5));
	}
	
	public Male() {this("John", "Doe", 0);}
	
//	Member functions
	
	public void getMarried(Female bride)
	{
	//	Get married
		
		if (Util.verbose) System.out.println("\t" + getName() + " married " + bride.getName() + "!");
		bride.setLastName(getLastName());
		
	//	Update marital status
		
		setIsMarried(true);
		setPartner(bride);
		bride.setIsMarried(true);
		bride.setPartner(this);
	}
}
