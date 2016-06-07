import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class CharacterFinal {
	public BufferedImage background;
	public String[] name;
	
	public int num = 0;
	public CharacterFinal(){
		name = new String[40];
	}
	
	public void keyinput(KeyEvent e){
		name[num] = e.getKeyText(e.getKeyCode());
		num ++;
	}
	
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		background = imageLoader.imageLoader("./menuBackground.png");
		g.drawImage(background, 0, 0, Game.WIDTH, Game.HEIGHT, null);
		System.out.println(Game.State);
		g.setFont(new Font("Arial", 1, 50));
		g.setColor(Color.BLACK);
		System.out.println(name[1]);
		g.drawString(name[1], 300, 300);
	}
	
	
}
