import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Key extends Entity{

    public OBJ_Key(GamePanel gp) {
        super(gp);

        name = "Key";
        type = type_pickUpOnly;
        forward1 = setup("/objects/key", gp.tileSize, gp.tileSize);
        description = "Key can open door or chest!";

    }

    public void pickUp(){
        gp.player.hasKey++;
        gp.playSE(9);
        if(gp.player.hasKey>1){
            gp.ui.displayedText = "You have "+gp.player.hasKey+" keys";
        }
        else {
            gp.ui.displayedText = "You have 1 key";
        }
        gp.ui.textDisplayed = true;
        consumed = true;
    }


}
