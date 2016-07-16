package com.team766.robot.Actors;

import interfaces.SpeedController;

import com.team766.lib.Scheduler.Actor;
import com.team766.lib.Scheduler.Message;
import com.team766.lib.Scheduler.MotorCommand;
import com.team766.robot.HardwareProvider;

public class Drive extends Actor{

	SpeedController leftMotor = HardwareProvider.getInstance().getLeftDrive();
	SpeedController rightMotor = HardwareProvider.getInstance().getRightDrive();
	
	MotorCommand[] motors;
	
	public void init() {
		acceptableMessages = new Class[]{MotorCommand.class};
	}
	
	public void run() {
		while(enabled){
			Message mess = readMessage();
			
			if(mess instanceof MotorCommand){
				MotorCommand motor = (MotorCommand)mess;
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
		}
	}

	

	public Message[] postMessage() {
		return null;
	}
	
	public String toString(){
		return "Actor:\tDrive";
	}

}
