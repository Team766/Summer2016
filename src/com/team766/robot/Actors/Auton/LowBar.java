package com.team766.robot.Actors.Auton;

import interfaces.SubActor;


public class LowBar extends AutonSelector implements SubActor{

	public LowBar(int mode) {
		super(mode);
	}

	@Override
	public void update(double[] values) {
		storeIntake();
		targetWithVision();
	}

	@Override
	public void stop() {
	}

}
