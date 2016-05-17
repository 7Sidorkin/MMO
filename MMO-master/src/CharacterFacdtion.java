import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class CharacterFaction {
	public Rectangle blackguardRect = new Rectangle(50, 125, 550, 410);
	public Rectangle moonshadowRect = new Rectangle(650, 100, 550, 410);
	public BufferedImage background;
	public BufferedImage blackguard;
	public BufferedImage moonshadow;
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		background = imageLoader.imageLoader("./menuBackground.png");
		blackguard = imageLoader.imageLoader("./blackguard.png");
		moonshadow = imageLoader.imageLoader("./moonshadow.png");
		g.drawImage(background, 0, 0, Game.WIDTH, Game.HEIGHT, null);
		g.drawImage(moonshadow, 600, 100, 600, 410, null);
		g.drawImage(blackguard, 50, 125, 800, 410, null);
	
	}
}
