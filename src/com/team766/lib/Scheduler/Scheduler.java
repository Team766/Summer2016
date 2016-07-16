package com.team766.lib.Scheduler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class Scheduler {
	
	private static Scheduler instance;
	
	private ArrayList<Actor> actors;
	
	public static Scheduler getInstance(){
		if(instance == null)
			instance = new Scheduler();
		
		return instance;
	}
	
	private Scheduler(){
		actors = new ArrayList<Actor>();
	}
	
	public void add(Actor act){
		actors.add(act);
		act.init();
		new Thread(act).start();
	}
	
	public void remove(Actor actor){
		actor.enabled = false;
		
		//Remove ALL instances of it from list
		for(int i = actors.size() - 1; i >= 0; i--){
			if(actors.get(i).toString().equals(actor.toString()))
				actors.remove(i);
		}
	}
	
	public void sendMessage(Message newMessage) throws InterruptedException{
		//Add messages to all Actor's queues
		for (Actor act : actors) {
			act.getInbox().put(newMessage);
			act.filterMessage();
		}
	}
	
}
