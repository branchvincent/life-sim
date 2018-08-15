package hw1;

import java.util.List;
import java.util.LinkedList;
import java.util.Collections;

public class Population 
{
//	Constructors
	
	public Population(List<String> maleNames, List<String> femaleNames, List<String> lastNames, int size)
	{
		this.maleNames = maleNames;
		this.femaleNames = femaleNames;
		this.lastNames = lastNames;
		this.people = new LinkedList<Person>();
				 
		for (int i = 0; i < size; i++)
			people.add(createPerson(20, 50));
	}
	
	public Population() {this(new LinkedList<String>(), new LinkedList<String>(), new LinkedList<String>(), 0);}
		
//	Inspectors
	
	public List<String> getMaleNames() {return maleNames;}
	public List<String> getFemaleName() {return femaleNames;}
	public List<String> getLastNames() {return lastNames;}
	public List<Person> getPeople() {return people;}
	
	public List<Person> getSubPopulation(String gender)
	{
		List<Person> subpopulation = new LinkedList<>(); 
		for (Person p : people)
			if (p.getGender() == gender)
				subpopulation.add(p);
		return subpopulation;
	}
	
	public List<Person> getSubPopulation(String gender, int minAge, boolean isMarried)
	{
		List<Person> subpopulation = new LinkedList<>(); 
		for (Person p : people)
			if (p.getGender() == gender && p.isMarried() == isMarried && p.getAge() >= minAge)
				subpopulation.add(p);
		return subpopulation;
	}
	
//	Mutators
	
	public void setMaleNames(List<String> names) {maleNames = names;}
	public void setFemaleNames(List<String> names) {femaleNames = names;}
	public void setLastNames(List<String> names) {lastNames = names;}
	public void setPeople(List<Person> people) {this.people = people;}
	
	public int agePeople(int years) 
	{		
		List<Person> dead = new LinkedList<>(); 
		
	//	Age each person
		
		for (Person p : people)
		{
			p.addToAge(1);
						
			if (p.getAge() >= p.getLifespan())
			{
				p.die();
				dead.add(p);
			}
		}
		
	//	Remove dead people
		
		int numDeaths = dead.size();
		people.removeAll(dead);
				
		return numDeaths;
	} 
	
	public int addPeople(double birthRate)
	{
	//	Get eligible women
		
		List<Person> marriedAdultFemales = getSubPopulation("Female", 18, true);
		int numBirths = (int) (marriedAdultFemales.size() * birthRate);

	//	Randomly impregnant women
		
		Collections.shuffle(marriedAdultFemales);
		for (int i = 0; i < numBirths; i++)
		{
			Female mom = (Female) marriedAdultFemales.get(i);
			Person child = mom.giveBirth(maleNames, femaleNames);
			people.add(child);
		}	
		
		return numBirths;
	}
	
	public int marryPeople(double marriageRate)
	{
	//	Get eligible adults
		
		List<Person> singleAdultMales = getSubPopulation("Male", 18, false);
		List<Person> singleAdultFemales = getSubPopulation("Female", 18, false);
		List<Person> minList = singleAdultMales;
		
		if (singleAdultFemales.size() < singleAdultMales.size())
			minList = singleAdultFemales;
		
	//	Randomly marry adults
		
		int numMarriages = (int) (minList.size() * marriageRate);
		Collections.shuffle(singleAdultMales);
		Collections.shuffle(singleAdultFemales);

		for (int i = 0; i < numMarriages; i++)
		{
			Male groom = (Male) singleAdultMales.get(i);
			Female bride = (Female) singleAdultFemales.get(i);
			groom.getMarried(bride);
		}		
		
		return numMarriages;
	}
	
//	Other member functions
	
	public void simulateLife(int years, double marriageRate, double birthRate)
	{
		int males = getSubPopulation("Male").size();
		int females = getSubPopulation("Female").size();
		
		System.out.println("Simulating life for " + years + " years...");
		System.out.println("Marriage Rate: " + marriageRate * 100 + "%");
		System.out.println("Birth Rate: " + birthRate * 100 + "%");
		System.out.println("Initial Population: " + people.size() + 
				" (" + males + " males, " + females + " females)");
		System.out.println();
		
		for (int y = 0; y < years; y++)
		{
			System.out.println("Year " + (y + 1));
			
			int numDeaths = agePeople(1);
			int numMarriages = marryPeople(marriageRate);
			int numBirths = addPeople(birthRate);
			
			if (people.size() == 0)
			{
				System.out.println("Uh oh! Your population has died out.");
				break;
			}
			
			print(numDeaths, numMarriages, numBirths);
		}
		
		System.out.println("Thanks for playing. Goodbye!");
	}
	
	public void print(int numDeaths, int numMarriages, int numBirths)
	{
		int males = getSubPopulation("Male").size();
		int females = getSubPopulation("Female").size();
		int couples = getSubPopulation("Male", 18, true).size();
		
		System.out.println("Population: " + people.size() + 
				" (" + males + " males, " + females + " females)");
		System.out.println("Couples: " + couples + 
				" (" + numMarriages + " new)");
		System.out.println("Births: " + numBirths);
		System.out.println("Deaths: " + numDeaths);
		System.out.println();
	}
	
	public Person createPerson(int minAge, int maxAge)
	{
	//	Choose attributes
		
		String maleName = Util.randString(maleNames);
		String femaleName = Util.randString(femaleNames);
		String lastName = Util.randString(lastNames);
		int age = Util.randNum(minAge, maxAge);
		
	//	Create person 
		
		Person person = Util.randPerson(maleName, femaleName, lastName, age);
		return person;
	}
	
//	Data members
	
	private List<String> maleNames;
	private List<String> femaleNames;
	private List<String> lastNames;
	private List<Person> people;
}
