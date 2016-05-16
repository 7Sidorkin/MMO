import java.awt.Graphics;
import java.awt.Rectangle;

public class Projectile extends GameObject {
	int speed = 10;
	int numTick = 0;
	int damage = 10;
	private Rectangle arrow;

	public Projectile(int x, int y, ID id, Player player) {
		super(x, y, id);
		this.motionX = (int) (speed* Math.cos(Math.toRadians(player.pointing - 90)));
		this.motionY = (int) (speed* Math.sin(Math.toRadians(player.pointing - 90)));
		this.arrow = new Rectangle(x, y, 5,5);
	}

	@Override
	public void tick() {
		this.x += this.motionX;
		this.y += this.motionY;
		this.numTick++;
		if(this.numTick > 65){
			Game.game.handler.removeObject(this);
		}
		this.arrow.setLocation(x, y);
		if(this.arrow.intersects(Game.game.player1.playerB)){
			Game.game.player1.health -= damage;
			Game.game.handler.removeObject(this);
		}
		if(this.arrow.intersects(Game.game.player2.playerB)){
			Game.game.player2.health -= damage;
			Game.game.handler.removeObject(this);
		}
		
	}

	@Override
	public void render(Graphics g) {
		g.drawRect(this.x, this.y, 5, 5);
	}
}
