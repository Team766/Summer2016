package com.team766.robot.Actors.Drive;

import interfaces.SubActor;
import lib.Message;

import com.team766.lib.Messages.DriveTo;

public class DriveToCommand extends Drive implements SubActor{

	DriveTo command;
	
	public DriveToCommand(Message m){
		command = (DriveTo)m;
	}
	
	//Values: {avgLinearRate(), leftRate(), rightRate(), avgDist(), leftDist(), rightDist()}
	//			0				1			2				3			4			5
	@Override
	public void update(double[] values) {
		if(values[3] < command.getXDist() || values[3] < command.getYDist()){
			System.out.println("Driving values!");
			if(command.getHeading() > 0){
				setLeft(0.5);
				setRight(-0.5);
			}else if(command.getHeading() < 0){
				setLeft(-0.5);
				setRight(0.5);
			}
			else{
				setLeft(0.5);
				setRight(0.5);
			}
		}else{
			setDrive(0.0);
			done = true;
		}
	}

	@Override
	public void stop() {
		setDrive(0.0);
	}

}
