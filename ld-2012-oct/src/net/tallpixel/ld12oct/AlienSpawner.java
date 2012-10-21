package net.tallpixel.ld12oct;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class AlienSpawner {
	private Array<AlienType> alien_types;
	public Array<Alien> aliens;
	
	private int alien_size = 35,
				alien_speed = 50;
	
	public AlienSpawner() {
		alien_types = new Array<AlienType>();
		aliens = new Array<Alien>();
		
		alien_types.add(new AlienType(
			new Texture(Gdx.files.internal("data/alien.png")),
			new Color(224/255.0f, 27/255.0f, 106/255.0f, 1),
			alien_speed
		));
		alien_types.add(new AlienType(
			new Texture(Gdx.files.internal("data/ufo.png")),
			new Color(27/255.0f, 80/255.0f, 244/255.0f, 1),
			alien_speed
		));
		alien_types.add(new AlienType(
			new Texture(Gdx.files.internal("data/speedy.png")),
			new Color(103/255.0f, 244/255.0f, 27/255.0f, 1),
			alien_speed
		));
		alien_types.add(new AlienType(
			new Texture(Gdx.files.internal("data/brainy.png")),
			new Color(255/255.0f, 255/255.0f, 0/255.0f, 1),
			alien_speed
		));
	}
	
	public void spawnAlien(int type, int x, int y) {
		Alien alien = new Alien(alien_types.get(type));
		alien.setX(x);
		alien.setY(y);
		alien.setSize(alien_size, alien_size);
		aliens.add(alien);
	}
	
	public void dispose() {
		for(AlienType t : alien_types) {
			t.dispose();
		}
	}
	
	public void draw(SpriteBatch batch) {
		for(Alien alien : aliens) {
			alien.draw(batch);
		}
	}
	
	/**
	 * Updates the alien cohort
	 * @param dt
	 */
	public void update(float dt) {
		Iterator<Alien> iter = aliens.iterator();
		while(iter.hasNext()) {
			Alien alien = iter.next();
			
			alien.setY(alien.getY() + alien.v * dt);
			if(alien.getY() > 480 - alien_size) iter.remove();
		}
	}

	public void loadFormation(FileHandle formation_file) {
		Array<String> lines = new Array<String>(formation_file.readString().split("\n"));
		aliens.clear();
		
		int length = -1, alien_dxy = alien_size + 10;
		for(int i=0; i<lines.size; i++) {
			String line = lines.get(i);
			
			if(length < line.length() || length == -1) {
				length = line.length();
			}
			
			for(int j=0; j<line.length(); j++) {
				char c = line.charAt(j);
				
				int type = 0;
				if(c == 'a') type = 0;
				if(c == 'b') type = 1;
				if(c == 'c') type = 2;
				if(c == 'd') type = 3;
				
				spawnAlien(type, j * alien_dxy, -i * alien_dxy);
			}
		}
		
		// Center the cohort
		int padding_x_left = (800 - length*alien_dxy) / 2;
		for(Alien a : aliens) {
			a.setX(a.getX() + padding_x_left);
		}
	}
	
}
