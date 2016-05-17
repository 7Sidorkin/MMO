import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class CharacterClass {
	public BufferedImage background;
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		background = imageLoader.imageLoader("./menuBackground.png");
		/* Insert code involving sprites here.
		 * The logic behind here is: 
		 * If the sprite for a warrior is clicked, warrior is inputted in the raceClass variable under character.
		 * If the sprite for a mage is clicked, mage is inputted in the raceClass variable under character.
		 * If the sprite for an archer is clicked, archer is inputted in the raceClass variable under character.
		 */
		System.out.println(Game.State);
	}
}
