package org.usfirst.frc.team2647.robot;

import edu.wpi.first.wpilibj.IterativeRobot;

/*unused imports
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.CANTalon;*/

import org.usfirst.frc.team2647.robot.Firebolt;
import org.usfirst.frc.team2647.robot.SnitchPitch;
import org.usfirst.frc.team2647.robot.HouseGearfindor;
import org.usfirst.frc.team2647.robot.Basilisk;
import org.usfirst.frc.team2647.robot.Leviosa;
import org.usfirst.frc.team2647.robot.Input;
import edu.wpi.first.wpilibj.AnalogPotentiometer; //For auto selection

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
	Input input = Input.getInstance();
	
	//Auton declarations
	int autoMode;
	int autoPhase;
	final int AUTO_DEFAULT = 0;
	final int AUTO_MIDDLE_PEG = 1;
	final int AUTO_FORWARD_SCORE = 2;
	final int AUTO_LEFT_SHOOT = 3;
	final int AUTO_MIDDLE_PEG_LEFT_SHOOT = 4;
	
	// Xbox 360 gamepad
	final Joy X360 = input.getJoy(2);
	
	static final int xb_A = 1, xb_B = 2, xb_X = 3, xb_Y = 4,
			 xb_LB = 5, xb_RB = 6, xb_SELECT = 7, xb_START = 8,
			 xb_LSTICK = 9, xb_RSTICK = 10;
	static final int xb_LSTICKX = 0, xb_LSTICKY = 1,
			 xb_LT = 2, xb_RT = 3,
			 xb_RSTICKX = 4, xb_RSTICKY = 5;
	
	// Extreme 3D Joystick
	final Joy X3D = input.getJoy(0);
	
	static final int x3d_X = 0, x3d_Y = 1, x3d_Z = 2, x3d_Slider = 3;
	
	// Drivetrain
	Firebolt drivetrain;
	
	// Shooter
	SnitchPitch shooter;
	
	// Gear Box
	HouseGearfindor gearBox;
	
	// Intake
	Basilisk feeder;
	
	// Climber
	Leviosa climber;
	
	public void initControllers() {
	//When this is done with, be sure to only init the buttons you need or you'll be wasting CPU cycles!!!
		
	//xbox 360
		X360.setButton("feedIn", xb_LB);
		X360.setButton("feedOut", xb_RB);
		
		X360.setAxis("lDrive", xb_LSTICKY);
		X360.setAxis("rDrive", xb_RSTICKY);
	//extreme 3d joystick
		X3D.setButton("controlledFire", 1);
		X3D.setButton("flyFor", 5);
		X3D.setButton("flyBack", 6);
		X3D.setButton("pistFor", 3);
		X3D.setButton("pistBack", 4);
		X3D.setButton("climbUp", 8);
		X3D.setButton("climbDown", 7);
		X3D.setButton("doorClose", 10);
		X3D.setButton("doorOpen", 9);
		
		X3D.setAxis("rampPos", x3d_Slider);
	}
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		drivetrain = new Firebolt(1, 2, 3, 4); // CAN ports. Front left motor, rear left motor, front right motor, rear right motor.
		shooter = new SnitchPitch(0, 1, 2); // PWN ports. Top motor, bottom motor, piston motor.
		gearBox = new HouseGearfindor(3,4, 7, 8); // PWM ports. Left door servo, right door servo, left ramp servo, right ramp servo.
		feeder = new Basilisk(5);
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
		if(true){
			
		}
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
		input.update();
		drivetrain.tankdrive(X360);
		//shooter.shoot(X3D);
		gearBox.setDoors(X3D);
		gearBox.setRamp(X3D);
		climber.climb(X3D);
		feeder.intake(X360);
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

