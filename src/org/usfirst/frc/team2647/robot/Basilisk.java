package org.usfirst.frc.team2647.robot;

import edu.wpi.first.wpilibj.Talon;

public class Basilisk {
	private Talon feeder;
	
	public Basilisk(int feederPort){
		feeder = new Talon(feederPort);
		//feeder.setInverted(true);
	}
	
	public void intake(boolean in, boolean out){
		if(in) feeder.set(-1.0);
		else if(out) feeder.set(1.0);
		else feeder.set(0.0);
	}
}
