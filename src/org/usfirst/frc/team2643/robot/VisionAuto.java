package org.usfirst.frc.team2643.robot;

import org.usfirst.frc.team2643.deprecated.VisionNarrowRatio;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class VisionAuto
{	
	static NetworkTable table = VisionNarrowRatio.table;
	private static double[] returnArray = new double[2];
	
	public static void startVision()
	{
		VisionCheckHeights.checkHeight("CenterX");
	}
	
	public static void positionForAuto(String pos)
	{
		if (pos.equals("Center"))
		{
			// align and move forward
		}
		else if (pos.equals("Left"))
		{
			VisionMove.movePos(1);
		}
		else if (pos.equals("Right"))
		{
			VisionMove.movePos(-1);
		}
	}
}
