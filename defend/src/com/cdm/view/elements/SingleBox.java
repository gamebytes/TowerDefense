package com.cdm.view.elements;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.cdm.view.IRenderer;
import com.cdm.view.Position;

public class SingleBox implements Element {

	private static final boolean CROSS = true;
	private Position pos = new Position(0, 0, Position.LEVEL_REF);
	private static List<Vector3> lines = new ArrayList<Vector3>();
	private float angle = 0.0f;
	private Color color = new Color(0.5f, 0.5f, 0.8f, 0.7f);
	private float size = 1.0f;

	public SingleBox() {
		if (lines.size() == 0) {
			float res = 4;
			Vector3 s = new Vector3(0.5f, -0.5f, 0);

			for (int x = 0; x < res; x++) {
				lines.add(new Vector3(x / res, 0, 0).sub(s));
				lines.add(new Vector3(0, x / res, 0).sub(s));
				lines.add(new Vector3(x / res, 1, 0).sub(s));
				lines.add(new Vector3(1, x / res, 0).sub(s));

				if (CROSS) {
					lines.add(new Vector3(x / res, 0, 0).sub(s));
					lines.add(new Vector3(1, 1 - x / res, 0).sub(s));
					lines.add(new Vector3(x / res, 1, 0).sub(s));
					lines.add(new Vector3(0, 1 - x / res, 0).sub(s));

				}
			}
		}
	}

	public Position getPos() {
		return pos;
	}

	public void draw(IRenderer renderer) {

		renderer.drawLines(pos, lines, angle, color, size);

	}

	public void move(float time) {

	}

	@Override
	public void setPosition(Position pos) {
		this.pos = pos;
	}

	@Override
	public int compareTo(Element arg0) {
		return arg0.hashCode() - this.hashCode();
	}

	@Override
	public void drawAfter(IRenderer renderer) {

	}

}
