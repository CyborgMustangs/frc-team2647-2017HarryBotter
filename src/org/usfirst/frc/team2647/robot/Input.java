package org.usfirst.frc.team2647.robot;

import java.util.Map;

import edu.wpi.first.wpilibj.Joystick;

public final class Input {
	
	private static final Input INSTANCE = new Input();
	private Input() {}
	public static Input getInstance() {
		return INSTANCE;
	}
	
	private static class Joy {
		private Joystick joystick_;
		private java.util.HashMap<Integer, Boolean> buttons_;
		private java.util.HashMap<Integer, Double> sticks_;
		
		public Joy(int port) {
			joystick_ = new Joystick(port);
		}
		private void checkButton(int key) {
			if (!(buttons_.containsKey(key))) buttons_.put(key, joystick_.getRawButton(key));
		}
		private void checkStick(int key) {
			if (!(sticks_.containsKey(key))) sticks_.put(key, joystick_.getRawAxis(key));
		}
		
		public boolean getButton(int key) {
			checkButton(key);
			return buttons_.get(key);
		}
		public double getStick(int key) {
			checkStick(key);
			return sticks_.get(key);
		}
		
		public void update() {
			for (int key : buttons_.keySet()) {
				buttons_.put(key, joystick_.getRawButton(key));
			}
			for (int key : sticks_.keySet()) {
				sticks_.put(key, joystick_.getRawAxis(key));
			}
		}
	}
	
	private static java.util.HashMap<Integer, Joy> joysticks_;
	
	//checks to see if the joystick map contains the current joystick; if it doesn't it adds it.
	private static void checkJoystick(int joyPort) {
		if (!(joysticks_.containsKey(joyPort)))
			joysticks_.put(joyPort, new Joy(joyPort));
	}
	
	public static boolean getButton(int joyPort, int key) {
		checkJoystick(joyPort);
		return joysticks_.get(joyPort).getButton(key);
	}
	public static double getStick(int joyPort, int key) {
		checkJoystick(joyPort);
		return joysticks_.get(joyPort).getStick(key);
	}
	public static void update() {
		for (Joy joystick : joysticks_.values()) {
		    joystick.update();
		}
	}
}
