import java.awt.image.BufferedImage;

public class TextureAtlas {
    BufferedImage image;
    public TextureAtlas(String imageName) {
        image=ResourceLoader.loadImage(imageName);
    }
    public BufferedImage cut(int x, int y, int width, int height) {
        return image.getSubimage(x, y, width, height);
    }
}
