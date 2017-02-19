package org.usfirst.frc.team2647.robot;

import edu.wpi.first.wpilibj.Talon;

public class Leviosa {
	private Talon climbMotor;
	
	public Leviosa(int climbMotorPort){
		climbMotor = new Talon(climbMotorPort);
	}
	
	public void climb(Input input, int joyPort){
		boolean up = input.getJoy(joyPort).getButton("climbUp");
		boolean down = input.getJoy(joyPort).getButton("climbDown");
		if(up) climbMotor.set(-1.0);
		else if(down) climbMotor.set(1.0);
		else climbMotor.set(0.0);
	}
}
