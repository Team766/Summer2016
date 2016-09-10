package com.team766.lib.Messages;

import lib.Message;
import lib.StatusUpdateMessage;

public class ShooterStatusUpdate extends StatusUpdateMessage{

	private double currentVelocity;
	
	public ShooterStatusUpdate(boolean done, Message currentMessage, double curr) {
		super(done, currentMessage);
		
		currentVelocity = curr;
	}

	public double getShooterCurrVelocity(){
		return currentVelocity;
	}
	
	public String toString() {
		return "Message:\tShooterStatusUpdate";
	}

}
