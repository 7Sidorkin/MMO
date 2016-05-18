import java.awt.image.BufferedImage;

public class SpriteSheetReader {

	private BufferedImage[] sprites;

	public SpriteSheetReader() {

	}

	public BufferedImage[] getSprites(int num, BufferedImage bigImg) {
		sprites = new BufferedImage[num];
		for (int j = 0; j < num; j++) {
			sprites[j] = bigImg.getSubimage(j * 32, 0, 32, 32);
		}

		return sprites;
	}
}
