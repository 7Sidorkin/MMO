import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Imageloader {
	public static BufferedImage imageLoader(String path){
		BufferedImage temp = null;
		try {
			temp = ImageIO.read(new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("error");
		}
		return temp;
		
	}
}
