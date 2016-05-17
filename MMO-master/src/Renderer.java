import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Renderer extends JPanel {

	private static final long serialVersionUID = 1L;

	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		Game.render((Graphics2D) g);
	}
}