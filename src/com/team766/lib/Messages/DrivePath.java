package com.team766.lib.Messages;

import lib.Message;

public class DrivePath extends Message{
	
	private String path;
	
	public DrivePath(String path){
		this.path = path;
	}
	
	public String getPath(){
		return path;
	}
	
	public String toString() {
		return "Message:\tDrive Path";
	}
}
