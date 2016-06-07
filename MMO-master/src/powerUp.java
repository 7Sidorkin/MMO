import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class powerUp extends GameObject {

	private Rectangle box;
	private int boost = 25;

	public BufferedImage healthI;
	public BufferedImage[] healthM;
	public Animation healthA;
	public SpriteSheetReader reader;

	public powerUp(int x, int y, ID id) {
		super(x, y, id);
		reader = new SpriteSheetReader();
		reader.size = 16;
		healthI = imageLoader.imageLoader("./MMO-master/src/grahpics/health.png");
		healthM = reader.getSprites(4, healthI);
		healthA = new Animation(10, healthM[0], healthM[1], healthM[2],
				healthM[3]);
		box = new Rectangle(x, y, 64, 64);
	}

	public void render(Graphics g) {

		healthA.drawAnimation(g, x, y, 0);
	}

	public void tick() {
		healthA.runAnimation();
		if (box.intersects(Game.game.player1.playerB)) {
			Game.game.player1.health += boost;
			Game.game.handler.removeObject(this);
		}
		if (box.intersects(Game.game.player2.playerB)) {
			Game.game.player2.health += boost;
			Game.game.handler.removeObject(this);
		}
	}

}
