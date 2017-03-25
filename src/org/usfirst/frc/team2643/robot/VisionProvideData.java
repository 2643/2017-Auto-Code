package org.usfirst.frc.team2643.robot;

public class VisionProvideData
{
	static double[] height = VisionAuto.table.getNumberArray("Height", new double[0]);
	static double[] temp = new double[2];

	public static int lengthOfArray(String name)
	{
		return VisionAuto.table.getNumberArray(name, new double[0]).length;
	}
	
	public static double[] provideArray(String name)
	{
		return VisionAuto.table.getNumberArray(name, new double[0]);
	}
	
	public static double provideNum(String name)
	{
		double temp = 0.0;
		return VisionAuto.table.getNumber(name, temp);
	}
	

	private static int largestValue(double[] table)
	{
		int largestVal = 0;

		for (int x = 0; x < table.length; x++)
		{
			if (table[largestVal] < table[x])
				largestVal = x;
		}
		return largestVal;
	}

	private static int secondLargestValue(double[] table, double largestVal)
	{
		int secondLargestVal = 0;

		for (int x = 0; x < table.length; x++)
		{
			if ((table[secondLargestVal] < table[x] && table[x] != largestVal))
			{
				secondLargestVal = x;
			}
		}
		return secondLargestVal;
	}
}
