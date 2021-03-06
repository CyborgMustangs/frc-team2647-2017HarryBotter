package org.usfirst.frc.team2647.robot;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;

// named after Harry's broomstick
public class Firebolt {
	private CANTalon frontLeft;
	private CANTalon rearLeft;
	private CANTalon frontRight;
	private CANTalon rearRight;
	private RobotDrive drive;
	private ADXRS450_Gyro gyro;
	
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
		//gyro = new ADXRS450_Gyro();
		//gyro.calibrate();
	}
	
	public void tankdrive(Joy joy){
		//bounds checking
		double leftVel = -joy.getAxis("lDrive") * 0.7;
		double rightVel = -joy.getAxis("rDrive") * 0.7;
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
	
	/*public double getHeading() {
		return gyro.getAngle();
	}
	
	public double getRotRate() {
		return gyro.getRate();
	}*/
	
	/*public void test(boolean port1on, boolean port2on, boolean port3on, boolean port4on){
		if(port1on) frontLeft.set(0.5);
		else frontLeft.set(0.0);
		
		if(port2on) rearLeft.set(0.5);
		else rearLeft.set(0.0);
		
		if(port3on) frontRight.set(0.5);
		else frontRight.set(0.0);
		
		if(port4on) rearRight.set(0.5);
		else rearRight.set(0.0);
	}*/
	
}
