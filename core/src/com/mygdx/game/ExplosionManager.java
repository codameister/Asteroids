package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ExplosionManager {

	private Texture explosionTexture;
	private Sprite explosionSprite;
	private Explosion explosionAnimated;
	private List<Explosion> explosions;
	
	public ExplosionManager()
	{
		explosions = new ArrayList<Explosion>();
	}
	
	 public void spawnexplosion(float xpos, float ypos)
	 {
		    explosionTexture = new Texture(Gdx.files.internal("explosion1.png"));
			explosionSprite = new Sprite(explosionTexture);
			explosionAnimated = new Explosion(explosionSprite);
			explosionAnimated.setPosition(xpos, ypos);	
			explosions.add(explosionAnimated);
	 }
	 
	 public void draw(SpriteBatch batch)
	{
		for(Explosion explosion : explosions)
		{
			explosion.draw(batch);
		}	
	}
}
