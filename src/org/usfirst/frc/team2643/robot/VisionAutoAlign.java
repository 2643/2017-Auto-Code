package org.usfirst.frc.team2643.robot;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 * DEPRECATED METHOD
 * @author Adley
 */

public class VisionAutoAlign
{
	static NetworkTable table = NetworkTable.getTable("Vision");

	private static double[] values = new double[0];
	private static double[] cX = table.getNumberArray("CenterX", values);
	static int[] importantValues = new int[2];
	private static int moveLeft = -1;
	private static int moveRight = 1;
	private static double speed = 0.2;
	static double difference = 0;

	public static void largestValues()
	{
		
	}

	public static int deleteSmallestValues(int compensation, int state, double[] h)
	{
		double tempHeight = 0;
		int tempPos = 0;

		for (int x = 0; x < h.length; x++)
		{
			if (tempHeight < h[x])
			{
				tempHeight = h[x];
				tempPos = x;
			}
		}

		importantValues[0] = tempPos;
		//System.out.println("First point is: " + h[tempPos]);

		int tempPos2 = 0;
		double tempHeight2 = 0;

		for (int x = 0; x < h.length; x++)
		{
			//System.out.println("Choice is : " + h[x]);
			if ((tempHeight2 < h[x] && x != tempPos)
					&& (tempHeight2 > (tempHeight - compensation) && tempHeight2 < (tempHeight + compensation)))
			{
				tempHeight2 = h[x];
				tempPos2 = x;
			}
			else if(x == h.length - 1)
			{
				state = 2;
				difference = 220.0 - tempPos;
			}
		}

		if (state != 2)
		{
			//System.out.println("Second point is: " + h[tempPos2]);
			importantValues[1] = tempPos2;
			return state;
		}
		return state;
	}

	public static void tracking(int compensation, int[] importantVal)
	{
		int[] trackingValues = importantVal;

		System.out.println(trackingValues[0] + " " + trackingValues[1]);
		double averageX = ((cX[trackingValues[0]] + cX[trackingValues[1]]) / 2);
		System.out.println(averageX);

		if (averageX > 220 + compensation)
		{
			moveDirection(moveLeft);
			System.out.println("MOVEING LEFT");
		}
		else if (averageX < 220 + compensation)
		{
			moveDirection(moveRight);
			System.out.println("MOVING RIGHT");
		}
		else
		{
			// compeleted and move forward
		}
	}

	/**
	 * Sets the direction it need to move
	 * 
	 * @param direction
	 */
	public static void moveDirection(int direction)
	{
		Robot.lBack.set(direction * speed);
		Robot.lFront.set(direction * speed);
		Robot.rBack.set(direction * speed);
		Robot.rFront.set(direction * speed);
		// System.out.println("Directions for left are: " + (direction * -speed)
		// + " Direction for right are: " + (direction * speed));
	}
}
