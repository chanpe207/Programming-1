import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class UI {

    GamePanel gp;
    Font courier_30;
    BufferedImage keyImage;
    BufferedImage treasureImage;
    BufferedImage potionImage;
    BufferedImage textboxImage;
    public boolean messageOn = false;
    public String message = "";
    public int textboxWidth = 300;
    public int textboxHeight = 90;
    public int statsboxWidth = 90;
    public int statsboxHeight = 162;
    public int statsboxArc = 10;
    public int messageCounter = 0;
    public int messageLength;
    public int messageHeight;

    public UI(GamePanel gp) {
        this.gp = gp;

        courier_30 = new Font("Courier", Font.BOLD, 20);

        OBJ_Key key = new OBJ_Key(gp);
        keyImage = key.image;

        OBJ_Chest treasure = new OBJ_Chest(gp);
        treasureImage = treasure.image;

        OBJ_Potion potion = new OBJ_Potion(gp);
        potionImage = potion.image;

        try{
            textboxImage = ImageIO.read(getClass().getResourceAsStream("/boxes/textbox.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void showMessage(String text) {

        message = text;
        messageOn = true;

    }

    public void draw(Graphics2D g2) {
        // statsbox
        g2.setColor(Color.black);
        g2.fillRoundRect(gp.tileSize/4, gp.tileSize/4, statsboxWidth, statsboxHeight, statsboxArc, statsboxArc);

        // no. of lives


        // text
        g2.setFont(courier_30);
        g2.setColor(Color.white);

        // no. of keys
        g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize/2, gp.tileSize/2, null);
        g2.drawString("x " + gp.player.hasKey, (gp.tileSize + 10), (gp.tileSize - 5));

        // no. of treasure
        g2.drawImage(treasureImage, gp.tileSize/2, (gp.tileSize/2 + 30), gp.tileSize/2, gp.tileSize/2, null);
        g2.drawString("x " + gp.player.hasTreasure, (gp.tileSize + 10), (gp.tileSize - 5 + 30));

        // current speed boost
        g2.drawImage(potionImage, gp.tileSize/2, (gp.tileSize/2 + 60), gp.tileSize/2, gp.tileSize/2, null);
        g2.drawString("x " + gp.player.hasPotion, (gp.tileSize + 10), (gp.tileSize - 5 + 60));

        // message
        if (messageOn == true) {

            messageLength = (int)g2.getFontMetrics().getStringBounds(message, g2).getWidth();
            messageHeight = (int)g2.getFontMetrics().getStringBounds(message, g2).getHeight();

            // textbox
            g2.drawImage(textboxImage, (gp.screenWidth/2 - textboxWidth/2), (gp.screenHeight - 40 - textboxHeight), textboxWidth, textboxHeight, null);

            // draw text
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(20F));
            g2.drawString(message, (gp.screenWidth/2 - (messageLength/2)), (gp.screenHeight - 40 - textboxHeight/2 + messageHeight/3));

            // remove message after 2 sec
            messageCounter++;
            if (messageCounter > 2*gp.FPS) {
                messageOn = false;
                messageCounter = 0;
            }
        }
    }
}
