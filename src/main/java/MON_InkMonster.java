import java.awt.*;
import java.util.Random;

public class MON_InkMonster extends Entity{

    public MON_InkMonster(GamePanel gp) {
        super(gp);

        name = "Ink Monster";
        speed = 1;
        maxLife = 4;
        life = maxLife;
        type = 2;
        blinkLength = 5;
        blinkIterations = 5;

        solidArea.x = 2 * gp.scale;
        solidArea.y = 8 * gp.scale;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 12 * gp.scale;
        solidArea.height = 8 * gp.scale;

        getImage();
    }

    public void getImage() {
        int width = gp.tileSize;
        int height = gp.tileSize;

        standingBackward = setup("/monster/ink_monster_left_1", width, height);
        standingForward = setup("/monster/ink_monster_right_1", width, height);
        standingLeft = setup("/monster/ink_monster_left_1", width, height);
        standingRight = setup("/monster/ink_monster_right_1", width, height);
        forward1 = setup("/monster/ink_monster_right_1", width, height);
        forward2 = setup("/monster/ink_monster_right_2", width, height);
        forward3 = setup("/monster/ink_monster_right_3", width, height);
        forward4 = setup("/monster/ink_monster_right_2", width, height);
        backward1 = setup("/monster/ink_monster_left_1", width, height);
        backward2 = setup("/monster/ink_monster_left_2", width, height);
        backward3 = setup("/monster/ink_monster_left_3", width, height);
        backward4 = setup("/monster/ink_monster_left_2", width, height);
        left1 = setup("/monster/ink_monster_left_1", width, height);
        left2 = setup("/monster/ink_monster_left_2", width, height);
        left3 = setup("/monster/ink_monster_left_3", width, height);
        left4 = setup("/monster/ink_monster_left_2", width, height);
        right1 = setup("/monster/ink_monster_right_1", width, height);
        right2 = setup("/monster/ink_monster_right_2", width, height);
        right3 = setup("/monster/ink_monster_right_3", width, height);
        right4 = setup("/monster/ink_monster_right_2", width, height);
    }

    public void setAction() {

        actionLockCounter++;
        spriteWalking = true;

        if(actionLockCounter == 120) {

            Random random = new Random();
            int i = random.nextInt(100)+1; // Random number from 1 to 100

            if(i <= 25) {
                direction = "up";
            }
            if(i > 25 && i <= 50) {
                direction = "down";
            }
            if(i > 50 && i <= 75) {
                direction = "left";
            }
            if(i > 75 && i <= 100) {
                direction = "right";
            }

            actionLockCounter = 0;
        }
    }
}
