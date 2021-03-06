package com.cdm.defend;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.cdm.Game;
import com.cdm.gui.effects.SoundFX;
import com.cdm.view.Campaign;
import com.cdm.view.HighScoreScreen;
import com.cdm.view.InputScreen;
import com.cdm.view.LevelScreen;
import com.cdm.view.MenuScreen;
import com.cdm.view.Screen;
import com.cdm.view.SoundScreen;

public class DefendGame implements ApplicationListener, Game {
	private static final long serialVersionUID = 1L;

	private boolean running = false;
	private Screen screen;
	private float accum = 0;
	boolean stop = false;
	private LevelScreen levelScreen;
	private MenuScreen menuScreen;
	private SoundScreen optionsScreen;
	private HighScoreScreen highscoreScreen;
	private InputScreen inputScreen;
	private long oldMicros = 0;

	public void create() {
		running = true;

		Campaign c = new Campaign("/com/cdm/view/campaign1.txt");
		levelScreen = new LevelScreen(this, c);
		optionsScreen = new SoundScreen(this);
		setScreen(menuScreen = new MenuScreen(this));
		highscoreScreen = new HighScoreScreen(this);
		inputScreen = new InputScreen(this, c);
		SoundFX.Initialize();
	}

	public void pause() {
		running = false;
	}

	public void resume() {
		running = true;
	}

	public void setScreen(Screen newScreen) {
		if (screen != null)
			screen.removed();

		screen = newScreen;

		if (screen != null) {
			Gdx.input.setInputProcessor(screen);
			screen.wait(this);
		}
	}

	public void render() {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		accum += Gdx.graphics.getDeltaTime();
		while (accum > 1.0f / 60.0f) {
			accum -= 1.0f / 60.0f;
		}
		move();
		screen.render(accum);

	}

	private void move() {
		if (!running)
			return;
		long millis = System.currentTimeMillis();
		long micro = System.nanoTime() / 1000 + millis * 1000;
		float delta = 0;
		if (oldMicros > 0) {
			delta = (micro - oldMicros) * 0.000001f;
			mywait(delta);
			screen.move(delta);
		}
		oldMicros = micro;

	}

	private void mywait(float delta) {
		try {
			Integer ms = (int) (delta * 1000);

			int wait = 15 - ms;
			if (wait > 5) {
				Thread.sleep(wait);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void dispose() {
		SoundFX.dispose();
		if (screen != null)
			screen.dispose();

	}

	@Override
	public void setScreen(String string) {
		if (Screen.LEVEL_SCREEN.equals(string))
			setScreen(levelScreen);
		else if (Screen.OPTIONS_SCREEN.equals(string))
			setScreen(optionsScreen);
		else if (Screen.MENU_SCREEN.equals(string))
			setScreen(menuScreen);
		else if (Screen.HIGHSCORE_SCREEN.equals(string))
			setScreen(highscoreScreen);
		else if (Screen.INPUT_SCREEN.equals(string))
			setScreen(inputScreen);
	}
}
