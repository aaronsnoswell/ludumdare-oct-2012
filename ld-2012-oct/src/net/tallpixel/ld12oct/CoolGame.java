package net.tallpixel.ld12oct;

import java.util.Iterator;

import net.tallpixel.ld12oct.utils.GameEventEmitter;
import net.tallpixel.ld12oct.utils.GameEventListener;
import net.tallpixel.ld12oct.utils.Lg;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class CoolGame implements ApplicationListener {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	
	Texture dropImage;
	Sound dropSound;
	Music rainMusic;
	
	Defender defender;
	AlienSpawner as;
	BulletSpawner bs;

	Sound shoot_sound;

	@Override
	public void create() {
		float w = Gdx.graphics.getWidth(),
			  h = Gdx.graphics.getHeight(),
			  w2 = w / 2,
			  h2 = h / 2;
		
		// load the drop sound effect and the rain background "music"
		//dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
		//rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));

		// start the playback of the background music immediately
		//rainMusic.setLooping(true);
		//rainMusic.play();
		
		shoot_sound = Gdx.audio.newSound(Gdx.files.internal("data/audio/shoot.wav"));
		
		//camera = new OrthographicCamera(1, h/w);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		batch = new SpriteBatch();

		// Craete the defender sprite
		defender = new Defender(800 / 2 - 48/2 / 2, 480 - 20 - 48);
		
		as = new AlienSpawner();
		as.loadFormation(Gdx.files.internal("data/formations/default.txt"));
		bs = new BulletSpawner();
		
		GameEventEmitter.addEventListener("fire_defender_weapon", new GameEventListener() {
			@Override
			public void run() {
				bs.spawnBullet(defender.getGunX(), defender.getGunY());
				shoot_sound.play();
			}
		});
	}
	
	@Override
	public void render() {
		float dt = Gdx.graphics.getDeltaTime();
		
		input();
		draw();
		update(dt);
	}
	
	private void input() {
		if(Gdx.input.isTouched()) {
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			//spawnAlien((int) touchPos.x, (int) touchPos.y);
		}
		
		/*
		if(Gdx.input.isKeyPressed(Keys.LEFT)) {
			bucket.x -= 200 * Gdx.graphics.getDeltaTime();
		}
		if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
			bucket.x += 200 * Gdx.graphics.getDeltaTime();
		}
		*/
		
		if(Gdx.input.isKeyPressed(Keys.ESCAPE)) {
			Gdx.app.exit();
		}
	}
	
	private void draw() {
		Gdx.gl.glClearColor(0.01f, 0.01f, 0.01f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		camera.update();

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		defender.draw(batch);
		bs.draw(batch);
		as.draw(batch);
		batch.end();
	}
	
	private void update(float dt) {
		as.update(dt);
		defender.update(as, dt);
		bs.update(dt);
	}

	@Override
	public void dispose() {
		// dispose of all the native resources
		//dropSound.dispose();
		//rainMusic.dispose();
		batch.dispose();
		as.dispose();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
	
}


