package com.team766.robot.Actors.Shooter;

import com.team766.lib.Messages.DriveStatusUpdate;
import com.team766.lib.Messages.ShooterStatusUpdate;
import com.team766.lib.Messages.UpdateShooterVelocity;
import com.team766.robot.Constants;
import com.team766.robot.HardwareProvider;

import interfaces.EncoderReader;
import interfaces.SpeedController;
import interfaces.SubActor;
import lib.Actor;
import lib.Message;
import lib.PIDController;

public class Shooter extends Actor{
	
	SpeedController motor = HardwareProvider.getInstance().getShooterMotor();
	EncoderReader encoder = HardwareProvider.getInstance().getShooterEncoder();
	PIDController velocityPID = new PIDController(Constants.k_shooterP, Constants.k_shooterI, Constants.k_shooterD, Constants.k_shooterThreshold);
	
	Message currentMessage;
	SubActor currentCommand;
	private boolean commandFinished;

	public void init() {
		commandFinished = false;
	}
	
	public void run() {
		while(enabled){
			if(newMessage()){
				if(currentCommand != null)
					currentCommand.stop();
				
				currentMessage = readMessage();
				
				if(currentMessage instanceof UpdateShooterVelocity)
					currentCommand = new UpdateShooterVelocityCommand(currentMessage);
				
				commandFinished = false;
				
				currentMessage = readMessage();
			}
			//Sends status update message
			sendMessage(new ShooterStatusUpdate(commandFinished, currentMessage, getRate()));
			
			sleep();
		}
	}


	public String toString() {
		return "Actor:\tShooter";
	}

	public void step() {
		if(currentCommand != null){
			if(currentCommand.isDone()){
				currentCommand.stop();
				commandFinished = true;
				currentCommand = null;
			}else{
				currentCommand.update();
			}
		}
	}
	
	protected void setMotor(double speed){
		motor.set(speed);
	}
	
	protected double getEncoder(){
		return encoder.get();
	}
	
	protected double getRate(){
		return encoder.getRate() * Constants.k_shooterGearRatio;
	}
	
}
