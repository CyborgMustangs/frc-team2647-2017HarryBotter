package org.usfirst.frc.team2647.robot;

import java.util.Map;

import edu.wpi.first.wpilibj.Joystick;

public final class Input {
	
	private static final Input INSTANCE = new Input();
	private Input() {}
	public static Input getInstance() {
		return INSTANCE;
	}
	
	private static class Button {
		public Button(int buttonNum, boolean buttonState) {
			num = buttonNum;
			state = buttonState;
		}
		public int num;
		public boolean state;
	}
	private static class Axis {
		public Axis(int axisNum, double axisState) {
			num = axisNum;
			state = axisState;
		}
		public int num;
		public double state;
	}
	
	private static class Joy {
		private Joystick joystick_;
		private java.util.HashMap<String, Button> buttons_;
		private java.util.HashMap<String, Axis> axes_;
		
		public Joy(int port) {
			joystick_ = new Joystick(port);
		}
		public void setButton(String name, int buttonNum) {
			buttons_.put(name, new Button(buttonNum, joystick_.getRawButton(buttonNum)));
		}
		public void setAxis(String name, int axisNum) {
			axes_.put(name, new Axis(axisNum, joystick_.getRawAxis(axisNum)));
		}
		public boolean getButton(String name) {
			if (buttons_.containsKey(name)) return buttons_.get(name).state;
			return false;
		}
		public double getAxis(String name) {
			if (axes_.containsKey(name)) return axes_.get(name).state;
			return 0.0;
		}
		
		public void update() {
			for (Button button : buttons_.values()) {
				button.state = joystick_.getRawButton(button.num);
			}
			for (Axis axis : axes_.values()) {
				axis.state = joystick_.getRawAxis(axis.num);
			}
		}
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
		else return false;
	}
	public static double getAxis(int joyPort, String name) {
		if (joysticks_.containsKey(joyPort)) return joysticks_.get(joyPort).getAxis(name);
		else return 0.0;
	}
	public static void update() {
		for (Joy joystick : joysticks_.values()) {
		    joystick.update();
		}
	}
}
