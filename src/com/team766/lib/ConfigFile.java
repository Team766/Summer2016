package com.team766.lib;

import interfaces.ConfigFileReader;

/**
 * Class for accessing the port or index for each hardware device
 * 
 * @author Brett Levenson
 *
 */

public class ConfigFile {
	private static ConfigFile instance;
	
	public static ConfigFile getInstance(){
		if(instance == null)
			instance = new ConfigFile();
		return instance;
	}
	
	public int getLeftMotor(){
		return ConfigFileReader.getInstance().getPort("leftMotor");
	}
	
	public int getRightMotor(){
		return ConfigFileReader.getInstance().getPort("rightMotor");
	}
	
	public int getLeftEncoder(){
		return ConfigFileReader.getInstance().getPort("leftEncoder");
	}
	
	public int getRightEncoder(){
		return ConfigFileReader.getInstance().getPort("rightEncoder");
	}
	
	public int getDriveShifter(){
		return ConfigFileReader.getInstance().getPort("driveShifter");
	}
	
	public int getGyro(){
		return ConfigFileReader.getInstance().getPort("gyro");
	}
	
	public int getLeftJoystick(){
		return ConfigFileReader.getInstance().getPort("leftJoystick");
	}
	
	public int getRightJoystick(){
		return ConfigFileReader.getInstance().getPort("rightJoystick");
	}
	
	public int getBoxJoystick(){
		return ConfigFileReader.getInstance().getPort("boxJoystick");
	}
}
