import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Key extends Entity{

    public OBJ_Key(GamePanel gp) {
        super(gp);

        name = "Key";
        forward1 = setup("/objects/key");
    }
}
