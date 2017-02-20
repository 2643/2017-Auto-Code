package org.usfirst.frc.team2643.robot;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class VisionMove
{
	private static int stateAuto = Robot.state;
	static NetworkTable table = VisionAuto.table;
	private static int center = 150;
	private static int compensation = 25;
	private static int left = 1;
	private static int right = -1;
	private static double highBound = 115;
	private static boolean finishToggle = false;
	private static double average;

	private static double[] centerX;
	// TODO: add in Encoder check

	public static void movePos(int direction)
	{
		switch (stateAuto)
		{
			case 0:
				System.out.println("CASE 0");
				while (Robot.lEncoder.get() > -2250 || Robot.rEncoder.get() < 1900)
				{
					String val = Robot.arduino.readString();
					if (Robot.gyroToggle)
					{
						Robot.tmp = 0;
						Robot.x = 0;
						Robot.gyroToggle = false;
					}

					GyroMaster.gyroMaster(val, 0.85);
				}

				if (Robot.lEncoder.get() < -2250 || Robot.rEncoder.get() > 1900)
				{
					VisionAutoMovement.moveDirection(1, 0);
					VisionCameraStatus.autoModeStatus(1);
					stateAuto = 1;
				}
				break;

			case 1:
				Robot.lEncoder.reset();
				Robot.rEncoder.reset();
				Robot.gyroToggle = true;
				boolean toggle = false;
				
				int heightL = VisionCheckHeights.lengthOfArray("Height");
				int centerXL = VisionCheckHeights.lengthOfArray("CenterX");

				System.out.println("CASE 1");
				while (heightL < 2)
				{
					VisionAutoMovement.moveDirection(direction, 0.3);
					heightL = VisionCheckHeights.lengthOfArray("Height");
					centerXL = VisionCheckHeights.lengthOfArray("CenterX");

					if (heightL >= 2 || centerXL >= 2)
					{
						toggle = true;
						break;
					}
				}

				if (toggle)
				{
					try
					{
						Thread.sleep(300);
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
					
					VisionAutoMovement.moveDirection(direction, 0);
					stateAuto = 2;
					centerX = VisionAuto.table.getNumberArray("CenterX", new double[0]);
				}
				break;

			case 2:
				boolean toggle1 = false;
				average = ((centerX[0] + centerX[1]) / 2.0);
				System.out.println("CASE 2");
								
				while (!toggle1)
				{
					System.out.println(average);
					if (average < center + compensation && average > center - compensation && centerX.length >= 2)
					{
						System.out.println("TOGGLE OFF");
						VisionAutoMovement.moveDirection(direction, 0);
						toggle1 = true;
						break;
					}
					else if (average > center + compensation && centerX.length >= 2)
					{
						System.out.println("LEFT");
						if(centerX.length < 2)
						{
							System.out.println("NOT TWO!");
						}
						average = ((centerX[0] + centerX[1]) / 2.0);
						centerX = VisionAuto.table.getNumberArray("CenterX", new double[0]);
						VisionAutoMovement.moveDirection(left, 0.225, 0.2);// changed
					}
					else if (average < center - compensation && centerX.length >= 2)
					{
						// System.out.println("RIGHT");
						if(centerX.length < 2)
						{
							System.out.println("NOT TWO");
						}
						average = ((centerX[0] + centerX[1]) / 2.0);
						centerX = VisionAuto.table.getNumberArray("CenterX", new double[0]);
						VisionAutoMovement.moveDirection(right, 0.235, 0.265);// changed
					}
					else
					{
						VisionAutoMovement.moveDirection(direction, 0);
						break;
					}
				}

				if (toggle1)
				{
					stateAuto = 3;
					break;
				}

				break;

			case 3:
				double[] height = VisionCheckHeights.provideArray("Height");
				boolean toggle3 = false;
				while (height[0] < highBound)
				{
					if (height[0] > highBound)
					{
						toggle3 = true;
						break;
					}
					else
					{
						VisionAutoMovement.moveForward(0.27, 0.24);
					}
					height = VisionCheckHeights.provideArray("Height");
				}

				if (toggle3)
				{
					System.out.println("MOVED THE CERTAIN DISTANCE!");
					VisionAutoMovement.moveForward(0);
					finishToggle = true;
					toggle1 = false;
					stateAuto = 4;
					Robot.lEncoder.reset();
					Robot.rEncoder.reset();
				}
				break;

			case 4:
				System.out.println("CASE 4");

				while (Robot.lEncoder.get() < -100 || Robot.rEncoder.get() < 80)
				{
					VisionAutoMovement.moveForward(0.23);
				}

				if (Robot.lEncoder.get() > -100 || Robot.rEncoder.get() > 80)
				{
					VisionAutoMovement.moveDirection(1, 0);
					System.out.println("END");
				}
				break;
		}
	}
}
