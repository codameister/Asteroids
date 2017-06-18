package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class AnimatedSprite {

	protected Sprite sprite;
	private TextureRegion[] frames;
	private Animation<TextureRegion> animation;
	private float stateTime;
	private TextureRegion currentFrame;
	protected Vector2 velocity = new Vector2();
	protected float timeSinceLastRotate = 0; 
	private Vector2 gravitysource = new Vector2(480, 240);
	private int gravitymagnitude = 20000;
	private Vector2 position = new Vector2();
	private Vector2 gravdirection = new Vector2();
	private Vector2 gravvector = new Vector2(100,100);
	private float gravstrength;
	private Vector2 gravacc = new Vector2();

	
	public AnimatedSprite(Sprite sprite, int tilewidth, int tileheight, int rows , int columns)
	{
		{
			this.sprite = sprite;
			Texture texture = sprite.getTexture();
			TextureRegion[][] temp = TextureRegion.split(texture, tilewidth, tileheight);
			frames = new TextureRegion[rows*columns];
			int index = 0;
			for(int i = 0; i < rows; i++)
			{
				for(int j = 0; j < columns; j++)
				{
					frames[index++] = temp[i][j];
				}
			}
			animation = new Animation<TextureRegion>(0.1f, frames);
			stateTime = 0f;
		}
	}	
	
public void draw(SpriteBatch spriteBatch, float rotationoriginx, float rotationoriginy, int rows, int columns, float angle, boolean looping, float xposoffset, float yposoffset)
{
	stateTime += Gdx.graphics.getDeltaTime();
	timeSinceLastRotate += Gdx.graphics.getDeltaTime();	
	currentFrame = animation.getKeyFrame(stateTime, looping);
	spriteBatch.draw(currentFrame, sprite.getX()-xposoffset, sprite.getY()-yposoffset, rotationoriginx, rotationoriginy, sprite.getWidth()/columns, sprite.getHeight()/columns, 1, 1, angle);
}

public void accelerate(double radians, float acceleration) {
	
	velocity.x += (acceleration * Math.sin(-radians));
	velocity.y += (acceleration * Math.cos(radians));	
}

public void setPosition(float x, float y)
{
	sprite.setPosition(x, y);
}

public boolean blackhole()
{
	if (gravvector.len() < 30)
	{
		return true;
	}
return false;
}

public void move() {
	
	 position.x = (sprite.getX());
	 position.y = (sprite.getY());
			 
	 gravitycalc();	 
	 screenwrap();

	float xMovement = ((velocity.x * Gdx.graphics.getDeltaTime()) + (gravacc.x * Gdx.graphics.getDeltaTime()));
	float yMovement = ((velocity.y * Gdx.graphics.getDeltaTime()) + (gravacc.y * Gdx.graphics.getDeltaTime()));
	 
	sprite.setPosition(sprite.getX() + xMovement, sprite.getY() + yMovement);
	
	deccelerate();
}

abstract void deccelerate();

private void gravitycalc() {
	gravvector.x = gravitysource.x - position.x;
	 gravvector.y = gravitysource.y - position.y;
	 gravdirection = gravvector.cpy();
	 gravdirection = gravdirection.nor();
	 gravstrength = (float) (gravitymagnitude/(gravvector.len()));
	 gravacc = gravdirection.scl(gravstrength);
}


public void screenwrap() {
	if (position.x < -80)
	 {
		 sprite.setPosition(880, (sprite.getY()));
	 }
	 if (position.x > 880)
	 {
		 sprite.setPosition(-80, (sprite.getY()));
	 }
	 
	 if (position.y > 480)
	 {
		 sprite.setPosition(sprite.getX(), -80);
	 }
	 if (position.y < -80)
	 {	
		 sprite.setPosition(sprite.getX(), 480);
	 }
}

public Polygon getBoundingBox(float angle, int rows, int columns, float rotationoriginx, float rotationoriginy, int offset) {
	
	Polygon polygon = new Polygon(new float []{0, 0, (sprite.getWidth()/columns)-offset,0, (sprite.getWidth()/columns)-offset, (sprite.getHeight()/rows)-offset, 0, (sprite.getHeight()/rows)-offset});
	polygon.setPosition(sprite.getX()+(offset/2), sprite.getY()+(offset/2));
	polygon.setRotation(angle);
	polygon.setOrigin(rotationoriginx-(offset/2), rotationoriginy-(offset/2));
	return polygon;
}
}
