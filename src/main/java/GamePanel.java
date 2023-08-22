import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    //Screen Settings
    final int originalTileSize = 16; //16 x 16 default tile size (players, mobs, etc)
    final int scale = 3; //rescales tiles so 16 x 16 appears 48 x 48

    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 18;
    final int maxScreenRow = 14;
    final int screenWidth = tileSize * maxScreenCol; // 864 wide
    final int screenHeight = tileSize * maxScreenRow; // 672 high

    Thread gameThread; //allows game to run over time

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        // GAME LOOP
        while(gameThread != null) {

            // UPDATE: updates information such as character positions
            update();

            // DRAW: draw the screen with the updated information
            repaint();
        }

    }

    public void update() {

    }

    public void paintComponent(Graphics g) {
        // this is a feature of superclass JPanel to draw graphics
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g; //converts graphics to graphics2D

        g2.setColor(Color.white);

        g2.fillRect(100, 100, tileSize, tileSize);

        g2.dispose(); //disposes of graphics context and releases system resources to save memory

    }
}
