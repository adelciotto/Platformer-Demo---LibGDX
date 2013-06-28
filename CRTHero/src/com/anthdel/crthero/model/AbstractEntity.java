package com.anthdel.crthero.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class AbstractEntity implements Entity {
	protected TextureRegion region;
	protected Vector2 position;
	protected Rectangle bounds;
	protected Vector2 velocity = new Vector2();
	protected float stateTime = 0.0f;
	protected Color color;
	protected float width, height;
	
	public AbstractEntity(TextureRegion region, Vector2 position) {
		this.region = region;
		this.position = position;
		width = 1 / 32f * region.getRegionWidth();
		height = 1 / 32f * region.getRegionHeight();
		bounds = new Rectangle(position.x, position.y, width, height);
	}

	@Override
	public Vector2 getPosition() {
		return position;
	}

	@Override
	public Rectangle getBounds() {
		return bounds;
	}

	@Override
	public Vector2 getVelocity() {
		return velocity;
	}

	@Override
	public Color getColor() {
		return color;
	}
	
	@Override
	public void update(float delta) {
		stateTime += delta;
	}

	@Override
	public void render(SpriteBatch batch, TextureRegion region) {
		batch.draw(region, position.x, position.y, width, height);
	}

	@Override
	public float getStateTime() {
		return stateTime;
	}


}
