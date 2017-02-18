package org.usfirst.frc.team2647.robot;

import java.util.Map;

import edu.wpi.first.wpilibj.Joystick;

public class Button {
	public Button(int buttonNum, boolean buttonState) {
		num = buttonNum;
		state = buttonState;
	}
	public int num;
	public boolean state;
}
public class Axis {
	public Axis(int axisNum, double axisState) {
		num = axisNum;
		state = axisState;
	}
	public int num;
	public double state;
}

public class Joy {
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
