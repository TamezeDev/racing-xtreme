package org.zeki.racingxtreme.model;

import java.util.ArrayList;

public class GameStyle {
    private enum typeGame {STANDARD, DEVELOPERS}

    private String gameSelected;

    public String getGameSelected() {
        return gameSelected;
    }

    public void setGameSelected(String gameSelected) {
        this.gameSelected = String.valueOf(typeGame.valueOf(gameSelected.toUpperCase()));
    }
    public ArrayList<String> getTypeGames(){
        ArrayList<String> typeGameList = new ArrayList<>();

        for(typeGame game: typeGame.values()){
            typeGameList.add(String.valueOf(game));
        }
        return typeGameList;
    }

}

