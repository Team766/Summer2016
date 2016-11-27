package com.team766.robot.Actors;

import org.opencv.core.Mat;

import interfaces.CameraReader;
import lib.Actor;

import com.team766.lib.Messages.VisionStatusUpdate;
import com.team766.robot.HardwareProvider;

public class Vision extends Actor{
	
	CameraReader axisCam = HardwareProvider.getInstance().getAxisCamera();
	CameraReader usbCam = HardwareProvider.getInstance().getUSBCamera();
	
	@Override
	public void init() {
	}
	
	@Override
	public void run() {
		Mat img;
		while(true){
			itsPerSec++;
			sleep();
	
			img = axisCam.getImage();
			if(img == null)
				continue;
		
			//Begin processing image below
			
			sendMessage(new VisionStatusUpdate(getAngle(), getDist()));
		}
	}
	
	public void step(){
	}
	
	public double getAngle(){
		return 0;
	}
	
	public double getDist(){
		return 0;
	}

	@Override
	public String toString(){
		return "Actor:\tVision";
	}

}
