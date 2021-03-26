package game.objects;
//This class creates the blocks
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import game.framework.GameObject;
import game.framework.ObjectId;

public class Blocks extends GameObject{
	/** Constructor <br>
	 * 
	 * @param x
	 * @param y
	 * @param id
	 */
	public Blocks(float x, float y, ObjectId id) {
		super(x, y, id);
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawRect((int)x, (int)y, 32, 32);
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}

}
