package net.tallpixel.ld12oct;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Bullet extends Sprite {
	float v = 60;
	
	public Bullet(float x, float y) {
		super(new Texture(Gdx.files.internal("data/bullet.png")));
		this.setX(x);
		this.setY(y);
	}
	
	public void update(float dt) {
		setY(getY() - v * dt);
	}
	
}
