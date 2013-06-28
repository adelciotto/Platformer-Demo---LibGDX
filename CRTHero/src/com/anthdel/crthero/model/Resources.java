package com.anthdel.crthero.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class Resources {
	private static TextureAtlas spritesAtlas;
	public static BitmapFont gameFont;
	public static TextureRegion playerIdleRight;
	public static TextureRegion playerIdleLeft;
	public static TextureRegion playerJumpRight;
	public static TextureRegion playerJumpLeft;
	public static TextureRegion playerFallRight;
	public static TextureRegion playerFallLeft;
	public static Animation walkRightAnimation;
	public static Animation walkLeftAnimation;
	public static TiledMap demoMap;

	public static void load() {
		spritesAtlas = new TextureAtlas(Gdx.files.internal("data/sprites/player.pack"));
		gameFont = new BitmapFont(Gdx.files.internal("data/fonts/gameFont.fnt"),
				Gdx.files.internal("data/fonts/gameFont_0.png"), false);
		gameFont.scale(-0.25f);
		gameFont.setColor(0,1, 0, 1f);
		
		//Load Sprite Textures
		playerIdleLeft = spritesAtlas.findRegion("bob-00");
		playerIdleRight = new TextureRegion(playerIdleLeft);
		playerIdleRight.flip(true, false);
		
		playerJumpLeft = spritesAtlas.findRegion("bob-up");
		playerJumpRight = new TextureRegion(playerJumpLeft);
		playerJumpRight.flip(true, false);
		
		playerFallLeft = spritesAtlas.findRegion("bob-down");
		playerFallRight = new TextureRegion(playerFallLeft);
		playerFallRight.flip(true, false);
		
		TextureRegion[] walkLeftFrames = new TextureRegion[6];
		for (int i = 0; i < 6; i++)
			walkLeftFrames[i] = spritesAtlas.findRegion("bob-0" + i);
		walkLeftAnimation = new Animation(Player.FRAME_DURATION, walkLeftFrames);
		
		TextureRegion[] walkRightFrames = new TextureRegion[6];
		for (int i = 0; i < 6; i++) {
			walkRightFrames[i] = new TextureRegion(walkLeftFrames[i]);
			walkRightFrames[i].flip(true, false);
		}
		walkRightAnimation = new Animation(Player.FRAME_DURATION, walkRightFrames);
		
		demoMap = new TmxMapLoader().load("data/sprites/DemoMap.tmx");
	}
	
	public static void dispose() {
		spritesAtlas.dispose();
		gameFont.dispose();
		demoMap.dispose();
	}

}