

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Menu {
	
		public Rectangle playButton = new Rectangle(550, 250, 200, 80);
		public Rectangle helpButton = new Rectangle(550, 350, 200, 80);
		public Rectangle quitButton = new Rectangle(550, 450, 200, 80);
		public Rectangle characterMenu = new Rectangle(420, 550, 470, 80);
		public static BufferedImage background;
	
		public Menu(){
			background = Imageloader.imageLoader("./menuBackground.jpg");
		}
		
	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		FontLoader.loadFont("./TubeOfCorn.ttf");
		Font titleFont = new Font("TubeOfCorn",Font.PLAIN, 93);
		Font buttonFont = new Font("TubeOfCorn",Font.PLAIN, 50);
		
		
		g.drawImage(background, 0, 0, Game.WIDTH, Game.HEIGHT, null);
		g.setFont(titleFont);
		g.setColor(Color.WHITE);
		g.drawString("GAME TITLE HERE", 1, 100);
		
		g.setFont(buttonFont);
		g.drawString("Play", 570, 300);
		g.drawString("Help", 570, 400);
		g.drawString("Quit", 570, 500);
		g.drawString("Character Creation", 440,600);
		g2d.setStroke(new BasicStroke(3));
		g2d.draw(playButton);
		g2d.draw(helpButton);
		g2d.draw(quitButton);
		g2d.draw(characterMenu);  
 	}
}
