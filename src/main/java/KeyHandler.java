import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public boolean debugKeyPressed;
    public  boolean enterPressed;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        // Title State
        if (gp.gameState == gp.titleState) {

            if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                gp.ui.commandNum--;
                if(gp.ui.commandNum<0) {
                    gp.ui.commandNum = 3;
                }
            }

            if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                gp.ui.commandNum++;
                if(gp.ui.commandNum>3) {
                    gp.ui.commandNum = 0;
                }
            }

            if(code == KeyEvent.VK_ENTER) {
                switch(gp.ui.commandNum) {
                    case 0: //New Game
                        gp.gameState = gp.playState;
                        gp.playMusic(4);
                        break;
                    case 1: //Load Game
                        break;
                    case 2: //Options
                        //Switch to options screen
                        gp.gameState = gp.optionsState;
                        gp.playMusic(4);
                        break;
                    case 3: //Quit
                        System.exit(0);
                        break;
                }
            }

        }

        // Pause State
        else if(gp.gameState == gp.pauseState) {
            if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                gp.ui.commandNum--;
                if(gp.ui.commandNum<0) {
                    gp.ui.commandNum = 2;
                }
            }

            if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                gp.ui.commandNum++;
                if(gp.ui.commandNum>2) {
                    gp.ui.commandNum = 0;
                }
            }

            if(code == KeyEvent.VK_ENTER) {
                switch(gp.ui.commandNum) {
                    case 0: //Save Game
                        break;
                    case 1: //Title Screen
                        gp.gameState = gp.titleState;
                        gp.stopMusic();
                        break;
                    case 2: //Quit
                        System.exit(0);
                        break;
                }
            }
        }
        // Play State
        else if (gp.gameState == gp.playState) {
            if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                upPressed = true;
            }
            if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
                leftPressed = true;
            }
            if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                downPressed = true;
            }
            if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
                rightPressed = true;
            }
            if(code == KeyEvent.VK_ENTER) {
                enterPressed = true;
            }

            // Debug state
            if(code == KeyEvent.VK_F3) {
                debugKeyPressed = true;
            }
        }


        // Pause State
        if(code == KeyEvent.VK_ESCAPE) {
            if(gp.gameState == gp.playState) {
                gp.gameState = gp.pauseState;
            }
            else if(gp.gameState == gp.pauseState) {
                gp.gameState = gp.playState;
            }
        }

        //Options State
        if(gp.gameState == gp.optionsState) {
            if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                gp.ui.commandNum--;
                if(gp.ui.commandNum<0) {
                    gp.ui.commandNum = 3;
                }
            }

            if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                gp.ui.commandNum++;
                if(gp.ui.commandNum>3) {
                    gp.ui.commandNum = 0;
                }
            }

            switch(gp.ui.commandNum) {
                case 0: //Music
                    if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT){
                        if(gp.music.volumeScale<5) {
                            gp.music.volumeScale++;
                            gp.music.checkVolume();
                        }
                    }
                    if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT){
                        if(gp.music.volumeScale>0){
                            gp.music.volumeScale--;
                            gp.music.checkVolume();
                        }
                    }
                    break;
                case 1: //Sound Effects
                    if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT){
                        if(gp.se.volumeScale<5) {
                            gp.se.volumeScale++;
                            gp.playSE(13);
                        }
                    }
                    if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT){
                        if(gp.se.volumeScale>0){
                            gp.se.volumeScale--;
                            gp.playSE(13);
                        }
                    }
                    break;
                case 2: //Controls

                    break;
                case 3: //Title Screen
                    if(code == KeyEvent.VK_ENTER) {
                        gp.gameState = gp.titleState;
                        gp.stopMusic();
                    }
                    break;
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            upPressed = false;
        }
        if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }
        if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            downPressed = false;
        }
        if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
        if(code == KeyEvent.VK_ENTER) {
            enterPressed = false;
        }

        // Debug state
        if(code == KeyEvent.VK_F3) {
            debugKeyPressed = false;
        }

    }
}
