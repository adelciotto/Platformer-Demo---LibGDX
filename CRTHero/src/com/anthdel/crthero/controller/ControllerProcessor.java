package com.anthdel.crthero.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;

public class ControllerProcessor implements InputProcessor {
	private PlayerController controller;

	public ControllerProcessor(PlayerController controller) {
		this.controller = controller;
	}

	@Override
	public boolean keyDown(int keycode) {
		switch (keycode) {
		case Keys.LEFT:
			controller.moveLeftPressed();
			break;
		case Keys.RIGHT:
			controller.moveRightPressed();
			break;
		case Keys.UP:
			controller.jumpPressed();
			break;
		case Keys.SPACE:
			controller.firePressed();
			break;
		}
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		switch (keycode) {
		case Keys.LEFT:
			controller.moveLeftReleased();
			break;
		case Keys.RIGHT:
			controller.moveRightReleased();
			break;
		case Keys.UP:
			controller.jumpReleased();
			break;
		case Keys.SPACE:
			controller.fireReleased();
			break;
		case Keys.ESCAPE:
			Gdx.app.exit();
			break;
		}
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
