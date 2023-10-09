import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Entity {
    GamePanel gp;

    // Character movement
    public int worldX, worldY;
    public int speed;
    public boolean spriteWalking;

    // Sprite Image
    public BufferedImage standingBackward, standingForward, standingLeft, standingRight, backward1, backward2, backward3, backward4, forward1, forward2, forward3, forward4, left1, left2, left3, left4, right1, right2, right3, right4;
    public String direction = "down";
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public int spriteFPS = 8; // FPS of character animation
    public int spriteNext = 60/spriteFPS; // number of updates before sprite changes

    // Collision area
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;

    // Dialogue
    public int actionLockCounter = 0;
//    String dialogues[] = new String[20];
//    int dialogueIndex = 0;

    // Character Status
    public int maxLife;
    public int life;

    // Object
    public BufferedImage image, image2, image3;
    public String name;
    public boolean collision = false;

    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public BufferedImage setup(String imageName) {

        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {

            image = ImageIO.read(getClass().getResourceAsStream(imageName +".png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        }catch(IOException e)   {
            e.printStackTrace();
        }

        return image;

    }

    public void setAction() {

    }

    public void update() {
        setAction();
        spriteWalking = true;

        collisionOn = false;
        gp.cChecker.checkTile(this);

        // If collision is false, entity can move
        if(collisionOn == false && spriteWalking == true) {

            switch(direction) {
                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
            }
        }

        // Walking or Standing sprite animation
        if(spriteWalking == true) {
            spriteCounter++;
            if (spriteCounter > spriteNext) {
                if (spriteNum < 4) {
                    spriteNum++;
                } else {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
        else {
            spriteNum = 5;
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

            switch (direction) {
                case "up":
                    if (spriteNum == 1) {
                        image = backward1;
                    }
                    if (spriteNum == 2) {
                        image = backward2;
                    }
                    if (spriteNum == 3) {
                        image = backward3;
                    }
                    if (spriteNum == 4) {
                        image = backward4;
                    }
                    if (spriteNum == 5) {
                        image = standingBackward;
                    }
                    break;
                case "down":
                    if (spriteNum == 1) {
                        image = forward1;
                    }
                    if (spriteNum == 2) {
                        image = forward2;
                    }
                    if (spriteNum == 3) {
                        image = forward3;
                    }
                    if (spriteNum == 4) {
                        image = forward4;
                    }
                    if (spriteNum == 5) {
                        image = standingForward;
                    }
                    break;
                case "right":
                    if (spriteNum == 1) {
                        image = right1;
                    }
                    if (spriteNum == 2) {
                        image = right2;
                    }
                    if (spriteNum == 3) {
                        image = right3;
                    }
                    if (spriteNum == 4) {
                        image = right4;
                    }
                    if (spriteNum == 5) {
                        image = standingRight;
                    }
                    break;
                case "left":
                    if (spriteNum == 1) {
                        image = left1;
                    }
                    if (spriteNum == 2) {
                        image = left2;
                    }
                    if (spriteNum == 3) {
                        image = left3;
                    }
                    if (spriteNum == 4) {
                        image = left4;
                    }
                    if (spriteNum == 5) {
                        image = standingLeft;
                    }
                    break;
            }
        }

        g2.drawImage(image, screenX, screenY, null);
    }

}
