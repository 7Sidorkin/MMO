import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game implements Runnable, KeyListener, MouseListener {

	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	public final String TITLE = "MMO";
	private boolean running = false;
	private Thread th;
	public static Renderer renderer;
	public static Menu menu;
	public static Controls controls;
	public static Game game;
	public Rectangle mouse;

	public int mouseX, mouseY;

	private BufferedImage images = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

	public static enum STATE {
		MENU, GAME, PAUSE, CONTROLS, CHARACTER,
	};

	public static STATE State = STATE.MENU;

	///////////////////////////////////////////////////////////////////////////

	public void init() {
		// requestFocus();
		// imageLoader loader = new imageLoader();
		// add all images here by going
		// "image name" = loader.loadImage("/nameoffile.png");
		// addKeyListener(new KeyInput(this));
		// this.addMouseListener(new MouseInput());
	}

	public Game() {
		menu = new Menu();
		renderer = new Renderer();
		controls = new Controls();
		mouse = new Rectangle();

	}

	private synchronized void start() {
		// If the program is already running then do nothing but if not running,
		// make it run and start the thread
		if (running)
			return;
		running = true;
		th = new Thread(this);
		th.start();
	}

	private synchronized void stop() {
		if (!running)
			return;
		running = false;
		try {
			th.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
	}

	public void run() {
		init();
		long lastTime = System.nanoTime();
		final double numberOfTicks = 60.0;
		double ns = 1000000000 / numberOfTicks;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				tick();
				delta--;
				updates++;
			}
			// ();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("Ticks: " + updates + "      Frames Per Second(FPS): " + frames);
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}

	private void tick() {
		renderer.repaint();

	}

	static void render(Graphics g) {
		if (game.State == State.MENU) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, WIDTH, HEIGHT);
			menu.render(g);
		}
		if(game.State == State.CONTROLS){
			controls.render(g);
		}
	}

	public static void main(String[] args) {
		// Creates new instance of Game

		game = new Game();
		// Sets the maximum, minimum and preferred sizes of the JFrame
		/*
		 * game.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		 * game.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		 * game.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		 */

		// Sets the title of the JFrame
		JFrame frame = new JFrame(game.TITLE);
		// Ads the instance of the game to the JFrame
		// frame.add(game);
		// Causes the window to be at preferred size initially
		frame.setSize(WIDTH, HEIGHT);
		// frame.pack();
		// Exits program on close
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Sets the program so it cannot be re-sizable
		frame.setResizable(false);
		frame.add(renderer);
		// Disables the setLocationRelativeTo function
		frame.setLocationRelativeTo(null);
		frame.addKeyListener(game);
		frame.addMouseListener(game);
		// Makes the JFrame visible
		frame.setVisible(true);
		// Starts the instance
		game.start();

	}

	public void keyPressed(KeyEvent e) {
		if(game.State == State.CONTROLS){
			if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
				game.State = State.MENU;
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		mouse.setBounds(e.getX(), e.getY(), 1, 1);
		if (game.State == State.MENU) {
			if (menu.playButton.contains(mouse)) {
				game.State = State.GAME;
			}
			if (menu.quitButton.contains(mouse)) {
				System.exit(1);
			}
			if (menu.helpButton.contains(mouse)) {
				game.State = State.CONTROLS;
			}
			if (menu.characterMenu.contains(mouse)){
				game.State = State.CHARACTER;
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
