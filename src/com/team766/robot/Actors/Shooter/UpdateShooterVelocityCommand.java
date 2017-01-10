package com.team766.robot.Actors.Shooter;

import com.team766.lib.Messages.UpdateShooterVelocity;

import interfaces.SubActor;
import lib.Message;

public class UpdateShooterVelocityCommand extends Shooter implements SubActor{

	UpdateShooterVelocity command;
	
	public UpdateShooterVelocityCommand(Message m){
		command = (UpdateShooterVelocity)m;
		velocityPID.reset();
	}
	
	public void update(double[] values){
		velocityPID.calculate(encoder.get(), false);
		motor.set(velocityPID.getOutput());
		
	}

	public void stop() {
		
	}

}
