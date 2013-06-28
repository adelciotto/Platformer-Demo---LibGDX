package com.anthdel.crthero.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

public class GameWorld {
	public static final int SCREEN_WIDTH = Gdx.graphics.getWidth();
	public static final int SCREEN_HEIGHT = Gdx.graphics.getHeight();
	public static final float VIEWPORT_WIDTH = 32;
	public static final float VIEWPORT_HEIGHT = 24;
	public static final float UNIT_SCALE = 1/32f;

	private Level level;
	private Player player;
	private OrthographicCamera cam;
	
	public Pool<Rectangle> rectPool = new Pool<Rectangle>() {
		@Override
		protected Rectangle newObject() {
			return new Rectangle();
		}
	};
	
	public Array<Rectangle> tiles = new Array<Rectangle>();

	public GameWorld() {
		cam = new OrthographicCamera();
		cam.setToOrtho(false, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
		cam.position.set(VIEWPORT_WIDTH / 2f, VIEWPORT_HEIGHT / 2f, 0);
		cam.update();
		player = new Player(Resources.playerIdleRight, new Vector2(32, 16));
		level = new Level(1, Resources.demoMap, this);
	}
	
	public void getTiles(int startX, int endX, int startY, int endY, Array<Rectangle> tiles) {
		rectPool.freeAll(tiles);
		tiles.clear();
		TiledMapTileLayer layer = (TiledMapTileLayer) level.getMap().getLayers().get(2);
		for (int y = startY; y <= endY; y++) {
			for (int x = startX; x <= endX; x++) {		
				Cell cell = layer.getCell(x, y);
				if (cell != null) {
					Rectangle rect = rectPool.obtain();
					rect.set(x, y, 1, 1);
					tiles.add(rect);
				}
			}
		}
	}

	public void update(float delta) {
		level.update(delta);
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public OrthographicCamera getCam() {
		return cam;
	}
	
	public Level getLevel() {
		return level;
	}

}
