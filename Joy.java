package org.usfirst.frc.team2647.robot;

import edu.wpi.first.wpilibj.Joystick;

public class Joy {
	private Joystick joystick_;
	private java.util.HashMap<String, Button> buttons_;
	private java.util.HashMap<String, Axis> axes_;
	
	private static class Button {
		public Button(int buttonNum, boolean buttonState) {
			num = buttonNum;
			state = buttonState;
			prevState = state;
		}
		public int num;
		public boolean state;
		public boolean prevState;
	}
	private static class Axis {
		public Axis(int axisNum, double axisState) {
			num = axisNum;
			state = axisState;
		}
		public int num;
		public double state;
	}
	
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
	public boolean wasPressed(String name) {
		if (buttons_.containsKey(name)) {
			Button button = buttons_.get(name);
			if (button.state == true && button.state != button.prevState) return true;
		}
		return false;
	}
	public boolean wasReleased(String name) {
		if (buttons_.containsKey(name)) {
			Button button = buttons_.get(name);
			if (button.state == false && button.state != button.prevState) return true;
		}
		return false;
	}

	public void update() {
		for (Button button : buttons_.values()) {
			button.prevState = button.state;
			button.state = joystick_.getRawButton(button.num);
		}
		for (Axis axis : axes_.values()) {
			axis.state = joystick_.getRawAxis(axis.num);
		}
	}
}
