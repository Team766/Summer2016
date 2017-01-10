package com.team766.robot.Actors.Drive;

import interfaces.SubActor;
import lib.Message;

import com.team766.lib.Messages.DriveDistance;

public class DriveProfilerCommand extends Drive implements SubActor{

	DriveDistance command;
	
	final double kMaxVel = 2;
	final double kMaxAccel = 0.5;
	final double kDt = 0.010;

	double velocity;
	double goal = 0;
	double position = 0;
	double idealPosition = 0;
	double direction;
	
	//States
	enum State{
		RAMP_UP, MAX_VEL, RAMP_DOWN, LOCK
	}
	State state_;
		
	public DriveProfilerCommand(Message command){
		this.command = (DriveDistance)command;
		
		state_ = State.RAMP_UP;
		goal = this.command.getDistance();
		
		direction = (goal < 0) ? -1 : 1;
		velocity = 0;
		
		done = false;
	}
	
	//Values: {avgLinearRate(), leftRate(), rightRate(), avgDist(), leftDist(), rightDist()}
	@Override
	public void update(double[] values) {
		position += values[0] * kDt;
		
		switch(state_){
			case RAMP_UP:
				velocity += direction * kMaxAccel * kDt;
				
				if(Math.abs(velocity) >= kMaxVel){
					state_ = State.MAX_VEL;
				}
				break;
			case MAX_VEL:
				velocity = kMaxVel * direction;
				break;
			case RAMP_DOWN:
				velocity -= direction * kMaxAccel * kDt;
				if (Math.abs(position) >= Math.abs(goal)){
					state_ = State.LOCK;
				}
				break;
			case LOCK:
				velocity = 0;
				done = true;
				break;
		}
		
		if((Math.abs(goal) - Math.abs(position) <= distToStop(values[0])) && (state_ != State.LOCK)){
			state_ = State.RAMP_DOWN;
		}
	
//		System.out.println("Vel: " + values[0] + " pos: " + values[3]);
		System.out.println("distToStop: " + distToStop(values[0]) + " position: " + position + " state_: " + state_ + " vel: " + velocity);
	
		idealPosition += velocity * kDt;
	
		linearVelocity.setSetpoint(velocity);
		linearVelocity.calculate(values[0], false);
		setDrive(linearVelocity.getOutput());
	}
	
	@Override
	public void stop() {
		setDrive(0.0);
	}
	
	private double distToStop(double vel){
		return (vel * vel) / (2 * kMaxAccel);
	}
	
	public String toString(){
		return "Drive Profiler Command";
	}

}
