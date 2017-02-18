package org.usfirst.frc.team2647.robot;

import edu.wpi.first.wpilibj.Servo;

public class HouseGearfindor {
	public Servo left;
	public Servo right;
	
	private final double leftClosePos = 1.0;
	private final double leftOpenPos = 0.0;
	private final double rightClosePos = 0.0;
	private final double rightOpenPos = 1.0;
	
	public HouseGearfindor(int leftPort, int rightPort)
	{
		left = new Servo(leftPort);
		right = new Servo(rightPort);
	}
	
	public void setDoors(boolean openButton, boolean closeButton){
		if (openButton){
			left.set(leftOpenPos);
			right.set(rightOpenPos);
		}else if(closeButton){
			left.set(leftClosePos);
			right.set(rightClosePos);
		}
	}
	
	public void setDoors(double slide)
	{
		if( (slide < 0) && (leftClosePos - left.get() > 0.1) && (rightClosePos - right.get() > 0.1))
		{
			left.set(leftClosePos);
			right.set(rightClosePos);
		}else if( (slide > 0) && (leftOpenPos - left.get() > 0.1) && (rightOpenPos - right.get() > 0.1))
		{
			left.set(leftOpenPos);
			right.set(rightOpenPos);
		}
	}
	/* The next two functions are commented out because they are obsolete 
	 * test functions.
	private void testLeft(boolean up, boolean down){
		if(up) left.set( (left.get() < 0.9) ? (left.get() + 0.1) : (1.0));
		else if(down) left.set( (left.get() > 0.1) ? (left.get() - 0.1) : (0.0) );
	}
	
	private void testRight(boolean up, boolean down){
		if(up) right.set( (right.get() < 0.9) ? (right.get() + 0.1) : (1.0));
		else if(down) right.set( (right.get() > 0.1) ? (right.get() - 0.1) : (0.0) );
	}
	*/
}
