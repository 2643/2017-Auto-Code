package org.usfirst.frc.team2643.robot;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 * Reintroduced code to eliminate not needed elements also introduces a
 * compensation function in order to check if there is enough
 * 
 * @author Adley
 *
 */
public class VisionNarrowDown
{
	static NetworkTable table = NetworkTable.getTable("Vision");
	static double[] centerXVal = new double[2];
	static double[] nArray = new double[2];

	private static double[] values = new double[0];
	private static double[] tableValue = new double[2];
	private static double[] returnTable = new double[2];

	private static int element1 = 0;
	private static int element2 = 0;

	private static String returnName = "CenterX";
	/*
	 * TODO: create private methods and add get methods in order to return
	 * values for vision assisted auto code create functions that narrow down
	 * the code DO NOT add functions that do the movement, create a class for
	 * that
	 */

	public static boolean narrowDownArray(String tableName, int compensation)
	{
		double[] a = table.getNumberArray(tableName, values);
		boolean allowMovement = false;

		if (a.length >= 2)
		{
			double temp = largestValue(a);
			double temp2 = secondLargestValue(a, temp);

			if (temp2 >= (temp - compensation) && temp2 <= (temp + compensation)
					&& (element1 < a.length || element2 < a.length))
			{

				/*
				 * centerXVal[0] = cX[element1]; centerXVal[1] = cX[element2];
				 * nArray[0] = temp; nArray[1] = temp2;
				 */

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

	public static double[] getArray()
	{
		return tableValue;
	}

	public static double[] centerXValues()
	{
		double[] narrowedTableValue = new double[2];

		return narrowedTableValue;
	}

	public static double[] getReturnArray()
	{
		return returnTable;
	}

	private static double largestValue(double[] table)
	{
		int largestVal = 0;

		for (int x = 0; x < table.length; x++)
		{
			if (table[largestVal] < table[x])
				largestVal = x;
		}

		element1 = largestVal;

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
		// System.out.println("element2: " + element2);
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
	}
}
