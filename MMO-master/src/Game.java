import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.PrintWriter;
import java.util.Random;

import javax.swing.JFrame;

public class Game implements Runnable, KeyListener, MouseListener {

	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	public final String TITLE = "MMO";
	private boolean running = false;
	private Thread th;
	public Player player1, player2;
	public static Renderer renderer;
	public static Menu menu;
	public static Controls controls;
	public static Character character;
	public static CharacterFaction faction;
	public static CharacterClass race;
	public static CharacterFaction createFaction;
	public static CharacterFinal finalChoice;

	public static int powerUpTick = 2700;
	
	public static Random random;
	
	public int powerTicks = 2700;
	
	public static Game game;
	public Rectangle mouse;
	public Player player;
	static PrintWriter writer;
	public Handler handler;
	public int mouseX, mouseY;
	public static int winner;

	public static boolean powerUpB = false;
	
	public static BufferedImage background;

	public BufferedImage rMage, rArcher, rHeavy, bMage, bHeavy, bArcher;

	private BufferedImage images = new BufferedImage(WIDTH, HEIGHT,
			BufferedImage.TYPE_INT_RGB);

	public static enum STATE {
		MENU, GAME, PAUSE, CONTROLS, CHARACTER, CHARACTER_FACTION, CHARACTERCREATE_CLASS, CHARACTERCREATE_NAME, CHARACTERSELECT, WIN
	};

	public static STATE State = STATE.GAME;

	// /////////////////////////////////////////////////////////////////////////

	public void init() {
		menu = new Menu();
		controls = new Controls();
		mouse = new Rectangle();
		player1 = new Player(Player.PLAYERTYPE.MAGE, 1);
		player2 = new Player(Player.PLAYERTYPE.ARCHER, 2);
		player1.setPlayerType(Player.PLAYERTYPE.HEAVY, 1);
		player2.setPlayerType(Player.PLAYERTYPE.ARCHER, 2);
		rMage = player1.rMageSI[1];
		rArcher = player1.rArcherSI[1];
		rHeavy = player1.rHeavySI[1];
		bMage = player1.bMageSI[1];
		bArcher = player1.bArcherSI[1];
		bHeavy = player1.bHeavySI[1];
		handler = new Handler();
		random = new Random();
		character = new Character();
		createFaction = new CharacterFaction();
		background = imageLoader
				.imageLoader("./MMO-master/src/grahpics/arenaSet.jpg");
	}

	public Game() {
		renderer = new Renderer();
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
				System.out.println("Ticks: " + updates
						+ "      Frames Per Second(FPS): " + frames);
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}

	private void tick() {
		renderer.repaint();
		game.player1.tick();
		game.player2.tick();
		handler.tick();
		powerTicks++;
		if(powerTicks > powerUpTick && powerUpB == false){
			powerUp powerUp = new powerUp(random.nextInt(1280),random.nextInt(720), ID.PowerUp );
			this.handler.addObject(powerUp);
			powerUpB = true;
			powerTicks = 0;
			System.out.println("should be spawning");
		}
			
	}

	static void render(Graphics2D g) {
		if (Game.State == STATE.MENU) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, WIDTH, HEIGHT);
			menu.render(g);
		}
		if (Game.State == STATE.CONTROLS) {
			controls.render(g);
		}
		if (Game.State == STATE.GAME) {
			g.drawImage(background, 0, 0, 1280, 720, null);
			game.player1.render(g);
			game.player2.render(g);
			game.handler.render(g);
		}
		if(Game.State == STATE.CHARACTER) {
			Game.character.render(g);
		}
		if(Game.State == STATE.CHARACTER_FACTION) {
			Game.createFaction.render(g);
		}
		if(Game.State == STATE.CHARACTERCREATE_CLASS) {
			Game.race.render(g);
		}
		if (Game.State == STATE.WIN) {
			g.setFont(new Font("Minecraft", Font.PLAIN, 93));
			g.setColor(Color.BLACK);
			g.drawString("Player" + Game.winner + " WINS", 0, 100);
		}
	}

	public static void main(String[] args) {

		/*
		 * File file = new File
		 * ("./MMO-master/src/grahpics/blackguard/Mage/test.txt"); try { writer
		 * = new PrintWriter(file); } catch (FileNotFoundException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 * writer.println("test");
		 */

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
		if (Game.State == STATE.CONTROLS || Game.State == STATE.WIN) {
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				Game.State = STATE.MENU;
			}
		}
		if (Game.State == STATE.GAME) {
			game.player1.control(e, true);
			game.player2.control(e, true);
		}
	}

	public void keyReleased(KeyEvent e) {
		if (Game.State == STATE.GAME) {
			game.player1.control(e, false);
			game.player2.control(e, false);
		}

	}

	public void mouseClicked(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		mouse.setBounds(e.getX(), e.getY(), 1, 1);
	switch(Game.State) {
		case MENU:
			if (menu.playButton.contains(mouse)) {
				player1.health = 100;
				player2.health = 100;
				player1.x = 100;
				player2.x = 700;
				player1.y = 100;
				player2.y = 100;

				player1.pointing = 0;
				player2.pointing = 0;

				Game.State = STATE.GAME;
			}
			break;
			
		case CHARACTER:
			if (character.createCharacter.contains(mouse)){
				Game.State = STATE.CHARACTER_FACTION;
				System.out.println("Running state: CharacterCreate_FACTION");
			}
			break;
		case CHARACTER_FACTION:
			if(createFaction.blackguardRect.contains(mouse)) {
				Character.chosenFaction = "blackguard";
				Game.State = STATE.CHARACTERCREATE_CLASS;
			}
			if(createFaction.moonshadowRect.contains(mouse)) {
				Character.chosenFaction = "moonshadow";
				Game.State = STATE.CHARACTERCREATE_CLASS;
			}
			break;
		case CHARACTERCREATE_CLASS:
				
			break;
		
		case CHARACTERCREATE_NAME:
				
			default:
				Game.State = STATE.MENU;
				break;
			
		}
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}
}
