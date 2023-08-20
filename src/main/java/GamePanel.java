import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    //Screen Settings
    final int originalTileSize = 16; //16 x 16 default tile size (players, mobs, etc)
    final int scale = 3; //rescales tiles so 16 x 16 appears 48 x 48

    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 18;
    final int maxScreenRow = 14;
    final int screenWidth = tileSize * maxScreenCol; // 864 wide
    final int screenHeight = tileSize * maxScreenRow; // 672 high

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
    }
}
