package com.cdm.view;

import com.cdm.Settings;

public class Position {
	public enum RefSystem {
		Screen, Level
	};

	private RefSystem system;

	public float x;

	public float y;

	public Position(float px, float py, RefSystem s) {
		x = px;
		y = py;
		system = s;
	}

	public float getX() {
		return x * getScale();
	}

	public float getY() {
		return y * getScale();
	}

	public float getScale() {
		if (system.equals(RefSystem.Screen))
			return 1;
		return Settings.CELL_WIDTH;
	}
}