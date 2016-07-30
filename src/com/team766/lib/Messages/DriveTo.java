package com.team766.lib.Messages;

import lib.Message;

public class DriveTo extends Message{

	private double heading,
				   xDist,
				   yDist;
	
	public DriveTo(double heading, double xDist, double yDist){
		this.heading = heading;
		this.xDist = xDist;
		this.yDist = yDist;
	}
	
	public double getHeading(){
		return heading;
	}
	
	public double getXDist(){
		return xDist;
	}
	
	public double getYDist(){
		return yDist;
	}
	
	public String toString() {
		return "Message:\tDrive To";
	}
}
