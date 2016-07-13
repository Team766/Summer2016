package com.team766.lib.Scheduler;

public abstract class Actor {
	
	public abstract void readMessage(Message m);
	
	public abstract Message postMessage();
	
	public abstract String toString();
}
