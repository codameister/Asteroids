package com.mygdx.game;

import java.util.Iterator;
import java.util.List;
import java.util.Timer;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	private OrthographicCamera camera;
	private Texture background;
	private Sprite spaceshipSprite;
	public SpaceShip spaceshipAnimated;
	FillViewport viewport;
	AsteroidManager asteroidmanager;
	CollisionManager collisionmanager;
	public boolean gameisover = false;
	ShotManager shotmanager;
	ExplosionManager explosionmanager;
	
	
	@Override
	public void create () {
		
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false);
		camera.update();

		
		viewport = new FillViewport(1024,768, camera);   
	    viewport.apply(true);
		
		
		background = new Texture(Gdx.files.internal("bg800400.png"));
		background.setWrap(Texture.TextureWrap.ClampToEdge, Texture.TextureWrap.ClampToEdge);
		Texture spaceshipTexture = new Texture(Gdx.files.internal("jetfighter.png"));
		spaceshipSprite = new Sprite(spaceshipTexture);
		spaceshipAnimated = new SpaceShip(spaceshipSprite);
		spaceshipAnimated.setPosition(200, 200);
		asteroidmanager = new AsteroidManager();
		explosionmanager = new ExplosionManager();
		shotmanager = new ShotManager(explosionmanager);
		
		
	}
	
	

	
	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(background, 0, 0);
		
		spaceshipAnimated.draw(batch);
		asteroidmanager.draw(batch);
		shotmanager.draw(batch);
		explosionmanager.draw(batch);
		
		if(gameisover)
		{
			BitmapFont font = new BitmapFont();
			//font.setFixedWidthGlyphs(glyphs);;
			font.draw(batch, "PLAYER HIT press Enter to continue", 200, 200);
		}
		
		batch.end();
		handleinput();
		
		
		if (!gameisover)
		{
		spaceshipAnimated.move();
		asteroidmanager.update();
		shotmanager.update();
		shotmanager.asteroidHit(asteroidmanager.asteroids);
		}
		
		if (asteroidmanager.asteroidTouches(asteroidmanager.asteroids, spaceshipAnimated))
		{
			
			gameisover = true;
			explosionmanager.spawnexplosion(spaceshipAnimated.sprite.getX(), spaceshipAnimated.sprite.getY());
		}
		
		
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		
	}
	private void handleinput()
	{
		if (gameisover)
		{
			if(Gdx.input.isKeyPressed(Input.Keys.ENTER))
			{
				asteroidmanager.dispose();
				shotmanager.dispose();
				gameisover = false;
			}
		}
		
		if (!gameisover)
		{
		if(Gdx.input.isKeyPressed(Input.Keys.UP))
		{
                spaceshipAnimated.accelerate();
                
		}
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
		{
                spaceshipAnimated.rotateright();
	
		}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
		{
                spaceshipAnimated.rotateleft();
	
		}
		if(Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT))
		{
                shotmanager.attack(spaceshipAnimated.angle, spaceshipAnimated.sprite.getX(), spaceshipAnimated.sprite.getY());
	
		}
		}
		
	}
	


	
}
