package ooga.view;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class SoundPlayer {

    public static final String SOUND_RESOURCES = "Audio/uno_audios";
    public static final String SOUND_DIRECTORY = "data/Audio/";
    private ResourceBundle soundResources;
    private MediaPlayer mediaPlayer;

    public SoundPlayer(){
        soundResources = ResourceBundle.getBundle(SOUND_RESOURCES);

    }

    public void playSound(String soundName){
        String soundPath = SOUND_DIRECTORY + soundResources.getString(soundName);
        Media sound = new Media(new File(soundPath).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }

}
