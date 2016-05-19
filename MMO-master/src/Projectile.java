import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

public class Projectile extends GameObject {
	int speed = 10;
	int numTick = 0;
	int damage = 10;
	private Rectangle arrow;
	private Ellipse2D aoe;
	private Player player;

	public Projectile(int x, int y, ID id, Player player) {
		super(x, y, id);
		this.player = player;
		if (player.type == Player.PLAYERTYPE.MAGE) {
			this.speed = 7;
			this.damage = 15;
			if (this.id == ID.Mage_Ability) {
				this.damage = 30;
			}
		}
		if (player.type == Player.PLAYERTYPE.HEAVY) {
			this.speed = 10;
			this.damage = 25;
		}
		switch (player.pointing) {
		case 1:

			motionX = 0;
			motionY = -speed;
			break;
		case 2:

			motionX = speed;
			motionY = 0;
			break;
		case 3:

			motionX = 0;
			motionY = speed;
			break;
		case 4:

			motionX = -speed;
			motionY = 0;
		}
		this.arrow = new Rectangle(x, y, 5, 5);
		System.out.println("shoot");

		System.out.println("x" + this.x + "y" + this.y);
	}

	public void tick() {
		this.x += this.motionX;
		this.y += this.motionY;
		this.numTick++;

		if (this.id == ID.Arrow || this.id == ID.magic) {
			if (this.numTick > 65) {
				Game.game.handler.removeObject(this);
			}
		} else if (this.id == ID.Melee) {
			if (this.numTick > 1) {
				Game.game.handler.removeObject(this);
			}
		} else if (this.id == ID.Mage_Ability || player.detonate) {
			if (this.numTick > 65 || player.detonate) {
				Game.game.handler.removeObject(this);
				this.motionX = 0;
				this.motionY = 0;
				aoe = new Ellipse2D.Double(this.x - 22, this.y - 22, 48, 48);
				if (this.aoe.intersects(Game.game.player1.playerB)) {
					Game.game.player1.health -= 50;
				}
				if (this.aoe.intersects(Game.game.player2.playerB)) {
					Game.game.player2.health -= 50;
				}
			}
		}
		this.arrow.setLocation(this.x, this.y);
		if (this.arrow.intersects(Game.game.player1.playerB)) {
			if (!Game.game.player1.blocking){
			Game.game.player1.health -= damage;
			}else{
				if (this.arrow.intersects(Game.game.player1.right) || this.arrow.intersects(Game.game.player1.left) || this.arrow.intersects(Game.game.player1.back)){
					Game.game.player1.health -= damage;
				}
			}
			Game.game.handler.removeObject(this);
		}
		if (this.arrow.intersects(Game.game.player2.playerB)) {
			if (!Game.game.player2.blocking){
			Game.game.player2.health -= damage;
			}else{
				if (this.arrow.intersects(Game.game.player2.right) || this.arrow.intersects(Game.game.player2.left) || this.arrow.intersects(Game.game.player2.back)){
					Game.game.player2.health -= damage;
				}
			}
			Game.game.handler.removeObject(this);
		}
	}

	public void render(Graphics g) {
		g.drawOval(x - 22, y - 22, 48, 48);
		if (this.speed == 7) {
			g.fillRect(this.x, this.y, 5, 5);
		}
		if (!(this.damage == 25)) {
			g.drawRect(this.x, this.y, 5, 5);
		}

	}
}