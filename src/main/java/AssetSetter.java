public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject() {
        gp.obj[0] = new OBJ_Chest(gp);
        gp.obj[0].worldX = gp.tileSize*3;
        gp.obj[0].worldY = gp.tileSize*3;

        gp.obj[1] = new OBJ_Door(gp);
        gp.obj[1].worldX = gp.tileSize*12;
        gp.obj[1].worldY = gp.tileSize*14;

    }
}
