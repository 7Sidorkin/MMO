import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Projectile extends GameObject {
	int speed = 10;
	int numTick = 0;
	int damage = 10;
	private Rectangle arrow;

	public Projectile(int x, int y, ID id, Player player) {
		super(x, y, id);
		if (player.type == Player.PLAYERTYPE.MAGE) {
			this.speed = 7;
			this.damage = 15;
		}
		if (player.type == Player.PLAYERTYPE.HEAVY) {
			this.speed = 10;
			this.damage = 25;
		}
		this.motionX = (int) (speed * Math.cos(Math
				.toRadians(player.pointing - 90)));
		this.motionY = (int) (speed * Math.sin(Math
				.toRadians(player.pointing - 90)));
		this.arrow = new Rectangle(x, y, 5, 5);
		System.out.println("shoot");
		this.x += this.motionX;
		this.x += this.motionX;
		this.y += this.motionY;
		this.y += this.motionY;
	}

	public void tick() {
		this.x += this.motionX;
		this.y += this.motionY;
		this.numTick++;
		if (this.id == ID.Arrow || this.id == ID.magic) {
		if (this.numTick > 65) {
			Game.game.handler.removeObject(this);
		}
		}else{
			if (this.numTick > 1) {
				Game.game.handler.removeObject(this);
		}
		this.arrow.setLocation(x, y);
		if (this.arrow.intersects(Game.game.player1.playerB)) {
			Game.game.player1.health -= damage;
			Game.game.handler.removeObject(this);
		}
		if (this.arrow.intersects(Game.game.player2.playerB)) {
			Game.game.player2.health -= damage;
			Game.game.handler.removeObject(this);
		}
		}

	}

	public void render(Graphics g) {
		g.drawRect(this.x, this.y, 5, 5);
		if (this.speed == 7) {
			g.fillRect(this.x, this.y, 5, 5);
		}
		if(!(this.damage == 25)){
			g.drawRect(this.x, this.y, 5, 5);
		}
	}
}