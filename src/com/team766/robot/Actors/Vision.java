package com.team766.robot.Actors;

import interfaces.CameraReader;
import lib.Actor;

import org.opencv.core.Mat;

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
		while(enabled){
			itsPerSec++;
			sleep();
			
			
			img = axisCam.getImage();
			if(img == null)
				continue;
			
			//Begin processing image below
			
		}
	}

	@Override
	public String toString(){
		return "Actor:\tVision";
	}

}
