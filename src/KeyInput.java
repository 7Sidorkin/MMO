
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

public class KeyInput extends KeyAdapter {

	//Creates new instance of game
	Game game;
	
	//This will allow to call the keyboard input within the game class
	public KeyInput(Game game){
		this.game = game;
	}
	
	public void keyPressed(KeyEvent e){
		//When key is pressed, this will call for the function within the game class to allow key input without player
		game.keyPressed(e);
	}
	
	public void keyReleased(KeyEvent e){
		//When key is pressed, this will call for the function within the game class to allow key input without player
		game.keyReleased(e);
	}
}
