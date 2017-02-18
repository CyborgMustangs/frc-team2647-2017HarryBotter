package org.usfirst.frc.team2647.robot;

import org.usfirst.frc.team2647.robot.Joy;

public final class Input {
	
	private static final Input INSTANCE = new Input();
	private Input() {}
	public static Input getInstance() {
		return INSTANCE;
	}
	
	private static java.util.HashMap<Integer, Joy> joysticks_;
	
	//checks to see if the joystick map contains the current joystick; if it doesn't it adds it.
	private static void checkJoystick(int joyPort) {
		if (!(joysticks_.containsKey(joyPort)))
			joysticks_.put(joyPort, new Joy(joyPort));
	}
	
	public static void setButton(int joyPort, String name, int buttonNum) {
		checkJoystick(joyPort);
		joysticks_.get(joyPort).setButton(name, buttonNum);
	}
	public static void setAxis(int joyPort, String name, int axisNum) {
		checkJoystick(joyPort);
		joysticks_.get(joyPort).setAxis(name, axisNum);
	}
	public static boolean getButton(int joyPort, String name) {
		if (joysticks_.containsKey(joyPort)) return joysticks_.get(joyPort).getButton(name);
	}
	public static double getAxis(int joyPort, String name) {
		if (joysticks_.containsKey(joyPort)) return joysticks_.get(joyPort).getAxis(name);
	}
	public static void update() {
		for (Joy joystick : joysticks_.values()) {
		    joystick.update();
		}
	}
}
