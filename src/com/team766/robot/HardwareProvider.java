package com.team766.robot;

import com.team766.lib.ConfigFile;

import interfaces.CameraReader;
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
		return RobotProvider.instance.getMotor(ConfigFile.getLeftMotor());
	}
	public SpeedController getRightDrive(){
		return RobotProvider.instance.getMotor(ConfigFile.getRightMotor());
	}
	
	public SpeedController getShooterMotor(){
		return RobotProvider.instance.getMotor(ConfigFile.getShooterMotor());
	}
	
	public EncoderReader getShooterEncoder(){
		return RobotProvider.instance.getEncoder(ConfigFile.getShooterEncoder()[0], ConfigFile.getShooterEncoder()[0]);
	}

	public EncoderReader getLeftEncoder(){
		return RobotProvider.instance.getEncoder(ConfigFile.getLeftEncoder()[0], ConfigFile.getLeftEncoder()[1]);
	}
	public EncoderReader getRightEncoder(){
		return RobotProvider.instance.getEncoder(ConfigFile.getRightEncoder()[0], ConfigFile.getRightEncoder()[1]);
	}

	public SolenoidController getDriveShifter(){
		return RobotProvider.instance.getSolenoid(ConfigFile.getDriveShifter());
	}

	public GyroReader getGyro(){
		return RobotProvider.instance.getGyro(ConfigFile.getGyro());
	}
	
	public CameraReader getAxisCamera(){
		return RobotProvider.instance.getCamera(ConfigFile.getAxisCamera()[0], ConfigFile.getAxisCamera()[1]);
	}
	
	public CameraReader getUSBCamera(){
		return RobotProvider.instance.getCamera(ConfigFile.getUSBCamera()[0], ConfigFile.getUSBCamera()[1]);
	}

	// Operator Devices
	public JoystickReader getLeftJoystick(){
		return RobotProvider.instance.getJoystick(ConfigFile.getLeftJoystick());
	}
	public JoystickReader getRightJoystick(){
		return RobotProvider.instance.getJoystick(ConfigFile.getRightJoystick());
	}
	public JoystickReader getBoxJoystick(){
		return RobotProvider.instance.getJoystick(ConfigFile.getBoxJoystick());
	}
}
