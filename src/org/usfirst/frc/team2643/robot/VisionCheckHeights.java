package org.usfirst.frc.team2643.robot;

public class VisionCheckHeights
{

	static double[] height = VisionAuto.table.getNumberArray("Height", new double[0]);
	static double[] temp = new double[2];
	private static int moveLeft = 1;
	private static int moveRight = -1;

	public static void checkHeight(String name)
	{
		System.out.println(height.length);
		
		if (height.length == 2)
		{
			System.out.println("Calculating");
			VisionAutoMovement.trackingRetro(VisionAuto.table.getNumberArray("CenterX", new double[0]), 75, true);
		}
		else if (height.length == 1)
		{
			System.out.println("Too little... calculating turn");
			
			if(moveToSide())
			{
				//move left
				VisionAutoMovement.moveDirection(moveLeft, 0.2);
			}
			else
			{
				//move right
				VisionAutoMovement.moveDirection(moveRight, 0.2);
			}
		}
		else if(height.length > 2)
		{
			System.out.println("Too much noise!!!");
		}
		else if (height == null || height.length == 0)
		{
			System.out.println("null, nothing in array");
			Robot.toggle = false;
		}
	}
	
	public static int lengthOfArray(String name)
	{
		return VisionAuto.table.getNumberArray(name, new double[0]).length;
	}
	
	public static double[] provideArray(String name)
	{
		return VisionAuto.table.getNumberArray(name, new double[0]);
	}

	private static boolean moveToSide()
	{
		double[] tmpArr = VisionAuto.table.getNumberArray("CenterX", new double[0]);
		if(tmpArr[0] > 320)
			return true;
		return false;
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
