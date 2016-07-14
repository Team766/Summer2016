package com.team766.lib.Scheduler;

public class MotorCommand extends Message{

	private double value;
	private Motor motor;
	
	public enum Motor{
		rightDrive,
		leftDrive
	}
	
	public MotorCommand(double in, Motor mot){
		messageType = Type.MOTOR_COMMAND;
		value = in;
		motor = mot;
	}
	
	public double getValue(){
		return value;
	}
	
	public Motor getMotor(){
		return motor;
	}
	
	public String toString() {
		return "Message:\tMotor Command";
	}

}
