package org.usfirst.frc.team2643.deprecated;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 * Deprecated
 * @author Adley
 *
 */

public class VisionNarrowRatio
{
	public static NetworkTable table = NetworkTable.getTable("Vision");
	
	static double[] height = table.getNumberArray("Height", new double[0]);
	static double[] width = table.getNumberArray("Width", new double[0]);
	private static int[] elements = new int[height.length];
	
	public static int[] narrowRatio(double compensationG, double compensationL)
	{	
		int pos = 0;
		
		for(int x = 0; x < height.length; x++)
		{
			double ratio = height[x]/width[x];
			
			if(ratio <= compensationG && ratio >= compensationL)
			{
				System.out.println(x);
				elements[pos] = x;
				pos++;
			}
		}
		
		return elements;
	}
}
