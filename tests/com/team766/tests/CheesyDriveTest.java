package com.team766.tests;

import lib.Scheduler;

import org.junit.Test;

import tests.RobotTestCase;

import com.team766.lib.ConfigFile;
import com.team766.lib.Messages.CheesyDrive;

public class CheesyDriveTest extends RobotTestCase {

	@Test
	public void testCheesyDrive() throws Exception {
		
		//Motors stopped when accel = 0
		Scheduler.getInstance().sendMessage(new CheesyDrive(false, 0, 0));
		assertTrueTimed(() -> {return instance.getMotor(ConfigFile.getLeftMotor()).get() == 0;}, 2);
		assertTrueTimed(() -> {return instance.getMotor(ConfigFile.getRightMotor()).get() == 0;}, 2);
		
		Scheduler.getInstance().sendMessage(new CheesyDrive(true, 0, 0));
		assertTrueTimed(() -> {return instance.getMotor(ConfigFile.getLeftMotor()).get() == 0;}, 2);
		assertTrueTimed(() -> {return instance.getMotor(ConfigFile.getRightMotor()).get() == 0;}, 2);
		
		
		//Spin full speed right
		Scheduler.getInstance().sendMessage(new CheesyDrive(false, 1, 1));
		assertTrueTimed(() -> {return instance.getMotor(ConfigFile.getLeftMotor()).get() >= 1;}, 2);
		assertTrueTimed(() -> {return instance.getMotor(ConfigFile.getRightMotor()).get() < instance.getMotor(ConfigFile.getLeftMotor()).get();}, 2);
		
		//Spin full speed left
		Scheduler.getInstance().sendMessage(new CheesyDrive(false, 1, -1));
		assertTrueTimed(() -> {return instance.getMotor(ConfigFile.getLeftMotor()).get() < instance.getMotor(ConfigFile.getRightMotor()).get();}, 2);
		assertTrueTimed(() -> {return instance.getMotor(ConfigFile.getRightMotor()).get() >= 1;}, 2);
		
		//Full speed forward
		Scheduler.getInstance().sendMessage(new CheesyDrive(false, 1, 0));
		assertTrueTimed(() -> {return instance.getMotor(ConfigFile.getLeftMotor()).get() >= 1;}, 2);
		assertTrueTimed(() -> {return instance.getMotor(ConfigFile.getRightMotor()).get() >= 1;}, 2);
		
		//Full speed backward
		Scheduler.getInstance().sendMessage(new CheesyDrive(false, -1, 0));
		assertTrueTimed(() -> {return instance.getMotor(ConfigFile.getLeftMotor()).get() == -1;}, 2);
		assertTrueTimed(() -> {return instance.getMotor(ConfigFile.getRightMotor()).get() == -1;}, 2);
		
		//Half power turn
		Scheduler.getInstance().sendMessage(new CheesyDrive(true, 0.5, 1));
		assertTrueTimed(() -> {return instance.getMotor(ConfigFile.getLeftMotor()).get() == -instance.getMotor(ConfigFile.getRightMotor()).get();}, 2);
		
		Scheduler.getInstance().sendMessage(new CheesyDrive(true, -0.5, -1));
		assertTrueTimed(() -> {return -instance.getMotor(ConfigFile.getLeftMotor()).get() == instance.getMotor(ConfigFile.getRightMotor()).get();}, 2);
	}
	
	@Test
	public void testQuickTurn() throws Exception {
		Scheduler.getInstance().sendMessage(new CheesyDrive(true, 0.5, 1));
		assertTrueTimed(() -> {return instance.getMotor(ConfigFile.getLeftMotor()).get() == -instance.getMotor(ConfigFile.getRightMotor()).get();}, 2);
		
		Scheduler.getInstance().sendMessage(new CheesyDrive(true, -0.5, -1));
		assertTrueTimed(() -> {return -instance.getMotor(ConfigFile.getLeftMotor()).get() == instance.getMotor(ConfigFile.getRightMotor()).get();}, 2);
		
		Scheduler.getInstance().sendMessage(new CheesyDrive(true, 0, 0.5));
		assertTrueTimed(() -> {return instance.getMotor(ConfigFile.getLeftMotor()).get() == -instance.getMotor(ConfigFile.getRightMotor()).get();}, 2);
	}
}
