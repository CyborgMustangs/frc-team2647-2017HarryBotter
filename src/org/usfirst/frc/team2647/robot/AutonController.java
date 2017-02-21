package org.usfirst.frc.team2647.robot;

public class AutonController {

	private int autoMode;
	private int autoPhase;
	
	public AutonController() {
		autoMode = 0;
		autoPhase = 0;
	}
	
	public int getAutoMode() {
		return autoMode;
	}
	
	public void setAutoMode(int automode) {
		autoMode = automode;
	}
	
	public int getAutoPhase() {
		return autoPhase;
	}
	
	public void setAutoPhase(int autophase) {
		autoPhase = autophase;
	}
	
	// This is what will be called in the periodic auton
	public void update() {
		switch(autoMode) {
		
		}
	}

}
