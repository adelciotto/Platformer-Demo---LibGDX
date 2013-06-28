package com.anthdel.crthero.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Level {
	private static final int Y_OFFSET = 1;

	private int levelID;
	private TiledMap map;
	private GameWorld world;
	private Player player;
	private OrthographicCamera cam;
	private Vector3 lerpTarget = new Vector3();
	private final float gravity = -2.0f;

	public Level(int levelID, TiledMap map, GameWorld world) {
		this.levelID = levelID;
		this.map = map;
		this.world = world;
		this.player = world.getPlayer();
		this.cam = world.getCam();
		player.setGravity(gravity);
	}

	public void updateCollision(float delta) {
		player.getVelocity().scl(delta);
		Rectangle playerRect = world.rectPool.obtain();
		playerRect.set(player.getPosition().x, player.getPosition().y,
				player.getBounds().width, player.getBounds().height);

		int startX, startY, endX, endY;
		if (player.getVelocity().x > 0)
			startX = endX = (int) (player.getPosition().x
					+ player.getBounds().width + player.getVelocity().x);
		else
			startX = endX = (int) (player.getPosition().x + player
					.getVelocity().x);

		startY = (int) (player.getPosition().y);
		endY = (int) (player.getPosition().y + player.getBounds().height);

		world.getTiles(startX, endX, startY, endY, world.tiles);
		playerRect.x += player.getVelocity().x;

		for (Rectangle tile : world.tiles) {
			if (playerRect.overlaps(tile)) {
				player.getVelocity().x = 0;
				break;
			}
		}

		playerRect.x = player.getPosition().x;

		if (player.getVelocity().y > 0)
			startY = endY = (int) (player.getPosition().y
					+ player.getBounds().height + player.getVelocity().y);
		else
			startY = endY = (int) (player.getPosition().y + player
					.getVelocity().y);
		
		startX = (int) (player.getPosition().x);
		endX = (int) (player.getPosition().x + player.getBounds().width);
		
		world.getTiles(startX, endX, startY, endY, world.tiles);
		playerRect.y += player.getVelocity().y;
		
		for (Rectangle tile : world.tiles) {
			if (playerRect.overlaps(tile)) {
				if (player.getVelocity().y < 0) {
					player.getPosition().y = tile.y + tile.height;
					player.setGrounded(true);
				}
				player.getVelocity().y = 0;
				break;
			}
		}
		world.rectPool.free(playerRect);
		
		player.getPosition().add(player.getVelocity());
		player.getBounds().x = player.getPosition().x;
	    player.getBounds().y = player.getPosition().y;
		
		player.getVelocity().scl(1 / delta);

	}
	
	private void updateCamera(float delta) {
		cam.position.lerp(
				lerpTarget.set(player.getPosition().x, player.getPosition().y
						+ Y_OFFSET, 0), 2f * delta);
		if (cam.position.x < 16)
			cam.position.x = 16;
		else if (cam.position.x > 48) {
			cam.position.x = 48;
		}
		if (cam.position.y < 12)
			cam.position.y = 12;
		cam.update();
	}

	public void update(float delta) {
		player.update(delta);
		updateCollision(delta);
		player.movementUpdate();
		updateCamera(delta);
	}

	public TiledMap getMap() {
		return map;
	}

}
