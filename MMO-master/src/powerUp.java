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
		if (id == ID.PowerUp) {
			healthI = imageLoader
					.imageLoader("./MMO-master/src/grahpics/health.png");
			healthM = reader.getSprites(4, healthI);
			healthA = new Animation(10, healthM[0], healthM[1], healthM[2],
					healthM[3]);
		} else {
			reader.size = 24;
			healthI = imageLoader
					.imageLoader("./MMO-master/src/grahpics/speed.png");
			healthM = reader.getSprites(12, healthI);
			healthA = new Animation(5, healthM[0], healthM[1], healthM[2], healthM[3], healthM[4], healthM[5],healthM[6],healthM[7],healthM[8],healthM[9],healthM[10],healthM[11],healthM[10],healthM[9],healthM[8],healthM[7],healthM[6],healthM[5],healthM[4],healthM[3],healthM[2],healthM[1]);
		}
		box = new Rectangle(x, y, 64, 64);
	}

	public void render(Graphics g) {
		healthA.drawAnimation(g, x, y, 0);
	}

	public void tick() {
		healthA.runAnimation();
		if (box.intersects(Game.game.player1.playerB)) {
			if (id == ID.PowerUp) {
				Game.game.player1.health += boost;
				Game.game.handler.removeObject(this);
			} else {
				Game.game.player1.speed = (int) (Game.game.player1.speed * 1.5);
				Game.game.handler.removeObject(this);
			}
		}
		if (box.intersects(Game.game.player2.playerB)) {
			if (id == ID.PowerUp) {
				Game.game.player2.health += boost;
				Game.game.handler.removeObject(this);
			} else {
				Game.game.player2.speed = (int) (Game.game.player2.speed * 1.5);
				Game.game.handler.removeObject(this);
			}
		}
	}

}
