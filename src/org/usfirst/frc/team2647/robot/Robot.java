package org.usfirst.frc.team2647.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.CANTalon;

import org.usfirst.frc.team2647.robot.Firebolt;
import org.usfirst.frc.team2647.robot.SnitchPitch;
import org.usfirst.frc.team2647.robot.HouseGearfindor;
import org.usfirst.frc.team2647.robot.Baskilisk;
import org.usfirst.frc.team2647.robot.Leviosa;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	// Xbox 360 gamepad
	Joystick gamepad;
	static final int xb_A = 1, xb_B = 2, xb_X = 3, xb_Y = 4,
					 xb_LB = 5, xb_RB = 6, xb_SELECT = 7, xb_START = 8,
					 xb_LSTICK = 9, xb_RSTICK = 10;
	static final int xb_LSTICKX = 0, xb_LSTICKY = 1,
					 xb_LT = 2, xb_RT = 3,
					 xb_RSTICKX = 4, xb_RSTICKY = 5;
	
	// Extreme 3D Joystick
	Joystick x3d;
	static final int x3d_X = 0, x3d_Y = 1, x3d_Z = 2, x3d_Slider = 3;
	
	// Drivetrain
	Firebolt drivetrain;
	
	// Shooter
	SnitchPitch shooter;
	
	// Gear Box
	HouseGearfindor gearBox;
	
	// Intake
	Baskilisk feeder;
	
	// Climber
	Leviosa climber;
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		drivetrain = new Firebolt(1, 2, 3, 4); // CAN ports. Front left motor, rear left motor, front right motor, rear right motord	
		gamepad = new Joystick(2);
		x3d = new Joystick(0);
		shooter = new SnitchPitch(0, 1, 2); // PWN ports. Top motor, bottom motor, piston motor.
		gearBox = new HouseGearfindor(3,4); // PWM ports. Left door servo, right door servo.
		feeder = new Baskilisk(5);
		climber = new Leviosa(6);
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the
	 * switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {
		
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		// Input Checking
		drivetrain.tankdrive(-gamepad.getRawAxis(xb_LSTICKY) * 0.7, -gamepad.getRawAxis(xb_RSTICKY) * 0.7);
		shooter.shoot(x3d.getRawButton(1), x3d.getRawButton(5), x3d.getRawButton(6), x3d.getRawButton(3), x3d.getRawButton(4));
		gearBox.setDoors(x3d.getRawAxis(x3d_Slider));
		climber.climb(x3d.getRawButton(8), x3d.getRawButton(7));
		feeder.intake(gamepad.getRawButton(xb_LB), gamepad.getRawButton(xb_RB));
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
	
	public void disabled(){
		
	}
}

