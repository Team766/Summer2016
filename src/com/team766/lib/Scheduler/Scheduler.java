package com.team766.lib.Scheduler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class Scheduler {
	
	private static Scheduler instance;
	
	private HashMap<String, LinkedBlockingQueue<Message>> mailbox;
	private ArrayList<Actor> actors;
	
	public static Scheduler getInstance(){
		if(instance == null)
			instance = new Scheduler();
		
		return instance;
	}
	
	private Scheduler(){
		mailbox = new HashMap<String, LinkedBlockingQueue<Message>>();
		actors = new ArrayList<Actor>();
	}
	
	public void add(Actor act){
		actors.add(act);
		mailbox.put(act.toString(), new LinkedBlockingQueue<Message>());
		act.init();
		new Thread(act).start();
	}
	
	public void remove(Actor actor){
		mailbox.remove(actor.toString());
	}
	
	public void sendMessage(Message newMessage){
		//Add messages to all Actor's queues
		for (LinkedBlockingQueue<Message> queue : mailbox.values()) {
		    queue.add(newMessage);
		}
		
		//Filter unnecessary messages from queues 
		for(Actor actor : actors){
			mailbox.put(actor.toString(), new LinkedBlockingQueue<Message>(Arrays.asList(actor.filterMessage(mailbox.get(actor.toString()).toArray(new Message[0])))));
		}
	}
	
	public Message readMessage(String id){
		try {
			return mailbox.get(id).take();
		} catch (InterruptedException e) {
			System.err.println("Failed to readMessage: " + id);
			e.printStackTrace();
		}
		return null;
	}
	
}
