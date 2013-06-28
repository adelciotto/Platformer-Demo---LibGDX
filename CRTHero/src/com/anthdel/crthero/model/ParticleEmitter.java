package com.anthdel.crthero.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

public class ParticleEmitter {
	private Vector2 location;
	private float minVel, maxVel;
	private float minLife, maxLife;
	private float minScale, maxScale;
	private int maxParticles;
	private Array<Particle> particles;

	public ParticleEmitter(Vector2 position, int maxParticles, float minLife,
			float maxLife) {
		this.location = position;
		this.maxParticles = maxParticles;
		this.minLife = minLife;
		this.maxLife = maxLife;
		particles = new Array<Particle>();
		addParticle();
	}

	public void setVelRange(float min, float max) {
		minVel = min;
		maxVel = max;
	}

	public void setScaleRange(float min, float max) {
		minScale = min;
		maxScale = max;
	}

	public void addParticle() {
		for (int i = 0; i < maxParticles; i++) {
			if (particles.size < maxParticles) {
				Particle p = new Particle(Resources.circleParticle);
				initParticle(p);
				particles.add(p);
			}
		}
	}

	private void initParticle(Particle p) {
		float vel = FastMath.randomFloat(minVel, maxVel);
		float life = FastMath.randomFloat(minLife, maxLife);
		float accel = -vel / life;
		float rotation = 0;
		float scale = FastMath.randomFloat(minScale, maxScale);
		Vector2 pos = new Vector2(location);
		Vector2 velocity = pickRandomDirection().scl(vel);
		Vector2 acceleration = pickRandomDirection().scl(accel);

		p.initialise(pos, velocity, acceleration, scale, scale, rotation,
				Color.GREEN, life);
	}

	private static Vector2 pickRandomDirection() {
		float angle = FastMath.randomFloat((float) Math.PI * 2, 0);
		return new Vector2(FastMath.sin(angle), FastMath.cos(angle));
	}

	public void update(float delta) {
		addParticle();
		for (int i = 0; i < particles.size; i++) {
			Particle p = particles.get(i);
			if (p.isAlive) {
				p.update(delta);
				if (!p.isAlive) {
					particles.removeValue(p, true);
				}
			}
		}
	}

	public void render(SpriteBatch batch) {
		for (Particle p : particles) {
			if (!p.isAlive)
				continue;

			p.render(batch);
		}
	}
	
	public void setLocation(Vector2 location) {
		this.location = location;
	}

}
