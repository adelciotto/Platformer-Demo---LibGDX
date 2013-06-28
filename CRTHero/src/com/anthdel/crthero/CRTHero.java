package com.anthdel.crthero;

import com.anthdel.crthero.model.GameWorld;
import com.anthdel.crthero.model.Resources;
import com.anthdel.crthero.view.screens.GameScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bitfire.postprocessing.PostProcessor;
import com.bitfire.postprocessing.effects.Bloom;
import com.bitfire.postprocessing.effects.CrtMonitor;
import com.bitfire.utils.ShaderLoader;

public class CRTHero extends Game {
	public static GameScreen gameScreen;
	public SpriteBatch batch;
	public PostProcessor postProcessor;

	@Override
	public void create() {
		Resources.load();
		batch = new SpriteBatch();
		postProcessor = new PostProcessor(false, true, true);
		gameScreen = new GameScreen(this);
		ShaderLoader.BasePath = "data/shaders/";
		CrtMonitor crtEffect = new CrtMonitor(GameWorld.SCREEN_WIDTH, GameWorld.SCREEN_HEIGHT, true, false);
		Bloom bloom = new Bloom((int)(GameWorld.SCREEN_WIDTH * 0.25f), (int)(GameWorld.SCREEN_HEIGHT * 0.25f));
		postProcessor.addEffect(crtEffect);
		postProcessor.addEffect(bloom);
		Gdx.input.setCursorCatched(true); // Hide the mouse cursor
		setScreen(gameScreen);
		
	}
	
	@Override
	public void render() {
		postProcessor.capture();
		super.render();
		postProcessor.render();
	}
	
	@Override
	public void dispose() {
		postProcessor.dispose();
		Resources.dispose();
	}

}
