import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class imageLoader {
	public static BufferedImage imageLoader(String path){
		BufferedImage temp = null;
		try {
			temp = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Cannot find image");
		}
		return temp;
		
	}
}
