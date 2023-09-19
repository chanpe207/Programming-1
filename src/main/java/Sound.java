import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {

    Clip clip; //opens audio files
    URL soundURL[] = new URL[30];

    public Sound() {

        soundURL[0] = getClass().getResource("/audio/big-impact-7054.wav"); // boss entry sound effect
        soundURL[1] = getClass().getResource("/audio/bitty-boss-54953.wav"); // miniboss music
        soundURL[2] = getClass().getResource("/audio/cartoon_wink_magic_sparkle-6896.wav"); // pick up potion/cookie
        soundURL[3] = getClass().getResource("/audio/cartoon-jump-6462.wav"); // jump
        soundURL[4] = getClass().getResource("/audio/cottagecore-17463.wav"); // world 1 music
        soundURL[5] = getClass().getResource("/audio/decidemp3-14575.wav"); // open treasure
        soundURL[6] = getClass().getResource("/audio/game-start-6104.wav"); // start button pressed
        soundURL[7] = getClass().getResource("/audio/punch-1-166694.wav"); // punch sound effect
        soundURL[8] = getClass().getResource("/audio/random-boss-fight-music-65460.wav"); // big boss music
        soundURL[9] = getClass().getResource("/audio/short-success-sound-glockenspiel-treasure-video-game-6346.wav"); //collected something
        soundURL[10] = getClass().getResource("/audio/success-1-6297.wav"); // completed puzzle/open door
        soundURL[11] = getClass().getResource("/audio/success-fanfare-trumpets-6185.wav"); //ended game successfully
        soundURL[12] = getClass().getResource("/audio/videogame-death-sound-43894.wav"); //game over
        soundURL[13] = getClass().getResource("/audio/hurt_c_08-102842.wav"); //hurt
    }

    public void setFile(int i) {

        try {

            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);

        }catch(Exception e){
        }
    }
    public void play() {
        clip.start();
    }
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop() {
        clip.stop();
    }
}
