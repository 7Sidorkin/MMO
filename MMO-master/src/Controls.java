import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Controls {
	public BufferedImage controls;
	Controls(){
		controls = Imageloader.imageLoader("./keyboardControls.png");
	}

	public void render(Graphics g){
		g.drawImage(Menu.background, 0, 0, Game.WIDTH, Game.HEIGHT, null);
		g.drawImage(controls, 40, 40, 1200, 640, null);
	}
}
