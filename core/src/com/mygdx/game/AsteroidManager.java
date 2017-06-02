package com.mygdx.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;


public class AsteroidManager {
	
	public List<Asteroid> asteroids = new ArrayList<Asteroid>();
	private Texture asteroidtexture;
	private Sprite asteroidSprite; 
	private Asteroid asteroidAnimated;
	public float timeSinceLastAsteroid = 0;
	private double waittime;
	
	public AsteroidManager()
	{
		spawnasteroid();
	}
	
	
	public void draw(SpriteBatch batch)
	{
		for(Asteroid asteroid : asteroids)
		{
			asteroid.draw(batch);
		}	
	}
	
private void spawncheck()
	{
		if (timeSinceLastAsteroid > waittime)
		{
			spawnasteroid();
			timeSinceLastAsteroid = 0;

		}
	}
public void spawnasteroid() {
		
		{
		asteroidtexture = new Texture(Gdx.files.internal("asteroidsmall76by74.png"));
		asteroidSprite = new Sprite(asteroidtexture);
		asteroidAnimated = new Asteroid(asteroidSprite);
		asteroidAnimated.setPosition((float) (Math.random()*200), -20);
		//asteroidAnimated.setPosition((float) (Math.random()*200), 200);

		
		asteroids.add(asteroidAnimated);
		waittime = Math.random()*10;
		}
	}

public void update()
		{
			Iterator<Asteroid> i = asteroids.iterator();
			while(i.hasNext())
			{
				Asteroid asteroid = i.next();
				asteroid.move();
			}
			timeSinceLastAsteroid += Gdx.graphics.getDeltaTime();
			spawncheck();
		}

public boolean asteroidTouches(List<Asteroid> asteroids, SpaceShip spaceship) {
	Iterator<Asteroid> i = asteroids.iterator();
	while(i.hasNext())
	{
		Asteroid asteroid = i.next();
		
		if(Intersector.overlapConvexPolygons(asteroid.getBoundingBox(asteroid.angle, Asteroid.rows, Asteroid.columns, asteroid.rotationoriginx, asteroid.rotationoriginy, asteroid.offset), spaceship.getBoundingBox(spaceship.angle, SpaceShip.rows, SpaceShip.columns, spaceship.rotationoriginx, spaceship.rotationoriginy, spaceship.offset)))
		{			
			return true;			
		}		
	}	
	return false;
}

public void dispose() 
{
	asteroids.clear();
}
}