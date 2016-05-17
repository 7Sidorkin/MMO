import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;


public class FontLoader {
private static GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

public static void loadFont(String path){
	try{
		ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(path)));
	
	}catch (FontFormatException | IOException e){
		e.printStackTrace();
		
	}
}
	
}