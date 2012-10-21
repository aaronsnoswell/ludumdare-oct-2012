package net.tallpixel.ld12oct;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Alien extends Sprite {
	float v;
	
	public Alien(AlienType type) {
		super(type.tex);
		this.setColor(type.col);
		this.v = type.v;
	}
	
	Color old_col;
	
	public void highlight() {
		old_col = this.getColor();
		this.setColor(Color.PINK);
	}
	
	public void unhighlight() {
		if(old_col != null) this.setColor(old_col);
	}
	
	public float getMidX() {
		return getX() + getWidth()/2;
	}
	
	public float getMidY() {
		return getY() + getHeight()/2;
	}
	
}
