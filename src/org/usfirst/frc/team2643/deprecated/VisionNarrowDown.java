package org.usfirst.frc.team2643.deprecated;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
/**
 * Reintroduced code to eliminate not needed elements
 * also introduces a compensation function in order to check if there is enough 
 * @author Adley
 *
 */
public class VisionNarrowDown
{
	static NetworkTable table = NetworkTable.getTable("Vision");
	private static double[] values = new double[0];
	private static double[] tableValue = new double[2];
	
	/*
	 * TODO:
	 *	create private methods and add get methods in order to return values for vision assisted auto code
	 *	create functions that narrow down the code
	 *	DO NOT add functions that do the movement, create a class for that
	 */
	
	public static boolean narrowDownArray(String tableName, int compensation)
	{
		double[] a = table.getNumberArray(tableName, values);
		double temp = largestValue(a);
		double temp2 = secondLargestValue(a, temp);
		System.out.println(temp2);
		if(temp2 > (temp - compensation) && temp2 < (temp + compensation))
		{
			narrowArray(temp, temp2);
			System.out.println("Found values");
			return true;
		}
		return false;	
	}
	
	public static double[] getArray()
	{
		return tableValue;
	}
	
	private static double largestValue(double[] table)
	{
		int largestVal = 0;
		
		for(int x = 0; x < table.length; x++)
		{
			if(table[largestVal] < table[x])
				largestVal = x;
		}
		
		return table[largestVal];
	}
	
	private static double secondLargestValue(double[] table, double largestVal)
	{
		int secondLargestVal = 0;
		
		for(int x = 0; x < table.length; x++)
		{
			if((table[secondLargestVal] < table[x] && table[x] != largestVal))
			{
				secondLargestVal = x;	
			}	
		}
		
		return table[secondLargestVal];
	}
	
	private static void narrowArray(double firstNum, double secondNum)
	{
		tableValue[0] = firstNum;
		tableValue[1] = secondNum;
	}
}
