import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Player {

	public static enum PLAYERTYPE {
		MAGE, HEAVY, ARCHER, I_HAVE_LITERALLY_NO_IDEA_OTHER_CLASSES,
	};

	public int x = 100, y = 100, motionX, motionY, width = 32, height = 32;

	public int health = 100;
	
	public int playerNum;

	public boolean detonate = false;
	
	public SpriteSheetReader reader;
	
	public AffineTransform at;

	public int cool1 = 1000; // cooldown time in milliseconds for main attack
	public int cool2 = 10;// cooldown time in milliseconds for the ability
	public long tack1Start = -cool1, tack2Start = -cool2;

	public int speed = 4;
	
	public Animation bMageS, bMageD, bMageL, bMageU, bMageR, rMageS, rMageD, rMageL, rMageU, rMageR, bArcherS, bArcherD, bArcherL, 
	bArcherU, bArcherR, rArcherS, rArcherD, rArcherL, 
	rArcherU, rArcherR, bHeavyS, bHeavyD, bHeavyL, bHeavyU, bHeavyR, rHeavyS, rHeavyD, rHeavyL, rHeavyU, rHeavyR;
	
	public BufferedImage[] bMageSI, bMageDI, bMageLI, bMageUI, bMageRI, rMageSI, rMageDI, rMageLI, rMageUI, rMageRI, bArcherSI, bArcherDI, bArcherI, 
			bArcherUI, bArcherRI, rArcherSI, rArcherDI, rArcherLI, 
			rArcherUI, rArcherRI, bHeavySI, bHeavyDI, bHeavyLI, bHeavyUI, bHeavyRI, rHeavySI, rHeavyDI, rHeavyLI, rHeavyUI, rHeavyRI;
	
	public int pointing = 1; // 1 up 2 right 3 down 4 left

	public Rectangle front, back, left, right, player;
	public Rectangle frontB, backB, leftB, rightB, playerB;
	public Shape frontS, backS, leftS, rightS, playerS;

	
	public Rectangle screenTop, screenBottom, screenLeft, screenRight;

	public PLAYERTYPE type;

	public Player(PLAYERTYPE p, int playerNumber) {
		reader = new SpriteSheetReader();
		this.playerNum = playerNumber;
		if (playerNum == 2) {
			x = 700;
			y = 100;
		}
		front = new Rectangle(x, y, 32, 1);
		back = new Rectangle(x, y + 31, 32, 1);
		left = new Rectangle(x, y, 1, 32);
		right = new Rectangle(x + 31, y, 1, 32);
		player = new Rectangle(x, y, 32, 32);
		playerB = player.getBounds();
		screenTop = new Rectangle(0, 0, 1280, 1);
		screenBottom = new Rectangle(0, 720, 1280, 1);
		screenLeft = new Rectangle(0, 0, 1, 720);
		screenRight = new Rectangle(1280, 0, 1, 720);
		this.type = p;
		bMageSI = getSprites(4, )
		bMageS = new Animation(3, )
	}

	public void render(Graphics2D g) {
		g.setColor(Color.black);
		g.drawLine(0, 20, 1280, 20);
		// g.drawRect(x, y, 32, 32);
		// g.rotate(Math.toRadians(pointing), x + 16, y + 16);
		g.draw(playerB);
		g.setColor(Color.red);
		g.draw(front);
		if (front.intersects(screenTop)) {
			System.out.println("yay");
		}
		if(this.health <= 0){
			if(this.playerNum == 1){
				Game.winner = 2;
			}
			if(this.playerNum == 2){
				Game.winner = 1;
			}
			Game.State = Game.STATE.WIN;
		}
		if(this.type == PLAYERTYPE.HEAVY){
			g.setColor(Color.MAGENTA);
			g.fill(this.player);
		}else if(this.type == PLAYERTYPE.MAGE){
			g.setColor(Color.GREEN);
			g.fill(this.player);
		}else if(this.type == PLAYERTYPE.ARCHER){
			g.setColor(Color.BLUE);
			g.fill(this.player);
		}
		g.setFont(new Font("Minecraft",Font.PLAIN, 10));
		g.drawString(""+ health, x, y);

	}

	public void tick() {
		at = AffineTransform.getRotateInstance(Math.toRadians(pointing), x + 16, y + 16);
		switch (pointing) {
		case 1 :
			front.setBounds(x, y, 32, 1);
			back.setBounds(x, y + 31, 32, 1);
			left.setBounds(x, y, 1, 32);
			right.setBounds(x + 31, y, 1, 32);
			player.setLocation(x, y);
			playerB.setLocation(x, y);
			break;
		case 2 :
			front.setBounds(x+ 31, y, 1, 32);
			back.setBounds(x, y, 1, 32);
			left.setBounds(x, y, 32, 1);
			right.setBounds(x, y + 31, 32, 1);
			player.setLocation(x, y);
			playerB.setLocation(x, y);
			break;
		case 3:
			front.setBounds(x, y + 31, 32, 1);
			back.setBounds(x, y, 32, 1);
			left.setBounds(x + 31, y, 1, 32);
			right.setBounds(x, y, 1, 32);
			player.setLocation(x, y);
			playerB.setLocation(x, y);
			break;
		case 4:
			front.setBounds(x, y, 1, 32);
			back.setBounds(x + 31, y, 1, 32);
			left.setBounds(x, y + 31, 32, 1);
			right.setBounds(x, y, 32, 1);
			player.setLocation(x, y);
			playerB.setLocation(x, y);
			break;
		}
		// if(player.getX() + 32 + speed > 1280 || player.getX() - speed < 0){
		// motionX = 0;
		// }
		// System.out.println(x + ", " + y);
		if (playerB.getX() + speed + 20 + playerB.getWidth() > 1280) {
			motionX = 0;
			x--;
		}

		if (playerB.getX() - speed - 20 < 0) {
			motionX = 0;
			x++;
		}
		if (playerB.getY() + speed + 20 + playerB.getHeight() > 720) {
			motionY = 0;
			y--;
		}
		if (playerB.getY() - speed - 20 < 0) {
			motionY = 0;
			y++;
		}
		if(playerNum == 1){
			if(this.player.intersects(Game.game.player2.player)){
				motionX = motionX * -1;
				motionY = motionY * -1;
				x += motionX;
				x += motionX;
				y += motionY;
				y += motionY;
				motionX = motionX * -1;
				motionY = motionY * -1;
			}
		}
		
		if(playerNum == 2){
			if(this.player.intersects(Game.game.player1.player)){
				motionX = motionX * -1;
				motionY = motionY * -1;
				x += motionX;
				x += motionX;
				y += motionY;
				y += motionY;
				motionX = motionX * -1;
				motionY = motionY * -1;
			}
		}
		
		x += motionX;
		y += motionY;

	}

	public void control(KeyEvent e, boolean pressed) {
		if (playerNum == 1) {
			if (pressed) {
				if (e.getKeyCode() == KeyEvent.VK_W) {
					motionY = -speed;
					pointing = 1;
					if (playerB.getY() - speed - 20 < 0) {
						motionY = 0;
						y++;
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_S) {
					motionY = speed;
					pointing = 3;
					if (playerB.getY() + speed + 20 + playerB.getHeight() > 720) {
						motionY = 0;
						y--;
					}

				}
				if (e.getKeyCode() == KeyEvent.VK_A) {
					motionX = -speed;
					pointing = 4;
					if (playerB.getX() - speed - 20 < 0) {
						motionX = 0;
						x++;
					}

				}
				if (e.getKeyCode() == KeyEvent.VK_D) {
					motionX = speed;
					pointing = 2;
					if (playerB.getX() + speed + 20 + playerB.getWidth() > 1280) {
						motionX = 0;
						x--;
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_V) {
					if (System.currentTimeMillis() - tack1Start > cool1) {
						tack1Start = System.currentTimeMillis();
						attack();
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_B) {
					if (System.currentTimeMillis() - tack2Start > cool2) {
						tack2Start = System.currentTimeMillis();
						ability();
					}
				}
				if(e.getKeyCode() == KeyEvent.VK_N){
					detonate = true;
				}
				
			}
			if (!pressed) {
				if (e.getKeyCode() == KeyEvent.VK_W) {
					motionY = 0;
				}
				if (e.getKeyCode() == KeyEvent.VK_S) {
					motionY = 0;
				}
				if (e.getKeyCode() == KeyEvent.VK_A) {
					motionX = 0;
				}
				if (e.getKeyCode() == KeyEvent.VK_D) {
					motionX = 0;
				}
				if(e.getKeyCode() == KeyEvent.VK_N){
					detonate = false;
				}
			}
		}else{
			if (pressed) {
				if (e.getKeyCode() == KeyEvent.VK_NUMPAD8) {
					motionY = -speed;
					pointing = 1;
					if (playerB.getY() - speed - 20 < 0) {
						motionY = 0;
						y++;
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_NUMPAD5) {
					motionY = speed;
					pointing = 3;
					if (playerB.getY() + speed + 20 + playerB.getHeight() > 720) {
						motionY = 0;
						y--;
					}

				}
				if (e.getKeyCode() == KeyEvent.VK_NUMPAD4) {
					motionX = -speed;
					pointing = 4;
					if (playerB.getX() - speed - 20 < 0) {
						motionX = 0;
						x++;
					}

				}
				if (e.getKeyCode() == KeyEvent.VK_NUMPAD6) {
					motionX = speed;
					pointing = 2;
					if (playerB.getX() + speed + 20 + playerB.getWidth() > 1280) {
						motionX = 0;
						x--;
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (System.currentTimeMillis() - tack1Start > cool1) {
						tack1Start = System.currentTimeMillis();
						attack();
					}
				}

				if (e.getKeyCode() == KeyEvent.VK_NUMPAD0) {
					if (System.currentTimeMillis() - tack2Start > cool2) {
						tack2Start = System.currentTimeMillis();
						ability();
					}
				}
				if(e.getKeyCode() == KeyEvent.VK_NUMPAD2){
					detonate = true;
				}
			}
			if (!pressed) {
				if (e.getKeyCode() == KeyEvent.VK_NUMPAD8) {
					motionY = 0;
				}
				if (e.getKeyCode() == KeyEvent.VK_NUMPAD5) {
					motionY = 0;
				}
				if (e.getKeyCode() == KeyEvent.VK_NUMPAD4) {
					motionX = 0;
				}
				if (e.getKeyCode() == KeyEvent.VK_NUMPAD6) {
					motionX = 0;
				}
				if(e.getKeyCode() == KeyEvent.VK_NUMPAD2){
					detonate = false;
				}
			}
		}
	}

	public void attack() {
		if (this.type == PLAYERTYPE.ARCHER) {
			Projectile arrow1 = new Projectile(x, y, ID.Arrow, this);
			Game.game.handler.addObject(arrow1);
		
		}
		if (this.type == PLAYERTYPE.MAGE) {
			Projectile magic1 = new Projectile(x, y, ID.magic, this);
			Game.game.handler.addObject(magic1);
		
		}
		if (this.type == PLAYERTYPE.HEAVY) {
			Projectile melee1 = new Projectile(x, y, ID.Melee, this);
			Game.game.handler.addObject(melee1);
		
		}
	}

	public void setPlayerType(PLAYERTYPE type) {
		this.type = type;
		if(this.type == PLAYERTYPE.HEAVY){
			this.health = 300;
		}
		if(this.type == PLAYERTYPE.MAGE){
			this.health = 60;
		}
	}
	
	public void ability(){
		if(this.type == PLAYERTYPE.HEAVY){
			
		}
		if(this.type == PLAYERTYPE.MAGE){
			Projectile melee1 = new Projectile(x, y, ID.Mage_Ability, this);
			Game.game.handler.addObject(melee1);
		}
		if(this.type == PLAYERTYPE.ARCHER){
			long start = 10;
			int n = 0;
			while(n < 6){
				if(start - System.currentTimeMillis()< -9){
				Projectile melee1 = new Projectile(x, y, ID.Arrow, this);
				Game.game.handler.addObject(melee1);
				start = System.currentTimeMillis();
				System.out.println("test" + n);
				n++;
				}
			}
		}
	}
}