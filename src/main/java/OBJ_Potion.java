import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Potion extends SuperObject{

    GamePanel gp;
    public OBJ_Potion(GamePanel gp) {
        this.gp = gp;

        name = "Potion";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/potion.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
