package com.anthdel.crthero.view;

import com.anthdel.crthero.CRTHero;
import com.anthdel.crthero.model.GameWorld;
import com.anthdel.crthero.model.Player;
import com.anthdel.crthero.model.Resources;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class WorldRenderer {
	private GameWorld world;
	private SpriteBatch batch;
	private OrthogonalTiledMapRenderer levelRenderer;
	private OrthographicCamera camera;

	public WorldRenderer(CRTHero game, GameWorld world) {
		this.world = world;
		this.batch = game.batch;
		this.camera = world.getCam();
		levelRenderer = new OrthogonalTiledMapRenderer(world.getLevel()
				.getMap(), GameWorld.UNIT_SCALE);
	}

	public void renderPlayer(SpriteBatch spriteBatch) {
		Player player = world.getPlayer();
		TextureRegion currentFrame;
		
		switch (player.getState()) {
		case IDLE:
			currentFrame = player.isFacingLeft() ? Resources.playerIdleLeft : Resources.playerIdleRight;
			break;
		case WALKING:
			currentFrame = player.isFacingLeft() ? Resources.walkLeftAnimation.getKeyFrame(player.getStateTime(), true) :
				Resources.walkRightAnimation.getKeyFrame(player.getStateTime(), true);
			break;
		case JUMPING:
			if (player.getVelocity().y > 0)
				currentFrame = player.isFacingLeft() ? Resources.playerJumpLeft : Resources.playerJumpRight;
			else
				currentFrame = player.isFacingLeft() ? Resources.playerFallLeft : Resources.playerFallRight;
			break;
		default:
			currentFrame = player.isFacingLeft() ? Resources.playerIdleLeft : Resources.playerIdleRight;
			break;
		}
		player.render(spriteBatch, currentFrame);
	}

	public void render() {
		int fps = Gdx.graphics.getFramesPerSecond();
		Gdx.gl20.glClearColor(0.25f, 0.25f, 0.25f, 1);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		levelRenderer.setView(camera);
		levelRenderer.render();
		
		SpriteBatch spriteBatch = levelRenderer.getSpriteBatch();
		spriteBatch.begin();
			renderPlayer(spriteBatch);
		spriteBatch.end();
		
		batch.begin();
		Resources.gameFont.draw(batch, "FPS: " + fps, 20,
				GameWorld.SCREEN_HEIGHT - 20);
		batch.end();
	}

	public void dispose() {
		batch.dispose();
	}
}
