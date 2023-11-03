import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Chest extends Entity{

    public OBJ_Chest(GamePanel gp) {
        super(gp);

        name = "Chest";
        type = type_interactable;
        forward1 = setup("/objects/chest", gp.tileSize, gp.tileSize);
    }

    public void use() {
        //check if key is available
        if(gp.player.hasKey>0) {
            //use key to open
            gp.player.hasKey--;
            gp.player.playerScore+=150;
            gp.playSE(10);
            consumed = true;
            gp.player.numChests--;
        }
        else {
            gp.ui.displayedText = "You need a key to open this!";
            gp.ui.textDisplayed = true;
        }

    }

    public void checkDrop () {
        dropItem(new OBJ_Key(gp));
    }
}
