package net.tallpixel.ld12oct;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class BulletSpawner {
	public Array<Bullet> bullets;
	
	private int bullet_size_w = 4, bullet_size_h = 8;
	private Texture bullet_texture;
	
	public BulletSpawner() {
		bullets = new Array<Bullet>();
		bullet_texture = new Texture(Gdx.files.internal("data/bullet.png"));
	}
	
	public void spawnBullet(float x, float y) {
		Bullet b = new Bullet(x, y);
		b.setSize(bullet_size_w, bullet_size_h);
		bullets.add(b);
	}
	
	public void dispose() {
		bullet_texture.dispose();
	}
	
	public void draw(SpriteBatch batch) {
		for(Bullet b : bullets) {
			b.draw(batch);
		}
	}
	
	/**
	 * Updates the alien cohort
	 * @param dt
	 */
	public void update(float dt) {
		Iterator<Bullet> iter = bullets.iterator();
		while(iter.hasNext()) {
			Bullet b = iter.next();
			
			b.update(dt);
			if(b.getY() > 480 - bullet_size_h) iter.remove();
		}
	}
	
}
