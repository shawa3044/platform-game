package game.framework;
// The Game Object class is an abstract class
// This class is the base for all the objects
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public abstract class GameObject {

	protected float x, y;
	protected ObjectId id;
	protected float velX = 0, velY=0;
	protected boolean falling = true;
	protected boolean jumping = false;
	/** Constructor <br>
	 * 
	 * @param x
	 * @param y
	 * @param id
	 */
	public GameObject(float x, float y, ObjectId id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	/** Abstract <br>
	 * 
	 * @param object
	 */
	public abstract void tick(LinkedList<GameObject> object);
	/** Abstract <br>
	 * 
	 * @param g
	 */
	public abstract void render(Graphics g);
	/** Abstract <br>
	 * 
	 * @return returns bound
	 */
	public abstract Rectangle getBounds();
	/** Getter <br>
	 * 
	 * @return x
	 */
	public float getX() {
		return x;
	}
	/** Getter <br>
	 * 
	 * @return y
	 */
	public float getY() {
		return y;
	}
	/** Setter <br>
	 * 
	 * @param x
	 */
	public void setX(float x) {
		this.x = x;
	}
	/** Setter <br>
	 * 
	 * @param y
	 */
	public void setY(float y) {
		this.y = y;
	}
	/** Getter <br>
	 * 
	 * @return velX
	 */
	public float getVelX() {
		return velX;
	}
	/** Getter <br>
	 * 
	 * @return velY
	 */
	public float getVelY() {
		return velY;
	}
	/** Setter <br>
	 * 
	 * @param velX
	 */
	public void setVelX(float velX) {
		this.velX = velX;
	}
	/** Setter <br>
	 * 
	 * @param velY
	 */
	public void setVelY(float velY) {
		this.velY = velY;
	}
	/** Getter <br>
	 * 
	 * @return falling
	 */
	public boolean isFalling() {
		return falling;
	}
	/** Setter <br>
	 * 
	 * @param falling
	 */
	public void setFalling(boolean falling) {
		this.falling = falling;
	}
	/** Getter <br>
	 * 
	 * @return jumping
	 */
	public boolean isJumping() {
		return jumping;
	}
	/** Setter <br>
	 * 
	 * @param jumping
	 */
	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}
	/** Getter <br>
	 * 
	 * @return id
	 */
	public ObjectId getId() {
		return id;
	}
}
