package org.zeki.racingxtreme.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Championship {
    private static final Championship INSTANCE = new Championship();
    private String difficulty;
    private String gameMode;
    private int playersNumber;
    private Race[] races;
    private List<Question> questions;
    private Set<Integer> usedIds= new HashSet<>();

    private Championship() {
    }

    public static Championship getInstance() {
        return INSTANCE;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getPlayersNumber() {
        return playersNumber;
    }

    public void setPlayersNumber(int playersNumber) {
        this.playersNumber = playersNumber;
    }

    public Race[] getRaces() {
        return races;
    }

    public void setNumberRaces(int totalRaces) {
        races = new Race[totalRaces];
    }

    public void addRace(Race race, int position) {
        races[position] = race;
    }

    public String getGameMode() {
        return gameMode;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }

    public Set<Integer> getUsedIds() {
        return usedIds;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
