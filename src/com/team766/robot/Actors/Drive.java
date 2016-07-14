package com.team766.robot.Actors;

import interfaces.RobotProvider;
import interfaces.SpeedController;

import com.team766.lib.Scheduler.Actor;
import com.team766.lib.Scheduler.Message;
import com.team766.lib.Scheduler.MotorCommand;

public class Drive extends Actor{

	SpeedController leftMotor = RobotProvider.instance.getLeftDrive();
	SpeedController rightMotor = RobotProvider.instance.getRightDrive();
	
	public void init() {
	}
	
	public void run() {
	}


	public void readMessage(Message m) {
		if(!relevantMessage(m))
			return;
		
		MotorCommand motor = (MotorCommand)m;
		switch(motor.getMotor()){
			case leftDrive:
				leftMotor.set(motor.getValue());
				break;
			case rightDrive:
				rightMotor.set(motor.getValue());
				break;
			default:
				System.out.println("Motor not recognized!");
				break;
			
		}
	
	}

	public Message postMessage() {
		return null;
	}
	
	public String toString(){
		return "Actor:\tDrive";
	}

}
