import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Potion extends Entity{

    public OBJ_Potion(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = type_consumable;
        name = "Potion";
        forward1 = setup("/objects/potion", gp.tileSize, gp.tileSize);
        description = "Potion speeds you up!";
    }
    public void use(){
        consumed = true; //updates in gp so this obj is null
        gp.player.effect = effect_speedIncrease; //player speed doubled for 10s
        gp.player.effectOn = true;
        gp.ui.displayedText = description; //shows description
        gp.ui.textDisplayed = true;
    }
}
