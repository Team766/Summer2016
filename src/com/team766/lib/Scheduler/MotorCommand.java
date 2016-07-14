package com.team766.lib.Scheduler;

public class MotorCommand extends Message{

	private double value;
	private String motor;
	
	public MotorCommand(double in, String mot){
		messageType = Type.MOTOR_COMMAND;
		value = in;
		motor = mot;
	}
	
	public double getValue(){
		return value;
	}
	
	public String getMotor(){
		return motor;
	}
	
	public String toString() {
		return "Message:\tMotor Command";
	}

}
