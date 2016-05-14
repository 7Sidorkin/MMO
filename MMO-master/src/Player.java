import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;

public class Player {

	public int x = 100, y = 100, motionX, motionY, width = 32, height = 32;

	public int rotationSpeed = 0;

	public AffineTransform at;

	public int speed = 10;

	public int pointing = 0; // 0 = Straight at top of screen

	public Rectangle front, back, left, right, player;
	public Rectangle frontB, backB, leftB, rightB, playerB;
	public Shape frontS, backS, leftS, rightS, playerS;

	public Rectangle screenTop, screenBottom, screenLeft, screenRight;

	public Player() {
		front = new Rectangle(x, y, 32, 1);
		back = new Rectangle(x, y + 31, 32, 1);
		left = new Rectangle(x, y, 1, 32);
		right = new Rectangle(x + 31, y, 1, 32);
		player = new Rectangle(x, y, 32, 32);
		at = new AffineTransform();
		at = AffineTransform.getRotateInstance(Math.toRadians(pointing), x + 16, y + 16);
		playerS = at.createTransformedShape(player);
		frontS = at.createTransformedShape(front);
		backS = at.createTransformedShape(back);
		leftS = at.createTransformedShape(left);
		rightS = at.createTransformedShape(right);
		playerB = playerS.getBounds();
		frontB = frontS.getBounds();
		backB = backS.getBounds();
		leftB = leftS.getBounds();
		rightB = rightS.getBounds();
		screenTop = new Rectangle(0, 0, 1280, 1);
		screenBottom = new Rectangle(0, 720, 1280, 1);
		screenLeft = new Rectangle(0, 0, 1, 720);
		screenRight = new Rectangle(1280, 0, 1, 720);
	}

	public void render(Graphics2D g) {
		g.setColor(Color.black);
		g.drawLine(0, 20, 1280, 20);
		// g.drawRect(x, y, 32, 32);
		g.rotate(Math.toRadians(pointing), x + 16, y + 16);
		g.draw(player);
		g.setColor(Color.red);
		g.draw(front);
		if (frontS.intersects(screenTop)) {
			System.out.println("yay");
		}

	}

	public void update() {
		at = AffineTransform.getRotateInstance(Math.toRadians(pointing), x + 16, y + 16);
		front.setLocation(x, y);
		back.setLocation(x, y + 31);
		left.setLocation(x, y);
		right.setLocation(x + 31, y);
		player.setLocation(x, y);
		playerS = at.createTransformedShape(player);
		frontS = at.createTransformedShape(front);
		backS = at.createTransformedShape(back);
		leftS = at.createTransformedShape(left);
		leftS = at.createTransformedShape(right);
		playerB = playerS.getBounds();
		frontB = frontS.getBounds();
		backB = backS.getBounds();
		leftB = leftS.getBounds();
		rightB = rightS.getBounds();
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
		
		x += motionX;
		y += motionY;
		pointing += rotationSpeed;
				
	}

	public void control(KeyEvent e, boolean pressed) {
		if (pressed) {
			if (e.getKeyCode() == KeyEvent.VK_W) {
				motionY = -speed;
				if (playerB.getY() - speed - 20 < 0) {
					motionY = 0;
					y++;
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_S) {
				motionY = speed;
				if (playerB.getY() + speed + 20 + playerB.getHeight() > 720) {
					motionY = 0;
					y--;
				}

			}
			if (e.getKeyCode() == KeyEvent.VK_A) {
				motionX = -speed;
				if (playerB.getX() - speed - 20 < 0) {
					motionX = 0;
					x++;
				}

			}
			if (e.getKeyCode() == KeyEvent.VK_D) {
				motionX = speed;
				if (playerB.getX() + speed + 20 + playerB.getWidth() > 1280) {
					motionX = 0;
					x--;
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_E) {
				rotationSpeed = 2;
			}
			if (e.getKeyCode() == KeyEvent.VK_Q) {
				rotationSpeed = -2;
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
			if (e.getKeyCode() == KeyEvent.VK_E) {
				rotationSpeed = 0;
			}
			if (e.getKeyCode() == KeyEvent.VK_Q) {
				rotationSpeed = 0;
			}
		}
	}
}
