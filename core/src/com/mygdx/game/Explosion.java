package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Explosion extends AnimatedSprite{

	public static int tilewidth = 512/4;
	public static int tileheight = 512/4;
	public static int rows = 4;
	public static int columns = 4;
	private float rotationoriginx = 0;
	private float rotationoriginy = 0;
	private float angle = 0;
	public boolean looping = false;
	private float xposoffset = 30;
	private float yposoffset = 30;
	
	public Explosion(Sprite sprite)
	{
		super(sprite, tilewidth, tileheight, rows , columns);
	}

	@Override
		void deccelerate() {
	}
	
	public void draw(SpriteBatch batch)
	{
		super.draw(batch, rotationoriginx , rotationoriginy , rows, columns, angle, looping, xposoffset, yposoffset);
	}
	
}
