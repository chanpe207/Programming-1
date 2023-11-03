import java.awt.*;

public class EventHandler {

    GamePanel gp;
    UI ui;
    EventRect[][][] eventRect;

    public EventHandler(GamePanel gp, UI ui) {
        this.gp = gp;
        this.ui = ui;

        eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldCol];
        int col = 0;
        int row = 0;
        int map = 0;
        while(map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow) {

            eventRect[map][col][row] = new EventRect();
            eventRect[map][col][row].x = gp.tileSize/8;
            eventRect[map][col][row].y = gp.tileSize/8;
            eventRect[map][col][row].width = gp.tileSize*6/8;
            eventRect[map][col][row].height = gp.tileSize*6/8;
            eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
            eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;

            col++;
            if(col == gp.maxWorldCol) {
                col = 0;
                row++;
                if(row == gp.maxWorldRow) {
                    row = 0;
                    map++;
                }
            }
        }

    }

    public void checkEvent() {
        //Switch map at bottom of map 0
        if(hit(0,27,40,"any") == true) {
            teleport(gp.gameState, 1, 27, 2);
        }
        else if(hit(0,26,40,"any") == true) {
            teleport(gp.gameState, 1, 26, 2);
        }

        //Switch map at top of map 1
        if(hit(1,27,1,"any") == true) {
            teleport(gp.gameState, 0, 27, 39);
        }
        else if(hit(1,26,1,"any") == true) {
            teleport(gp.gameState, 0, 26, 39);
        }
    }

    public boolean hit(int map, int col, int row, String reqDirection) {
        boolean hit = false;

        if(map == gp.currentMap) {
            //Player's position
            gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
            gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

            //eventRect's position
            eventRect[map][col][row].x = col*gp.tileSize + eventRect[map][col][row].x;
            eventRect[map][col][row].y = row*gp.tileSize + eventRect[map][col][row].y;

            if(gp.player.solidArea.intersects(eventRect[map][col][row])) {
                //can define if event happens when player is facing a certain direction
                if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                    hit = true;
                }
            }

            //Reset defaults
            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;

            eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
            eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
        }

        return hit;
    }

    public void damagePit(int gameState) {
        gp.gameState = gameState;
        System.out.println("You fell into a pit!");
        gp.player.life -= 1;
    }

    public void teleport(int gameState, int map, int col, int row) {
        gp.gameState = gameState;
        gp.currentMap = map;
        gp.player.worldX = col*gp.tileSize;
        gp.player.worldY = row*gp.tileSize;
    }

}
