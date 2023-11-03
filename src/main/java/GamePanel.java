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
    public final int maxMap = 10;
    public int currentMap = 0;

    // FPS
    int FPS = 60;

    // System
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound se = new Sound();
    CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this, keyH);
    public EventHandler eHandler = new EventHandler(this, ui);
    Config config = new Config(this);
    Thread gameThread; //allows game to run over time

    // Entities and Objects
    public Player player = new Player(this,keyH);
    public Entity obj[][] = new Entity[maxMap][30];
    public Entity monster[][] = new Entity[maxMap][30];
    ArrayList<Entity> entityList = new ArrayList<>();

    // Game State
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int optionsState = 3;
    public final int gameOverState = 4;

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame() {

        aSetter.setObject();
        aSetter.setMonster();
        gameState = titleState;
    }

    public void resetGame() {
        entityList.clear();
        for(int j=0;j<obj.length;j++) {
            for (int i = 0; i < obj[j].length; i++) {
                obj[j][i] = null;
            }
        }
        for(int j=0;j<obj.length;j++) {
            for (int i = 0; i < monster[j].length; i++) {
                monster[j][i] = null;
            }
        }
        player.setDefaultValues();
        aSetter.setObject();
        aSetter.setMonster();
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
            //Player
            player.update();

            //Monsters
            for(int i = 0; i < monster[1].length; i++) {
                if(monster[currentMap][i] != null) {
                    if(monster[currentMap][i].alive == true) {
                        monster[currentMap][i].update();
                    }
                    else {
                        monster[currentMap][i].checkDrop();//check for loot drop
                        monster[currentMap][i] = null;
                    }
                }
            }

            //Objects
            for(int i = 0; i < obj[1].length; i++) {
                if(obj[currentMap][i] != null) {
                    if(obj[currentMap][i].consumed == true) {
                        obj[currentMap][i] = null;
                    }
                }
            }
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

            for(int i = 0; i < obj[1].length; i++) {
                if(obj[currentMap][i] != null) {
                    entityList.add(obj[currentMap][i]);
                }
            }
            for(int i = 0; i < monster[1].length; i++) {
                if(monster[currentMap][i] != null) {
                    entityList.add(monster[currentMap][i]);
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
            entityList.clear();

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
