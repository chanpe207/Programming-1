import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font courier_20;
    BufferedImage full_heart, half_heart, no_heart;
    public boolean messageOn = false;
    public String message = "";
    public int commandNum = 0;

    public UI(GamePanel gp) {
        this.gp = gp;

        courier_20 = new Font("Courier", Font.BOLD, 20);

        // Create HUD Object
        SuperObject heart = new OBJ_Heart(gp);
        full_heart = heart.image;
        half_heart = heart.image2;
        no_heart = heart.image3;
    }

    public void showMessage(String text) {

        message = text;
        messageOn = true;

    }

    public void draw(Graphics2D g2) {

        this.g2 = g2;

        g2.setFont(courier_20);
        g2.setColor(Color.white);

        // Title Screen
        if(gp.gameState == gp.titleState) {
            drawTitleScreen();
        }

        //Play State
        if(gp.gameState == gp.playState) {
            // Do play state
            drawPlayerLife();
        }
        if(gp.gameState == gp.pauseState) {
            // Pause screen
            drawPauseScreen();
        }
    }

    public void drawPlayerLife() {

        // display on top left
        int x = gp.screenWidth - (gp.tileSize+5)*4;
        int y = gp.tileSize/2;
        int i = 0;

        // Draw blank hearts
        while(i < gp.player.maxLife/2) {
            g2.drawImage(no_heart, x, y, null);
            i++;
            x += gp.tileSize+5;
        }

        // Reset
        x = gp.screenWidth - (gp.tileSize+5)*4;
        y = gp.tileSize/2;
        i = 0;

        // Draw Current life
        while(i < gp.player.life) {
            g2.drawImage(half_heart, x, y, null);
            i++;
            if(i < gp.player.life) {
                g2.drawImage(full_heart, x, y, null);
            }
            i++;
            x += gp.tileSize+5;
        }
    }

    public void drawPauseScreen() {

        //Background Colour
        g2.setColor(new Color(0, 0, 0, 128));
        g2.fillRect(0,0,gp.screenWidth, gp.screenHeight);

        //Paused Shadow
        String text = "PAUSED";
        g2.setColor(new Color(16, 96, 145));
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 60));
        int x = getXforCenteredText(text);
        int y = gp.screenHeight/2 - gp.tileSize*3;
        g2.drawString(text, (x+5), (y+5));

        // Paused
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        //Menu
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 40));

        text = "Save Game";
        x = getXforCenteredText(text);
        y = gp.screenHeight/2 + gp.tileSize*2;
        g2.drawString(text, x, y);
        if(commandNum == 0) {
            g2.drawString(">", x-gp.tileSize, y);
        }

        text = "Title Screen";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(commandNum == 1) {
            g2.drawString(">", x-gp.tileSize, y);
        }

        text = "Quit";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(commandNum == 2) {
            g2.drawString(">", x-gp.tileSize, y);
        }

    }

    public void drawTitleScreen() {

        //Background Colour
        g2.setColor(new Color(26, 153, 166));
        g2.fillRect(0,0,gp.screenWidth, gp.screenHeight);

        //Title Shadow
        String text = "CHARLIZE'S GAME";
        g2.setColor(new Color(16, 96, 145));
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 60));
        int x = getXforCenteredText(text);
        int y = gp.screenHeight/2 - gp.tileSize*3;
        g2.drawString(text, (x+5), (y+5));

        //Title Name
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        //Character Image
        x = (gp.screenWidth/2-gp.tileSize);
        y = (gp.screenHeight/2-gp.tileSize);
        g2.drawImage(gp.player.standingRight, x, y, gp.tileSize*2, gp.tileSize*2, null);

        //Menu
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 40));

        text = "New Game";
        x = getXforCenteredText(text);
        y = gp.screenHeight/2 + gp.tileSize*2;
        g2.drawString(text, x, y);
        if(commandNum == 0) {
            g2.drawString(">", x-gp.tileSize, y);
        }

        text = "Load Game";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(commandNum == 1) {
            g2.drawString(">", x-gp.tileSize, y);
        }

        text = "Quit";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(commandNum == 2) {
            g2.drawString(">", x-gp.tileSize, y);
        }

    }

    public int getXforCenteredText(String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }

}
