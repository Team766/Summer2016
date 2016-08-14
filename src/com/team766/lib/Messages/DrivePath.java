package com.team766.lib.Messages;

import trajectory.AutoPaths;
import trajectory.Path;
import lib.Message;

public class DrivePath extends Message{
	
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
