package com.team766.robot;

import interfaces.MyRobot;
import lib.HTTPServer;
import lib.Scheduler;
import lib.LogFactory;
import trajectory.AutoPaths;

import com.team766.robot.Actors.OperatorControl;
import com.team766.robot.Actors.Vision;
import com.team766.robot.Actors.Auton.AutonSelector;
import com.team766.robot.Actors.Drive.Drive;

/**
 * 2016 Summer robot code
 * 
 * @author Brett Levenson
 */
public class Robot implements MyRobot {
	private long prevTime;
	private boolean autonDone = false;
	private boolean teleopDone = false;
	
	private final long RUN_TIME = 10;
	private long lastSleepTime = 0;
	
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
		LogFactory.createInstance("General");
		
		Scheduler.getInstance().add(new Drive());
		Scheduler.getInstance().add(new Vision());
		
		AutoPaths.loadPaths();
		System.out.println("IM ALIVE!!");
		
		new Thread(new HTTPServer(Constants.AUTONS)).start();
		
		prevTime = System.currentTimeMillis();
    }
    
    public void autonomousInit() {
    	LogFactory.getInstance("General").print("Auton Init");
    	setState(GameState.Auton);
    	emptyInboxes();
    	Scheduler.getInstance().add(new AutonSelector(Constants.getAutonMode()));
    	
    	autonDone = true;
    }

    public void autonomousPeriodic() {
    	if(System.currentTimeMillis() - prevTime >= 1000){
//    		System.out.println(Scheduler.getInstance().getCountsPerSecond());
    		prevTime = System.currentTimeMillis();
    	}
    	sleep();
    }
    
    public void teleopInit() {
    	LogFactory.getInstance("General").print("Teleop Init");
    	setState(GameState.Teleop);
    	emptyInboxes();
    	
    	Scheduler.getInstance().remove(AutonSelector.class);
    	
		Scheduler.getInstance().add(new OperatorControl());
		teleopDone = true;
	}

    public void teleopPeriodic() {
    	sleep();
    }
    
    public void disabledInit() {
    	LogFactory.getInstance("General").print("Robot Disabled");
    	setState(GameState.Disabled);
    	
    	if(autonDone && teleopDone){
			LogFactory.closeFiles();
		}
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
	
	private void emptyInboxes(){
		Scheduler.getInstance().getActor(Drive.class).clearInbox();
	}
	
	private void sleep(){
		//Run loops at set speed
		while(System.currentTimeMillis() - lastSleepTime <= RUN_TIME);
		
		lastSleepTime = System.currentTimeMillis();
	}
}