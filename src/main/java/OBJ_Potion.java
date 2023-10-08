import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Potion extends Entity{

    public OBJ_Potion(GamePanel gp) {
        super(gp);

        name = "Potion";
        image = setup("/objects/potion");
    }
}
