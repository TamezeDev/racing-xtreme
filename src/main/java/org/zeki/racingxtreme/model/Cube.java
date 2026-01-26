package org.zeki.racingxtreme.model;

import javafx.scene.image.Image;

import java.util.Objects;

public class Cube {
    private Image[] turningCubeImages;

    public Cube() {
        turningCubeImages = new Image[3];
        loadTurningImages();
    }

    private void loadTurningImages(){
        String defaultPath = "/img/cube/turning";
        for (int i = 0; i < turningCubeImages.length; i++) {
            turningCubeImages[i] = new Image(Objects.requireNonNull(getClass().getResourceAsStream(defaultPath + (i + 1) + ".png")));
        }
    }
    public int throwCube(){
        int randomNum = (int)((Math.random() * 6) + 1);
        return randomNum;
    }

    public Image getImageCubeNum (int num){
        String defaultPath = "/img/cube/result";
        return new Image(Objects.requireNonNull(getClass().getResourceAsStream(defaultPath + num + ".png")));
    }

    public Image[] getTurningCubeImages() {
        return turningCubeImages;
    }

    public void setTurningCubeImages(Image[] turningCubeImages) {
        this.turningCubeImages = turningCubeImages;
    }
}
