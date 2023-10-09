import java.awt.*;
import java.util.Random;

public class MON_InkMonster extends Entity{

    public MON_InkMonster(GamePanel gp) {
        super(gp);

        name = "Ink Monster";
        speed = 1;
        maxLife = 4;
        life = maxLife;

        solidArea.x = 2 * gp.scale;
        solidArea.y = 8 * gp.scale;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 12 * gp.scale;
        solidArea.height = 8 * gp.scale;

        getImage();
    }

    public void getImage() {
        standingBackward = setup("/monster/ink_monster_left_1");
        standingForward = setup("/monster/ink_monster_right_1");
        standingLeft = setup("/monster/ink_monster_left_1");
        standingRight = setup("/monster/ink_monster_right_1");
        forward1 = setup("/monster/ink_monster_right_1");
        forward2 = setup("/monster/ink_monster_right_2");
        forward3 = setup("/monster/ink_monster_right_3");
        forward4 = setup("/monster/ink_monster_right_2");
        backward1 = setup("/monster/ink_monster_left_1");
        backward2 = setup("/monster/ink_monster_left_2");
        backward3 = setup("/monster/ink_monster_left_3");
        backward4 = setup("/monster/ink_monster_left_2");
        left1 = setup("/monster/ink_monster_left_1");
        left2 = setup("/monster/ink_monster_left_2");
        left3 = setup("/monster/ink_monster_left_3");
        left4 = setup("/monster/ink_monster_left_2");
        right1 = setup("/monster/ink_monster_right_1");
        right2 = setup("/monster/ink_monster_right_2");
        right3 = setup("/monster/ink_monster_right_3");
        right4 = setup("/monster/ink_monster_right_2");
    }

    public void setAction() {

        actionLockCounter++;

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
