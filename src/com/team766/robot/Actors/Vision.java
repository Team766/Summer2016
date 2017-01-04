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
			//displayInText(img);
			//System.out.println("(" + img.get(10, 10)[0] + "," + img.get(10, 10)[1] + ", " + img.get(10, 10)[2] + ")");
			
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
	
	private void displayInText(Mat img){
		for(int i = 0; i < img.rows(); i += 5){
			for(int j = 0; j < img.cols(); j += 5){
				System.out.print((img.get(i, j)[0] == 122 && img.get(i, j)[1] == 78 && img.get(i, j)[2] == 49) ? 1 : 0);
			}
			System.out.println();
		}
		
		System.out.println("\n\n\n");
	}

}
