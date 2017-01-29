package com.team766.lib.Messages;

import com.team766.lib.AutoPaths;

import trajectory.Path;
import lib.Message;

public class DrivePath implements Message{
	
	private Path path;
	
	public DrivePath(String pathName){
		path = AutoPaths.get(pathName);
	}
	
	public Path getPath(){
		return path;
	}
	
	public String toString() {
		return "Message:\tDrive Path";
	}
}
