package org.usfirst.frc.team2647.robot;

import edu.wpi.first.wpilibj.Talon;

public class Leviosa {
	private Talon climbMotor;
	
	public Leviosa(int climbMotorPort){
		climbMotor = new Talon(climbMotorPort);
	}
	
	public void climb(boolean up, boolean down){
		if(up) climbMotor.set(-1.0);
		else if(down) climbMotor.set(1.0);
		else climbMotor.set(0.0);
	}
}
