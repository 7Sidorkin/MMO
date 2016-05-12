
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {
	public void mouseClicked(MouseEvent arg0) {
	}
	public void mouseEntered(MouseEvent arg0) {
	}
	public void mouseExited(MouseEvent arg0) {
	}
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();

		if (Game.State == Game.STATE.MENU) {
			// Play Button
			if (mx >= 286 && mx <= 486) {
				if (my >= 200 && my <= 300) {
					// Pressed Play Button
					Game.State = Game.STATE.GAME;
				}
			}
		}
		if (Game.State == Game.STATE.MENU) {
			// Quit Button
			if (mx >= 286 && mx <= 486) {
				if (my >= 500 && my <= 600) {
					// Pressed Quit Button
					System.exit(1);
				}
			}
		}
		if (Game.State == Game.STATE.PAUSE) {
			// Resume Button
			if (mx >= 140 && mx <= 640) {
				if (my >= 300 && my <= 500) {
					// Pressed Resume Button
					Game.State = Game.STATE.GAME;
				}
			}
		}
		if (Game.State == Game.STATE.MENU) {
			// Help Button
			if (mx >= 286 && mx <= 486) {
				if (my >= 350 && my <= 450) {
					// Pressed Help Button
					Game.State = Game.STATE.HELP;
				}
			}
		}
		if (Game.State == Game.STATE.CONTROLS) {
			// Back Button
			if (mx >= 286 && mx <= 486) {
				if (my >= 630 && my <= 730) {
					// Pressed Back Button
					Game.State = Game.STATE.MENU;
				}
			}
		}
		if (Game.State == Game.STATE.GAMEOVER) {
			// PlayAgain Button
			if (mx >= 175 && mx <= 600) {
				if (my >= 400 && my <= 525) {
					// Pressed Play Button
					Game.State = Game.STATE.GAME;
					System.out.println("STATE CHANGED");
					Game.HEALTH = 200;
				}
			}
		}
	public void mouseReleased(MouseEvent arg0) {
	}
}
