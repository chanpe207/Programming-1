import java.awt.*;

public class EventHandler {

    GamePanel gp;
    UI ui;
    Rectangle eventRect;
    int eventRectDefaultX, eventRectDefaultY;

    public EventHandler(GamePanel gp, UI ui) {
        this.gp = gp;
        this.ui = ui;

        eventRect = new Rectangle();
        eventRect.x = 23;
        eventRect.y = 23;
        eventRect.width = 2;
        eventRect.height = 2;
        eventRectDefaultX = eventRect.x;
        eventRectDefaultY = eventRect.y;
    }

    public void checkEvent() {
        //Event happens when hit
        if(hit(1,20,"any") == true) {
            teleport(gp.gameState);
            ui.displayedText = "You were teleported! Oh no! I am running out of room to talk!";
            ui.textDisplayed = true;
        }
    }

    public boolean hit(int eventCol, int eventRow, String reqDirection) {
        boolean hit = false;

        //Player's position
        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

        //eventRect's position
        eventRect.x = eventCol*gp.tileSize + eventRect.x;
        eventRect.y = eventRow*gp.tileSize + eventRect.y;

        if(gp.player.solidArea.intersects(eventRect)) {
            //can define if event happens when player is facing a certain direction
            if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                hit = true;
            }
        }

        //Reset defaults
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;

        eventRect.x = eventRectDefaultX;
        eventRect.y = eventRectDefaultY;

        return hit;
    }

    public void damagePit(int gameState) {
        gp.gameState = gameState;
        System.out.println("You fell into a pit!");
        gp.player.life -= 1;
    }

    public void teleport(int gameState) {
        gp.gameState = gameState;
        System.out.println("You were teleported!");
        gp.player.worldX = 26*gp.tileSize;
        gp.player.worldY = 4*gp.tileSize;
    }
}
