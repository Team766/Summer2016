
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

	public enum GameState{
		Teleop,
		Disabled,
		Test,
		Auton
	}
	
	public static GameState gameState = GameState.Disabled;

    public static GameState getState() {
        return gameState;
    }

    public static void setState(GameState state) {
        gameState = state;
    }
	
	public void robotInit() {
		Scheduler.getInstance().add(new Drive());
		Scheduler.getInstance().add(new OperatorControl());
    }
    
    public void autonomousInit() {
    	setState(GameState.Auton);
    }

    public void autonomousPeriodic() {
    }
    
    public void teleopInit() {
    	setState(GameState.Teleop);
	}

    public void teleopPeriodic() {
    }
    
    public void disabledInit() {
    	setState(GameState.Disabled);
	}
    
    public void disabledPeriodic() {
	}
    
	public void testInit() {
		setState(GameState.Test);
	}
	
	public void testPeriodic() {
    }

    
}
