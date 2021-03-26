package game.window;
//This is the Window class it creates the game's window
import java.awt.Dimension;
import javax.swing.JFrame;

public class Window {
	
	public Window(int w, int h, String title, Game game){
		game.setPreferredSize(new Dimension(w,h));
		game.setMaximumSize(new Dimension(w,h));
		game.setMinimumSize(new Dimension(w,h));
		//these lines create and set the JFrame
		JFrame frame = new JFrame(title);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		game.start();
	}
}
