package com.team766.robot.Actors.Drive;

import lib.Message;
import interfaces.SubActor;

import com.team766.lib.Messages.MotorCommand;

public class MotorSubCommand extends Drive implements SubActor{

	MotorCommand command;
	
	public MotorSubCommand(Message command){
		this.command = (MotorCommand)command;
	}
	
	@Override
	public void update() {
		switch(command.getMotor()){
		case leftDrive:
			leftMotor.set(command.getValue());
			break;
		case rightDrive:
			rightMotor.set(command.getValue());
			break;
		default:
			System.out.println("Motor not recognized!");
			break;
		}
	}
	
	@Override
	public void stop() {
	}

}