package com.mygdx.game;

import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SpaceShip extends AnimatedSprite {

	protected final float rotatewait = 0.005f;
	protected double radians;
	protected float shipacceleration = 10;
	protected float rotationoriginx = 40;
	protected float rotationoriginy = 50;
	private static int tilewidth = 80;
	private static int tileheight = 100;
	public static int rows = 2;
	public static int columns = 2;
	public float angle;
	public int offset = 20;
	public boolean looping = true;
	private float xposoffset = 0;
	private float yposoffset = 0;
	
	

	public void draw(SpriteBatch spritebatch) {
		super.draw(spritebatch, rotationoriginx, rotationoriginy, rows, columns, angle, looping, xposoffset, yposoffset);
	}

	public SpaceShip(Sprite sprite) {
		super(sprite, tilewidth, tileheight, rows, columns);
		angle = 0;
		
	}

	private boolean canrotate() {
		return timeSinceLastRotate > rotatewait;
	}

	public void rotateright() {
		if (canrotate())
		{
			if (angle == 355)
				{
					angle = 0;
				}
			else
				{
					angle += 5;
				}
		
		timeSinceLastRotate = 0f; 
		radians = Math.toRadians(angle);
		
	
		}	
	}

	public void rotateleft() {
		if (canrotate())
		{
			if (angle == 0)
				{
					angle = 355;
				}
			else
				{
					angle -= 5;
				}
		
			timeSinceLastRotate = 0f; 
			radians = Math.toRadians(angle);
		}	
	}

	public void accelerate() {
		super.accelerate(radians, shipacceleration);
	}

	@Override
	public void deccelerate() {
		if (velocity.x > 0)
		{
			velocity.x = velocity.x - 1;
				if (velocity.x < 0)
				{
					velocity.x = 0;
				}
				
		}
		
		if (velocity.x < 0)
		{
			velocity.x = velocity.x + 1;
				if (velocity.x > 0)
				{
					velocity.x = 0;
				}
				
		}
	
		if (velocity.y > 0)
		{
			velocity.y = velocity.y - 1;
				if (velocity.y < 0)
				{
					velocity.y= 0;
				}
				
		}
		if (velocity.y < 0)
		{
			velocity.y = velocity.y + 1;
				if (velocity.y > 0)
				{
					velocity.y = 0;
				}
				
		}
	}


	
	
}
