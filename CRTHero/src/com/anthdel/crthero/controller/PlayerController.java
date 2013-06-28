package com.anthdel.crthero.controller;

import java.util.HashMap;
import java.util.Map;

import com.anthdel.crthero.model.Level;
import com.anthdel.crthero.model.Player;
import com.anthdel.crthero.model.Player.State;
import com.badlogic.gdx.math.MathUtils;

public class PlayerController {
	enum Keys {
		MOVE_LEFT, MOVE_RIGHT, JUMP, FIRE
	}
	
	private static final float LONG_JUMP_PRESS = 200l;
	public static final float ACCELERATION = 10f;
	private static final float MAX_JUMP_SPEED = 25f;
	public static final float DAMP = 0.87f;

	private static Map<Keys, Boolean> keys = new HashMap<PlayerController.Keys, Boolean>();
	static {
		keys.put(Keys.MOVE_LEFT, false);
		keys.put(Keys.MOVE_RIGHT, false);
		keys.put(Keys.JUMP, false);
		keys.put(Keys.FIRE, false);
	}
	
	private Player player;
	private long jumpingPressedTime;
	private boolean jumpingPressed;
	
	public PlayerController(Player player) {
		this.player = player;
	}

	/*
	 * Key Pressed methods, used in the input processor's input methods
	 */
	public void moveLeftPressed() {
		keys.get(keys.put(Keys.MOVE_LEFT, true));
	}

	public void moveRightPressed() {
		keys.get(keys.put(Keys.MOVE_RIGHT, true));
	}

	public void jumpPressed() {
		keys.get(keys.put(Keys.JUMP, true));
	}

	public void firePressed() {
		keys.get(keys.put(Keys.FIRE, true));
	}

	/*
	 * Key Releases
	 */
	public void moveLeftReleased() {
		keys.get(keys.put(Keys.MOVE_LEFT, false));
	}

	public void moveRightReleased() {
		keys.get(keys.put(Keys.MOVE_RIGHT, false));
	}

	public void jumpReleased() {
		keys.get(keys.put(Keys.JUMP, false));
	}

	public void fireReleased() {
		keys.get(keys.put(Keys.FIRE, false));
	}
	
	public boolean processInput() {
		if (keys.get(Keys.JUMP)) {
			if (!player.getState().equals(State.JUMPING)) {
				jumpingPressed = true;
				jumpingPressedTime = System.currentTimeMillis();
				player.setState(State.JUMPING);
				player.getVelocity().y += MAX_JUMP_SPEED;
				player.setGrounded(false);
			} else {
				if (jumpingPressed && ((System.currentTimeMillis() - jumpingPressedTime) >= LONG_JUMP_PRESS )) 
					jumpingPressed = false;
				else {
					if (jumpingPressed)
						player.getVelocity().y = MAX_JUMP_SPEED;
				}
			}
		}
		
		if (keys.get(Keys.MOVE_LEFT)) {
			player.setFacingLeft(true);
			if (!player.getState().equals(State.JUMPING))
				player.setState(State.WALKING);
			player.getVelocity().x = -ACCELERATION;
		}
		
		else if (keys.get(Keys.MOVE_RIGHT)) {
			player.setFacingLeft(false);
			if (!player.getState().equals(State.JUMPING))
				player.setState(State.WALKING);
			player.getVelocity().x = ACCELERATION;
		}
		/*
		else {
			if (!player.getState().equals(State.JUMPING))
				player.setState(State.IDLE);
			player.getAcceleration().x = 0;
		}
		*/
		return false;
	}
	
	
}
