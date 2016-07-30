package com.team766.tests;

import org.junit.Test;

import tests.Camera;
import tests.RobotTestCase;

import com.team766.lib.ConfigFile;

public class VisionTest extends RobotTestCase{
	@Test
	public void testGrabImage() throws Exception{
		setImage("img_1.jpeg");
	}
	
	private void setImage(String image){
		((Camera)(instance.getCamera(ConfigFile.getAxisCamera()[0], ConfigFile.getAxisCamera()[1]))).setNextImage(image);
	}
}
