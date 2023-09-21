import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Cookie extends SuperObject{

    GamePanel gp;

    public OBJ_Cookie(GamePanel gp) {
        this.gp = gp;

        name = "Cookie";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/cookie.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
