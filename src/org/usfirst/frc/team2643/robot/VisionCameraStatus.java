package org.usfirst.frc.team2643.robot;

import org.usfirst.frc.team2643.deprecated.VisionNarrowRatio;

public class VisionCameraStatus
{	
	public static void cameraStatus(int call)
	{
		VisionNarrowRatio.table.putNumber("Camera Call", call);
	}
	
	public static void autoModeStatus(int call)
	{
		VisionNarrowRatio.table.putNumber("Auto Mode", call);
	}
}
