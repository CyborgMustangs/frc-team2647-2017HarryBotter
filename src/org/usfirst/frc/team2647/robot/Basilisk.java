package org.usfirst.frc.team2647.robot;

import edu.wpi.first.wpilibj.Talon;

public class Basilisk {
	private Talon feeder;
	
	public Basilisk(int feederPort){
		feeder = new Talon(feederPort);
		//feeder.setInverted(true);
	}
	
	public void intake(Joy joy){
		boolean in = joy.getButton("feedIn");
		if(in) feeder.set(-1.0);
		else {
			boolean out = joy.getButton("feedOut");
			if(out) feeder.set(1.0);
			else feeder.set(0.0);
		}
	}
}