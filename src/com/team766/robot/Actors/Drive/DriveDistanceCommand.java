package com.team766.robot.Actors.Drive;

import interfaces.SubActor;
import lib.Message;

import com.team766.lib.Messages.DriveDistance;
import com.team766.robot.Constants;

public class DriveDistanceCommand extends Drive implements SubActor{

	DriveDistance command;
	
	private boolean doneTurning;
	private long startTime;
	
	public DriveDistanceCommand(Message m){
		command = (DriveDistance)m;
		doneTurning = false;
				
		switchAngleGains(true);
		anglePID.setSetpoint(gyro.getAngle() + command.getAngle());

		done = false;
		
		startTime = System.currentTimeMillis()/1000;
	}
	
	//Values: {avgLinearRate(), leftRate(), rightRate(), avgDist(), leftDist(), rightDist()}
	@Override
	public void update(double[] values) {
		if(!doneTurning){
			anglePID.calculate(gyro.getAngle(), true);
			
			if(anglePID.isDone()){
				System.out.println("Done Turning");
				doneTurning = true;
				
				setDrive(0.0);
				
				distancePID.setSetpoint(values[3] + command.getDistance());
				switchAngleGains(false);
				startTime = System.currentTimeMillis() / 1000;
			}
			System.out.println(gyro.getAngle() + "\t" + anglePID.getCurrentError() + "\t" + anglePID.getSetpoint());
//			System.out.println(System.currentTimeMillis()/1000.0 - startTime + "\t" + anglePID.getError() + "\t" + gyro.getAngle() + "\t" + anglePID.getOutput());
					
			leftMotor.set(anglePID.getOutput());
			rightMotor.set(-anglePID.getOutput());
		}else{
//			System.out.println("Left: " + leftDist() + "\tRight: " + rightDist() + "\tERR: " + distancePID.getCurrentError());
			
			distancePID.calculate(values[3], true);
			anglePID.calculate(gyro.getAngle(), true);
			
			if(command.getDistance() == 0 || (distancePID.isDone() && Math.abs(avgLinearRate()) < Constants.MAX_STOPPING_VEL)){
				System.out.println("Done Distance");
				setDrive(0.0);
				System.out.println(anglePID.getCurrentError());
				done = anglePID.isDone();
			}
			
//			System.out.println(System.currentTimeMillis()/1000.0 - startTime + "\t" + distancePID.getCurrentError() + "\t" + avgLinearRate());
			
			//Drive straight
			setLeft(distancePID.getOutput() + anglePID.getOutput());
			setRight(distancePID.getOutput() - anglePID.getOutput());
			
//			System.out.println(avgDist());
			
		}
	}

	@Override
	public void stop() {
		setDrive(0.0);
	}

}
