package org.zeki.racingxtreme.model;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Team {
    private String name;
    private String imagePath;
    private Image logo;
    private Car car;
    private ArrayList<Driver> driversList;

    public Team() {
    }

    private Team(String name, String imagePath) {
        this.name = name;
        this.imagePath = imagePath;
        logo = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Image getLogo() {
        return logo;
    }

    public void setLogo(Image logo) {
        this.logo = logo;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public ArrayList<Driver> getDriversList() {
        return driversList;
    }

    public void setDriversList(ArrayList<Driver> driversList) {
        this.driversList = driversList;
    }

    public ArrayList<Team> getAllTeams() {
        //Create and return all teams
        String defaultPath = "/img/team/";

        Team team1 = new Team("Red bull", defaultPath + "redbull.png");
        Team team2 = new Team("Ferrari", defaultPath + "ferrari.png");
        Team team3 = new Team("Mercedes", defaultPath + "mercedes.png");
        Team team4 = new Team("McLaren", defaultPath + "mclaren.png");
        Team team5 = new Team("Aston Martin", defaultPath + "astonMartin.png");
        Team team6 = new Team("Williams", defaultPath + "williams.png");
        Team team7 = new Team("Sauber", defaultPath + "sauber.png");
        Team team8 = new Team("Racing Bulls", defaultPath + "racingRedBull.png");
        Team team9 = new Team("Alpine", defaultPath + "alpine.png");
        Team team10 = new Team("Haas", defaultPath + "haas.png");
        return new ArrayList<Team>(List.of(team1, team2, team3, team4, team5, team6, team7, team8, team9, team10));
    }

}
