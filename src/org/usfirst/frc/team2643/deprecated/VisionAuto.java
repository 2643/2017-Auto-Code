package org.usfirst.frc.team2643.deprecated;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 * DEPRECATED METHOD
 * @author Adley
 */

public class VisionAuto 
{
	static NetworkTable table = NetworkTable.getTable("Vision");
	private static int[] value;
	private static double[] values = new double[0];
	private static Timer time = new Timer();
	private static int setTime = 10;
	private static double[] h = table.getNumberArray("Height", values);
	
	public static void moveFunction(int state)
	{
		switch(state)
		{
			case 0: //start case
				//System.out.println("Running first state!");
				state = VisionAutoAlign.deleteSmallestValues(50, 1, h);
				value = VisionAutoAlign.importantValues;
				System.out.println(state);
				break;
			
			case 1: //move to align correctly
				VisionAutoAlign.tracking(25, value);
				break;
				
			case 2: //if there aren't enough contours, move a certain direction
				time.start();
				System.out.println(time.get());
				VisionAutoAlign.moveDirection(moveWhichDirection());
				if(time.get() > setTime)
				{
					time.stop();
					time.reset();
					/*
					Robot.lBack.set(0);
					Robot.lFront.set(0);
					Robot.rBack.set(0);
					Robot.rFront.set(0);*/
					state = 0;
				}
				break;
				
		}	
	}
	
	private static int moveWhichDirection()
	{
		System.out.println(VisionAutoAlign.difference);
		if(VisionAutoAlign.difference < 0)
			return -1;
		return 1;
	}
}
