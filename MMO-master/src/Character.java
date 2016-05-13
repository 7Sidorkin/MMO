package _character;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

//Character selection.
public class Character {
	String[] faction = { "blackguard", "moonshadow" };
	
	//Each character must have the following:
	static String chosenFaction;

	public static void main(String[] args) throws IOException {
		String name;
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Type 'SELECT' to select a pre-existing character or\ntype 'CREATE' to create/edit a character.");
		String response = scanner.nextLine().toUpperCase();
		switch(response) {
		case "SELECT":
			System.out.println("Enter character name:");
			name = scanner.nextLine();
			String path = "T:/CEN-ICS3U1-1/COMMON/MMORPG/characters/" + name + ".txt";
			Scanner fileRead = new Scanner(path);
			String content = new String(Files.readAllBytes(Paths.get(path)));
			System.out.println(content);
			break;
		case "CREATE":
			System.out.println("Name:");
		    name = scanner.nextLine();
		    String path2 = "T:/CEN-ICS3U1-1/COMMON/MMORPG/characters/" + name + ".txt";
			System.out.println("Faction: ");
			chosenFaction = scanner.nextLine().toLowerCase();
			System.out.println(name + "\n" + chosenFaction);
			scanner.close();
			
			File file = new File (path2);
			PrintWriter writer = new PrintWriter(file);
			file.getParentFile().mkdirs();
			writer.println(name);
			writer.println(chosenFaction);
			writer.close();
			System.out.println("You have created the character " + name);
			if(file.exists() && !file.isDirectory()) { System.out.println("Stored bytes\nPath: " + path2); }
			break;
		}
		
	}
}