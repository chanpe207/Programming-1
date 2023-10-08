import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GamePanel extends JPanel implements Runnable{
    //Screen Settings
    final int originalTileSize = 16; //16 x 16 default tile size (players, mobs, etc)
    public final int scale = 3; //rescales tiles so 16 x 16 appears 48 x 48

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 18;
    public final int maxScreenRow = 14;
    public final int screenWidth = tileSize * maxScreenCol; // 864 wide
    public final int screenHeight = tileSize * maxScreenRow; // 672 high

    // World Settings
    public final int maxWorldCol = maxScreenCol * 3;
    public final int maxWorldRow = maxScreenRow * 3;

    // FPS
    int FPS = 60;

    // System
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler(this); //instantiates keyHandler class
    Sound music = new Sound();
    Sound se = new Sound();
    CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    Thread gameThread; //allows game to run over time

    // Entities and Objects
    public Player player = new Player(this,keyH);
    public Entity obj[] = new Entity[10];
    ArrayList<Entity> entityList = new ArrayList<>();

    // Game State
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame() {

        aSetter.setObject();
        gameState = titleState;
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

        if(gameState == playState) {
            player.update();
        }
        if(gameState == pauseState) {
            //nothing
        }

    }

    public void paintComponent(Graphics g) {
        // this is a feature of superclass JPanel to draw graphics
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g; //converts graphics to graphics2D

        // Draw Title Screen
        if (gameState == titleState) {
            ui.draw(g2);
        }
        else { // Draw the game
            // draw tiles first
            tileM.draw(g2);

            // Create entities list
            entityList.add(player);

            for(int i = 0; i < obj.length; i++) {
                if(obj[i] != null) {
                    entityList.add(obj[i]);
                }
            }

            // Sort entities by worldY
            Collections.sort(entityList, new Comparator<Entity>() {
                @Override
                public int compare(Entity e1, Entity e2) {
                    int result = Integer.compare(e1.worldY, e2.worldY);
                    return result;
                }
            });

            // Draw entities
            for(int i = 0; i < entityList.size(); i++) {
                entityList.get(i).draw(g2);
            }
            // Empty entity list
            for(int i = 0; i < entityList.size(); i++) {
                entityList.remove(i);
            }

            // draw UI last
            ui.draw(g2);
        }

        g2.dispose(); //disposes of graphics context and releases system resources to save memory

    }

    //Music
    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }

    //Sound Effects
    public void playSE(int i) {
        se.setFile(i);
        se.play();
    }
}
