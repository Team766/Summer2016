package com.team766.lib.Scheduler;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Actor implements Runnable{
	
	public Message.Type[] acceptableMessages = new Message.Type[]{};
	
	public abstract void init();
	
	public abstract void readMessage(Message[] m);
	
	public abstract Message[] postMessage();
	
	public Message[] relevantMessage(Message[] m){
		boolean safe;
		ArrayList<Message> messages = new ArrayList<Message>(Arrays.asList(m));
		for(int i = messages.size(); i >= 0; i--){
			safe = false;
			for(Message.Type mess : acceptableMessages){
				if(m[i].getType() == mess){
					safe = true;
					break;
				}
			}
			if(!safe)
				messages.remove(i);
		}
		return messages.toArray(new Message[0]);
	}
	
	public abstract String toString();
}
