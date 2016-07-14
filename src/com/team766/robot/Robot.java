
package com.team766.robot;

import interfaces.MyRobot;

import com.team766.lib.Scheduler.Scheduler;
import com.team766.robot.Actors.Drive;
import com.team766.robot.Actors.OperatorControl;

/**
 * 2016 Summer robot code
 * 
 * @author Brett Levenson
 */
public class Robot implements MyRobot {

	public void robotInit() {
		Scheduler.getInstance().add(new Drive());
		Scheduler.getInstance().add(new OperatorControl());
    }
    
    public void autonomousInit() {
    	Scheduler.getInstance().gameState = Scheduler.GameState.Auton;
    }

    public void autonomousPeriodic() {
    }
    
    public void teleopInit() {
    	Scheduler.getInstance().gameState = Scheduler.GameState.Teleop;
	}

    public void teleopPeriodic() {
    }
    
    public void disabledInit() {
    	Scheduler.getInstance().gameState = Scheduler.GameState.Disabled;
	}
    
    public void disabledPeriodic() {
	}
    
	public void testInit() {
		Scheduler.getInstance().gameState = Scheduler.GameState.Test;
	}
	
	public void testPeriodic() {
    }

    
}
