package net.tallpixel.ld12oct;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class AlienType {
	Texture tex;
	Color col;
	float v;
	
	public AlienType(Texture tex, Color col, float v) {
		this.tex = tex;
		this.col = col;
		this.v = v;
	}
	
	public void dispose() {
		tex.dispose();
	}
}
