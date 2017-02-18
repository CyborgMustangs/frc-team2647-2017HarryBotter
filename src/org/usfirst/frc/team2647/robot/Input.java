package org.usfirst.frc.team2647.robot;

import org.usfirst.frc.team2647.robot.Joy;

public final class Input {
	private static java.util.HashMap<Integer, Joy> joysticks_;
	
	public static Joy getJoy(int joyPort) {
		if (!(joysticks_.containsKey(joyPort)))
			joysticks_.put(joyPort, new Joy(joyPort));
		return joysticks_.get(joyPort);
	}
	public static void update() {
		for (Joy joystick : joysticks_.values()) {
		    joystick.update();
		}
	}
}
