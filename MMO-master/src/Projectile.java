import java.awt.Graphics;

public class Projectile extends GameObject {
	int speed = 10;
	int numTick = 0;

	public Projectile(int x, int y, ID id) {
		super(x, y, id);
		this.motionX = (int) (speed* Math.cos(Math.toRadians(Game.game.player.pointing - 90)));
		this.motionY = (int) (speed* Math.sin(Math.toRadians(Game.game.player.pointing - 90)));
	}

	@Override
	public void tick() {
		this.x += this.motionX;
		this.y += this.motionY;
		this.numTick++;
		if(this.numTick > 65){
			Game.game.handler.removeObject(this);
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawRect(this.x, this.y, 5, 5);
	}
}
