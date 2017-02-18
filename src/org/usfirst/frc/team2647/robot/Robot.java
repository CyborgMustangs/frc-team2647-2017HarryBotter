package org.usfirst.frc.team2647.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.CANTalon;

import org.usfirst.frc.team2647.robot.Firebolt;
import org.usfirst.frc.team2647.robot.SnitchPitch;
import org.usfirst.frc.team2647.robot.HouseGearfindor;
import org.usfirst.frc.team2647.robot.Baskilisk;
import org.usfirst.frc.team2647.robot.Leviosa;
import org.usfirst.frc.team2647.robot.Input;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
	Input input = Input.getInstance();
	// Xbox 360 gamepad
	static final int X360 = 2;
	
	// Extreme 3D Joystick
	static final int X3D = 0;
	
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
	
	public void initControllers() {
	//When this is done with, be sure to only init the buttons you need or you'll be wasting CPU cycles!!!
		
	//xbox 360
		input.setButton(X360, "A", 1);
		input.setButton(X360, "B", 2);
		input.setButton(X360, "X", 3);
		input.setButton(X360, "Y", 4);
		input.setButton(X360, "LB", 5);
		input.setButton(X360, "RB", 6);
		input.setButton(X360, "SELECT", 7);
		input.setButton(X360, "START", 8);
		input.setButton(X360, "LSTICK", 9);
		input.setButton(X360, "RSTICK", 10);
		
		input.setAxis(X360, "LSTICKX", 0);
		input.setAxis(X360, "LSTICKY", 1);
		input.setAxis(X360, "LT", 2);
		input.setAxis(X360, "RT", 3);
		input.setAxis(X360, "RSTICKX", 4);
		input.setAxis(X360, "RSTICKY", 5);
	//extreme 3d joystick
		//more to be defined
		input.setAxis(X3D, "X", 0);
		input.setAxis(X3D, "Y", 1);
		input.setAxis(X3D, "Z", 2);
		input.setAxis(X3D, "SLIDER", 3);
	}
	
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
		initControllers();
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

