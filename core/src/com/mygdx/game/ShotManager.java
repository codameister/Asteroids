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

public class ShotManager {
	
	public List<Missile> missiles = new ArrayList<Missile>();
	public List<Explosion> explosions = new ArrayList<Explosion>();
	private Texture missiletexture;
	private Sprite missileSprite; 
	private Missile missileAnimated;
	private float waittime = 1;
	private float timesincelastshot;
	private boolean canattack = true;
	ExplosionManager explosionmanager;
	
	public ShotManager(ExplosionManager explosionmanager)
	{
		this.explosionmanager = explosionmanager;
		
	}
	
	public void attack(float angle, float xpos, float ypos) {
		if (canattack)
			{
			missiletexture = new Texture(Gdx.files.internal("missile.png"));
			missileSprite = new Sprite(missiletexture);
			missileAnimated = new Missile(missileSprite, angle);
			missileAnimated.setPosition(xpos+35, ypos);
			missiles.add(missileAnimated);
			canattack = false;
			}
		}

	public void draw(SpriteBatch batch)
	{
		for(Missile missile : missiles)
		{
			missile.draw(batch);
		}
	}

	public void update()
	{
		Iterator<Missile> i = missiles.iterator();
		while(i.hasNext())
		{
			Missile missile = i.next();
			missile.move();
		}
		timesincelastshot += Gdx.graphics.getDeltaTime();
		spawncheck();
	}

	private void spawncheck() {
		if (waittime < timesincelastshot)
		{
			canattack = true;
			timesincelastshot = 0;
		}
	}

	public void dispose() {
		missiles.clear();	
	}
	
	public void asteroidHit(List<Asteroid> asteroids) {
		Iterator<Asteroid> i = asteroids.iterator();
		Iterator<Missile> j = missiles.iterator();
		boolean removed;
		while(j.hasNext())
		{
			removed = false;
			Missile missile = j.next();
			while(i.hasNext())
			{
				Asteroid asteroid = i.next();
				if (!removed)
				{
					if(Intersector.overlapConvexPolygons(asteroid.getBoundingBox(asteroid.angle, Asteroid.rows, Asteroid.columns, asteroid.rotationoriginx, asteroid.rotationoriginy, asteroid.offset), missile.getBoundingBox(missile.angle, Missile.rows, Missile.columns, missile.rotationoriginx, missile.rotationoriginy, missile.offset)))
					{		
						explosionmanager.spawnexplosion(asteroid.sprite.getX(), asteroid.sprite.getY());
						i.remove();
						j.remove();
						removed = true;			
					}	
					
					if(1000 < (System.currentTimeMillis() - missile.missiletimer))
					{
						j.remove();
						removed = true;
					}
				}					
			}	
		}
	}
	}  
