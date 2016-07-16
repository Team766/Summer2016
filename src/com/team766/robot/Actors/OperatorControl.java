package com.team766.robot.Actors;

import interfaces.JoystickReader;

import com.team766.lib.Scheduler.Actor;
import com.team766.lib.Scheduler.MotorCommand;
import com.team766.robot.HardwareProvider;

public class OperatorControl extends Actor {
	
	JoystickReader jLeft = HardwareProvider.getInstance().getLeftJoystick();
	JoystickReader jRight = HardwareProvider.getInstance().getRightJoystick();
	JoystickReader jBox = HardwareProvider.getInstance().getBoxJoystick();
	
	public void init() {
		acceptableMessages = new Class[]{};
	}
	
	public void run() {
		while(enabled){
			sendMessage(new MotorCommand(jLeft.getRawAxis(0), MotorCommand.Motor.leftDrive));
			sendMessage(new MotorCommand(jRight.getRawAxis(0), MotorCommand.Motor.rightDrive));
		}
	}

	public String toString() {
		return "Actor:\tOperatorControl";
	}
	
}
