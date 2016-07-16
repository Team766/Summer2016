package com.team766.lib.Scheduler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.LinkedBlockingQueue;

public abstract class Actor implements Runnable{
	
	public Class<? extends Message>[] acceptableMessages = (Class<? extends Message>[])new Class[]{};
	private LinkedBlockingQueue<Message> inbox = new LinkedBlockingQueue<Message>();
	
	public boolean enabled = true;
	
	public abstract void init();
	
	public void filterMessage(){
		boolean safe;
		ArrayList<Message> messages = new ArrayList<Message>(inbox);
		for(int i = messages.size()-1; i >= 0; i--){
			safe = false;
			for(Class<? extends Message> message : acceptableMessages){
				if(messages.get(i).getClass().equals(message)){
					safe = true;
					break;
				}
			}
			if(!safe)
				messages.remove(i);
		}
		inbox = new LinkedBlockingQueue<Message>(Arrays.asList(messages.toArray(new Message[0])));
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
