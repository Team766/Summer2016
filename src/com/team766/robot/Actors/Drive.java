package com.team766.robot.Actors;

import interfaces.EncoderReader;
import interfaces.SpeedController;

import com.team766.lib.Scheduler.Actor;
import com.team766.lib.Scheduler.ChessyDrive;
import com.team766.lib.Scheduler.DriveTo;
import com.team766.lib.Scheduler.Message;
import com.team766.lib.Scheduler.MotorCommand;
import com.team766.robot.Constants;
import com.team766.robot.HardwareProvider;

public class Drive extends Actor{

	SpeedController leftMotor = HardwareProvider.getInstance().getLeftDrive();
	SpeedController rightMotor = HardwareProvider.getInstance().getRightDrive();
	
	EncoderReader leftEncoder = HardwareProvider.getInstance().getLeftEncoder();
	EncoderReader rightEncoder = HardwareProvider.getInstance().getRightEncoder();
	
	MotorCommand[] motors;
	
	public void init() {
		acceptableMessages = new Class[]{MotorCommand.class, DriveTo.class};
	}
	
	public void run() {
		while(enabled){
			Message mess = readMessage();
			if(mess instanceof MotorCommand){
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
			}else if(mess instanceof DriveTo){
				DriveTo driver = (DriveTo)mess;
				while(avgDist() < driver.getXDist()){
					setDrive(0.5);
					
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				setDrive(0.0);
			}else if(mess instanceof ChessyDrive){
				ChessyDrive driver = (ChessyDrive)mess;
		
			}
		}
	}
	
	
	private double avgDist(){
		return (leftDist() + rightDist())/2;
	}
	
	private double leftDist(){
		return leftEncoder.getRaw() / Constants.counts_per_rev * Constants.wheel_circumference;
	}
	
	private double rightDist(){
		return leftEncoder.getRaw() / Constants.counts_per_rev * Constants.wheel_circumference;
	}
	
	private void setDrive(double power){
		leftMotor.set(power);
		rightMotor.set(power);
	}
	
	public String toString(){
		return "Actor:\tDrive";
	}
	
}
