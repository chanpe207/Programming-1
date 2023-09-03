import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    public int worldX, worldY;
    public int speed;
    public boolean spriteWalking;

    public BufferedImage standingBackward, standingForward, standingLeft, standingRight, backward1, backward2, backward3, backward4, forward1, forward2, forward3, forward4, left1, left2, left3, left4, right1, right2, right3, right4;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;
    public int spriteFPS = 8; // FPS of character animation
    public int spriteNext = 60/spriteFPS; // number of updates before sprite changes
    public Rectangle solidArea;
    public boolean collisionOn = false;
}
