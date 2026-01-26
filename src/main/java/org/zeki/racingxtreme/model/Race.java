package org.zeki.racingxtreme.model;

import java.util.ArrayList;

public class Race {
    private final Weather WEATHER;
    private final Circuit CIRCUIT;
    private ArrayList<Driver> driverList;
    private Driver[] resultRace;
    private int round;

    public Race() {
        this.WEATHER = new Weather();
        this.CIRCUIT = new Circuit();
        resultRace = new Driver[4];
        driverList = new ArrayList<>();
        round = 1;
    }

    public void setDriver(Driver driver, int pos){
        driverList.set(pos, driver);
    }

    public void createEmptyDrivers(int players){
        for (int i = 0; i < players; i++) {
            driverList.add(new Driver());
        }
    }
    public ArrayList<Driver> getDriverList() {
        return driverList;
    }

    public void setDriverList(ArrayList<Driver> driverList) {
        this.driverList = driverList;
    }

    public Driver[] getResultRace() {
        return resultRace;
    }

    public void setResultRace(Driver[] resultRace) {
        this.resultRace = resultRace;
    }

    public Weather getWEATHER() {
        return WEATHER;
    }

    public Circuit getCIRCUIT() {
        return CIRCUIT;
    }

    public int getRound() {
        return round;
    }

    public void increaseRound(){
        round++;
    };
}
