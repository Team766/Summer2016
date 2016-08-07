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
		
		gyro.reset();
		
		anglePID.setSetpoint(getAngle() + command.getAngle());

		done = false;
	}
	
	@Override
	public void update() {
		if(!doneTurning){
			anglePID.calculate(getAngle(), true);
			
			if(anglePID.isDone()){
				System.out.println("Done Turning");
				doneTurning = true;
				
				distancePID.setSetpoint(avgDist() + command.getDistance());
				anglePID.reset();
			}

			leftMotor.set(anglePID.getOutput());
			rightMotor.set(-anglePID.getOutput());

		}else{
			System.out.println("Left: " + leftDist() + "\tRight: " + rightDist() + "\tERR: " + distancePID.getCurrentError());
			
			distancePID.calculate(avgDist(), true);
			anglePID.calculate(getAngle(), true);
			
			if(distancePID.isDone()){
				System.out.println("Done Driving :(");
				setDrive(0.0);
				done = anglePID.isDone();
			}

			//Drive straight
			leftMotor.set(distancePID.getOutput() + anglePID.getOutput());
			rightMotor.set(distancePID.getOutput() - anglePID.getOutput());
			
//			System.out.println(avgDist());
			
		}
	}

	@Override
	public void stop() {
		setDrive(0.0);
	}

}
