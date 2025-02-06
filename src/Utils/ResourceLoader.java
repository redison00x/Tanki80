import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ResourceLoader {
    public static final String Path = "resource/";
    public static BufferedImage loadImage(String fileName) {

        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(Path + fileName));

        }catch (IOException e) {
            e.printStackTrace();
        }


        return image;
    }

}
