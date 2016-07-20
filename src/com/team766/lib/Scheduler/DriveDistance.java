package com.team766.lib.Scheduler;

import lib.Message;

public class DriveDistance extends Message{
	
	private double distance;
	private double angle;
	
	public DriveDistance(double ang, double dist){
		distance = dist;
		angle = ang;
	}
	
	public double getDistance(){
		return distance;
	}
	
	public double getAngle(){
		return angle;
	}
}
