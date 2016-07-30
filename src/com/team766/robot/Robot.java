
package com.team766.robot;

import org.opencv.core.Core;

import interfaces.MyRobot;
import lib.Scheduler;

import com.team766.robot.Actors.Drive;
import com.team766.robot.Actors.OperatorControl;
import com.team766.robot.Actors.Vision;

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
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		Scheduler.getInstance().add(new Drive());
		Scheduler.getInstance().add(new Vision());
    }
    
    public void autonomousInit() {
    	setState(GameState.Auton);
    }

    public void autonomousPeriodic() {
    }
    
    public void teleopInit() {
    	setState(GameState.Teleop);
		Scheduler.getInstance().add(new OperatorControl());
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
