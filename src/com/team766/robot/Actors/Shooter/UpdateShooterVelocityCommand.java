package com.team766.robot.Actors.Shooter;

import lib.Message;

import com.team766.lib.CommandBase;
import com.team766.lib.Messages.UpdateShooterVelocity;

public class UpdateShooterVelocityCommand extends CommandBase{

	UpdateShooterVelocity command;
	private boolean done;
	
	public UpdateShooterVelocityCommand(Message m){
		command = (UpdateShooterVelocity)m;
		Shooter.velocityPID.reset();
		done = false;
	}
	
	public void update(){
		Shooter.velocityPID.calculate(Shooter.getEncoder(), false);
		Shooter.setMotor(Shooter.velocityPID.getOutput());
		done = true;
	}

	public void stop() {
	}

	@Override
	public boolean isDone() {
		return done;
	}

}
