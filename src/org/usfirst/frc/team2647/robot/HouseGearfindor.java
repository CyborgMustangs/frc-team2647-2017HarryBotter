package org.usfirst.frc.team2647.robot;

import edu.wpi.first.wpilibj.Servo;

public class HouseGearfindor {
	public Servo left;
	public Servo right;
	
	public HouseGearfindor(int leftPort, int rightPort){
		left = new Servo(leftPort);
		right = new Servo(rightPort);
	}
	
	public void setDoors(boolean openButton, boolean closeButton){
		if (openButton){
			left.set(0.0);
			right.set(1.0);
		}else if(closeButton){
			left.set(1);
			right.set(0.85);
		}
	}
	
	public void testLeft(boolean up, boolean down){
		if(up) left.set( (left.get() < 0.9) ? (left.get() + 0.1) : (1.0));
		else if(down) left.set( (left.get() > 0.1) ? (left.get() - 0.1) : (0.0) );
	}
	
	public void testRight(boolean up, boolean down){
		if(up) right.set( (right.get() < 0.9) ? (right.get() + 0.1) : (1.0));
		else if(down) right.set( (right.get() > 0.1) ? (right.get() - 0.1) : (0.0) );
	}
}
