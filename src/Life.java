package hw1;

import java.nio.file.*;
import java.io.IOException;
import java.util.List;

public class Life 
{
	public static void main(String[] args)
	{		
	//	Read in names
		
		List<String> maleNames = getNames("src/hw1/names_male.txt");
		List<String> femaleNames = getNames("src/hw1/names_female.txt");
		List<String> lastNames = getNames("src/hw1/names_last.txt");
		
	//	User settings
		
		int popSize = 100;			// initial population size
		int simYears = 200;			// duration of simulation (in years)
		double marriageRate = 0.1;	// marriage rate per year (for single adults)
		double birthRate = 0.1;	// birth rate per year (for married couples)
		
	//	Simulate life!
						
		Population population = new Population(maleNames, femaleNames, lastNames, popSize);
		population.simulateLife(simYears, marriageRate, birthRate);
	}
	
//	Member functions
	
	public static List<String> getNames(String file)
	{
		List<String> names = null;
		try {names = Files.readAllLines(Paths.get(file));} 
		catch (IOException e) {e.printStackTrace();}
		return names;
	}
}
