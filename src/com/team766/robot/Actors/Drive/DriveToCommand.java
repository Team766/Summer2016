package com.team766.robot.Actors.Drive;

import interfaces.SubActor;
import lib.Message;

import com.team766.lib.CommandBase;
import com.team766.lib.Messages.DriveTo;

public class DriveToCommand extends CommandBase{

	DriveTo command;
	private boolean done;
	
	public DriveToCommand(Message m){
		command = (DriveTo)m;
		done = false;
	}
	
	@Override
	public void update() {
		if(Drive.avgDist() < command.getXDist() || Drive.avgDist() < command.getYDist()){
			System.out.println("Driving values!");
			if(command.getHeading() > 0){
				Drive.setLeft(0.5);
				Drive.setRight(-0.5);
			}else if(command.getHeading() < 0){
				Drive.setLeft(-0.5);
				Drive.setRight(0.5);
			}
			else{
				Drive.setLeft(0.5);
				Drive.setRight(0.5);
			}
		}else{
			Drive.setDrive(0.0);
			done = true;
		}
	}

	@Override
	public void stop() {
		Drive.setDrive(0.0);
	}

	@Override
	public boolean isDone() {
		return done;
	}

}
