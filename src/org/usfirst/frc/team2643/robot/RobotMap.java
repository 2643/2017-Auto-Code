package org.usfirst.frc.team2643.robot;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap 
{
	static Talon lBack = new Talon(2);
	static Talon lFront = new Talon(3);
	static Spark rBack = new Spark(0);
	static Spark rFront = new Spark(1);
	
	
}
