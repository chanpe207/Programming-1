import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    public int numChests;

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
        getPlayerAttackImage();

    }

    public void setDefaultValues () {
        worldX = gp.tileSize * 27 - (gp.tileSize/2);
        worldY = gp.tileSize * 14 - (gp.tileSize/2);
        speed = 4;
        direction = "down";
        spriteWalking = false;
        invincibleTransparency = 0.3f;
        invincible = false;

        // Player status
        maxLife = 6; // 2 lives = 1 heart
        life = maxLife;
        type = type_player;
        playerScore = 0;
        numChests = 3;

        //attack area
        attackArea.width = 4*gp.scale;
        attackArea.height = 17*gp.scale;
        attackArea.x = 6*gp.scale;
        attackArea.y = 15*gp.scale;

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

        //Attack key pressed
        if (keyH.enterPressed == true && attacking == false) {
            gp.playSE(7);
            attacking = true;
        }

        if (attacking == true) {
            attacking();
        }
        else {
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

        // Invincible status effect
        if(invincible == true) {
            invincibleCounter++;
            if(invincibleCounter>60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }

        // Effects
        if(effectOn == true) {
            effectCounter++;
            switch(effect){
                case effect_speedIncrease:
                    gp.player.speed = 8;
                    if(effectCounter>10*gp.FPS) {
                        gp.player.speed = 4;
                        effectCounter = 0;
                        effectOn = false;
                    }
                    break;
                case effect_speedDecrease:
                    gp.player.speed = 2;
                    if(effectCounter>10*gp.FPS) {
                        gp.player.speed = 4;
                        effectCounter = 0;
                        effectOn = false;
                    }
                    break;
                case effect_damageIncrease:
                    break;
            }
        }

        //Game Over Condition
        if(life <= 0) {
            gp.gameState = gp.gameOverState;
            gp.stopMusic();
            gp.playSE(12);
        }

        //Win Condition
        if(numChests <= 0) {
            gp.gameState = gp.winState;
            gp.stopMusic();
            gp.playSE(11);
        }
    }

    public void attacking() {
        spriteCounter++;

        if(spriteCounter <= spriteNext*4) {
            spriteNum = 1;

            //Save current worldX/Y and solidArea
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;
            int solidAreaX = solidArea.x;
            int solidAreaY = solidArea.y;

            //Adjust solidArea to attackArea
            switch(direction) {
                case "up":
                    solidArea.width = attackArea.width;
                    solidArea.height = attackArea.height;
                    solidArea.x = attackArea.x;
                    solidArea.y = -gp.tileSize;
                    break;
                case "down":
                    solidArea.width = attackArea.width;
                    solidArea.height = attackArea.height;
                    solidArea.x = attackArea.x;
                    solidArea.y = attackArea.y;
                    break;
                case "left":
                    solidArea.width = attackArea.height;
                    solidArea.height = attackArea.width;
                    solidArea.x = solidAreaX - attackArea.height;
                    solidArea.y = solidAreaY + (2*gp.scale);
                    break;
                case "right":
                    solidArea.width = attackArea.height;
                    solidArea.height = attackArea.width;
                    solidArea.x = solidAreaX + solidAreaWidth;
                    solidArea.y = solidAreaY + (2*gp.scale);
                    break;
            }

            //collision check the attackArea with monsters
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            damageMonster(monsterIndex);

            //reset player's worldX/Y and solidArea
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
            solidArea.x = solidAreaX;
            solidArea.y = solidAreaY;

        }
        else {
            spriteCounter = 0;
            attacking = false;
        }
    }

    public void pickUpObject(int i) {

        //check if an object was touched
        if(i != 999) {
            switch(gp.obj[gp.currentMap][i].type) {
                case type_interactable:
                case type_consumable:
                    gp.obj[gp.currentMap][i].use();
                    break;
                case type_pickUpOnly:
                    gp.obj[gp.currentMap][i].pickUp();
                    break;
            }
        }

    }

    public void contactMonster(int i) {
        //check if a monster was touched
        if(i != 999) {

            if(invincible == false) {
                gp.playSE(13);
                life --;
                invincible = true;
            }

        }
    }

    public void damageMonster(int i) {
        if(i != 999) {
            if(gp.monster[gp.currentMap][i].invincible == false) {
                gp.playSE(13);
                gp.monster[gp.currentMap][i].life --;
                gp.monster[gp.currentMap][i].invincible = true;
                gp.monster[gp.currentMap][i].damageReaction();

                if(gp.monster[gp.currentMap][i].life <= 0) {
                    gp.playSE(13);
                    gp.monster[gp.currentMap][i].dying = true;
                }
            }
        }
    }



}
