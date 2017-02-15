package org.usfirst.frc.team2643.robot;

import org.usfirst.frc.team2643.deprecated.VisionNarrowRatio;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class VisionNarrowHeights
{
	static NetworkTable table = VisionNarrowRatio.table;
	static double[] height = table.getNumberArray("Height", new double[0]); 
	static double[] temp = new double[2];
	public static double[] buidArray(String name)
	{
		double[] returnArr = new double[2];
		
		if(height == null || height.length == 0)
		{
			System.out.println("null, nothing in array");
			//returnArr = temp;
		}
		else if(height.length == 2)
		{
			System.out.println("Calculating");
			returnArr = table.getNumberArray(name, new double[0]);
			temp = returnArr;
		}
		else if(height.length == 1)
		{
			System.out.println("Too little...");
			//returnArr = temp;
		}	
		else
		{
			System.out.println("Too much noise!!! breaking");
			//returnArr = temp;
		}
		
		/*
		 	int[] elements = narrowHeight();
			double[] arrayTemp = table.getNumberArray(name, new double[0]);
			
			returnArr[0] = arrayTemp[elements[0]];
			returnArr[1] = arrayTemp[elements[1]];
		
			//System.out.println(arrayTemp[elements[0]] + "    " + arrayTemp[elements[1]]);
		
		 */
		
		return returnArr;
	}
	
	private static int[] narrowHeight()
	{
		int[] values = new int[2];
		int temp = largestValue(height);
		int temp2 = secondLargestValue(height, temp);
		values[0] = temp;
		values[1] = temp2;
		return values;
	}
	
	private static int largestValue(double[] table)
	{
		int largestVal = 0;

		for (int x = 0; x < table.length; x++)
		{
			if (table[largestVal] < table[x])
				largestVal = x;
		}
		
		//System.out.println(table[largestVal]);
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
		
		//System.out.println(table[secondLargestVal]);
		return secondLargestVal;
	}
}
