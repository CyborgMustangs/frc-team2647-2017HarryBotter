package org.usfirst.frc.team2647.robot;

import edu.wpi.first.wpilibj.Servo;

public class HouseGearfindor {
	private Servo leftDoor;
	private Servo rightDoor;
	private Servo leftRamp;
	private Servo rightRamp;
	
	
	private final double leftDoorClosePos = 1.0;
	private final double leftDoorOpenPos = 0.0;
	private final double rightDoorClosePos = 0.0;
	private final double rightDoorOpenPos = 1.0;
	
	private final double leftRampClosePos = 1.0;
	private final double leftRampOpenPos = 0.0;
	private final double rightRampClosePos = 0.0;
	private final double rightRampOpenPos = 1.0;
	
	public HouseGearfindor(int leftDoorPort, int rightDoorPort, int leftRampPort, int rightRampPort) {
		leftDoor = new Servo(leftDoorPort);
		rightDoor = new Servo(rightDoorPort);
		leftRamp = new Servo(leftRampPort);
		rightRamp = new Servo(rightRampPort);
	}
	
	public void setDoors(boolean openButton, boolean closeButton) {
		if (openButton) {
			leftDoor.set(leftDoorOpenPos);
			rightDoor.set(rightDoorOpenPos);
		}else if(closeButton) {
			leftDoor.set(leftDoorClosePos);
			rightDoor.set(rightDoorClosePos);
		}
	}
	
	public void setDoors(Joy joy) {
		boolean close = joy.getButton("doorClose");
		boolean open = joy.getButton("doorOpen");
		if(close) {
			if(leftDoorClosePos - leftDoor.get() > 0.1) leftDoor.set(leftDoorClosePos);
			if(rightDoorClosePos - rightDoor.get() > 0.1) rightDoor.set(rightDoorClosePos);
		}
		else if(open) {
			if(leftDoorOpenPos - leftDoor.get() > 0.1) leftDoor.set(leftDoorOpenPos);
			if(rightDoorOpenPos - rightDoor.get() > 0.1) rightDoor.set(rightDoorOpenPos);
		}
	}
	
	/*public void setRampLeft(Joy joy) {
		double slide = joy.getAxis("rampPos");
		leftRamp.set((slide + 1) / 2);
	}
	
	public void setRampRight(Joy joy) {
		double slide = joy.getAxis("rampPos");
		rightRamp.set((slide + 1) / 2);
	}*/
	
	public void setRamp(Joy joy) {
		double slide = joy.getAxis("rampPos");
		double right = ((slide + 1) / 2);
		double left = Math.abs(right - 0.95); //set to 0.95 instead of 1 because the left is slightly ahead of the right.
		leftRamp.set(left);
		rightRamp.set(right);
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
