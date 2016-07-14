package com.team766.lib.Scheduler;

public abstract class Actor implements Runnable{
	
	public Message[] acceptableMessages;
	
	public abstract void init();
	
	public abstract void readMessage(Message m);
	
	public abstract Message postMessage();
	
	public boolean relevantMessage(Message m){
		for(Message mess : acceptableMessages){
			if(m.getType() == mess.getType())
				return true;
		}
		return false;
			
	}
	
	public abstract String toString();
}
