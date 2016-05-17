import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Menu {
	
		public Rectangle playButton = new Rectangle((Game.WIDTH/2) - 320, 250, 600, 80);
		public Rectangle helpButton = new Rectangle((Game.WIDTH/2) - 320, 350, 600, 80);
		public Rectangle quitButton = new Rectangle((Game.WIDTH/2) - 320, 450, 600, 80);
		public Rectangle characterMenu = new Rectangle((Game.WIDTH/2) - 320, 250, 470, 80);
		public BufferedImage background;
	
		public Menu(){
			background = imageLoader.imageLoader("./menuBackground.png");
		}

		
	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		FontLoader.loadFont("./Minecraft.ttf");
		Font titleFont = new Font("Minecraft",Font.PLAIN, 93);
		Font buttonFont = new Font("Minecraft",Font.PLAIN, 50);
		
		
		g.drawImage(background, 0, 0, Game.WIDTH, Game.HEIGHT, null);
		g.setFont(titleFont);
		g.setColor(Color.BLACK);
		
		g.drawString("IDK", 200, 100);
		g.setColor(Color.GRAY);
		g.fillRect((Game.WIDTH/2) - 320, 250, 600, 80);
		g.fillRect((Game.WIDTH/2) - 320, 350, 600, 80);
		g.fillRect((Game.WIDTH/2) - 320, 450, 600, 80);
		g.setColor(Color.WHITE);
		g.setFont(buttonFont);
		g.drawString("Play", 570, 300);
		g.drawString("Help", 570, 400);
		g.drawString("Quit", 570, 500);
		//g.drawString("Character Creation", 440,600);
		g.setColor(Color.BLACK);
		g2d.setStroke(new BasicStroke(3));
		g2d.draw(playButton);
		g2d.draw(helpButton);
		g2d.draw(quitButton);
		
		//g2d.draw(characterMenu);  
 	}
}