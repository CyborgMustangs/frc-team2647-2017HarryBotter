package org.usfirst.frc.team2647.robot;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;

import org.usfirst.frc.team2647.robot.Input;

// named after Harry's broomstick
public class Firebolt {
	private CANTalon frontLeft;
	private CANTalon rearLeft;
	private CANTalon frontRight;
	private CANTalon rearRight;
	private RobotDrive drive;
	
	//Squared scaling - creates a smoother driving experience by squaring input values.
	private boolean squaredScalingEnabled = false;
	public boolean getsquaredScalingEnabled(){
		return squaredScalingEnabled;
	}
	public void setsquaredScalingEnabled(boolean enabled){
		squaredScalingEnabled = enabled;
	}
	
	public Firebolt(int frontLeftPort, int frontRightPort, int rearLeftPort, int rearRightPort){
		frontLeft = new CANTalon(frontLeftPort);
		rearLeft = new CANTalon(rearLeftPort);
		frontRight = new CANTalon(frontRightPort);
		rearRight = new CANTalon(rearRightPort);
		frontLeft.enable();
		//rearLeft.setInverted(true);
		rearLeft.enable();
		frontRight.enable();
		//rearRight.setInverted(true);
		rearRight.enable();
		drive = new RobotDrive(frontLeft, rearLeft, frontRight, rearRight);
		drive.setSafetyEnabled(false);
	}
	
	public void tankdrive(Input input, int joyPort){
		//bounds checking
		double leftVel = input.getJoy(joyPort).getAxis("lDrive") * 0.7;
		double rightVel = input.getJoy(joyPort).getAxis("rDrive") * 0.7;
		if(leftVel < -1.0) leftVel = -1.0;
		else if(leftVel > 1.0) leftVel = 1.0;
		if(rightVel < -1.0) rightVel = -1.0;
		else if(rightVel > 1.0) rightVel = 1.0;
		
		drive.tankDrive(leftVel, rightVel, squaredScalingEnabled);
	}
	
	public void arcadeDrive(double speed, double rot){
		//bounds checking
		if(speed < -1.0) speed = -1.0;
		else if(speed > 1.0) speed = 1.0;
		if(rot < -1.0) rot = -1.0;
		else if(rot > 1.0) rot = 1.0;
				
		drive.arcadeDrive(speed, rot, squaredScalingEnabled);
	}
	
	public void test(boolean port1on, boolean port2on, boolean port3on, boolean port4on){
		if(port1on) frontLeft.set(0.5);
		else frontLeft.set(0.0);
		
		if(port2on) rearLeft.set(0.5);
		else rearLeft.set(0.0);
		
		if(port3on) frontRight.set(0.5);
		else frontRight.set(0.0);
		
		if(port4on) rearRight.set(0.5);
		else rearRight.set(0.0);
	}
	
}
