import java.awt.image.BufferedImage;

public class SpriteSheetReader {

	private BufferedImage[] sprites;
	int size = 32;

	public SpriteSheetReader() {

	}

	public BufferedImage[] getSprites(int num, BufferedImage bigImg) {
		sprites = new BufferedImage[num];
		for (int j = 0; j < num; j++) {
			sprites[j] = bigImg.getSubimage(j * size, 0, size, size);
		}

		return sprites;
	}
}
