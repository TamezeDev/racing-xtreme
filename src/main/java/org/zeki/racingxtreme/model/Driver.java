package org.zeki.racingxtreme.model;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Objects;

public class Driver {

    private String name;
    private String lastName;
    private int dorsal;
    private Image image;
    private int combo;
    private int totalScore;
    private boolean rightQuestion;
    private String nickName;
    private int[] raceScores;
    private int waterSkill, endurance, luck;
    private Car car;

    public Driver() {
    }

    public int getCombo() {
        return combo;
    }

    public void setCombo(int combo) {
        this.combo = combo;
    }

    public Driver(String name, String lastName, int dorsal, int waterSkill, int endurance, int luck,int numRaces, Car car, String imagPath) {
        this.name = name;
        this.lastName = lastName;
        this.dorsal = dorsal;
        this.waterSkill = waterSkill;
        this.endurance = endurance;
        this.luck = luck;
        this.car = car;
        raceScores = new int[numRaces];
        image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagPath)));
        combo = 0;
        totalScore = 0;
    }

    public ArrayList<Driver> getDriverList(String teamName, Car car, int numRaces) {
        String defaultPath = "/img/driver/";
        ArrayList<Driver> driversList = new ArrayList<>();

        switch (teamName) {
            case "Red bull" -> {
                driversList.add(new Driver("Max", "Verstappen", 1, 8, 9, 7,numRaces, car, defaultPath + "MaxVerstappen.png"));
                driversList.add(new Driver("Sergio", "Pérez", 22, 8, 9, 6,numRaces, car, defaultPath + "SergioPérez.png"));
                return driversList;
            }
            case "Ferrari" -> {
                driversList.add(new Driver("Charles", "Leclerc", 16, 7, 8, 7,numRaces, car, defaultPath + "CharlesLeclerc.png"));
                driversList.add(new Driver("Lewis", "Hamilton", 44, 9, 8, 8,numRaces, car, defaultPath + "LewisHamilton.png"));
                return driversList;
            }
            case "Mercedes" -> {
                driversList.add(new Driver("George", "Russell", 63, 7, 7, 8,numRaces, car, defaultPath + "GeorgeRussell.png"));
                driversList.add(new Driver("Kimi", "Antonelli", 12, 7, 8, 6,numRaces, car, defaultPath + "KimiAntonelli.png"));
                return driversList;
            }
            case "McLaren" -> {
                driversList.add(new Driver("Lando", "Norris", 4, 9, 7, 7,numRaces, car, defaultPath + "LandoNorris.png"));
                driversList.add(new Driver("Oscar", "Piastri", 81, 8, 5, 9,numRaces, car, defaultPath + "OscarPiastri.png"));
                return driversList;
            }
            case "Aston Martin" -> {
                driversList.add(new Driver("Fernando", "Alonso", 14, 9, 9, 5,numRaces, car, defaultPath + "FernandoAlonso.png"));
                driversList.add(new Driver("Lance", "Stroll", 18, 7, 8, 9,numRaces, car, defaultPath + "LanceStroll.png"));
                return driversList;
            }
            case "Alpine" -> {
                driversList.add(new Driver("Pierre", "Gasly", 10, 8, 6, 7,numRaces, car, defaultPath + "PierreGasly.png"));
                driversList.add(new Driver("Franco", "Colapinto", 43, 6, 7, 6,numRaces, car, defaultPath + "EstebanOcon.png"));
                return driversList;
            }
            case "Williams" -> {
                driversList.add(new Driver("Alexander", "Albon", 23, 7, 7, 8,numRaces, car, defaultPath + "AlexanderAlbon.png"));
                driversList.add(new Driver("Carlos", "Sainz", 55, 9, 8, 8,numRaces, car, defaultPath + "CarlosSainzJr.png"));
                return driversList;
            }
            case "Sauber" -> {
                driversList.add(new Driver("Valtteri", "Bottas", 77, 7, 7, 8,numRaces, car, defaultPath + "ValtteriBottas.png"));
                driversList.add(new Driver("Gabriel", "Bortoleto", 5, 6, 7, 9,numRaces, car, defaultPath + "GabrielBortoleto.png"));
                return driversList;
            }
            case "Haas" -> {
                driversList.add(new Driver("Esteban", "Ocon", 31, 8, 8, 7,numRaces, car, defaultPath + "EstebanOcon.png"));
                driversList.add(new Driver("Kevin", "Magnussen", 20, 6, 6, 9,numRaces, car, defaultPath + "KevinMagnussen.png"));
                return driversList;
            }
            case "Racing Bulls" -> {
                driversList.add(new Driver("Daniel", "Ricciardo", 33, 7, 6, 6,numRaces, car, defaultPath + "DanielRicciardo.png"));
                driversList.add(new Driver("Logan", "Sargeant", 2, 7, 8, 7,numRaces, car, defaultPath + "LoganSargeant.png"));
                return driversList;
            }

        }
        return driversList;
    }

    public void increaseTotalScore(int scoreAdd){
        totalScore +=scoreAdd;
    }
    public void increaseCombo() {
        combo += 1;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public int getDorsal() {
        return dorsal;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int[] getRaceScores() {
        return raceScores;
    }

    public void setRaceScores(int raceScores, int raceNum) {
        this.raceScores[raceNum] = raceScores;
    }

    public int getWaterSkill() {
        return waterSkill;
    }

    public void setWaterSkill(int waterSkill) {
        this.waterSkill = waterSkill;
    }

    public int getEndurance() {
        return endurance;
    }

    public void setEndurance(int endurance) {
        this.endurance = endurance;
    }

    public int getLuck() {
        return luck;
    }

    public void setLuck(int luck) {
        this.luck = luck;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public boolean isRightQuestion() {
        return rightQuestion;
    }

    public void setRightQuestion(boolean rightQuestion) {
        this.rightQuestion = rightQuestion;
    }
}
