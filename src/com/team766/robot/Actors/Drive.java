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
		acceptableMessages = new Message.Type[]{Message.Type.MOTOR_COMMAND};
	}
	
	public void run() {
		Message mess = readMessage();
		
		switch(mess.getType()){
		case MOTOR_COMMAND:
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
			break;
		case JOYSTICK:
			break;
		case MECH_STATE:
			break;
		case SENSOR_VALUE:
			break;
		case SETPOINTS:
			break;
		case WORLD_STATE:
			break;
		default:
			break;
		}
	}

	

	public Message[] postMessage() {
		return null;
	}
	
	public String toString(){
		return "Actor:\tDrive";
	}

}
