package org.zeki.racingxtreme.model;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.zeki.racingxtreme.util.Path;

public class AudioManager {
    private static AudioManager instance;
    private MediaPlayer mediaPlayer;
    Media media;
    Path path = new Path();

    private AudioManager() {
        String startAudioPath = String.valueOf(getClass().getResource(path.getSTARTSOUND().toString()));
        media = new Media(startAudioPath);
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }

    public static AudioManager getInstance() {
        if (instance == null) {
            instance = new AudioManager();
        }
        return instance;
    }

    private void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }

    }

    private void play() {
        if (mediaPlayer != null) mediaPlayer.play();
    }

    public void resetStart() {
        if (mediaPlayer != null) {
            stop();
        }
        String startAudioPath = String.valueOf(getClass().getResource(path.getSTARTSOUND()));
        media = new Media(startAudioPath);
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        play();
    }

    public void setRandomRaceGame() {
        int randoNum = (int) ((Math.random() * 6) + 1);
        String pathAudio;
        switch (randoNum) {
            case 1 -> {
                pathAudio = String.valueOf(getClass().getResource(path.getRaceSound1()));
                setMediaPlayer(pathAudio);
            }
            case 2 -> {
                pathAudio = String.valueOf(getClass().getResource(path.getRaceSound2()));
                setMediaPlayer(pathAudio);
            }
            case 3 -> {
                pathAudio = String.valueOf(getClass().getResource(path.getRaceSound3()));
                setMediaPlayer(pathAudio);
            }
            case 4 -> {
                pathAudio = String.valueOf(getClass().getResource(path.getRaceSound4()));
                setMediaPlayer(pathAudio);
            }
            case 5 -> {
                pathAudio = String.valueOf(getClass().getResource(path.getRaceSound5()));
                setMediaPlayer(pathAudio);
            }
            case 6 -> {
                pathAudio = String.valueOf(getClass().getResource(path.getRaceSound6()));
                setMediaPlayer(pathAudio);
            }
        }
    }

    public void setVictorySound() {
        if (mediaPlayer != null) {
            stop();
        }
        String startAudioPath = String.valueOf(getClass().getResource(path.getVICTORY_SOUND()));
        media = new Media(startAudioPath);
        mediaPlayer = new MediaPlayer(media);
        play();
    }

    public void setMediaPlayer(String pathAudio) {
        if (mediaPlayer != null) {
            stop();
        }
        media = new Media(pathAudio);
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        play();
    }
}
