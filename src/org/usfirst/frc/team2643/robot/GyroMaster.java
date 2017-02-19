package org.usfirst.frc.team2643.robot;

import java.text.ParseException;

public class GyroMaster
{
	public static void moveForward(double speed)
	{
		Robot.lFront.set(speed);
		Robot.rFront.set(-speed);
		Robot.lBack.set(speed);
		Robot.rBack.set(-speed);
	}
	
	public static void levelDrive(double speed, double leftSpeed, double rightSpeed)
	{
		Robot.lFront.set(leftSpeed);
		Robot.rFront.set(-rightSpeed);
		Robot.lBack.set(leftSpeed);
		Robot.rBack.set(-rightSpeed);
	}
	
	public static void gyroCompensation(double gyroVal, double highBound, double lowBound)
	{
		Robot.tmp = Robot.tmp + gyroVal;
		
		if (Robot.tmp > highBound)
		{
			System.out.println("MOVING RIGHT or LEFT?");
			levelDrive(0.5, 0.65, 0.10);
		}
		else if (Robot.tmp < lowBound)
		{
			System.out.println("MOVING LEFT or RIGHT?");
			levelDrive(0.5, -0.2, 0.2);
		}
	}
	
	public static void gyroMaster(String val)
	{
		// System.out.println(val);
		if (val.equals("") || val.equals(" ") || val.substring(0, 1).equals("z") || val.equals("rz") || val.equals("c"))
		{
			moveForward(0.5);
		}
		else
		{
			try
			{
				if (Robot.x == 0)
				{
					Robot.x++;
				}
				else
				{
					Robot.myNumber = Robot.f.parse(val).doubleValue();
					System.out.println(Robot.myNumber);
					gyroCompensation(Robot.myNumber, 0, 0);
				}
			}
			catch (ParseException e)
			{
				e.printStackTrace();
			}
		}
	}
	
}
