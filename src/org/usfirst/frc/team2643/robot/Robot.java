
package org.usfirst.frc.team2643.robot;


import java.text.NumberFormat;

import org.usfirst.frc.team2643.robot.subsystems.ExampleSubsystem;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot
{
	public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
	public static OI oi;
	
	Command autonomousCommand;
	
	// ** Arduino code
	final static int baut = 230400;
	static SerialPort arduino = new SerialPort(baut, SerialPort.Port.kUSB);

	Joystick stick = new Joystick(0);

	static Spark lBack = new Spark(2);
	static Spark lFront = new Spark(3);
	static Spark rBack = new Spark(0);
	static Spark rFront = new Spark(1);

	static Encoder lEncoder = new Encoder(1, 2);
	static Encoder rEncoder = new Encoder(3, 4);

	private static int vision = 0;
	private static int stream = 1;
	
	static boolean toggle = true;
	
	static int state = 0;
	
	static boolean isAuto = true;
	static Timer time = new Timer();
	
	static NumberFormat f = NumberFormat.getInstance();
	static double myNumber = 0;
	static double tmp = 0;

	static double gyroVal = 0;
	static boolean gyroToggle = true;

	static int x = 0;
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit()
	{
		oi = new OI();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit()
	{

	}

	@Override
	public void disabledPeriodic()
	{
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit()
	{
		if (autonomousCommand != null)
			autonomousCommand.start();
			lEncoder.reset();
			rEncoder.reset();
			state = 0;	
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic()
	{
		while(isAutonomous())
		{
			VisionAuto.positionForAuto("Right");
		}
	}

	@Override
	public void teleopInit()
	{
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		
		lEncoder.reset();
		rEncoder.reset();
		VisionCameraStatus.autoModeStatus(0);
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic()
	{
		while (isEnabled() && isOperatorControl())
		{
			// vision assisted auto
			isAuto = false;
			boolean cameraToggle = true;
			boolean streamToggle = true;
			
			if (stick.getRawButton(4))
			{
				//starts vision
				while(toggle)
				{
					if(stick.getRawButton(2))
						toggle = false;
					if(cameraToggle)
					{
						//System.out.println("toggling once");
						VisionCameraStatus.cameraStatus(1);
						cameraToggle = false;
						try
						{
							Thread.sleep(3000);
						}
						catch (InterruptedException e)
						{
							e.printStackTrace();
						}
					}
					//System.out.println("Vision");
					VisionAuto.startVision();
				}
			}
			else if(stick.getRawButton(6))
			{
				//just start the vision no scanner
				VisionCameraStatus.cameraStatus(1);
			}
			else if(stick.getRawButton(3))
			{
				//starts stream
				if(streamToggle)
				{
					VisionCameraStatus.cameraStatus(0);
					streamToggle = false;
				}	
			}
			else if(stick.getRawButton(1))
			{
				//reseters
				toggle = true;
				gyroToggle = true;
			}
			/*else if(stick.getRawButton(5))
			{
				//gyro drive
				while (stick.getRawButton(5))
				{
					
					String val = arduino.readString();
					if (gyroToggle)
					{
						//System.out.println("once right?");
						tmp = 0;
						x = 0;
						gyroToggle = false;
					}

					GyroMaster.gyroMaster(val);
				}
			}*/
			else
			{
				System.out.println(VisionCheckHeights.lengthOfArray("Height"));
				
				streamToggle = true;
				cameraToggle = true;
				
				lBack.set(-stick.getRawAxis(1));
				lFront.set(-stick.getRawAxis(1));
				rBack.set(stick.getRawAxis(3));
				rFront.set(stick.getRawAxis(3));
			}
		}
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic()
	{
		LiveWindow.run();
	}
}
