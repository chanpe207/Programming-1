import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        // Collision area of player
        solidArea = new Rectangle();
        solidArea.x = 5 * gp.scale;
        solidArea.y = 8 * gp.scale;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 6 * gp.scale;
        solidArea.height = 7 * gp.scale;


        setDefaultValues();
        getPlayerImage();

    }

    public void setDefaultValues () {
        worldX = gp.tileSize * 27 - (gp.tileSize/2);
        worldY = gp.tileSize * 4 - (gp.tileSize/2);
        speed = 4;
        direction = "down";
        spriteWalking = false;

        // Player status
        maxLife = 6; // 2 lives = 1 heart
        life = maxLife;

    }

    public void getPlayerImage() {
        int width = gp.tileSize;
        int height = gp.tileSize;

        standingBackward = setup("/player/Player_Standing_Backward", width, height);
        standingForward = setup("/player/Player_Standing_Forward", width, height);
        standingLeft = setup("/player/Player_Standing_Left", width, height);
        standingRight = setup("/player/Player_Standing_Right", width, height);
        forward1 = setup("/player/Player_Walking_Forward_1", width, height);
        forward2 = setup("/player/Player_Walking_Forward_2", width, height);
        forward3 = setup("/player/Player_Walking_Forward_3", width, height);
        forward4 = setup("/player/Player_Walking_Forward_4", width, height);
        backward1 = setup("/player/Player_Walking_Backward_1", width, height);
        backward2 = setup("/player/Player_Walking_Backward_2", width, height);
        backward3 = setup("/player/Player_Walking_Backward_3", width, height);
        backward4 = setup("/player/Player_Walking_Backward_4", width, height);
        left1 = setup("/player/Player_Walking_Left_1", width, height);
        left2 = setup("/player/Player_Walking_Left_2", width, height);
        left3 = setup("/player/Player_Walking_Left_3", width, height);
        left4 = setup("/player/Player_Walking_Left_4", width, height);
        right1 = setup("/player/Player_Walking_Right_1", width, height);
        right2 = setup("/player/Player_Walking_Right_2", width, height);
        right3 = setup("/player/Player_Walking_Right_3", width, height);
        right4 = setup("/player/Player_Walking_Right_4", width, height);

    }

    public void getPlayerAttackImage() {
        attackUp1 = setup("/player/Player_Backward_Attack", gp.tileSize, gp.tileSize*2);
        attackDown1 = setup("/player/Player_Forward_Attack", gp.tileSize, gp.tileSize*2);
        attackLeft1 = setup("/player/Player_Left_Attack", gp.tileSize*2, gp.tileSize);
        attackRight1 = setup("/player/Player_Right_Attack", gp.tileSize*2, gp.tileSize);
    }

    public void update() {

        //Key presses
        if(keyH.upPressed == true) {
            direction = "up";
            spriteWalking = true;
        }
        else if(keyH.downPressed == true) {
            direction = "down";
            spriteWalking = true;
        }
        else if(keyH.rightPressed == true) {
            direction = "right";
            spriteWalking = true;
        }
        else if(keyH.leftPressed == true) {
            direction = "left";
            spriteWalking = true;
        }
        else {
            spriteWalking = false;
        }

        // Check tile collision
        collisionOn = false;
        gp.cChecker.checkTile(this);

        // Check object collision
        int objIndex = gp.cChecker.checkObject(this, true);
        pickUpObject(objIndex);

        // Check monster collision
        int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
        contactMonster(monsterIndex);

        //Check Event
        gp.eHandler.checkEvent();

        // If collision is false, and arrow key is pressed, player can move
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

    public void pickUpObject(int i) {

        //check if an object was touched
        if(i != 999) {

        }

    }

    public void contactMonster(int i) {
        //check if a monster was touched
        if(i != 999) {

            if(invincible == false) {
                life --;
                invincible = true;
            }

        }
    }



}
