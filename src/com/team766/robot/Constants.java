package com.team766.robot;

public class Constants {
	public static final int ACTOR_COUNT = 5;
	
	public static final double wheel_circumference = 0.2032 * Math.PI;
	public static final double counts_per_rev = 360;
	
	public static final double maxAngularVelocity = 50;
	public static final double maxLinearVelocity = 10;
	
	//Buttons
	public static final int driverQuickTurn = 1;
	
	//Axis
	public static final int steerAxis = 1;
	public static final int accelAxis = 0;
	
	//Drive PID
	public static final double MAX_STOPPING_VEL = 0.1;
	
	public static final double k_angularP =	0.04;//1/maxAngularVelocity;	//.012
	public static final double k_angularI = 0.0000;
	public static final double k_angularD = 0.3;
	public static final double k_angularThresh = 0.9;
	
	public static final double k_linearP = 1.0;//1/maxLinearVelocity;
	public static final double k_linearI = 0.00;
	public static final double k_linearD = 0.00;
	public static final double k_linearThresh = 0.01;

	public static final double STARTING_HEADING = -180;
}
