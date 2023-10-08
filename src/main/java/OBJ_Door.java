import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class OBJ_Door extends Entity{

    public OBJ_Door(GamePanel gp) {
        super(gp);

        name = "Door";
        image = setup("/objects/door");
        collision = true;

        // Collision area of door
        solidArea = new Rectangle();
        solidArea.x = 0 * gp.scale;
        solidArea.y = 2 * gp.scale;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = gp.tileSize;
        solidArea.height = 14 * gp.scale;
    }
}
