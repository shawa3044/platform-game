package game.window;
// This class handles all the object
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import game.framework.GameObject;
import game.framework.ObjectId;
import game.objects.Blocks;
import game.objects.Flag;
import game.objects.Player;

public class Handler {

	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	private GameObject tempObject;
	private Camera camera;
	private BufferedImage level2 = null, level3 = null, level4 = null, level5 = null, level6 = null, level7 = null, level8 = null;
	
	public Handler(Camera camera) {
		this.camera = camera;
		ImageLoader loader = new ImageLoader();
		level2 = loader.loadImage("/level2.png"); // loading the level 2
		level3 = loader.loadImage("/level3.png"); // loading the level 3
		level4 = loader.loadImage("/level4.png"); // loading the level 4
		level5 = loader.loadImage("/level5png.png"); // loading the level 5 
		level6 = loader.loadImage("/level6png.png"); // loading the level 6
		level7 = loader.loadImage("/level7png.png"); // loading the level 7
		level8 = loader.loadImage("/level8png.png"); // loading the level 8
	}
	public void tick() {
		for(int i = 0; i < object.size(); i++) {
			tempObject = object.get(i);
			
			tempObject.tick(object);
		}
	}
	public void render(Graphics g) {
		for(int i = 0; i < object.size(); i++) {
			tempObject = object.get(i);
			
			tempObject.render(g);
		}
	}
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
	public void LoadImageLevel(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();
		for(int xx = 0; xx < h; xx++) {
			for(int yy = 0; yy < w; yy++) {
				int pixle = image.getRGB(xx, yy);
				int r = (pixle >> 16) & 0xff;
				int g = (pixle >> 8) & 0xff;
				int b = (pixle) & 0xff;
				
				if(r == 255 && g == 255 && b == 255) {
					addObject(new Blocks(xx*32, yy*32, ObjectId.Block));
				}
				if(r == 0 && g == 0 && b == 255) {
					addObject(new Player(xx*32, yy*32, ObjectId.player, this, camera));
				}
				if(r == 255 && g == 255 && b == 0) {
					addObject(new Flag(xx*32, yy*32, ObjectId.flag));
				}
			}
		}
		
	}
	public void switchLevel() {
		clearLevel();
		camera.setX(0);
		switch(Game.LEVEL){
		case 1:
			LoadImageLevel(level2);
			Game.LEVEL++;
			break;
		case 2:
			LoadImageLevel(level3);
			Game.LEVEL++;
			break;
		case 3:
			LoadImageLevel(level4);
			Game.LEVEL++;
			break;
		case 4:
			LoadImageLevel(level5);
			Game.LEVEL++;
			break;
		case 5:
			LoadImageLevel(level6);
			Game.LEVEL++;
			break;
		case 6:
			LoadImageLevel(level7);
			Game.LEVEL++;
			break;
		case 7:
			LoadImageLevel(level8);
			Game.LEVEL++;
			break;
		case 8:
			System.exit(1);
			break;
		
		}
	}
	private void clearLevel() {
		object.clear();
	}
	public void createLevel() {
		for(int yy = 0; yy < Game.height + 32; yy += 32) {
			addObject(new Blocks(0, yy, ObjectId.Block));
		}
		for(int xx = 0; xx < (Game.width *2); xx += 32) {
			addObject(new Blocks(xx, Game.height - 32, ObjectId.Block));
		}
		for(int xx = 200; xx < 600; xx += 32) {
			addObject(new Blocks(xx, 400, ObjectId.Block));
		}
	}
}
