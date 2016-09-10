
package com.team766.robot;

import interfaces.MyRobot;
import lib.Scheduler;

import org.opencv.core.Core;

import trajectory.AutoPaths;

import com.team766.robot.Actors.AutonSelector;
import com.team766.robot.Actors.OperatorControl;
import com.team766.robot.Actors.Vision;
import com.team766.robot.Actors.Drive.Drive;

/**
 * 2016 Summer robot code
 * 
 * @author Brett Levenson
 */
public class Robot implements MyRobot {
	private long prevTime;
	
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
		//System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		Scheduler.getInstance().add(new Drive());
		Scheduler.getInstance().add(new Vision());
		
		AutoPaths.loadPaths();
		System.err.println("IM ALIVE!!");
		prevTime = System.currentTimeMillis();
    }
    
    public void autonomousInit() {
    	setState(GameState.Auton);
    	Scheduler.getInstance().add(new AutonSelector());
    }

    public void autonomousPeriodic() {
    	if(System.currentTimeMillis() - prevTime >= 1000){
//    		System.out.println(Scheduler.getInstance().getCountsPerSecond());
    		prevTime = System.currentTimeMillis();
    	}
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
	
	public void startCompetition(){
		System.out.println("Wrong one...close enough?");
	}

    
}
