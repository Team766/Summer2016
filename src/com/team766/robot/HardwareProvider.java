package com.team766.robot;

import com.team766.lib.ConfigFile;

import interfaces.EncoderReader;
import interfaces.GyroReader;
import interfaces.JoystickReader;
import interfaces.RobotProvider;
import interfaces.SolenoidController;
import interfaces.SpeedController;

public class HardwareProvider {
	private static HardwareProvider instance;
	
	public static HardwareProvider getInstance(){
		if(instance == null){
			instance = new HardwareProvider();
		}
		return instance;
	}
	
	// HAL
	public SpeedController getLeftDrive(){
		return RobotProvider.instance.getMotor(ConfigFile.getInstance().getLeftMotor());
	}
	public SpeedController getRightDrive(){
		return RobotProvider.instance.getMotor(ConfigFile.getInstance().getRightMotor());
	}

	public EncoderReader getLeftEncoder(){
		return RobotProvider.instance.getEncoder(ConfigFile.getInstance().getLeftEncoder());
	}
	public EncoderReader getRightEncoder(){
		return RobotProvider.instance.getEncoder(ConfigFile.getInstance().getRightEncoder());
	}

	public SolenoidController getDriveShifter(){
		return RobotProvider.instance.getSolenoid(ConfigFile.getInstance().getDriveShifter());
	}

	public GyroReader getGyro(){
		return RobotProvider.instance.getGyro(ConfigFile.getInstance().getGyro());
	}

	// Operator Devices
	public JoystickReader getLeftJoystick(){
		return RobotProvider.instance.getJoystick(ConfigFile.getInstance().getLeftJoystick());
	}
	public JoystickReader getRightJoystick(){
		return RobotProvider.instance.getJoystick(ConfigFile.getInstance().getRightJoystick());
	}
	public JoystickReader getBoxJoystick(){
		return RobotProvider.instance.getJoystick(ConfigFile.getInstance().getBoxJoystick());
	}
}
