package org.usfirst.frc.team2647.robot;

import edu.wpi.first.wpilibj.Talon;

public class Leviosa {
	private Talon climbMotor;
	
	public Leviosa(int climbMotorPort){
		climbMotor = new Talon(climbMotorPort);
	}
	
	public void climb(Joy joy){
		boolean up = joy.getButton("climbUp");
		if(up) climbMotor.set(-1.0);
		else {
			boolean down = joy.getButton("climbDown");
			if(down) climbMotor.set(1.0);
			else climbMotor.set(0.0);
		}
	}
}
