package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;



public class Missile extends AnimatedSprite {
	

//private double angle;
private float speed = 800;
public float rotationoriginx = 5;
public float rotationoriginy = 50;
private static int tilewidth = 10;
private static int tileheight = 50;
public static int rows = 4;
public static int columns = 2;
private double radians;
public float angle;
public long missiletimer;
public int offset = 0;

	public Missile(Sprite sprite, float angle){
		super(sprite, tilewidth, tileheight, rows, columns);
			missiletimer = System.currentTimeMillis();
			this.angle = angle;
			accelerate();
	}
	
	public void accelerate(){
		radians = Math.toRadians(angle);
		super.accelerate(radians, speed);
	}
	
	public void draw(SpriteBatch spritebatch) {
		super.draw(spritebatch, rotationoriginx, rotationoriginy, rows, columns, angle);
		
	}
	
	@Override
	void deccelerate() {}
	
}


