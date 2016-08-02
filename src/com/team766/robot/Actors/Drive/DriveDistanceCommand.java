package com.team766.robot.Actors.Drive;

import interfaces.SubActor;
import lib.Message;

import com.team766.lib.Messages.DriveDistance;

public class DriveDistanceCommand extends Drive implements SubActor{

	DriveDistance command;
	
	private boolean doneTurning;
	
	public DriveDistanceCommand(Message m){
		command = (DriveDistance)m;
		doneTurning = false;
	}
	
	@Override
	public void update() {
		if(!doneTurning){
			anglePID.setSetpoint(command.getAngle());
			
			anglePID.calculate(gyro.getAngle(), true);
			System.out.println("GYRO\t" + gyro.getAngle());
			leftMotor.set(anglePID.getOutput());
			rightMotor.set(-anglePID.getOutput());
			
			if(anglePID.isDone()){
				doneTurning = true;
				anglePID.reset();
			}
		}else{
			anglePID.setSetpoint(0);
			distancePID.setSetpoint(command.getDistance());
			
			distancePID.calculate(avgDist(), true);
			anglePID.calculate(avgDist(), true);
			
			//Drive straight
			leftMotor.set(distancePID.getOutput() + anglePID.getOutput());
			rightMotor.set(distancePID.getOutput() - anglePID.getOutput());
			
			if(distancePID.isDone()){
				setDrive(0.0);
			}
		}
	}

	@Override
	public void stop() {
		setDrive(0.0);
	}

}
