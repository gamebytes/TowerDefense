package com.cdm.view;

import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;

public interface IRenderer {

	void drawRect(float x0, float y0, float x1, float y1, Color c);

	public void fillRect(float x0, float y0, float x1, float y1, Color c);

	void drawLines(Position pos, List<Vector3> lines, float angle, Color color,
			float size);

	void drawPoly(Position pos, List<Vector3> lines, float angle, Color color,
			float size);

	void drawText(int i, int j, String string, Color c);

	void drawText(Position position, String money, Color moneyColor);

	public void drawText(int i, int j, String string, Color c, float scale);

	public void render(PolySprite sprite, Position pos, float size,
			float angle, int glTriangles);

	public void render(PolySprite sprite, Position pos, float size,
			float angle, int glTriangles, Color color);

}
