package com.team766.lib;

import interfaces.ConfigFileReader;

/**
 * Class for accessing the port or index for each hardware device
 * 
 * @author Brett Levenson
 *
 */

public class ConfigFile {
	
	public static int getLeftMotor(){
		return ConfigFileReader.getInstance().getPort("leftMotor");
	}
	
	public static int getRightMotor(){
		return ConfigFileReader.getInstance().getPort("rightMotor");
	}
	
	public static int getShooterMotor(){
		return ConfigFileReader.getInstance().getPort("shooterMotor");
	}
	
	public static int[] getShooterEncoder(){
		return ConfigFileReader.getInstance().getPorts("shooterEncoder");
	}
	
	public static int[] getLeftEncoder(){
		return ConfigFileReader.getInstance().getPorts("leftEncoder");
	}
	
	public static int[] getRightEncoder(){
		return ConfigFileReader.getInstance().getPorts("rightEncoder");
	}
	
	public static int getDriveShifter(){
		return ConfigFileReader.getInstance().getPort("driveShifter");
	}
	
	public static int getGyro(){
		return ConfigFileReader.getInstance().getPort("gyro");
	}
	
	public static String[] getAxisCamera(){
		return ConfigFileReader.getInstance().getString("axisCamera");
	}
	
	public static String[] getUSBCamera(){
		return ConfigFileReader.getInstance().getString("usbCamera");
	}
	
	public static int getLeftJoystick(){
		return ConfigFileReader.getInstance().getPort("leftJoystick");
	}
	
	public static int getRightJoystick(){
		return ConfigFileReader.getInstance().getPort("rightJoystick");
	}
	
	public static int getBoxJoystick(){
		return ConfigFileReader.getInstance().getPort("boxJoystick");
	}
}
