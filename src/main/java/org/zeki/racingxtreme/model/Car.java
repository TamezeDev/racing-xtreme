package org.zeki.racingxtreme.model;

import javafx.scene.image.Image;

import java.util.Objects;

public class Car {
    private String name;
    private String model;
    private Image image;
    private Image raceImage;
    private int speed;
    private int acceleration;
    private int hardness;
    private int basePower;
    private double kilometers;


    public Car() {

    }

    private Car(String name, String model, int speed, int acceleration, int hardness, String pathImage, String pathRaceImage) {
        this.name = name;
        this.model = model;
        this.speed = speed;
        this.acceleration = acceleration;
        this.hardness = hardness;
        kilometers = 0;
        image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(pathImage)));
        raceImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream(pathRaceImage)));
    }

    public Car getCar(String teamName) {
        String defaultPath = "/img/car/";
        switch (teamName) {
            case "Red bull" -> {
                return new Car("Red bull", "RB22", 9, 9, 7, defaultPath + "RedbullCar.png", defaultPath + "RedbullCarRace.png");
            }
            case "Ferrari" -> {
                return new Car("Ferrari", "SF-25", 8, 9, 8, defaultPath + "FerrariCar.png", defaultPath + "FerrariCarRace.png");
            }
            case "Mercedes" -> {
                return new Car("Mercedes", "W16", 7, 9, 8, defaultPath + "MercedesCar.png", defaultPath + "MercedesCarRace.png");
            }
            case "McLaren" -> {
                return new Car("McLaren", "MCL39", 8, 9, 7, defaultPath + "McLarenCar.png", defaultPath + "McLarenCarRace.png");
            }
            case "Aston Martin" -> {
                return new Car("Aston Martin", "AMR25", 7, 7, 7, defaultPath + "AstonMartin.png", defaultPath + "AstonMartinRace.png");
            }
            case "Williams" -> {
                return new Car("Williams", "FW47", 7, 8, 7, defaultPath + "WilliamsCar.png", defaultPath + "WilliamsCarRace.png");
            }
            case "Sauber" -> {
                return new Car("Sauber", "C45", 8, 8, 7, defaultPath + "SauberCar.png", defaultPath + "SauberCarRace.png");
            }
            case "Racing Bulls" -> {
                return new Car("Racing Bulls", "VCAR-02", 8, 6, 8, defaultPath + "RacingBullsCar.png", defaultPath + "RacingBullsCarRace.png");
            }
            case "Alpine" -> {
                return new Car("Alpine", "A525", 6, 9, 8, defaultPath + "AlpineCar.png", defaultPath + "AlpineCarRace.png");
            }
            case "Haas" -> {
                return new Car("Haas", "VF-25", 6, 8, 7, defaultPath + "HaasCar.png", defaultPath + "HaasCarRace.png");
            }

        }
        return null;
    }

    public double getKilometers() {
        return kilometers;
    }

    public void setKilometers(double kilometers) {
        this.kilometers = kilometers;
    }

    public String getName() {
        return name;
    }

    public String getmodel() {
        return model;
    }

    public Image getImage() {
        return image;
    }


    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getSpeed() {
        return speed;
    }

    public int getAcceleration() {
        return acceleration;
    }

    public int getHardness() {
        return hardness;
    }

    public int getBasePower() {
        return basePower;
    }

    public void setBasePower(int basePower) {
        this.basePower = basePower;
    }

    public void increaseKm(double km) {
        kilometers += km;
    }

    public Image getRaceImage() {
        return raceImage;
    }
}
