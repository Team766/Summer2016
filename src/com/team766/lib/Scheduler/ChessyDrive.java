package com.team766.lib.Scheduler;

public class ChessyDrive extends Message{
	private boolean quickTurn;
	private double accel;
	private double steer;
	
	public ChessyDrive(boolean quickTurn, double accel, double steer){
		this.quickTurn = quickTurn;
		this.accel = accel;
		this.steer = steer;
	}
	
	public double getAngularVelocity(){
		return steer;
	}
	
	public double getLinearVelocity(){
		return accel;
	}
	
	public boolean getQuickTurn(){
		return quickTurn;
	}
}
