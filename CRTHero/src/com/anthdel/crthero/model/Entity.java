package com.anthdel.crthero.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Interface for all entities to implement Contains all public methods
 * 
 * @author Anthony Del Ciotto
 * 
 */
public interface Entity {
	/**
	 * Accessor methods
	 * 
	 * @return
	 */
	public Vector2 getPosition();

	public Rectangle getBounds();

	public Vector2 getVelocity();
	
	public float getStateTime();
	
	public Color getColor();

	/**
	 * Update the entitys logic and perform physics
	 * 
	 * @param delta
	 *            change in time since last frame
	 */
	public void update(float delta);

	/**
	 * Renders the entity
	 * 
	 * @param batch
	 * @param region
	 *            that is to be drawn current frame - used for animations and
	 *            state changes
	 */
	public void render(SpriteBatch batch, TextureRegion region);
}
