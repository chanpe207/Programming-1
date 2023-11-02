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
    boolean spriteInvisible = false;
    int blinkLength = 5;
    int blinkIterations = 5;
    int blinks = 0;

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
    public boolean dying = false;
    int dyingCounter = 0;
    public boolean alive = true;
    boolean hpBarOn = false;
    int hpBarCounter = 0;
    public boolean effectOn = false;
    int effectCounter = 0;
    public int playerScore = 0;

    // Type
    public int type; //0 - player, 1 - npc, 2 - monster, etc.
    public final int type_player = 0;
    public final int type_npc = 1;
    public final int type_monster = 2;
    public final int type_consumable = 3;

    //Effect
    public int effect; //0 - speed increase, 1 - speed decrease, 2 - damage increase, etc.
    public final int effect_speedIncrease = 0;
    public final int effect_speedDecrease = 1;
    public final int effect_damageIncrease = 2;

    // Object
    public BufferedImage image, image2, image3;
    public String name;
    public boolean collision = false;
    public String description;
    public boolean consumed = false;

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
                gp.playSE(13);
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

        //Monster HP bar
        if (type == 2 && hpBarOn == true) {
            hpBarCounter++;
            g2.setColor(Color.white);
            g2.fillRect(screenX-2, screenY - 17, gp.tileSize+4, 14);
            g2.setColor(Color.black);
            g2.fillRect(screenX, screenY - 15, gp.tileSize, 10);
            g2.setColor(Color.red);
            g2.fillRect(screenX, screenY - 15, (gp.tileSize/maxLife)*life, 10);
            if(hpBarCounter > gp.FPS*10) {
                hpBarOn = false;
                hpBarCounter = 0;
            }
        }



        // Make entity transparent when invincible
        if(invincible == true) {
            changeAlpha(g2, invincibleTransparency);
            hpBarOn = true;
            hpBarCounter = 0;
        }

        // Draw entity dying animation
        if(dying == true) {
            dyingAnimation(g2);
        }

        g2.drawImage(image, tempScreenX, tempScreenY, null);

        // Reset alpha
        changeAlpha(g2, 1f);

    }

    public void dyingAnimation(Graphics2D g2) {
        dyingCounter++;

        // blink the sprite
        if (dyingCounter <= blinkLength) {
            changeAlpha(g2, 0f);
        }
        if (blinks < blinkIterations) {
            if (dyingCounter > blinkLength * (blinks + 1) && dyingCounter <= blinkLength * (blinks + 2)) {
                if (!spriteInvisible) {
                    changeAlpha(g2, 1f);
                    spriteInvisible = true;
                    blinks++;
                } else {
                    changeAlpha(g2, 0f);
                    spriteInvisible = false;
                    blinks++;
                }
            }
        }
        // kill sprite
        if (blinks >= blinkIterations) {
            dying = false;
            alive = false;
            blinks = 0;
        }

    }

    public void changeAlpha(Graphics2D g2, float alphaValue) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
    }

    protected void damageReaction() {
        //move in opposite direction
        actionLockCounter = 0;
        direction = gp.player.direction;

    }

    protected void use() {
    }
}
