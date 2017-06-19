package com.mygdx.game;

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

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	private OrthographicCamera camera;
	private Texture background;
	private Sprite spaceshipSprite;
	public SpaceShip spaceshipAnimated;
	FillViewport viewport;
	AsteroidManager asteroidmanager;
	public boolean gameisover = false;
	ShotManager shotmanager;
	ExplosionManager explosionmanager;
	BitmapFont font;
	
	
	@Override
	public void create () {
		
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false);
		camera.update();

		viewport = new FillViewport(800,400, camera);   
	    viewport.apply(true);
		
		background = new Texture(Gdx.files.internal("bg800500.png"));
		background.setWrap(Texture.TextureWrap.ClampToEdge, Texture.TextureWrap.ClampToEdge);
		Texture spaceshipTexture = new Texture(Gdx.files.internal("jetfighter.png"));
		spaceshipSprite = new Sprite(spaceshipTexture);
		spaceshipAnimated = new SpaceShip(spaceshipSprite);
		spaceshipAnimated.setPosition(50, 50);
		asteroidmanager = new AsteroidManager();
		explosionmanager = new ExplosionManager();
		shotmanager = new ShotManager(explosionmanager);
		font = new BitmapFont();	
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
		
		if(gameisover){
			//font.setFixedWidthGlyphs(glyphs);;
			font.draw(batch, "PLAYER HIT press Enter to continue. Left ctrl to Fire", 200, 200);
		}
		
		batch.end();
		handleinput();
		
		if (!gameisover){
			spaceshipAnimated.move();
			asteroidmanager.update();
			shotmanager.update();
			shotmanager.asteroidHit(asteroidmanager.asteroids);
			asteroidmanager.blackholetouches(explosionmanager);
		}
		
		if (asteroidmanager.asteroidTouches(asteroidmanager.asteroids, spaceshipAnimated)){
			gameisover = true;
			explosionmanager.spawnexplosion(spaceshipAnimated.sprite.getX(), spaceshipAnimated.sprite.getY());
		}
		
		if (spaceshipAnimated.blackhole()){
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
			if(Gdx.input.isKeyPressed(Input.Keys.ENTER)){
				asteroidmanager.dispose();
				shotmanager.dispose();
				gameisover = false;
				spaceshipAnimated.setPosition(50, 50);
				spaceshipAnimated.velocity.x = 0;
				spaceshipAnimated.velocity.y = 0;
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
