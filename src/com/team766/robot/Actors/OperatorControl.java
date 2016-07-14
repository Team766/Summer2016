package com.team766.robot.Actors;

import interfaces.JoystickReader;
import interfaces.RobotProvider;

import com.team766.lib.Scheduler.Actor;
import com.team766.lib.Scheduler.Message;
import com.team766.lib.Scheduler.MotorCommand;
import com.team766.robot.HardwareProvider;

public class OperatorControl extends Actor {
	
	JoystickReader jLeft = HardwareProvider.getInstance().getLeftJoystick();
	JoystickReader jRight = HardwareProvider.getInstance().getRightJoystick();
	JoystickReader jBox = HardwareProvider.getInstance().getBoxJoystick();
	
	public void init() {
		acceptableMessages = new Message.Type[]{Message.Type.JOYSTICK};
	}
	
	public void run() {
	}

	public void readMessage(Message[] m) {
		m = relevantMessage(m);
	}

	public Message[] postMessage() {
		return new Message[]{new MotorCommand(jLeft.getRawAxis(0), MotorCommand.Motor.leftDrive),
				new MotorCommand(jRight.getRawAxis(0), MotorCommand.Motor.rightDrive)};
	}

	public String toString() {
		return "Actor:\tOperatorControl";
	}
	
}
