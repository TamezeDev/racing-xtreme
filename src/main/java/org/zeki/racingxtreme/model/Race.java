package org.zeki.racingxtreme.model;

import java.util.ArrayList;

public class Race {
    private final Weather WEATHER;
    private final Circuit CIRCUIT;
    private ArrayList<Driver> driverList;
    private ArrayList<Driver> updatePositionsList;
    private int round;

    public Race() {
        this.WEATHER = new Weather();
        this.CIRCUIT = new Circuit();
        updatePositionsList = new ArrayList<>();
        driverList = new ArrayList<>();
        round = 1;
    }

    public void setDriver(Driver driver, int pos){
        driverList.set(pos, driver);
        updatePositionsList.set(pos, driver);
    }

    public void createEmptyDrivers(int players){
        for (int i = 0; i < players; i++) {
            Driver emptyDrive = new Driver();
            driverList.add(emptyDrive);
            updatePositionsList.add(emptyDrive);
        }
    }
    public ArrayList<Driver> getDriverList() {
        return driverList;
    }

    public ArrayList<Driver> getUpdatePositionsList() {
        return updatePositionsList;
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
