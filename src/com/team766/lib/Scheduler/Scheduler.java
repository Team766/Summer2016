package com.team766.lib.Scheduler;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.team766.robot.Constants;

public class Scheduler implements Runnable{
	
	private ArrayBlockingQueue<Actor> actors = new ArrayBlockingQueue<Actor>(Constants.ACTOR_COUNT);
	private LinkedBlockingQueue<Message> messages = new LinkedBlockingQueue<Message>();
	
	public Scheduler(){
	}
	
	public void add(Actor act){
		try {
			actors.put(act);
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("Scheduler:  Failed to add actor- " + act.toString());
		}
	}
	
	public void remove(Actor act){
	}
	
	public void run(){
		while(true){
			//Receive messages from all actors
			for(Actor act : actors){
				messages.add(act.postMessage());
			}
			
			//Distribute messages to actors
			for(int i = messages.size(); i > 0; i--){
				for(Actor act : actors){
					try {
						act.readMessage(messages.take());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
