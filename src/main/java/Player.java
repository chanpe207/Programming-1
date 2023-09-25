import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
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
    }

    public void getPlayerImage() {

        standingBackward = setup("Player_Standing_Backward");
        standingForward = setup("Player_Standing_Forward");
        standingLeft = setup("Player_Standing_Left");
        standingRight = setup("Player_Standing_Right");
        forward1 = setup("Player_Walking_Forward_1");
        forward2 = setup("Player_Walking_Forward_2");
        forward3 = setup("Player_Walking_Forward_3");
        forward4 = setup("Player_Walking_Forward_4");
        backward1 = setup("Player_Walking_Backward_1");
        backward2 = setup("Player_Walking_Backward_2");
        backward3 = setup("Player_Walking_Backward_3");
        backward4 = setup("Player_Walking_Backward_4");
        left1 = setup("Player_Walking_Left_1");
        left2 = setup("Player_Walking_Left_2");
        left3 = setup("Player_Walking_Left_3");
        left4 = setup("Player_Walking_Left_4");
        right1 = setup("Player_Walking_Right_1");
        right2 = setup("Player_Walking_Right_2");
        right3 = setup("Player_Walking_Right_3");
        right4 = setup("Player_Walking_Right_4");

    }

    public BufferedImage setup(String imageName) {

        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {

            image = ImageIO.read(getClass().getResourceAsStream("/player/"+ imageName +".png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        }catch(IOException e)   {
            e.printStackTrace();
        }

        return image;

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
    }

    public void pickUpObject(int i) {

        //check if an object was touched
        if(i != 999) {

        }

    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        switch(direction) {
            case "up":
                if(spriteNum == 1) {
                    image = backward1;
                }
                if(spriteNum == 2) {
                    image = backward2;
                }
                if(spriteNum == 3) {
                    image = backward3;
                }
                if(spriteNum == 4) {
                    image = backward4;
                }
                if(spriteNum == 5) {
                    image = standingBackward;
                }
                break;
            case "down":
                if(spriteNum == 1) {
                    image = forward1;
                }
                if(spriteNum == 2) {
                    image = forward2;
                }
                if(spriteNum == 3) {
                    image = forward3;
                }
                if(spriteNum == 4) {
                    image = forward4;
                }
                if(spriteNum == 5) {
                    image = standingForward;
                }
                break;
            case "right":
                if(spriteNum == 1) {
                    image = right1;
                }
                if(spriteNum == 2) {
                    image = right2;
                }
                if(spriteNum == 3) {
                    image = right3;
                }
                if(spriteNum == 4) {
                    image = right4;
                }
                if(spriteNum == 5) {
                    image = standingRight;
                }
                break;
            case "left":
                if(spriteNum == 1) {
                    image = left1;
                }
                if(spriteNum == 2) {
                    image = left2;
                }
                if(spriteNum == 3) {
                    image = left3;
                }
                if(spriteNum == 4) {
                    image = left4;
                }
                if(spriteNum == 5) {
                    image = standingLeft;
                }
                break;
        }

        g2.drawImage(image, screenX, screenY, null);
    }

}
