package game.window;
//Alayna Shaw
//April 23, 2018
//Final Project 
//This is the Game class it contains the main method and starts the game
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import game.framework.KeyInput;
import game.framework.ObjectId;
import game.objects.Blocks;
import game.objects.Flag;
import game.objects.Player;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 1L;
	
	private boolean running = false;
	private Thread thread;
	
	public static int width, height;
	public BufferedImage level = null;
	Handler handler;
	Camera camera;
	public static int LEVEL = 1;
	//this method create the objects
	private void init() {
		width = getWidth();
		height = getHeight();
		ImageLoader loader = new ImageLoader();
		level = loader.loadImage("/level.png"); // loading the level 1
		camera = new Camera(0,0);
		handler = new Handler(camera);
		
		handler.LoadImageLevel(level);
		//handler.addObject(new Player(100,100,ObjectId.player, handler));
		//handler.createLevel();
		this.addKeyListener(new KeyInput(handler));
	}
	//this class keeps track of the game starting
	public synchronized void start() {
		//makes sure thread isn't created again
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	//This method runs the game
	@Override
	public void run() {
		init();
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountofTicks = 60.0;
		double ns = 1000000000 / amountofTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now -lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
			//ps = frames;
			//ticks = updates;
				frames = 0;
				updates = 0;
				
			}
		}
		
	}
	private void tick() {
		handler.tick();
		for(int i = 0; i < handler.object.size(); i++) {
			if(handler.object.get(i).getId() == ObjectId.player) {
				camera.tick(handler.object.get(i));
			}
		}
	}
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D)g;
		/////////////////////////////////
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(),getHeight());
		/////////////////////////////////
		g2d.translate(camera.getX(), camera.getY()); //Begin Camera
		////////////////////////////////
		handler.render(g);
		////////////////////////////////
		g2d.translate(-camera.getX(), -camera.getY()); //End Camera
		////////////////////////////////
		g.dispose();
		bs.show();
	}
	
	//this is the main method, it creates the window
	public static void main(String[]args) {
		new Window(800, 600, "Final Game",new Game());
	}
}
