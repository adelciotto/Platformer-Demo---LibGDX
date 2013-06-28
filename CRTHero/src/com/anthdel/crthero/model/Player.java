package com.anthdel.crthero.model;

import com.anthdel.crthero.controller.PlayerController;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player extends AbstractEntity {
	public enum State {
		IDLE, WALKING, JUMPING, SHOOTING, SLIDING
	}

	public static final float FRAME_DURATION = 0.06f;

	private PlayerController controller;
	private boolean facingLeft = false;
	private State currentState = State.IDLE;
	private boolean isGrounded = false;
	private float gravity;

	public Player(TextureRegion region, Vector2 position) {
		super(region, position);
		width *= 1.5f;
		height *= 2f;
		controller = new PlayerController(this);
	}
	
	@Override
	public void update(float delta) {
		controller.processInput();
		if (isGrounded && currentState.equals(State.JUMPING))
			currentState = State.IDLE;
		
		velocity.y += gravity;
		super.update(delta);
	}
	
	public void movementUpdate() {
		velocity.x = MathUtils.clamp(velocity.x, -PlayerController.ACCELERATION, PlayerController.ACCELERATION);
		if (Math.abs(velocity.x) < 1) {
			velocity.x = 0;
			currentState = State.IDLE;
		}
		velocity.x *= PlayerController.DAMP;
	}
	
	public void setGravity(float gravity) {
		this.gravity = gravity;
	}
	
	public State getState() {
		return currentState;
	}
	
	public void setState(State newState) {
		currentState = newState;
	}
	
	public boolean isFacingLeft() {
		return facingLeft;
	}
	
	public boolean isGrounded() {
		return isGrounded;
	}
	
	public void setFacingLeft(boolean facingLeft) {
		this.facingLeft = facingLeft;
	}
	
	public void setGrounded(boolean isGrounded) {
		this.isGrounded = isGrounded;
	}
	
	public PlayerController getController() {
		return controller;
	}

}
