package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Explosion extends AnimatedSprite{

	public static int tilewidth = 786/3;
	public static int tileheight = 512/4;
	public static int rows = 4;
	public static int columns = 3;
	private float rotationoriginx = 0;
	private float rotationoriginy = 0;
	private float angle = 0;
	
	
	public Explosion(Sprite sprite)
	{
		super(sprite, tilewidth, tileheight, rows , columns);
	}

	@Override
		void deccelerate() {
	}
	
	public void draw(SpriteBatch batch)
	{
		super.draw(batch, rotationoriginx , rotationoriginy , rows, columns, angle );
	}
	
}
