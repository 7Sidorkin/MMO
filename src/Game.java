import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable{
	
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	public final String TITLE = "MMO";
	private boolean running = false;
	private Thread th;
	
	private BufferedImage images = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
	
	public static enum STATE{
		MENU,
		GAME,
		PAUSE,
		CONTROLS,
		CHARACTER,
	};
	
	public static STATE State = STATE.MENU;
	
///////////////////////////////////////////////////////////////////////////	
	
	public void init(){
		requestFocus();
		imageLoader loader = new imageLoader();
		//add all images here by going
		//"image name" = loader.loadImage("/nameoffile.png");
		addKeyListener(new KeyInput(this));
		this.addMouseListener(new MouseInput());
	}
	
	private synchronized void start(){
		//If the program is already running then do nothing but if not running, make it run and start the thread
		if(running)
			return;
		running = true;
		th = new Thread(this);
		th.start();
	}
	private synchronized void stop(){
		if(!running)
			return;
		running = false;
		try {
			th.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
	}
	public void run(){
		init();
		long lastTime = System.nanoTime();
		final double numberOfTicks = 60.0;
		double ns = 1000000000 / numberOfTicks;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1){
				//tick();
				delta--;
				updates++;
			}
			//render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("Ticks: " + updates + "      Frames Per Second(FPS): " + frames);
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}
	
	public static void main(String[] args) {
		//Creates new instance of Game
				
				Game game = new Game();
				//Sets the maximum, minimum and preferred sizes of the JFrame
				game.setPreferredSize(new Dimension(WIDTH, HEIGHT));
				game.setMaximumSize(new Dimension(WIDTH, HEIGHT));
				game.setMinimumSize(new Dimension(WIDTH, HEIGHT));
				
				//Sets the title of the JFrame
				JFrame frame = new JFrame(game.TITLE);
				//Ads the instance of the game to the JFrame
				frame.add(game);
				//Causes the window to be at preferred size initially
				frame.pack();
				//Exits program on close
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				//Sets the program so it cannot be re-sizable
				frame.setResizable(false);
				//Disables the setLocationRelativeTo function
				frame.setLocationRelativeTo(null);
				//Makes the JFrame visible
				frame.setVisible(true);
				//Starts the instance
				//game.start();
		
	}

}