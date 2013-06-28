package com.anthdel.crthero.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Particle extends WorldObject {
	private float xScale, yScale;
	private float timeToLive, timeLived = 0.0f;
	public boolean isAlive;
	private int poolID;

	public Particle(TextureRegion region) {
		super(region);
		color = Color.GREEN;
	}

	public void initialise(Vector2 position, Vector2 velocity,
			Vector2 acceleration, float xScale, float yScale, float rotation,
			Color color, float timeToLive) {
		this.position = position;
		this.acceleration = acceleration;
		this.xScale = xScale;
		this.yScale = yScale;
		this.rotation = rotation;
		this.color = color;
		this.timeToLive = timeToLive;
		isAlive = true;
	}

	@Override
	public void update(float delta) {
		isAlive = timeLived < timeToLive && color.a > 0;
		super.update(delta);
		timeLived += delta;
	}

	@Override
	public void render(SpriteBatch batch) {
		float normalizedLifeTime = timeLived / timeToLive;
		float yScale = this.yScale * (.75f + .25f * normalizedLifeTime);
		float xScale = this.xScale * (.75f + .25f * normalizedLifeTime);
		float alpha = 4 * normalizedLifeTime * (1 - normalizedLifeTime);
		
		
		//color.mul(alpha);
		batch.setColor(color);
		batch.draw(region, position.x, position.y, origin.x, origin.y,
				region.getRegionWidth(), region.getRegionHeight(), xScale,
				yScale, rotation);
	}
}
