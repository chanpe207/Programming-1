import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Cookie extends Entity{

    public OBJ_Cookie(GamePanel gp) {
        super(gp);

        name = "Cookie";
        forward1 = setup("/objects/cookie");
    }
}
