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
	
	//Values: {avgLinearRate(), leftRate(), rightRate(), avgDist(), leftDist(), rightDist()}
	@Override
	public void update(double[] values) {
		angularVelocity.setSetpoint(command.getAngularVelocity() * (Constants.maxAngularVelocity));
		linearVelocity.setSetpoint(command.getLinearVelocity() * (Constants.maxLinearVelocity));
		
		angularVelocity.calculate(gyro.getRate(), true);
		linearVelocity.calculate(values[0], true);

		
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
