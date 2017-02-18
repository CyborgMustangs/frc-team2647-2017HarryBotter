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
		private java.util.HashMap<Integer, Double> axes_;
		private java.util.HashMap<String, Integer> buttonNames_;
		private java.util.HashMap<String, Integer> axisNames_;
		
		public Joy(int port) {
			joystick_ = new Joystick(port);
		}
		public void addButton(String name, int button) {
			buttonNames_.put(name, button);
			buttons_.put(button, joystick_getRawButton(button));
		}
		public void addAxis(String name, int axis) {
			axisNames_.put(name, axis);
			axes_.put(axis, joystick_getRawAxis(axis));
		}
		public boolean getButton(String name) {
			if (buttonNames_.containsKey(name)) return buttons_.get(buttonNames_.get(name));
		}
		public double getAxis(String name) {
			if (axisNames_.containsKey(name)) return axes_.get(axisNames_.get(name));
		}
		
		public void update() {
			for (int button : buttons_.keySet()) {
				buttons_.put(key, joystick_.getRawButton(button));
			}
			for (int axis : axes_.keySet()) {
				axes_.put(key, joystick_.getRawAxis(axis));
			}
		}
	}
	
	private static java.util.HashMap<Integer, Joy> joysticks_;
	
	//checks to see if the joystick map contains the current joystick; if it doesn't it adds it.
	private static void checkJoystick(int joyPort) {
		if (!(joysticks_.containsKey(joyPort)))
			joysticks_.put(joyPort, new Joy(joyPort));
	}
	
	public static void addButton(int joyPort, String name, int button) {
		checkJoystick(joyPort);
		return joysticks_.get(joyPort).addButton(name, button);
	}
	public static void addAxis(int joyPort, String name, int axis) {
		checkJoystick(joyPort);
		return joysticks_.get(joyPort).addAxis(name, axis);
	}
	public static boolean getButton(int joyPort, String name) {
		checkJoystick(joyPort);
		return joysticks_.get(joyPort).getButton(name);
	}
	public static double getAxis(int joyPort, String name) {
		checkJoystick(joyPort);
		return joysticks_.get(joyPort).getAxis(name);
	}
	public static void update() {
		for (Joy joystick : joysticks_.values()) {
		    joystick.update();
		}
	}
}
