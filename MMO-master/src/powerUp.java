import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class powerUp extends GameObject {

	private Rectangle box;
	private int boost = 25;

	public powerUp(int x, int y, ID id) {
		super(x, y, id);
		box = new Rectangle(x, y , 64, 64);
	}

	public void render(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(x, y, 64, 64);
	}

	public void tick(){
		if(box.intersects(Game.game.player1.playerB)){
			Game.game.player1.health += boost;
			Game.game.handler.removeObject(this);
		}
		if(box.intersects(Game.game.player2.playerB)){
			Game.game.player2.health += boost;
			Game.game.handler.removeObject(this);
		}
	}

}
