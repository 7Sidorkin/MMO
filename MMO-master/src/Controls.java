import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Controls {
	
	public BufferedImage controls;
	public BufferedImage controlsBack;
	Controls(){
		controls = imageLoader.imageLoader("./MMO-master/keyboardControls.png");
		controlsBack = imageLoader.imageLoader("./menuBackground.png");
	}

	public void render(Graphics g){
		g.drawImage(controlsBack, 0, 0, Game.WIDTH, Game.HEIGHT, null);
		g.drawImage(controls, 100, 100, 1000, 400, null);
	}
}