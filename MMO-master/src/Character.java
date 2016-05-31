import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

//Character selection.
public class Character {
	public static String raceClass;
	static String[] factions = { "blackguard", "moonshadow" };
	static String[] classes = { "mage", "archer", "warrior" };
	public Rectangle createCharacter = new Rectangle(70, 270, 450, 100);
	public Rectangle selectCharacter = new Rectangle(750, 270, 450, 100);
	//Each character must have the following:
	static String chosenFaction;
	public BufferedImage background;
	
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g; //Initialize the 2D Graphics renderer.
		FontLoader.loadFont("./Minecraft.ttf");
		background = imageLoader.imageLoader("./menuBackground.png");
		g.drawImage(background, 0, 0, Game.WIDTH, Game.HEIGHT, null);
		Font currentFont = new Font("Minecraft",Font.PLAIN, 93);
		g.setFont(currentFont);
		g.setColor(Color.GRAY);
		g.fillRect(70, 270, 450, 100);
		g.fillRect(750, 270, 450, 100);
		g.setColor(Color.WHITE);
		g.drawString("CREATE", 100, 350);
		g.drawString("SELECT", 800, 350);
		g.setColor(Color.BLACK);
		g2d.draw(createCharacter);
		g2d.draw(selectCharacter);
		
	}
	public static void main(String[] args) throws IOException {
		String name;
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Type 'SELECT' to select a pre-existing character or\ntype 'CREATE' to create/edit a character.");
		String response = scanner.nextLine().toUpperCase();
		switch(response) {
		case "SELECT": //Select characters.
			System.out.println("Enter character name:");
			name = scanner.nextLine();
			String flPath = "T:/CEN-ICS3U1-1/COMMON/MMORPG/characters/" + name + "/" +"fl.txt"; //first last
			String classPath = "T:/CEN-ICS3U1-1/COMMON/MMORPG/characters/" + name + "/" +"class.txt";
			String factionPath = "T:/CEN-ICS3U1-1/COMMON/MMORPG/characters/" + name + "/" +"faction.txt";
			String retrievedName = new String(Files.readAllBytes(Paths.get(flPath)));
			String retrievedClass = new String(Files.readAllBytes(Paths.get(classPath)));
			String retrievedFaction = new String(Files.readAllBytes(Paths.get(factionPath)));
			System.out.println("Character Stats:");
			System.out.println("Name: " + retrievedName);
			System.out.println("Class: " + retrievedClass);
			System.out.println("Faction: " + retrievedFaction);
			break;
		case "CREATE":
			
			System.out.println("Name:");
		    name = scanner.nextLine();
		    String writeFLPath = "T:/CEN-ICS3U1-1/COMMON/MMORPG/characters/" + name + "/" + "fl.txt";
			System.out.println("Enter Faction: ");
			chosenFaction = scanner.nextLine().toLowerCase();
			String writeFCPath = "T:/CEN-ICS3U1-1/COMMON/MMORPG/characters/" + name + "/" + "faction.txt";
			System.out.println("Class: ");
			String writeCPath = "T:/CEN-ICS3U1-1/COMMON/MMORPG/characters/" + name + "/" + "class.txt";
			raceClass = scanner.nextLine().toLowerCase();
			System.out.println(name + "\n" + chosenFaction + "\n" + raceClass);
			scanner.close();
			
			File flFile = new File (writeFLPath);
			flFile.getParentFile().mkdirs();
			PrintWriter writer = new PrintWriter(flFile);
			writer.println(name);
			// ------------------------
			File FCFile = new File (writeFCPath);
			FCFile.getParentFile().mkdirs();
			PrintWriter writer2 = new PrintWriter(FCFile);
			writer2.println(chosenFaction);
			//--------------------------
			File CFile = new File (writeCPath);
			CFile.getParentFile().mkdirs();
			PrintWriter writer3 = new PrintWriter(CFile);
			writer3.println(raceClass);
			writer.close();
			writer2.close();
			writer3.close();
			System.out.println("You have created the character " + name + " in faction " + chosenFaction + " and class " + raceClass);
			if(flFile.exists() && !flFile.isDirectory()) { System.out.println("Stored bytes\n"); }
			break;
		}
		
	}
}