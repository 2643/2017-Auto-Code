package org.usfirst.frc.team2643.robot;

public class VisionMoveCenter
{	
	private static int state = Robot.state;

	// CASE 0
	private static double moveLeft0 = 0.38;
	private static double moveRight0 = 0.38;
	private static double highBound = 60.0;

	// CASE 1
	private static double moveLeft1 = 0.235;
	private static double moveRight1 = 0.235;
	private static double val = 0.0;

	// CASE 2
	private static double highBound2 = 80.0;

	// CASE 4
	private static double highBound3 = 110.0;

	// CASE 6
	private static double moveLeft6 = 0.75;
	private static double moveRight6 = 0.73;
	private static double moveTime = 0.85;

	// CASE VALUES FROM RASPPI
	private static double[] cenX = VisionProvideData.provideArray("CenterX");
	private static double[] ra = VisionProvideData.provideArray("Ratio");
	private static double[] height = VisionProvideData.provideArray("Height");
	private static double avg = VisionProvideData.provideNum("Average");

	public static void resetState(){
		state = 0;
	}
	
	public static void moveToCenter()
	{
		switch (state)
		{
			case 0:
				state = VisionAutoMovement.autoForward(moveLeft0, moveRight0, highBound, state, 1, 7);
				System.out.println("Case 1");
				break;
			case 1:
				state = VisionAutoMovement.autoCal(moveLeft1, moveRight1, state, 2, 7);
				System.out.println("Case 2");
				break;

			case 2:
				state = VisionAutoMovement.autoForward(moveLeft0, moveRight0, highBound2, state, 3, 7);
				System.out.println("Case 3");
				break;

			case 3:
				state = VisionAutoMovement.autoCal(moveLeft1, moveRight1, state, 4, 7);
				System.out.println("Case 4");
				break;

			case 4:
				state = VisionAutoMovement.autoForward(moveLeft0, moveRight0, highBound3, state, 5, 7);
				System.out.println("Case 5");
				break;

			case 5:
				state = VisionAutoMovement.autoCal(moveLeft1, moveRight1, state, 6, 7);
				System.out.println("Case 6");
				break;

			case 6:
				state = VisionAutoMovement.autoForwardTimed(moveLeft6, moveRight6, moveTime, state, 7);
				break;
				
			case 7:
				break;
		}
	}
}
