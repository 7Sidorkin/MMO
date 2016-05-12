

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Menu {
	
		public Rectangle playButton = new Rectangle(600, 300, 200, 80);
		public Rectangle helpButton = new Rectangle(600, 400, 200, 80);
		public Rectangle quitButton = new Rectangle(600, 500, 200, 80);
		public Rectangle characherMenu = new Rectangle(600, 600, 200, 80);
	
	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		FontLoader.loadFont("./res/GODOFWAR.TTF");
		Font titleFont = new Font("Arial",Font.PLAIN, 93);
		Font buttonFont = new Font("Arial",Font.PLAIN, 50);
		
		
		
		g.setFont(titleFont);
		g.setColor(Color.WHITE);
		g.drawString("GAME TITLE HERE", 1, 100);
		
		g.setFont(buttonFont);
		g.drawString("Play", 600, 300);
		g.drawString("Help", 600, 400);
		g.drawString("Quit", 600, 500);
		g.drawString("Charachter Creation", 600,600);
		g2d.setStroke(new BasicStroke(3);
		g2d.draw(playButton);
		g2d.draw(helpButton);
		g2d.draw(quitButton);
		g2d.draw(characterMenu);
	}
}
