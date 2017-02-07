package org.usfirst.frc.team2643.robot;

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
	static double[] centerXVal = new double[2];
	static double[] nArray = new double[2];
	
	private static double[] values = new double[0];
	private static double[] tableValue = new double[2];
	private static int[] tableElements = new int[2];
	
	private static int element1 = 0;
	private static int element2 = 0;
	
	static double[] cX = table.getNumberArray("CenterX", values);
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
		System.out.println("largest Value:" + temp);
		System.out.println("second largest value: " + temp2);
		if(temp2 > (temp - compensation) && temp2 < (temp + compensation) && (element1 < a.length || element2 < a.length))
		{
			centerXVal[0] = cX[element1];
			centerXVal[1] = cX[element2];
			nArray[0] = temp;
			nArray[1] = temp2;
			//narrowArray(temp, temp2);
			//narrowElementsOfArray(element1, element2);
			System.out.println("Found values");
			return true;
		}
		return false;	
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
	
	public static int[] getElement()
	{
		return tableElements;
	}
	
	private static double largestValue(double[] table)
	{
		int largestVal = 0;
		
		for(int x = 0; x < table.length; x++)
		{
			if(table[largestVal] < table[x])
				largestVal = x;
		}
		
		element1 = largestVal;
		//System.out.println("element2: " + element1);
		
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
		
		element2 = secondLargestVal;
		//System.out.println("element2: " + element2);
		return table[secondLargestVal];
	}
	
	private static void narrowArray(double firstNum, double secondNum)
	{
		tableValue[0] = firstNum;
		tableValue[1] = secondNum;
	}
	
	private static void narrowElementsOfArray(int firstEl, int secondEl)
	{
		tableElements[0] = firstEl;
		tableElements[1] = secondEl;
	}
}
