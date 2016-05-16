import java.awt.Graphics;

public class Projectile extends GameObject {
	int speed = 10;

	public Projectile(int x, int y, ID id) {
		super(x, y, id);
		motionX = (int) (speed* Math.cos(Math.toRadians(Game.game.player.pointing - 90)));
		motionY = (int) (speed* Math.sin(Math.toRadians(Game.game.player.pointing - 90)));
	}

	@Override
	public void tick() {
		x += motionX;
		y += motionY;
	}

	@Override
	public void render(Graphics g) {
		g.drawRect(x, y, 5, 5);
	}
}
