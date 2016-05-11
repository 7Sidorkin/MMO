import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class game extends Canvas implements Runnable{
	
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	final String TITLE = "MMO";
	
	public static void main(String[] args) {
		//Creates new instance of Game
				
				game game = new game();
				//Sets the maximum, minimum and preferred sizes of the JFrame
				game.setPreferredSize(new Dimension(WIDTH, HEIGHT));
				game.setMaximumSize(new Dimension(WIDTH, HEIGHT));
				game.setMinimumSize(new Dimension(WIDTH, HEIGHT));
				
				//Sets the title of the JFrame
				JFrame frame = new JFrame(game.TITLE);
				//Ads the instance of the game to the JFrame
				frame.add(game);
				//Causes the window to be at preferred size initially
				frame.pack();
				//Exits program on close
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				//Sets the program so it cannot be re-sizable
				frame.setResizable(false);
				//Disables the setLocationRelativeTo function
				frame.setLocationRelativeTo(null);
				//Makes the JFrame visible
				frame.setVisible(true);
				//Starts the instance
				//game.start();
		

	}

}
