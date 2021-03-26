package game.objects;
// This class creates the player
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

import game.framework.GameObject;
import game.framework.ObjectId;
import game.window.Camera;
import game.window.Handler;

public class Player extends GameObject{

	private float width = 32, height = 64;
	private float gravity = 0.5f;
	private final float maxSpeed = 10;
	private Handler handler;
	private Camera camera;
	/** Constructor <br>
	 * 
	 * @param x
	 * @param y
	 * @param id
	 */
	public Player(float x, float y, ObjectId id, Handler handler, Camera camera) {
		super(x, y, id);
		this.handler = handler;
		this.camera = camera;
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		x += velX;
		y += velY;
		if(falling || jumping) {
			velY += gravity;
			if(velY > maxSpeed) {
				velY = maxSpeed;
			}
		
		}
		Collision(object);
	}
	
	private void Collision(LinkedList<GameObject> object) {
		//Top
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			if(tempObject.getId() == ObjectId.Block) {
				if(getBoundsTop().intersects(tempObject.getBounds())) {
					y = tempObject.getY() + 32;
					velY = 0;
				}
			}
		}
		//Bottom
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			if(tempObject.getId() == ObjectId.Block) {
				if(getBounds().intersects(tempObject.getBounds())) {
					y = tempObject.getY() - height;
					velY = 0;
					jumping = false;
					falling = false;
				}
				else {
					falling = true;
				}
			}
		}
		//Right
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			if(tempObject.getId() == ObjectId.Block) {
				if(getBoundsRight().intersects(tempObject.getBounds())) {
					x = tempObject.getX() - width;
				}
			}
		}
		//Left
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			if(tempObject.getId() == ObjectId.Block) {
				if(getBoundsLeft().intersects(tempObject.getBounds())) {
					x = tempObject.getX() + 35;
				}
			}
			else if(tempObject.getId() == ObjectId.flag) {
				//switch level
				if(getBounds().intersects(tempObject.getBounds())) {
					handler.switchLevel();
				}
			}
		} 
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect((int)x, (int)y, (int)width, (int)height);	
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.draw(getBounds());
		g2d.draw(getBoundsLeft());
		g2d.draw(getBoundsRight());
		g2d.draw(getBoundsTop());
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) ((int)x + (width/2) - ((width/2)/2)), (int) ((int)y + (height/2)), (int)width/2, (int)height/2);
	}
	public Rectangle getBoundsTop() {
		return new Rectangle((int) ((int)x + (width/2) - ((width/2)/2)), (int)y, (int)width/2, (int)height/2);
	}

	public Rectangle getBoundsRight() {
		return new Rectangle((int) ((int)x + width - 5), (int)y + 5, (int)5, (int)height-10);
	}
	public Rectangle getBoundsLeft() {
		return new Rectangle((int)x, (int)y + 5, (int)5, (int)height-10);
	}
	

}
