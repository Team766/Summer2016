package com.team766.robot.Actors;

import interfaces.EncoderReader;
import interfaces.GyroReader;
import interfaces.SpeedController;
import lib.Actor;
import lib.Message;
import lib.PIDController;

import com.team766.lib.Scheduler.CheesyDrive;
import com.team766.lib.Scheduler.DriveTo;
import com.team766.lib.Scheduler.MotorCommand;
import com.team766.robot.Constants;
import com.team766.robot.HardwareProvider;

public class Drive extends Actor{

	SpeedController leftMotor = HardwareProvider.getInstance().getLeftDrive();
	SpeedController rightMotor = HardwareProvider.getInstance().getRightDrive();
	
	EncoderReader leftEncoder = HardwareProvider.getInstance().getLeftEncoder();
	EncoderReader rightEncoder = HardwareProvider.getInstance().getRightEncoder();
	
	GyroReader gyro = HardwareProvider.getInstance().getGyro();
	
	PIDController angularVelocity = new PIDController(Constants.k_angularP, Constants.k_angularI, Constants.k_angularD, Constants.k_angularThresh);
	PIDController linearVelocity = new PIDController(Constants.k_angularP, Constants.k_angularI, Constants.k_angularD, Constants.k_angularThresh);
	
	private Message currentMessage;
	
	MotorCommand[] motors;
	
	public void init() {
		acceptableMessages = new Class[]{MotorCommand.class, DriveTo.class, CheesyDrive.class};
	}
	
	public void run() {
		while(enabled){
			
			//Check for new messages
			if(newMessage())
				currentMessage = readMessage();
			
			if(currentMessage instanceof MotorCommand){
				MotorCommand motor = (MotorCommand)currentMessage;
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
			}else if(currentMessage instanceof DriveTo){
				DriveTo driver = (DriveTo)currentMessage;
				
				if(avgDist() < driver.getXDist()){
					setDrive(0.5);
				}else
					setDrive(0.0);
			}else if(currentMessage instanceof CheesyDrive){
				CheesyDrive driver = (CheesyDrive)currentMessage;
				angularVelocity.setSetpoint(driver.getAngularVelocity());
				linearVelocity.setSetpoint(driver.getLinearVelocity());
				
				angularVelocity.calculate(gyro.getRate(), true);
				linearVelocity.calculate(avgLinearRate(), true);
				System.out.println("Lin:" + linearVelocity.getOutput() + "\t" + angularVelocity.getOutput());
				if(!driver.getQuickTurn()){
					leftMotor.set(linearVelocity.getOutput() + angularVelocity.getOutput());
					rightMotor.set(linearVelocity.getOutput() - angularVelocity.getOutput());
				}else{
					leftMotor.set(angularVelocity.getOutput());
					rightMotor.set(-angularVelocity.getOutput());
				}
			}
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public double avgLinearRate(){
		return (leftEncoder.getRate() + rightEncoder.getRate())/2.0;
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
