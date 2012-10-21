package net.tallpixel.ld12oct;

import net.tallpixel.ld12oct.utils.GameEventEmitter;
import net.tallpixel.ld12oct.utils.Lg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.TimeUtils;

public class Defender extends Sprite {
	float v = 0;
	float max_speed = 6;
	
	double last_shot = 0,
		  shot_cooldown = 700;
	
	public Defender(int x, int y) {
		super(new Texture(Gdx.files.internal("data/defender.png")));
		setSize(48, 48);
		setX(x);
		setY(y);
		setColor(Color.RED);
	}
	
	public void update(AlienSpawner as, float dt) {
		// Find the closest alien
		Alien closest = null;
		float closest_dy = -1, closest_dx = -1;
		
		for(Alien alien : as.aliens) {
			float dy = 480 - alien.getY();
			float dx = alien.getMidX() - getMidX();
			
			if(Math.abs(dy) <= Math.abs(closest_dy) || closest_dy == -1) {
				closest_dy = dy;
				
				if(Math.abs(dx) < Math.abs(closest_dx) || closest_dx == -1) {
					closest_dx = dx;
					closest = alien;
				}
			}
			alien.unhighlight();
		}
		
		if(closest != null) {
			closest.highlight();
			
			float d = closest_dx;
			float k = 0.05f;
			float f = k*d;
			float mass = 10.0f;
			float accel = f / mass;
			v += accel;
			
			if(v > 0) {
				v = Math.min(v, max_speed);
			} else {
				v = Math.max(v, -max_speed);
			}
			
			double time = TimeUtils.millis();
			if(time - last_shot >= shot_cooldown) {
				if(Math.abs(closest_dx) < (getWidth()/2 - closest.getWidth()/2)) {
					GameEventEmitter.trigger("fire_defender_weapon");
					last_shot = time;
				}
			}
		}
		
		v *= 0.8;
		setX(getX() + v);
		
		// Make sure the defender stays within the screen bounds
		if(getX() < 0) setX(0);
		if(getX() > 800 - getWidth()) setX(800 - getWidth());
	}
	
	public float getGunX() {
		return getMidX();
	}
	
	public float getGunY() {
		return getY();
	}
	
	public float getMidX() {
		return getX() + getWidth()/2;
	}
	
	public float getMidY() {
		return getY() + getHeight()/2;
	}
}
