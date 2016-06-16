import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.JFrame;

public class Game implements Runnable, KeyListener, MouseListener,
		MouseMotionListener {
	public static JFrame frame;
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
	public static CharacterFinal characterFinal;

	public static int powerUpTick = 2700;// was 2700

	public static Random random;

	public int powerTicks = 1000;

	public int badTicks1 = 0, badTicks2 = 0;

	public static Game game;
	public Rectangle mouse;
	public Player player;
	static PrintWriter writer;
	public Handler handler;
	public int mouseX, mouseY;
	public static int winner;

	public boolean playButton, helpButton, quitButton;

	public int playerCount = 0;

	public int factionNum = 1;

	public static boolean powerUpB = false;

	public static BufferedImage background;

	public BufferedImage rMage, rArcher, rHeavy, bMage, bHeavy, bArcher;

	public File music = new File("./backgroundMusic.wav");

	public File moonWalk = new File("./moonWalk.wav");

	public int musicTime = 2400;

	private BufferedImage images = new BufferedImage(WIDTH, HEIGHT,
			BufferedImage.TYPE_INT_RGB);

	public static enum STATE {
		MENU, GAME, PAUSE, CONTROLS, CHARACTER, CHARACTER_FACTION, CHARACTERCREATE_CLASS, CHARACTERCREATE_NAME, CHARACTERSELECT, WIN
	};

	public static STATE State = STATE.MENU;

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
		race = new CharacterClass();
		characterFinal = new CharacterFinal();
		PlayMusic(music);
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
		final double numberOfTicks = 120.0;
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
		powerTicks++;
		musicTime++;
		if (powerTicks > powerUpTick && powerUpB == false) {
			if (random.nextInt(2) == 0) {
				powerUp powerUp = new powerUp(random.nextInt(1200),
						random.nextInt(700), ID.PowerUp);
				this.handler.addObject(powerUp);
			} else {
				powerUp powerUp = new powerUp(random.nextInt(1280),
						random.nextInt(720), ID.Speed);
				this.handler.addObject(powerUp);
			}
			powerUpB = true;
			powerTicks = 0;
			System.out.println("should be spawning");
		}
		if (Game.State == STATE.MENU) {
			if (mouse.intersects(menu.playButton)) {
				System.out.println("yay");
				playButton = true;
			} else {
				playButton = false;
			}
			if (mouse.intersects(menu.helpButton)) {
				helpButton = true;
			} else {
				helpButton = false;
			}
			if (mouse.intersects(menu.quitButton)) {
				quitButton = true;
			} else {
				quitButton = false;
			}
		}

		if ((player1.pointing == 1 && player1.motionY > 0)
				|| (player1.pointing == 2 && player1.motionX < 0)
				|| (player1.pointing == 3 && player1.motionY < 0)
				|| (player1.pointing == 14 && player1.motionX > 0)) {

			badTicks1++;
		}

		if ((player2.pointing == 1 && player2.motionY > 0)
				|| (player2.pointing == 2 && player2.motionX < 0)
				|| (player2.pointing == 3 && player2.motionY < 0)
				|| (player2.pointing == 14 && player2.motionX > 0)) {
			badTicks2++;
		}
		if(badTicks1 > 60){
			player1.motionX = 0;
			player1.motionY = 0;
			badTicks1 = 0;
		}
		if(badTicks2 > 60){
			player2.motionX = 0;
			player2.motionY = 0;
			badTicks2 = 0;
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
			g.setFont(new Font("Arial", 1, 20));
			if(game.player1.faction == 1){g.setColor(Color.red);}else{g.setColor(Color.blue);}
			g.drawString("Player 1", 0, 20);
			g.fillRect(0, 20, (int) (((game.player1.health / game.player1.maxHealth) * 100) *2), 20);
			//System.out.println((game.player1.health / game.player1.maxHealth) * 100);
			float tack1width = (System.currentTimeMillis() - game.player1.tack1Start)/ game.player1.cool1;
			if( tack1width * 100 > 99){
				g.drawString("Attack Ready", 0, 60);
			}
			if(game.player1.type != Player.PLAYERTYPE.HEAVY){
			float tack2width = (System.currentTimeMillis() - game.player1.tack2Start)/ game.player1.cool2;
			if( tack2width * 100 > 99){
				g.drawString("Ability 1 Ready", 0, 80);
			}
			}
			float tack3width = (System.currentTimeMillis() - game.player1.tack3Start)/ game.player1.cool3;
			if( tack3width * 100 > 99){
				g.drawString("Ability 2 Ready", 0, 100);
			}
			if (game.player1.speed > 2){
				g.drawString("Speed Boost", 0, 120);
			}
			if(game.player2.faction == 1){g.setColor(Color.red);}else{g.setColor(Color.blue);}
			float tack1width2 = (System.currentTimeMillis() - game.player2.tack1Start)/ game.player2.cool1;
			if( tack1width2 * 100 > 99){
				g.drawString("Attack Ready", 1080, 60);
			}
			if(game.player2.type != Player.PLAYERTYPE.HEAVY){
			float tack2width2 = (System.currentTimeMillis() - game.player2.tack2Start)/ game.player2.cool2;
			if( tack2width2 * 100 > 99){
				g.drawString("Ability 1 Ready", 1080, 80);
			}
			}
			float tack3width2 = (System.currentTimeMillis() - game.player2.tack3Start)/ game.player2.cool3;
			if( tack3width2 * 100 > 99){
				g.drawString("Ability 2 Ready",1080, 100);
			}
			if (game.player2.speed > 2){
				g.drawString("Speed Boost", 1080, 120);
			}
			g.setFont(new Font("Arial", 1, 20));
			g.drawString("Player 2", 1080, 20);
			g.fillRect(1080, 20, (int) (((game.player2.health / game.player2.maxHealth) * 100) *2), 20);
			
			
		}
		if (Game.State == STATE.CHARACTER) {
			Game.character.render(g);
		}
		if (Game.State == STATE.CHARACTER_FACTION) {
			Game.createFaction.render(g);
		}
		if (Game.State == STATE.CHARACTERCREATE_CLASS) {
			Game.race.render(g);
		}
		if (Game.State == STATE.CHARACTERCREATE_NAME) {
			Game.characterFinal.render(g);

		}
		if (Game.State == STATE.WIN) {
			g.drawImage(background, 0, 0, 1280, 720, null);
			g.setFont(new Font("Minecraft", Font.PLAIN, 93));
			g.setColor(Color.BLACK);
			g.drawString("Player" + Game.winner + " WINS", 350, 300);
		}
	}

	public static void main(String[] args) {

		File file = new File("./test.txt");
		try {
			writer = new PrintWriter(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		writer.println("test");

		// Creates new instance of Game

		game = new Game();
		// Sets the maximum, minimum and preferred sizes of the JFrame
		/*
		 * game.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		 * game.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		 * game.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		 */

		// Sets the title of the JFrame
		frame = new JFrame(game.TITLE);
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
		frame.addMouseMotionListener(game);

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
		switch (Game.State) {
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
				playerCount = 0;

				Game.State = STATE.CHARACTER_FACTION;
			}
			if (menu.quitButton.contains(mouse)) {
				System.exit(1);
			}
			if (menu.helpButton.contains(mouse)) {
				Game.State = STATE.CONTROLS;
			}
			break;

		case CHARACTER:
			if (character.createCharacter.contains(mouse)) {
				Game.State = STATE.CHARACTER_FACTION;
				System.out.println("Running state: CharacterCreate_FACTION");
			}
			break;
		case CHARACTER_FACTION:
			if (createFaction.blackguardRect.contains(mouse)) {
				Character.chosenFaction = "blackguard";
				Game.State = STATE.CHARACTERCREATE_CLASS;
			}
			if (createFaction.moonshadowRect.contains(mouse)) {
				Character.chosenFaction = "moonshadow";
				Game.State = STATE.CHARACTERCREATE_CLASS;
			}
			break;
		case CHARACTERCREATE_CLASS:
			switch (Character.chosenFaction) {
			case "moonshadow":
				if (race.bMageRect.contains(mouse)) {
					playerCount++;
					Game.State = STATE.CHARACTERCREATE_CLASS;
					Character.chosenFaction = "blackguard";
					Character.raceClass = "bmage";
					System.out.println(Game.State + " " + Character.raceClass);
					if (playerCount == 1) {
						player1.setPlayerType(Player.PLAYERTYPE.MAGE, 2);
					}
					if (playerCount == 2) {
						player2.setPlayerType(Player.PLAYERTYPE.MAGE, 2);
						Game.State = STATE.GAME;
					}

				}
				if (race.bHeavyRect.contains(mouse)) {
					playerCount++;
					Game.State = STATE.CHARACTERCREATE_CLASS;
					Character.chosenFaction = "blackguard";
					Character.raceClass = "bheavy";
					System.out.println(Game.State + " " + Character.raceClass);
					if (playerCount == 1) {
						player1.setPlayerType(Player.PLAYERTYPE.HEAVY, 2);
					}
					if (playerCount == 2) {
						player2.setPlayerType(Player.PLAYERTYPE.HEAVY, 2);
						Game.State = STATE.GAME;
					}
				}
				if (race.bArcherRect.contains(mouse)) {
					playerCount++;
					Game.State = STATE.CHARACTERCREATE_CLASS;
					Character.chosenFaction = "blackguard";
					Character.raceClass = "barcher";
					System.out.println(Game.State + " " + Character.raceClass);
					if (playerCount == 1) {
						player1.setPlayerType(Player.PLAYERTYPE.ARCHER, 2);
					}
					if (playerCount == 2) {
						player2.setPlayerType(Player.PLAYERTYPE.ARCHER, 2);
						Game.State = STATE.GAME;
					}
				}
				break;
			case "blackguard":
				if (race.rMageRect.contains(mouse)) {
					playerCount++;
					Game.State = STATE.CHARACTERCREATE_CLASS;
					Character.chosenFaction = "moonshadow";
					Character.raceClass = "rmage";
					System.out.println(Game.State + " " + Character.raceClass);
					if (playerCount == 1) {
						player1.setPlayerType(Player.PLAYERTYPE.MAGE, 1);
					}
					if (playerCount == 2) {
						player2.setPlayerType(Player.PLAYERTYPE.MAGE, 1);
						Game.State = STATE.GAME;
					}
				}
				if (race.rHeavyRect.contains(mouse)) {
					playerCount++;
					Game.State = STATE.CHARACTERCREATE_CLASS;
					Character.chosenFaction = "moonshadow";
					Character.raceClass = "rheavy";
					System.out.println(Game.State + " " + Character.raceClass);
					if (playerCount == 1) {
						player1.setPlayerType(Player.PLAYERTYPE.HEAVY, 1);
					}
					if (playerCount == 2) {
						player2.setPlayerType(Player.PLAYERTYPE.HEAVY, 1);
						Game.State = STATE.GAME;
					}
				}
				if (race.rArcherRect.contains(mouse)) {
					playerCount++;
					Game.State = STATE.CHARACTERCREATE_CLASS;
					Character.chosenFaction = "moonshadow";
					Character.raceClass = "rarcher";
					System.out.println(Game.State + " " + Character.raceClass);
					if (playerCount == 1) {
						player1.setPlayerType(Player.PLAYERTYPE.ARCHER, 1);
					}
					if (playerCount == 2) {
						player2.setPlayerType(Player.PLAYERTYPE.ARCHER, 1);
						Game.State = STATE.GAME;
					}
				}
				break;
			}

		case CHARACTERCREATE_NAME:
			System.out.println(Game.State);
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

	@Override
	public void mouseDragged(MouseEvent e) {
		mouse.setLocation(e.getX(), e.getY());

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouse.setLocation(e.getX(), e.getY());

	}

	static void PlayMusic(File Sound) {
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(Sound));

			clip.loop(clip.LOOP_CONTINUOUSLY);
		} catch (Exception e) {
			System.out.println("help");
			e.printStackTrace();
		}
	}

	static void PlaySound(File Sound) {
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(Sound));

			clip.start();
		} catch (Exception e) {
			System.out.println("help");
			e.printStackTrace();
		}
	}

}