package org.usfirst.frc.team2643.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class VisionMove
{
	private static int state;
	static NetworkTable table = VisionAuto.table;
	private static int left = 1;
	private static int right = -1;
	private static boolean finishToggle = false;
	private static double average;
	private static Timer time = new Timer();

	// First Case
	private static int moveForwardRightEncoder = 2075; // ENCODER ticks
	private static int moveForwardLeftEncoder = -2075;

	private static double moveSpeed0 = 0.75; // <--for case 0

	// Second Case
	private static double moveSpeed1 = 0.35; // <--for case 1

	// Third Case
	private static double moveSpeed2L = 0.35;// <--for case 2
	private static double moveSpeed2R = 0.325;// <--for case 2

	// Forth Case
	private static double moveLeft1 = 0.365;
	private static double moveRight1 = 0.355;

	// Fifth Case
	private static double moveLeft0 = 0.565;
	private static double moveRight0 = 0.555;

	// CASE 2
	private static double highBound = 90.0;

	// CASE 4
	private static double highBound2 = 135.0;

	// CASE 6
	private static double moveLeft6 = 0.75;
	private static double moveRight6 = 0.74;
	private static double moveTime = 0.85;

	public static void resetState(){
		state = 0;
	}
	
	public static void movePos(int direction)
	{
		System.out.print("Direction: " + direction + "	" + state);
		switch (state)
		{
			/*
			 * MOVE FORWARD to @moveForwardLeftEncoder
			 * and @moveForwardRightEncoder
			 */
			case 0:
				state = VisionAutoMovement.autoForward(moveSpeed0, moveForwardLeftEncoder, moveForwardRightEncoder, state, 1);
				System.out.println("CASE 0");
				break;

			/*
			 * TURN @Direction at speed @moveSpeed1 until it sees 2 objects
			 */
			case 1:
				state = VisionAutoMovement.autoTurnDirection(1, moveSpeed1, moveSpeed1, direction, 2, 9);
				System.out.println("CASE 1");
				break;

			/*
			 * TURN @Direction at speed @moveSpeed2L and @moveSpeed2R
			 */
			case 2:
				state = VisionAutoMovement.autoTurnDirection(2, moveSpeed2L, moveSpeed2R, direction, 3, 9);
				System.out.println("CASE 2");
				break;

			case 3:
				state = VisionAutoMovement.autoCal(moveLeft1, moveRight1, state, 4, 9);
				System.out.println("Case 3" + "	" +  state);
				break;

			case 4:
				state = VisionAutoMovement.autoForward(moveLeft0, moveRight0, highBound, state, 5, 9);
				System.out.println("Case 4");
				break;

			case 5:
				state = VisionAutoMovement.autoCal(moveLeft1, moveRight1, state, 6, 9);
				System.out.println("Case 5");
				break;

			case 6:
				state = VisionAutoMovement.autoForward(moveLeft0, moveRight0, highBound2, state, 7, 9);
				System.out.println("Case 6");
				break;

			case 7:
				state = VisionAutoMovement.autoCal(moveLeft1, moveRight1, state, 8, 9);
				System.out.println("Case 7");
				break;

			case 8:
				state = VisionAutoMovement.autoForwardTimed(moveLeft6, moveRight6, moveTime, state, 9);
				System.out.println("Case 8");
				break;

			case 9:
				System.out.print("Case 9--");
				break;
		}
	}
}
