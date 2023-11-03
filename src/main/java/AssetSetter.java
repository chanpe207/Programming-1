public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject() {
        gp.obj[0][0] = new OBJ_Chest(gp);
        gp.obj[0][0].worldX = gp.tileSize*3;
        gp.obj[0][0].worldY = gp.tileSize*3;

        gp.obj[0][1] = new OBJ_Door(gp);
        gp.obj[0][1].worldX = gp.tileSize*12;
        gp.obj[0][1].worldY = gp.tileSize*14;

        gp.obj[0][2] = new OBJ_Potion(gp);
        gp.obj[0][2].worldX = gp.tileSize*30;
        gp.obj[0][2].worldY = gp.tileSize*30;

        gp.obj[0][3] = new OBJ_Potion(gp);
        gp.obj[0][3].worldX = gp.tileSize*35;
        gp.obj[0][3].worldY = gp.tileSize*35;

        gp.obj[0][4] = new OBJ_Cookie(gp);
        gp.obj[0][4].worldX = gp.tileSize*38;
        gp.obj[0][4].worldY = gp.tileSize*30;

        gp.obj[0][5] = new OBJ_Cookie(gp);
        gp.obj[0][5].worldX = gp.tileSize*38;
        gp.obj[0][5].worldY = gp.tileSize*35;

        gp.obj[0][6] = new OBJ_Door(gp);
        gp.obj[0][6].worldX = gp.tileSize*26;
        gp.obj[0][6].worldY = gp.tileSize*40;

        gp.obj[0][7] = new OBJ_Door(gp);
        gp.obj[0][7].worldX = gp.tileSize*27;
        gp.obj[0][7].worldY = gp.tileSize*40;

        gp.obj[0][8] = new OBJ_Key(gp);
        gp.obj[0][8].worldX = gp.tileSize*7;
        gp.obj[0][8].worldY = gp.tileSize*37;

        gp.obj[0][9] = new OBJ_Door(gp);
        gp.obj[0][9].worldX = gp.tileSize*17;
        gp.obj[0][9].worldY = gp.tileSize*6;

        gp.obj[0][10] = new OBJ_Chest(gp);
        gp.obj[0][10].worldX = gp.tileSize*17;
        gp.obj[0][10].worldY = gp.tileSize*9;

        gp.obj[1][0] = new OBJ_Chest(gp);
        gp.obj[1][0].worldX = gp.tileSize*27;
        gp.obj[1][0].worldY = gp.tileSize*10;

    }

    public void setMonster() {
        gp.monster[0][0] = new MON_InkMonster(gp);
        gp.monster[0][0].worldX = gp.tileSize*12;
        gp.monster[0][0].worldY = gp.tileSize*31;

        gp.monster[0][1] = new MON_InkMonster(gp);
        gp.monster[0][1].worldX = gp.tileSize*13;
        gp.monster[0][1].worldY = gp.tileSize*31;

        gp.monster[1][0] = new MON_InkMonster(gp);
        gp.monster[1][0].worldX = gp.tileSize*6;
        gp.monster[1][0].worldY = gp.tileSize*9;

        gp.monster[1][1] = new MON_InkMonster(gp);
        gp.monster[1][1].worldX = gp.tileSize*13;
        gp.monster[1][1].worldY = gp.tileSize*3;
    }
}
