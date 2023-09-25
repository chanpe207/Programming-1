import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font courier_20;
    public boolean messageOn = false;
    public String message = "";

    public UI(GamePanel gp) {
        this.gp = gp;

        courier_20 = new Font("Courier", Font.BOLD, 20);
    }

    public void showMessage(String text) {

        message = text;
        messageOn = true;

    }

    public void draw(Graphics2D g2) {

        this.g2 = g2;

        g2.setFont(courier_20);
        g2.setColor(Color.white);

        if(gp.gameState == gp.playState) {
            // Do play state
        }
        if(gp.gameState == gp.pauseState) {
            // Pause screen
            drawPauseScreen();
        }
    }

    public void drawPauseScreen() {

        String text = "PAUSED";
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80));
        int x = getXforCenteredText(text);
        int y = gp.screenHeight/2;
        
        g2.drawString(text, x, y);

    }

    public int getXforCenteredText(String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }

}
