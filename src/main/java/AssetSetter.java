public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject() {

        gp.obj[0] = new OBJ_Key(gp);
        gp.obj[0].worldX = 6 * gp.tileSize;
        gp.obj[0].worldY = 27 * gp.tileSize;

        gp.obj[1] = new OBJ_Key(gp);
        gp.obj[1].worldX = 20 * gp.tileSize;
        gp.obj[1].worldY = 20 * gp.tileSize;

        gp.obj[2] = new OBJ_Door(gp);
        gp.obj[2].worldX = 3 * gp.tileSize;
        gp.obj[2].worldY = 3 * gp.tileSize;

        gp.obj[3] = new OBJ_Door(gp);
        gp.obj[3].worldX = 44 * gp.tileSize;
        gp.obj[3].worldY = 19 * gp.tileSize;

        gp.obj[4] = new OBJ_Chest(gp);
        gp.obj[4].worldX = 37 * gp.tileSize;
        gp.obj[4].worldY = 30 * gp.tileSize;

        gp.obj[5] = new OBJ_Potion(gp);
        gp.obj[5].worldX = 47 * gp.tileSize;
        gp.obj[5].worldY = 37 * gp.tileSize;

        gp.obj[6] = new OBJ_Cookie(gp);
        gp.obj[6].worldX = 45 * gp.tileSize;
        gp.obj[6].worldY = 26 * gp.tileSize;

        gp.obj[7] = new OBJ_Cookie(gp);
        gp.obj[7].worldX = 33 * gp.tileSize;
        gp.obj[7].worldY = 33 * gp.tileSize;

        gp.obj[8] = new OBJ_Cookie(gp);
        gp.obj[8].worldX = 40 * gp.tileSize;
        gp.obj[8].worldY = 40 * gp.tileSize;

    }
}
