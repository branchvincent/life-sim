package hw1;

import java.util.List;

public class Female extends Person
{
//	Constructors
	
	public Female(String firstName, String lastName, int age)
	{
		this.setName(firstName, lastName);
		this.setGender("Female");
		this.setAge(age);
		this.setLifespan(Util.randGuassian(81, 5));
	}
	
	public Female() {this("Jane", "Doe", 0);}
	 
//	Member functions
	
	public Person giveBirth(List<String> maleNames, List<String> femaleNames)
	{		
	//	Choose names
		
		String maleName = Util.randString(maleNames);
		String femaleName = Util.randString(femaleNames);
		
	//	Give birth
		
		Person child = Util.randPerson(maleName, femaleName, getLastName(), 0);
		if (Util.verbose) System.out.println("\t" + getName() + " just had " + child.getFirstName() + "!");
		return child;
	}
}
