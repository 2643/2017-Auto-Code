package org.usfirst.frc.team2643.robot;

public class VisionMove
{
	private static int stateAuto = Robot.state;
	
	public static void movePos(int Direction)
	{
		switch (stateAuto)
		{
			case 0:
				// move with gyro at a certain point
				if (Robot.lEncoder.get() > 100 || Robot.rEncoder.get() > 100)
				{
					stateAuto = 1;
				}
				break;
				
			case 1:
				Robot.lEncoder.reset();
				Robot.rEncoder.reset();
				
				//turn robot right for certain amount of encoder ticks
				
				if(Robot.lEncoder.get() > 100 || Robot.rEncoder.get() < 100)
				{
					stateAuto = 2;
				}
				break;
			
			case 2:
				/*
				boolean comp = VisionNarrowDown.narrowDownArray("Height", 50);
				if (comp)
				{
					double[] tmp = VisionNarrowDown.getArray();
					double[] tmp2 = VisionNarrowDown.getReturnArray();
					System.out.println(tmp2[0] + "   " + tmp2[1]);
					VisionAutoMovement.trackingRetro(tmp2, 70, true);
				}
				break;
				
			case 3:
				double[] a = VisionNarrowDown.getArray("Height", 70);
				if(a[0] > 300 || a[1] > 300)
				{
					//stop
				}
				break;*/
		}
	}
}
