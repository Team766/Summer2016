package com.team766.robot.Actors.Auton;

import lib.Actor;
import lib.LogFactory;
import lib.Message;

import com.team766.lib.Messages.DriveDistance;
import com.team766.lib.Messages.DriveStatusUpdate;
import com.team766.lib.Messages.DriveTo;
import com.team766.lib.Messages.VisionStatusUpdate;
import com.team766.robot.Constants;

public class AutonSelector extends Actor{
	
	private int autonMode;
	
	public AutonSelector(int mode){
		this.autonMode = mode;
	}
	
	@Override
	public void init() {
		System.out.println("Init!");
		acceptableMessages = new Class[]{DriveStatusUpdate.class, VisionStatusUpdate.class};
	}
	
	@Override
	public void run() {
		switch(Constants.AUTONS[autonMode]){
			case "None":
				System.out.println("Auton: None");
				LogFactory.getInstance("General").print("Auton: None");
				break;
			case "Inside Lane Path":
				System.out.println("Auton: Inside Lane Path");
//				waitForMessage(new DrivePath("InsideLanePathFar"), DriveStatusUpdate.class);
//				waitForMessage(new DrivePath("StraightAheadPath"), DriveStatusUpdate.class);
				waitForMessage(new DriveDistance(0, -5), DriveStatusUpdate.class);
				break;
			case "Low Bar":
				System.out.println("Auton: Low Bar");
				new LowBar().update();
				break;
			default:
				System.out.println("Auton: Failed to select auton");
				LogFactory.getInstance("General").print("Auton: Failed to select auton");
				break;
		}
	}
	
	public void updateMode(int mode){
		autonMode = mode;
	}
	
	public void step(){
	}

	@Override
	public String toString() {
		return "Actor:\tAutonSelector";
	}

	public void storeIntake(){
	}
	
	public void targetWithVision(){
		VisionStatusUpdate visionStatus;
		do{
			visionStatus = newVisionStatus();
			if(visionStatus == null)
				continue;
			
			sendMessage(new DriveTo(visionStatus.getAngle(),	//Angle
									visionStatus.getDist() * Math.cos(Math.toRadians(visionStatus.getAngle())),		//X
									visionStatus.getDist() * Math.sin(Math.toRadians(visionStatus.getAngle()))));	//Y
			
			sleep();
		}while(visionStatus == null ||
				(Math.abs(visionStatus.getAngle()) > Constants.k_angularThresh &&
			   Math.abs(visionStatus.getDist()) > Constants.k_linearThresh));
	}
	
	private synchronized VisionStatusUpdate newVisionStatus(){
		for(Message m : getInbox()){
			System.out.println(m.toString());
			if(m instanceof DriveStatusUpdate)
				getInbox().remove(m);
			if(m instanceof VisionStatusUpdate)
				return (VisionStatusUpdate)m;
		}
		return null;
	}
}
