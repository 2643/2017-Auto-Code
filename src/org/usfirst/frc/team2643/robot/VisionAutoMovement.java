package org.usfirst.frc.team2643.robot;

public class VisionAutoMovement
{
	// private static double[] values = new double[0];
	private static int moveLeft = 1;
	private static int moveRight = -1;
	// private static int stop = 0;
	private static double speed = 0.2;

	public static void trackingRetro(double[] centerXVal, int compensation, boolean period)
	{
		double averageX = ((centerXVal[0] + centerXVal[1]) / 2.0);

		System.out.println("first centerX value is: " + centerXVal[0] + "    Second centerX value is: " + centerXVal[1]
				+ "    average is: " + averageX);
		masterMove(averageX, compensation, period);
	}

	private static void masterMove(double averageX, int compensation, boolean period)
	{
		if (averageX < 220 + compensation && averageX > 220 - compensation)
		{
			if (period)
			{
				// Robot.toggle = false;
				// if(VisionNarrowHeights.height[0] > )
				moveForward(speed + 0.3);
				System.out.println("MOVING FORWARD*");
				// System.out.println("AUTO - MOVING FORWARD");
			}
			else
			{
				Robot.state++;
			}
		}
		else if (averageX > 220 + compensation)
		{
			moveDirection(moveLeft, speed);
			System.out.println("MOVING LEFT*");
		}
		else if (averageX < 220 - compensation)
		{
			moveDirection(moveRight, speed);
			System.out.println("MOVING RIGHT*");
		}
	}

	public static void moveDirection(int direction, double speed)
	{
		Robot.lBack.set(direction * speed);
		Robot.lFront.set(direction * speed);
		Robot.rBack.set(direction * speed);
		Robot.rFront.set(direction * speed);
	}

	public static void moveForward(double speed)
	{
		Robot.lBack.set(speed);
		Robot.lFront.set(speed);
		Robot.rBack.set(-speed);
		Robot.rFront.set(-speed);
	}
}
