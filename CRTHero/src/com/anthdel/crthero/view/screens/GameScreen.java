package com.anthdel.crthero.view.screens;

import com.anthdel.crthero.CRTHero;
import com.anthdel.crthero.controller.PlayerController;
import com.anthdel.crthero.controller.ControllerProcessor;
import com.anthdel.crthero.model.GameWorld;
import com.anthdel.crthero.view.WorldRenderer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class GameScreen implements Screen {
	private CRTHero game;
	private ControllerProcessor controllerProcessor;
	private GameWorld gameWorld;
	private WorldRenderer worldRenderer;
	
	public GameScreen(CRTHero game) {
		this.game = game;
	}

	@Override
	public void render(float delta) {
		gameWorld.update(delta);
		
		worldRenderer.render();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		gameWorld = new GameWorld();
		controllerProcessor = new ControllerProcessor(gameWorld.getPlayer().getController());
		Gdx.input.setInputProcessor(controllerProcessor);
		worldRenderer = new WorldRenderer(game, gameWorld);
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		worldRenderer.dispose();

	}

}
