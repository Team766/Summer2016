package com.team766.robot.Actors.Drive;

import interfaces.EncoderReader;
import interfaces.GyroReader;
import interfaces.SpeedController;
import interfaces.SubActor;
import lib.Actor;
import lib.Message;
import lib.PIDController;

import com.team766.lib.Messages.CheesyDrive;
import com.team766.lib.Messages.DriveDistance;
import com.team766.lib.Messages.DriveStatusUpdate;
import com.team766.lib.Messages.DriveTo;
import com.team766.lib.Messages.MotorCommand;
import com.team766.robot.Constants;
import com.team766.robot.HardwareProvider;

public class Drive extends Actor{

	SpeedController leftMotor = HardwareProvider.getInstance().getLeftDrive();
	SpeedController rightMotor = HardwareProvider.getInstance().getRightDrive();
	
	EncoderReader leftEncoder = HardwareProvider.getInstance().getLeftEncoder();
	EncoderReader rightEncoder = HardwareProvider.getInstance().getRightEncoder();
	
	GyroReader gyro = HardwareProvider.getInstance().getGyro();
	
	PIDController angularVelocity = new PIDController(Constants.k_angularP, Constants.k_angularI, Constants.k_angularD, Constants.k_angularThresh);
	PIDController linearVelocity = new PIDController(Constants.k_linearP, Constants.k_linearI, Constants.k_linearD, Constants.k_linearThresh);
	
	PIDController anglePID = new PIDController(Constants.k_angularP, Constants.k_angularI, Constants.k_angularD, Constants.k_angularThresh);
	PIDController distancePID = new PIDController(Constants.k_linearP, Constants.k_linearI, Constants.k_linearD, Constants.k_linearThresh);
	
	protected double xPos = 0;
	protected double yPos = 0;
	
	private boolean commandFinished;
	
	Message currentMessage;
	MotorCommand[] motors;
	SubActor currentCommand;
	
	public void init() {
		acceptableMessages = new Class[]{MotorCommand.class, DriveTo.class, CheesyDrive.class, DriveDistance.class};
		commandFinished = false;
	}
	
	public void run() {
		while(enabled){
			
			//Check for new messages
			if(newMessage()){
				if(currentCommand != null)
					currentCommand.stop();
				
				commandFinished = false;
				
				currentMessage = readMessage();
				
				if(currentMessage instanceof MotorCommand)
					currentCommand = new MotorSubCommand(currentMessage);
				else if(currentMessage instanceof DriveTo)
					currentCommand = new DriveToCommand(currentMessage);
				else if(currentMessage instanceof DriveDistance)
					currentCommand = new DriveDistanceCommand(currentMessage);
				else if(currentMessage instanceof CheesyDrive)
					currentCommand = new CheesyDriveCommand(currentMessage);
				
				//Reset Control loops
				resetControlLoops();
			}
			
							
			if(currentCommand != null){
				if(currentCommand.isDone()){
					currentCommand.stop();
					commandFinished = true;
					currentCommand = null;
				}else{
					currentCommand.update();
				}
			}
			
			//Send Status Update	#StayUpToDate	#Current	#inTheKnow
			sendMessage(new DriveStatusUpdate(commandFinished, currentMessage, xPos, yPos, avgLinearRate()));
			
			itsPerSec++;
			sleep();
		}
	}
	
	
	public double avgLinearRate(){
		return (leftEncoder.getRate() + rightEncoder.getRate())/2.0;
	}
	
	protected double avgDist(){
		return (leftDist() + rightDist())/2.0;
	}
	
	protected double leftDist(){
		return leftEncoder.getRaw() / Constants.counts_per_rev * Constants.wheel_circumference;
	}
	
	protected double rightDist(){
		return leftEncoder.getRaw() / Constants.counts_per_rev * Constants.wheel_circumference;
	}
	
	protected void setDrive(double power){
		leftMotor.set(power);
		rightMotor.set(power);
	}
	
	public String toString(){
		return "Actor:\tDrive";
	}
	
	private void resetControlLoops(){
		angularVelocity.reset();
		linearVelocity.reset();
		anglePID.reset();
		distancePID.reset();
	}
	
	/**
	 * -180 to 180 degrees
	 * @return
	 */
	public double getAngleNeg180to180(){
		double angle = gyro.getAngle();
		if(angle < 0)
			while(angle < -180)
				angle += 360;
		else
			while(angle > 180)
				angle -= 360;
		
		return angle;
	}
	
}
