package org.usfirst.frc.team2647.robot;

import java.util.HashMap;

import org.usfirst.frc.team2647.robot.Joy;

public final class Input {
	private Input() {
		joysticks_ = new HashMap<Integer, Joy>();
	}
	
	private static final Input INSTANCE = new Input();
	private java.util.HashMap<Integer, Joy> joysticks_;
	
	public static Input getInstance() {
		return INSTANCE;
	}
	public Joy getJoy(int joyPort) {
		if (!(joysticks_.containsKey(joyPort)))
			joysticks_.put(joyPort, new Joy(joyPort));
		return joysticks_.get(joyPort);
	}
	public void update() {
		for (Joy joystick : joysticks_.values()) {
		    joystick.update();
		}
	}
}
