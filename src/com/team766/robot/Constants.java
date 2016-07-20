package com.team766.robot;

public class Constants {
	public static final int ACTOR_COUNT = 5;
	
	public static final double wheel_circumference = 0.2032 * Math.PI;
	public static final double counts_per_rev = 250;
	
	public static final double maxAngularVelocity = 10;
	public static final double maxLinearVelocity = 10;
	
	//Buttons
	public static final int driverQuickTurn = 1;
	
	//Axis
	public static final int steerAxis = 1;
	public static final int accelAxis = 0;
	
	//Drive PID
	public static final double k_angularP =	1/maxAngularVelocity;
	public static final double k_angularI = 0;
	public static final double k_angularD = 0;
	public static final double k_angularThresh = 1;
	
	public static final double k_linearP = 1/maxLinearVelocity;
	public static final double k_linearI = 0;
	public static final double k_linearD = 0;
	public static final double k_linearThresh = 1;
}
