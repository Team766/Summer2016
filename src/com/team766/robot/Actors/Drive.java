package com.team766.robot.Actors;

import interfaces.RobotProvider;
import interfaces.SpeedController;

import com.team766.lib.Scheduler.Actor;
import com.team766.lib.Scheduler.Message;
import com.team766.lib.Scheduler.MotorCommand;

public class Drive extends Actor{

	SpeedController leftMotor = RobotProvider.instance.getLeftDrive();
	SpeedController rightMotor = RobotProvider.instance.getRightDrive();
	
	MotorCommand[] motors;
	
	public void init() {
		acceptableMessages = new Message.Type[]{Message.Type.MOTOR_COMMAND};
	}
	
	public void run() {
	}


	public void readMessage(Message[] m) {
		m = relevantMessage(m);
	
		for(Message mess : m){
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
	
	}

	public Message[] postMessage() {
		return null;
	}
	
	public String toString(){
		return "Actor:\tDrive";
	}

}
