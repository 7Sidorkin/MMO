import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TextField;
import java.awt.image.BufferedImage;

public class CharacterFinal {
	public BufferedImage background;
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		background = imageLoader.imageLoader("./menuBackground.png");
		g.drawImage(background, 0, 0, Game.WIDTH, Game.HEIGHT, null);
		System.out.println(Game.State);
		TextField tfInput = new TextField(30); // Declare and allocate an TextField instance called tfInput
		Game.game.frame.add(tfInput);                          // "this" Container adds the TextField
	}
}
