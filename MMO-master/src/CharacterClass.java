import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class CharacterClass {
	public Rectangle rMageRect;
	public Rectangle rHeavyRect;
	public Rectangle rArcherRect;
	public Rectangle bMageRect;
	public Rectangle bHeavyRect;
	public Rectangle bArcherRect;
	public BufferedImage background;
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		background = imageLoader.imageLoader("./menuBackground.png");
		System.out.println(Game.State);
		g.drawImage(background, 0, 0, 1280, 720, null);
		switch(Character.chosenFaction) {
		case "blackguard":
			g.drawImage(Game.game.rMage, 300, 300, 128, 128, null);
			g.drawImage(Game.game.rHeavy, 600, 300, 128, 128, null);
			g.drawImage(Game.game.rArcher, 900, 300, 128, 128, null);
			rMageRect = new Rectangle(300, 300, 128, 128);
			rHeavyRect = new Rectangle(600, 300, 128, 128);
			rArcherRect = new Rectangle(900, 300, 128, 128);
			g2d.draw(rMageRect);
			g2d.draw(rHeavyRect);
			g2d.draw(rArcherRect);
			break;
			
		case "moonshadow":
			g.drawImage(Game.game.bMage, 300, 300, 128, 128, null);
			g.drawImage(Game.game.bHeavy, 600, 300, 128, 128, null);
			g.drawImage(Game.game.bArcher, 900, 300, 128, 128, null);
			bMageRect = new Rectangle(300, 300, 128, 128);
			bHeavyRect = new Rectangle(600, 300, 128, 128);
			bArcherRect = new Rectangle(900, 300, 128, 128);
			g2d.draw(bMageRect);
			g2d.draw(bHeavyRect);
			g2d.draw(bArcherRect);
			break;
			
		default:
			Game.State = Game.STATE.CHARACTER_FACTION;
			break;
		}
	}
	
}
