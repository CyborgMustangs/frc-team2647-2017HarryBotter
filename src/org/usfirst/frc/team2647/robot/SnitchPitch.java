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
	
	
	public SnitchPitch(int topMotorPort, int botMotorPort, int pistonPort){
		top = new Spark(topMotorPort);
		bot = new Spark(botMotorPort);
		piston = new Talon(pistonPort);
		flywheelsRevved = false;
		flywheelsTimeStarted = false;
		flywheelsRevInitTime = 0.0;
		flywheelsRevDeltaTime = 0.0;
	}
	
	public void rawRapidFire(boolean fire){
		if(fire){
			top.set(1.0);
			bot.set(1.0);
			piston.set(1.0);
		}
		else{
			top.set(0.0);
			bot.set(0.0);
			piston.set(0.0);
		}
	}
	
	public void controlledRapidFire(boolean fire){
		if(fire){
			if(flywheelsRevved){
				top.set(1.0);
				bot.set(1.0);
				piston.set(1.0);
			}else{
				if(!flywheelsTimeStarted){
					flywheelsRevInitTime = Timer.getFPGATimestamp();
					flywheelsTimeStarted = true;
				}
				top.set(1.0);
				bot.set(1.0);
				flywheelsRevDeltaTime = Timer.getFPGATimestamp() - flywheelsRevInitTime;
				flywheelsRevved = (flywheelsRevDeltaTime > flywheelsRevTargetTime) ? true : false;
			}
		}else{
			flywheelsTimeStarted = false;
			flywheelsRevved = false;
			flywheelsRevDeltaTime = 0.0;
			top.set(0.0);
			bot.set(0.0);
			piston.set(0.0);
		}
	}
}
