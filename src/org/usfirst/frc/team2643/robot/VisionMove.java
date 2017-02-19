package org.usfirst.frc.team2643.robot;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class VisionMove
{
	private static int stateAuto = Robot.state;
	static NetworkTable table = VisionAuto.table;
	private static int center = 220;
	private static int compensation = 75;
	private static int left = 1;
	private static int right = -1;

	public static void movePos(int direction)
	{
		switch (stateAuto)
		{
			case 0:

				while (Robot.lEncoder.get() > -2300 || Robot.rEncoder.get() < 1850)
				{
					// System.out.println("RUNNING: case 0 LEFT ENCODER AT: " +
					// Robot.lEncoder.get()
					// + " RIGHT ENCODER AT: " + Robot.rEncoder.get());
					String val = Robot.arduino.readString();
					if (Robot.gyroToggle)
					{
						// System.out.println("once right?");
						Robot.tmp = 0;
						Robot.x = 0;
						Robot.gyroToggle = false;
					}

					GyroMaster.gyroMaster(val);
				}

				if (Robot.lEncoder.get() < -2300 || Robot.rEncoder.get() > 1850)
				{
					VisionAutoMovement.moveDirection(1, 0);
					System.out.println("RUNNING CASE 1");
					stateAuto = 1;
				}
				break;

			case 1:
				Robot.lEncoder.reset();
				Robot.rEncoder.reset();
				Robot.gyroToggle = true;
				boolean toggle = false;
				// turn robot for certain amount of encoder ticks or by vision
				int height = VisionCheckHeights.lengthOfArray("Height");
				int centerX = VisionCheckHeights.lengthOfArray("CenterX");

				while (height < 2)
				{
					VisionAutoMovement.moveDirection(direction, 0.25);
					height = VisionCheckHeights.lengthOfArray("Height");
					centerX = VisionCheckHeights.lengthOfArray("CenterX");
					//System.out.println(height + "    " + centerX);
					
					if(height >= 2 || centerX >= 2)
					{
						toggle = true;
						break;
					}
				}

				if (toggle)
				{
					VisionAutoMovement.moveDirection(direction, 0);
					stateAuto = 2;
				}
				break;
				
			case 2:
				double[] centerX2 = VisionCheckHeights.provideArray("CenterX");
				double average = (centerX2[0] + centerX2[1]) / 2.0;
				boolean toggle1 = false;
				while (average > center + compensation || average < center - compensation)
				{
					System.out.println(average);
					if (average > center + compensation)
					{
						VisionAutoMovement.moveDirection(left, 0.2);
					}
					else if (average < center - compensation)
					{
						VisionAutoMovement.moveDirection(right, 0.2);
					}
					else
					{
						toggle1 = true;
						break;
					}
					
					centerX2 = VisionCheckHeights.provideArray("CenterX");
					average = (centerX2[0] + centerX2[1]) / 2.0;
				}
				
				if(toggle1)
				{
					System.out.println("FINISHED COMP");
					VisionAutoMovement.moveDirection(direction, 0);
					stateAuto = 3;
				}

				break;
		}
	}
}
