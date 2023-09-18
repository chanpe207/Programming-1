import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Potion extends SuperObject{

    public OBJ_Potion() {

        name = "Potion";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/potion.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
