import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Controls {
	
	public BufferedImage controls;
	public BufferedImage controlsBack;
	Controls(){
		controls = imageLoader.imageLoader("./keyboardControls.png");
		controlsBack = imageLoader.imageLoader("./menuBackground.jpg");
	}

	public void render(Graphics g){
		g.drawImage(controlsBack, 0, 0, Game.WIDTH, Game.HEIGHT, null);
		g.drawImage(controls, 40, 40, 1200, 640, null);
	}
}
