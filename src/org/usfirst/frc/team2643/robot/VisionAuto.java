package org.usfirst.frc.team2643.robot;

public class VisionAuto
{	
	public static void positionForAuto(String pos)
	{
		if (pos.equals("Center"))
		{
			// align and move forward
		}
		else if (pos.equals("Left"))
		{
			VisionMove.movePos(-1);
		}
		else if (pos.equals("Right"))
		{
			VisionMove.movePos(1);
		}
	}
}
