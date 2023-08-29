//package entity;

//import java.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
        getPlayerImage();

    }

    public void setDefaultValues () {
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {

        try {
//            System.out.println("Image loading started");

            standingBackward = ImageIO.read(getClass().getResourceAsStream("/player/Player_Standing_Backward.png"));
            standingForward = ImageIO.read(getClass().getResourceAsStream("/player/Player_Standing_Forward.png"));
            standingLeft = ImageIO.read(getClass().getResourceAsStream("/player/Player_Standing_Left.png"));
            standingRight = ImageIO.read(getClass().getResourceAsStream("/player/Player_Standing_Right.png"));
            forward1 = ImageIO.read(getClass().getResourceAsStream("/player/Player_Walking_Forward_1.png"));
            forward2 = ImageIO.read(getClass().getResourceAsStream("/player/Player_Walking_Forward_2.png"));
            forward3 = ImageIO.read(getClass().getResourceAsStream("/player/Player_Walking_Forward_3.png"));
            forward4 = ImageIO.read(getClass().getResourceAsStream("/player/Player_Walking_Forward_4.png"));
            backward1 = ImageIO.read(getClass().getResourceAsStream("/player/Player_Walking_Backward_1.png"));
            backward2 = ImageIO.read(getClass().getResourceAsStream("/player/Player_Walking_Backward_2.png"));
            backward3 = ImageIO.read(getClass().getResourceAsStream("/player/Player_Walking_Backward_3.png"));
            backward4 = ImageIO.read(getClass().getResourceAsStream("/player/Player_Walking_Backward_4.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/Player_Walking_Left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/Player_Walking_Left_2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/player/Player_Walking_Left_3.png"));
            left4 = ImageIO.read(getClass().getResourceAsStream("/player/Player_Walking_Left_4.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/Player_Walking_Right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/Player_Walking_Right_2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/player/Player_Walking_Right_3.png"));
            right4 = ImageIO.read(getClass().getResourceAsStream("/player/Player_Walking_Right_4.png"));

//            System.out.println("Image loading ended");

        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {

        //Key presses
        if(keyH.upPressed == true) {
            direction = "up";
            y -= speed;
        }
        else if(keyH.downPressed == true) {
            direction = "down";
            y += speed;
        }
        else if(keyH.rightPressed == true) {
            direction = "right";
            x += speed;
        }
        else if(keyH.leftPressed == true) {
            direction = "left";
            x -= speed;
        }
    }

    public void draw(Graphics2D g2) {

//        g2.setColor(Color.white);
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;

        switch(direction) {
            case "up":
                image = backward1;
                break;
            case "down":
                image = forward1;
                break;
            case "right":
                image = right1;
                break;
            case "left":
                image = left1;
                break;
        }

        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }

}
