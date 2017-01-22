package com.team766.robot.Actors.Drive;

import lib.Message;
import interfaces.SubActor;

import com.team766.lib.CommandBase;
import com.team766.lib.Messages.MotorCommand;

public class MotorSubCommand extends CommandBase{

	MotorCommand command;
	private boolean done;
		
	public MotorSubCommand(Message command){
		this.command = (MotorCommand)command;
		done = false;
	}
	
	//Values: {avgLinearRate(), leftRate(), rightRate(), avgDist(), leftDist(), rightDist()}
	@Override
	public void update() {
		switch(command.getMotor()){
			case leftDrive:
				Drive.setLeft(command.getValue());
				break;
			case rightDrive:
				Drive.setRight(command.getValue());
				break;
			default:
				System.out.println("Motor not recognized!");
				break;
		}
		
		//Only need to run once to set the motors
		done = true;
	}
	
	@Override
	public void stop() {
	}

	@Override
	public boolean isDone() {
		return done;
	}

}
