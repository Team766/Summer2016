package com.team766.lib.Scheduler;

import java.util.concurrent.LinkedBlockingQueue;

public abstract class Actor implements Runnable{
	
	public Class<? extends Message>[] acceptableMessages = (Class<? extends Message>[])new Class[]{};
	private LinkedBlockingQueue<Message> inbox = new LinkedBlockingQueue<Message>();
	
	public boolean enabled = true;
	
	public abstract void init();
	
	public void filterMessage(){
	}
	
	protected boolean keepMessage(Message m){
	    for(Class<? extends Message> message : acceptableMessages){
	        if(m.getClass().equals(message)){
	            return true;
	        }
	    }
	    return false;
	}

	public void tryAddingMessage(Message m){
	    if(keepMessage(m)){
	        try {
	            inbox.put(m);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	public void sendMessage(Message mess){
		try {
			Scheduler.getInstance().sendMessage(mess);
		} catch (InterruptedException e) {
			System.err.println("Failed to send message: " + toString());
			e.printStackTrace();
		}
	}
	
	public Message readMessage(){
		try {
			return inbox.take();
		} catch (InterruptedException e) {
			System.err.println("Failed to readMessage: " + toString());
			e.printStackTrace();
		}
		return null;
	}
	
	public LinkedBlockingQueue<Message> getInbox(){
		return inbox;
	}
	
	
	public abstract String toString();
	
}
