import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    //Screen Settings
    final int originalTileSize = 16; //16 x 16 default tile size (players, mobs, etc)
    final int scale = 3; //rescales tiles so 16 x 16 appears 48 x 48

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 18;
    public final int maxScreenRow = 14;
    public final int screenWidth = tileSize * maxScreenCol; // 864 wide
    public final int screenHeight = tileSize * maxScreenRow; // 672 high

    // FPS
    int FPS = 60;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler(); //instantiates keyHandler class
    Thread gameThread; //allows game to run over time
    Player player = new Player(this,keyH);

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.white);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        // GAME LOOP

        // TIME: set the length of time for each iteration
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null) {

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval; // delta is a ratio of time passed to next draw interval
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta > 1) {
                // UPDATE: updates information such as character positions
                update();

                // DRAW: draw the screen with the updated information
                repaint(); //calls paintComponent

                delta--; //reset delta
                drawCount++;
            }

            if(timer >= 1000000000) {
//                System.out.println("FPS:" + drawCount);
                drawCount = 0;
                timer = 0;
            }


        }

    }

    public void update() {
        player.update();

    }

    public void paintComponent(Graphics g) {
        // this is a feature of superclass JPanel to draw graphics
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g; //converts graphics to graphics2D

        // draw tiles first
        tileM.draw(g2);

        //draw player next
        player.draw(g2);

        g2.dispose(); //disposes of graphics context and releases system resources to save memory

    }
}
