package com.team766.lib.Messages;

import lib.Message;

public class UpdateShooterVelocity implements Message{
	
	private double velocity;
	
	public UpdateShooterVelocity(double v){
		velocity = v;
	}

	public double getVelocity(){
		return velocity;
	}

	public String toString() {
		return "Message:\tUpdateShooterVelocity";
	}

}
