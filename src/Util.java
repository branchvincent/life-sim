package hw1;

import java.util.Random;
import java.util.List;

public class Util 
{
//	Member functions
	
	public static int randNum(int min, int max)
	{
		return min + rand.nextInt((max - min) + 1);
	}
	
	public static int randGuassian(int avg, int stdDev)
	{
		return (int) (avg + rand.nextGaussian() * stdDev);
	}
	
	public static String randString(List<String> strings)
	{
		int n = Util.rand.nextInt(strings.size());
		return strings.get(n);
	}
	
	public static Person randPerson(String maleName, String femaleName, String lastName, int age)
	{
		if (randNum(0,1) == 0) 
			return new Male(maleName, lastName, age);
		else 
			return new Female(femaleName, lastName, age);
	}
	
//	Data members
	
	public static final Random rand = new Random();
	public static boolean verbose = false;

}
