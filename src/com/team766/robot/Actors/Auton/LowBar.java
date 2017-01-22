package com.team766.robot.Actors.Auton;

import com.team766.lib.CommandBase;


public class LowBar extends CommandBase{

	public LowBar() {
	}

	@Override
	public void update() {
		Auton.storeIntake();
		Auton.targetWithVision();
	}

	@Override
	public void stop() {
	}

	@Override
	public boolean isDone() {
		return false;
	}

}
