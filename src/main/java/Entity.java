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
    public boolean attacking = false;

    // Sprite Image
    public BufferedImage standingBackward, standingForward, standingLeft, standingRight, backward1, backward2, backward3, backward4, forward1, forward2, forward3, forward4, left1, left2, left3, left4, right1, right2, right3, right4;
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2;
    public String direction = "down";
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public int spriteFPS = 8; // FPS of character animation
    public int spriteNext = 60/spriteFPS; // number of updates before sprite changes
    public float invincibleTransparency = 0.4f;

    // Collision area
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;

    //Attack
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);

    // Dialogue
    public int actionLockCounter = 0;
//    String dialogues[] = new String[20];
//    int dialogueIndex = 0;

    // Character Status
    public int maxLife;
    public int life;
    public boolean invincible = false;
    public int invincibleCounter = 0;
    public int type; //0 - player, 1 - npc, 2 - monster, etc.

    // Object
    public BufferedImage image, image2, image3;
    public String name;
    public boolean collision = false;

    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public BufferedImage setup(String imageName, int width, int height) {

        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {

            image = ImageIO.read(getClass().getResourceAsStream(imageName +".png"));
            image = uTool.scaleImage(image, width, height);

        }catch(IOException e)   {
            e.printStackTrace();
        }

        return image;

    }

    public void setAction() {

    }

    public void update() {
        setAction();

        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.monster);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);

        if(this.type == 2 && contactPlayer == true) {
            if(gp.player.invincible == false) {
                gp.player.life --;
                gp.player.invincible = true;
            }
        }

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

        // Invincible status effect
        if(invincible == true) {
            invincibleCounter++;
            if(invincibleCounter>60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        int tempScreenX = screenX;
        int tempScreenY = screenY;

        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

            switch (direction) {
                case "up":
                    if (attacking == false) {
                        if (spriteNum == 1) {image = backward1;}
                        if (spriteNum == 2) {image = backward2;}
                        if (spriteNum == 3) {image = backward3;}
                        if (spriteNum == 4) {image = backward4;}
                        if (spriteNum == 5) {image = standingBackward;}
                    }
                    else {
                        if (spriteNum == 1) {image = attackUp1; tempScreenY = screenY - gp.tileSize;}
                    }
                    break;
                case "down":
                    if (attacking == false) {
                        if (spriteNum == 1) {image = forward1;}
                        if (spriteNum == 2) {image = forward2;}
                        if (spriteNum == 3) {image = forward3;}
                        if (spriteNum == 4) {image = forward4;}
                        if (spriteNum == 5) {image = standingForward;}
                    }
                    else {
                        if (spriteNum == 1) {image = attackDown1;}
                    }
                    break;
                case "right":
                    if (attacking == false) {
                        if (spriteNum == 1) {image = right1;}
                        if (spriteNum == 2) {image = right2;}
                        if (spriteNum == 3) {image = right3;}
                        if (spriteNum == 4) {image = right4;}
                        if (spriteNum == 5) {image = standingRight;}
                    }
                    else {
                        if (spriteNum == 1) {image = attackRight1;}
                    }
                    break;
                case "left":
                    if (attacking == false) {
                        if (spriteNum == 1) {image = left1;}
                        if (spriteNum == 2) {image = left2;}
                        if (spriteNum == 3) {image = left3;}
                        if (spriteNum == 4) {image = left4;}
                        if (spriteNum == 5) {image = standingLeft;}
                    }
                    else {
                        if (spriteNum == 1) {image = attackLeft1; tempScreenX = screenX - gp.tileSize;}
                    }
                    break;
            }
        }

        // Make player transparent when invincible
        if(invincible == true) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, invincibleTransparency));
        }

        g2.drawImage(image, tempScreenX, tempScreenY, null);

        // Reset alpha
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

    }

}
