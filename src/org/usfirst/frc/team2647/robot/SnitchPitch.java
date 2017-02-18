package org.usfirst.frc.team2647.robot;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;

public class SnitchPitch {
	private Spark top;
	private Spark bot;
	private Talon piston;
	
	// Variables for controlledRapidFire
	boolean flywheelsRevved;
	boolean flywheelsTimeStarted;
	static final double flywheelsRevTargetTime = 1.0;
	double flywheelsRevInitTime;
	double flywheelsRevDeltaTime;
	
	
	public SnitchPitch(int topMotorPort, int botMotorPort, int pistonPort)
	{
		top = new Spark(topMotorPort);
		bot = new Spark(botMotorPort);
		piston = new Talon(pistonPort);
		flywheelsRevved = false;
		flywheelsTimeStarted = false;
		flywheelsRevInitTime = 0.0;
		flywheelsRevDeltaTime = 0.0;
	}
	
	/* The next two functions are made private because they are used internally 
	 * in shoot() to prevent issues with double-assignment and interruption. 
	 */
	private void rawFlywheels(boolean forward, boolean backward)
	{
		if(forward){ top.set(1.0); bot.set(1.0); }
		else if(backward){ top.set(-1.0); bot.set(-1.0); }
		else{ top.set(0.0); bot.set(0.0); }
	}
	
	private void rawPiston(boolean forward, boolean backward)
	{
		if(forward) piston.set(1.0);
		else if(backward) piston.set(-1.0);
		else{ piston.set(0.0); }
	}
	
	public void shoot(boolean controlledFire, boolean flywheelsForward, 
					  boolean flywheelsBackward, boolean pistonForward, 
					  boolean pistonBackward)
	{
		if(controlledFire)
		{
			if(flywheelsRevved)
			{
				rawFlywheels(true, false);
				rawPiston(true, false);
			}else
			{
				if(!flywheelsTimeStarted)
				{
					flywheelsRevInitTime = Timer.getFPGATimestamp();
					flywheelsTimeStarted = true;
				}
				rawFlywheels(true, false);
				flywheelsRevDeltaTime = Timer.getFPGATimestamp() - flywheelsRevInitTime;
				flywheelsRevved = (flywheelsRevDeltaTime > flywheelsRevTargetTime) ? true : false;
			}
		}else
		{
			flywheelsTimeStarted = false;
			flywheelsRevved = false;
			flywheelsRevDeltaTime = 0.0;
			
			rawFlywheels(flywheelsForward, flywheelsBackward);
			rawPiston(pistonForward, pistonBackward);
		}
	}
}
