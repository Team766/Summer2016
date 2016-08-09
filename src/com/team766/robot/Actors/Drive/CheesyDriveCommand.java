package com.team766.robot.Actors.Drive;

import interfaces.SubActor;
import lib.Message;

import com.team766.lib.Messages.CheesyDrive;
import com.team766.robot.Constants;

public class CheesyDriveCommand extends Drive implements SubActor{

	CheesyDrive command;
	
	public CheesyDriveCommand(Message message){
		command = (CheesyDrive)message;
	}
	
	@Override
	public void update() {
		angularVelocity.setSetpoint(command.getAngularVelocity() * (Constants.maxAngularVelocity));
		linearVelocity.setSetpoint(command.getLinearVelocity() * (Constants.maxLinearVelocity));
		
		angularVelocity.calculate(gyro.getRate(), true);
		linearVelocity.calculate(avgLinearRate(), true);

		
		if(!command.getQuickTurn()){
			leftMotor.set(linearVelocity.getOutput() + angularVelocity.getOutput());
			rightMotor.set(linearVelocity.getOutput() - angularVelocity.getOutput());
		}else{
			leftMotor.set(angularVelocity.getOutput());
			rightMotor.set(-angularVelocity.getOutput());
		}
		
		done = false;
	}

	@Override
	public void stop() {
	}

}
