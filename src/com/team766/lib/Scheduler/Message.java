package com.team766.lib.Scheduler;

public abstract class Message {
	
	public enum Type{
		SENSOR_VALUE,
		MECH_STATE,
		SETPOINTS,
		MOTOR_COMMAND,
		WORLD_STATE,
		JOYSTICK
	};
	
	public Type messageType;
	
	public Type getType(){
		return messageType;
	}
	
	public abstract String toString();
}
