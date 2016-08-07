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
	public static final double k_angularP =	0.003;//1/maxAngularVelocity;
	public static final double k_angularI = 0.00000;//15
	public static final double k_angularD = 0.003979;
	public static final double k_angularThresh = 0.9;
	
	public static final double k_linearP = 0.6;//1/maxLinearVelocity;
	public static final double k_linearI = 0.002;
	public static final double k_linearD = 0.004;
	public static final double k_linearThresh = 0.01;
}
