package com.team766.robot.Actors;

import interfaces.JoystickReader;

import com.team766.lib.Scheduler.Actor;
import com.team766.lib.Scheduler.MotorCommand;
import com.team766.lib.Scheduler.Scheduler;
import com.team766.robot.Constants;
import com.team766.robot.HardwareProvider;
import com.team766.robot.Robot;
import com.team766.lib.Scheduler.ChessyDrive;

public class OperatorControl extends Actor {
	
	JoystickReader jLeft = HardwareProvider.getInstance().getLeftJoystick();
	JoystickReader jRight = HardwareProvider.getInstance().getRightJoystick();
	JoystickReader jBox = HardwareProvider.getInstance().getBoxJoystick();
	
	public void init() {
		acceptableMessages = new Class[]{};
	}
	
	public void run() {
		while(Robot.getState() == Robot.GameState.Teleop){
//			sendMessage(new MotorCommand(jLeft.getRawAxis(0), MotorCommand.Motor.leftDrive));
//			sendMessage(new MotorCommand(jRight.getRawAxis(0), MotorCommand.Motor.rightDrive));
			
			sendMessage(new ChessyDrive(jLeft.getRawButton(Constants.driverQuickTurn), jLeft.getRawAxis(Constants.accelAxis), jRight.getRawAxis(Constants.steerAxis)));
		}
		Scheduler.getInstance().remove(this);
	}
	
	public String toString(){
		return "Actor:\tOperator Control";
	}

}
