package org.usfirst.frc.team2643.robot;

public class VisionAutoMovement
{
	private static double[] values = new double[0];
			
	private static int moveLeft = 1;
	private static int moveRight = -1;
	
	public static void trackingRetro(double[] centerXVal, int compensation)
	{
		double averageX = ((centerXVal[0] + centerXVal[1]) / 2.0);
		
		//System.out.println("first centerX value is: " + cX[values[0]] + "    Second centerX value is: " + cX[values[1]] + 
			//	"    average is: " + averageX);

		if (averageX > 220 + compensation)
		{
			moveDirection(moveLeft, 0.2);
			System.out.println("MOVING LEFT");
		}
		else if (averageX < 220 + compensation)
		{
			moveDirection(moveRight, 0.2);
			System.out.println("MOVING RIGHT");
		}
		else
		{
			// compeleted and move forward
		}
	}
	
	public static void moveDirection(int direction, double speed)
	{
		Robot.lBack.set(direction * speed);
		Robot.lFront.set(direction * speed);
		Robot.rBack.set(direction * speed);
		Robot.rFront.set(direction * speed);
	}
}
