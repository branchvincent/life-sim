package hw1;

public abstract class Person
{
//	Constructors
	
	public Person(String firstName, String lastName, int age)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		gender = "?";
		this.age = age;
		lifespan = Util.randGuassian(79, 3);
		married = false;
		partner = null;
	}
	
	public Person() {this("","",0);}
	
//	Inspectors
	
	public String getFirstName() {return firstName;}
	public String getLastName() {return lastName;}
	public String getName() {return firstName + " " + lastName;}
	public String getGender() {return gender;}
	public int getAge() {return age;}
	public int getLifespan() {return lifespan;}
	public boolean isMarried() {return married;}
	public Person getPartner() {return partner;}
	
//	Mutators
	
	public void setFirstName(String firstName) {this.firstName = firstName;}
	public void setLastName(String lastName) {this.lastName = lastName;}
	public void setName(String firstName, String lastName) {this.firstName = firstName; this.lastName = lastName;}
	public void setGender(String gender) {this.gender = gender;}
	public void setAge(int age) {this.age = age;}
	public void setLifespan(int lifespan) {this.lifespan = lifespan;}
	public void setIsMarried(boolean married) {this.married = married;}
	public void setPartner(Person partner) {this.partner = partner;}
	public void addToAge(int years) {age += years;}
	
//	Other member functions
	
	public void die()
	{
		if (Util.verbose) System.out.println("\t" + getName() + " has died at age " + lifespan);
		
		if (partner != null)
		{
			partner.setIsMarried(false);
			partner.setPartner(null);
		}
	}
	
	public void print()
	{
		System.out.println("Name: " + getName());
		System.out.println("Gender: " + gender);
		System.out.println("Age: " + age);
		System.out.println("Lifespan: " + lifespan);
		
		if (married)
		{
			System.out.println("Marital Status: Married");
			System.out.println("Partner: " + partner.getName());
		}
		else
			System.out.println("Marital Status: Single");
		
		System.out.println();
	}
	
//	Data members
	
	private String firstName;
	private String lastName;
	private String gender;
	private int age;
	private int lifespan;
	private boolean married;
	private Person partner;
}
