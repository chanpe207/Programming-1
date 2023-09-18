import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Cookie extends SuperObject{

    public OBJ_Cookie() {

        name = "Cookie";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/cookie.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
