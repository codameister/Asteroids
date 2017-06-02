package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class Asteroid extends AnimatedSprite {

	public float rotationoriginx = 1;
	public float rotationoriginy = 1;
	private static int tilewidth = 38;
	private static int tileheight = 37;
	public static int rows = 2;
	public static int columns = 2;
	public float angle = 0;
	public int radius = 10;
	public int offset = 0;

	public Asteroid(Sprite sprite) {
		super(sprite, tilewidth, tileheight, rows, columns);
				  
			velocity.x = (float)(Math.random() * 400)-200;
			velocity.y = (float)(Math.random() * 400)-200;	
			//velocity.x = 0;
			//velocity.y = 0;
	}
	
	public void draw(SpriteBatch spritebatch) {
		super.draw(spritebatch, rotationoriginx, rotationoriginy, rows, columns, angle);
		
	}
	
	
	@Override
	void deccelerate() {}
	
}
