public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject() {

        gp.obj[0] = new OBJ_Key();
        gp.obj[0].worldX = 14 * gp.tileSize;
        gp.obj[0].worldY = 14 * gp.tileSize;

        gp.obj[1] = new OBJ_Key();
        gp.obj[1].worldX = 20 * gp.tileSize;
        gp.obj[1].worldY = 20 * gp.tileSize;

    }
}
