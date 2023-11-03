import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class OBJ_Door extends Entity{

    public OBJ_Door(GamePanel gp) {
        super(gp);

        name = "Door";
        type = type_interactable;
        forward1 = setup("/objects/door", gp.tileSize, gp.tileSize);
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

    public void use() {
        //check if key is available
        if(gp.player.hasKey>0) {
            //use key to open
            gp.player.hasKey--;
            gp.playSE(10);
            consumed = true;
        }
        else {
            gp.ui.displayedText = "You need a key to open this!";
            gp.ui.textDisplayed = true;
        }

    }
}
