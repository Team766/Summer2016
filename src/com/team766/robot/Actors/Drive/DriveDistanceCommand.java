package com.team766.robot.Actors.Drive;

import lib.Message;

import com.team766.lib.CommandBase;
import com.team766.lib.Messages.DriveDistance;
import com.team766.robot.Constants;

public class DriveDistanceCommand extends CommandBase{

	DriveDistance command;
	
	private boolean doneTurning;
	private long startTime;
	
	private boolean done;
	
	public DriveDistanceCommand(Message m){
		command = (DriveDistance)m;
		doneTurning = false;
				
		Drive.switchAngleGains(true);
		Drive.anglePID.setSetpoint(Drive.getAngle() + command.getAngle());

		done = false;
		
		startTime = System.currentTimeMillis()/1000;
	}
	
	//Values: {avgLinearRate(), leftRate(), rightRate(), avgDist(), leftDist(), rightDist()}
	public void update() {
		if(!doneTurning){
			Drive.anglePID.calculate(Drive.getAngle(), true);
			
			if(Drive.anglePID.isDone()){
				System.out.println("Done Turning");
				doneTurning = true;
				
				Drive.setDrive(0.0);
				
				Drive.distancePID.setSetpoint(Drive.avgDist() + command.getDistance());
				Drive.switchAngleGains(false);
				startTime = System.currentTimeMillis() / 1000;
			}
			System.out.println(Drive.getAngle() + "\t" + Drive.anglePID.getCurrentError() + "\t" + Drive.anglePID.getSetpoint());
//			System.out.println(System.currentTimeMillis()/1000.0 - startTime + "\t" + anglePID.getError() + "\t" + gyro.getAngle() + "\t" + anglePID.getOutput());
					
			Drive.setLeft(Drive.anglePID.getOutput());
			Drive.setRight(-Drive.anglePID.getOutput());
		}else{
//			System.out.println("Left: " + leftDist() + "\tRight: " + rightDist() + "\tERR: " + distancePID.getCurrentError());
			
			Drive.distancePID.calculate(Drive.avgDist(), true);
			Drive.anglePID.calculate(Drive.getAngle(), true);
			
			if(command.getDistance() == 0 || (Drive.distancePID.isDone() && Math.abs(Drive.avgLinearRate()) < Constants.MAX_STOPPING_VEL)){
				System.out.println("Done Distance");
				Drive.setDrive(0.0);
				System.out.println(Drive.anglePID.getCurrentError());
				done = Drive.anglePID.isDone();
			}
			
//			System.out.println(System.currentTimeMillis()/1000.0 - startTime + "\t" + distancePID.getCurrentError() + "\t" + avgLinearRate());
			
			//Drive straight
			Drive.setLeft(Drive.distancePID.getOutput() + Drive.anglePID.getOutput());
			Drive.setRight(Drive.distancePID.getOutput() - Drive.anglePID.getOutput());
			
//			System.out.println(avgDist());
			
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
