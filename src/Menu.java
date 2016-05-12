package com.game.scr.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Menu {
	
		public Rectangle playButton = new Rectangle(286, 200, 200, 100);
		public Rectangle helpButton = new Rectangle(286, 350, 200, 100);
		public Rectangle quitButton = new Rectangle(286, 500, 200, 100);
	
	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		FontLoader.loadFont("./res/GODOFWAR.TTF");
		Font titleFont = new Font("GodOfWar",Font.PLAIN, 93);
		Font buttonFont = new Font("GodOfWar",Font.PLAIN, 75);
		
		
		
		g.setFont(titleFont);
		g.setColor(Color.green);
		g.drawString("Space Warfare", 8, 100);
		
		g.setFont(buttonFont);
		g.drawString("Play", 293, 270);
		g.drawString("Help", 288, 420);
		g.drawString("Quit", 293, 565);
		g2d.draw(playButton);
		g2d.draw(helpButton);
		g2d.draw(quitButton);
	}
}
