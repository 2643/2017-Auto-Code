package org.usfirst.frc.team2643.deprecated;

/**
 * DEPREVATED
 * 
 * @author Adley
 *
 */
public class VisionNarrowDown
{
	/*public 
	static double[] centerXVal = new double[2];
	static double[] nArray = new double[2];
	static double[] values = new double[0];
	
	private static double[] tableValue = new double[2];
	private static double[] returnTable = new double[2];

	private static int element1 = 0;
	private static int element2 = 0;
	
	private static double ratioSize = 0;

	private static String returnName = "CenterX";
	/*
	 * TODO: create private methods and add get methods in order to return
	 * values for vision assisted auto code create functions that narrow down
	 * the code DO NOT add functions that do the movement, create a class for
	 * that
	 */

	/*public static boolean narrowDownArray(String tableName, int compensation//, String returnTable//
			)
	{
		//double[] a = table.getNumberArray(tableName, values);
		boolean allowMovement = false;

		if (a.length >= 2)
		{
			double temp = largestValue(a);
			double temp2 = secondLargestValue(a, temp);

			if (temp2 >= (temp - compensation) && temp2 <= (temp + compensation)
					&& (element1 < a.length || element2 < a.length))
			{
				narrowArray(temp, temp2);
				narrowElementsOfArray(returnName, element1, element2);
				System.out.println("**Found values");
				allowMovement = true;
			}
			else
				allowMovement = false;
		}
		return allowMovement;
	}
	
	/*public static double[] getArray(String tableName, int compensation)
	{
		double[] a = table.getNumberArray
		
		return ;
	}*/
	/*
	 * May not be used...
	 */
	/*private static double largestValue(double[] table)
	{
		int largestVal = 0;

		for (int x = 0; x < table.length; x++)
		{
			if (table[largestVal] < table[x])
				largestVal = x;
		}

		element1 = largestVal;
		System.out.println(table[largestVal]);
		return table[largestVal];
	}

	private static double secondLargestValue(double[] table, double largestVal)
	{
		int secondLargestVal = 0;

		for (int x = 0; x < table.length; x++)
		{
			if ((table[secondLargestVal] < table[x] && table[x] != largestVal))
			{
				secondLargestVal = x;
			}
		}

		element2 = secondLargestVal;

		System.out.println(table[secondLargestVal]);
		
		return table[secondLargestVal];
	}

	private static void narrowArray(double firstNum, double secondNum)
	{
		tableValue[0] = firstNum;
		tableValue[1] = secondNum;
	}

	private static void narrowElementsOfArray(String tableName, int firstEl, int secondEl)
	{
		double[] a = table.getNumberArray(tableName, values);
		returnTable[0] = a[firstEl];
		returnTable[1] = a[secondEl];
	}*/
}
