package com.mygdx.game;

import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class CollisionManager {
	
	SpaceShip spaceship;
	List<Asteroid> asteroids;
	
	public CollisionManager(List<Asteroid> asteroids, SpaceShip spaceship)
	{
		this.spaceship = spaceship;
		this.asteroids = asteroids;	
	}
	
//	public void collisions()
//	{
//		if (asteroidTouches(this.asteroids, this.spaceship))
//		{
//			.gameover = true;
//		}
//
//	}
	
	
	
/*	private boolean asteroidTouches(List<Asteroid> asteroids, Rectangle boundingBox) {
		Iterator<Asteroid> i = asteroids.iterator();
		while(i.hasNext())
		{
			Asteroid asteroid = i.next();
			if(asteroid.getBoundingBox().overlaps(boundingBox))
			{
				i.remove();
				return true;
			}
		}
		
		return false;*/
	}


