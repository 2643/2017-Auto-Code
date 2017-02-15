package org.usfirst.frc.team2643.robot;

import org.usfirst.frc.team2643.deprecated.VisionNarrowRatio;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class VisionCenterX
{
	static NetworkTable table = VisionNarrowRatio.table;
	static double[] centerX = table.getNumberArray("CenterX", new double[0]);
	
	public static double[] returnCenterX(int[] elements)
	{
		double[] returnX = new double[2];
		returnX[0] = centerX[elements[0]];
		returnX[0] = centerX[elements[0]];
		
		return returnX;
	}
}