package com.team766.lib.Scheduler;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.team766.robot.Constants;

public class Scheduler implements Runnable{
	
	private static Scheduler instance;
	
	private ArrayBlockingQueue<Actor> actors = new ArrayBlockingQueue<Actor>(Constants.ACTOR_COUNT);
	private LinkedBlockingQueue<Message> messages = new LinkedBlockingQueue<Message>();
	
	public enum GameState{
		Teleop,
		Disabled,
		Test,
		Auton
	}
	
	public GameState gameState = GameState.Disabled;
	
	public static Scheduler getInstance(){
		if(instance == null)
			instance = new Scheduler();
		
		return instance;
	}
	
	private Scheduler(){
	}
	
	public void add(Actor act){
		try {
			actors.put(act);
			act.init();
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("Scheduler:  Failed to add actor- " + act.toString());
		}
	}
	
	public void remove(Actor actor){
		for(Actor act: actors){
			if(act.toString().equals(actor.toString()))
				actors.remove(act);
		}
	}
	
	public void run(){
		while(true){
			//Receive messages from all actors
			for(Actor act : actors){
				for(Message m : act.postMessage())
					messages.add(m);
			}
			
			//Distribute messages to actors
			Message[] out = messages.toArray(new Message[0]);
			messages.clear();
			for(Actor act : actors){
				act.readMessage(out);
			}
			
			//Update loops
			for(Actor act : actors){
				act.run();
			}
			
		}
	}
}
