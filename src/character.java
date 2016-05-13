import java.util.Scanner;

//Character selection.
public class character {
	String[] skins = { "white", "hispanic", "simpson", "black"};
	String[] hairColour = { "blonde", "brown", "black", "white", "red" };
	String[] shirt = { "white", "red", "blue", "yellow", "pink", "purple", "grey", "green" };
	String[] pants = { "lt_blue", "dk_blue", "black", "white", "brown" };
	String[] gender = { "xx", "xy" };
	
	//Each character must have the following:
	static String chosenSkin;
	static String chosenHair;
	static String chosenShirt;
	static String chosenPants;
	static String chosenGender;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Skin: ");
		chosenSkin = scanner.nextLine();
		System.out.println("Hair: ");
		chosenHair = scanner.nextLine();
		System.out.println("Shirt: ");
		chosenShirt = scanner.nextLine();
		System.out.println("Pants:");
		chosenPants = scanner.nextLine();
		System.out.println("Male or Female?:");
		chosenGender = scanner.nextLine();
		
		System.out.println(chosenSkin + "\n" + chosenHair + "\n" + chosenShirt + "\n" + chosenPants + "\n" + chosenGender);
		scanner.close();
	}
	
}
