import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Cookie extends Entity{

    public OBJ_Cookie(GamePanel gp) {
        super(gp);

        name = "Cookie";
        forward1 = setup("/objects/cookie", gp.tileSize, gp.tileSize);
        type = type_consumable;
        description = "Cookie heals your life by 1 heart";
    }
    public void use(){
        consumed = true; //updates in gp so this obj is null
        gp.playSE(2); //play sound effect
        if(gp.player.life < gp.player.maxLife) {
            gp.player.life ++; //player life increases by 1
            if(gp.player.life < gp.player.maxLife) {
                gp.player.life ++; //player life increases by 1
            }
        }
        gp.ui.displayedText = description; //shows description
        gp.ui.textDisplayed = true;
    }
}
