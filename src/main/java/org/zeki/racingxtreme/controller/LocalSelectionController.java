package org.zeki.racingxtreme.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.zeki.racingxtreme.model.*;
import org.zeki.racingxtreme.util.Path;
import org.zeki.racingxtreme.util.SceneHelper;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class LocalSelectionController {

    private final Path path = new Path();

    @FXML
    private ImageView leftBackground;

    @FXML
    private ImageView rightBackground;

    @FXML
    private ImageView topBackground;

    @FXML
    private ComboBox<String> difficultComboBox;

    @FXML
    private ComboBox<String> playersComboBox;

    @FXML
    private ComboBox<String> gameModeComboBox;

    @FXML
    private ComboBox<String> racesComboBox;

    @FXML
    public void initialize() {
        setResponsiveBackground();
        loadPlayersToComboBox();
        loadDifficultToComboBox();
        loadNumberRaces();
        loadGames();
    }

    @FXML
    private void goBackToStartScene(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path.getStartStage()));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(path.getGlobalStyles())).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void goToSelectTeam(ActionEvent event) throws IOException {
        createChampionship();
        createAllRaces();
        createEmptyDrivers();
        loadQuestions();
        SceneHelper.goToOtherScene(path.getSelectTeam(), event);
    }

    private void loadQuestions() {
        String gameMode = Championship.getInstance().getGameMode();
        String questionsFilePath = null;
        switch (gameMode) {
            case "STANDARD" -> questionsFilePath = "/question/questionF1.json";
            case "DEVELOPERS" -> questionsFilePath = "/question/questionDeveloper.json";
        }
        try {
            if (questionsFilePath != null) {
                InputStream is = getClass().getResourceAsStream(questionsFilePath);
                Gson gson = new Gson();
                Type listType = new TypeToken<List<Question>>() {
                }.getType();
                Championship.getInstance().setQuestions(gson.fromJson(new InputStreamReader(is), listType));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setResponsiveBackground() {
        //Fit three backgrounds to  wide/height properties
        BorderPane pane = (BorderPane) topBackground.getParent();

        topBackground.fitWidthProperty().bind(pane.widthProperty());
        topBackground.setPreserveRatio(false);

        double highBackgroundSize = 400.0;
        leftBackground.fitHeightProperty().bind(pane.heightProperty().subtract(highBackgroundSize));
        leftBackground.fitWidthProperty().bind(pane.widthProperty().multiply(0.25));
        leftBackground.setPreserveRatio(false);

        rightBackground.fitHeightProperty().bind(pane.heightProperty().subtract(highBackgroundSize));
        rightBackground.fitWidthProperty().bind(pane.widthProperty().multiply(0.25));
        rightBackground.setPreserveRatio(false);
    }

    private void loadPlayersToComboBox() {
        String playerText;
        for (int i = 1; i < 5; i++) {

            if (i != 1) {
                playerText = i + " JUGADORES";
            } else {
                playerText = i + " JUGADOR";
            }
            playersComboBox.getItems().add(playerText);
        }
        difficultComboBox.getSelectionModel().select(0);
    }

    private void loadDifficultToComboBox() {
        Difficult difficult = new Difficult();
        String[] difficulties = difficult.getDifficulties();
        for (String difficulty : difficulties) {
            difficultComboBox.getItems().add(difficulty);
        }
        difficultComboBox.getSelectionModel().select(1);
    }

    private void loadNumberRaces() {
        for (int i = 1; i < 6; i++) {
            racesComboBox.getItems().add(String.valueOf(i));
        }
        racesComboBox.getSelectionModel().select(0);
    }

    private void loadGames() {
        GameStyle gameStyle = new GameStyle();
        ArrayList<String> modalGameList = gameStyle.getTypeGames();
        for (String typeGame : modalGameList) {
            gameModeComboBox.getItems().add(typeGame);
        }
        gameModeComboBox.getSelectionModel().select(0);
    }

    private void createChampionship() {
        String userDifficultSelected = difficultComboBox.getSelectionModel().getSelectedItem();
        if (userDifficultSelected != null) {
            Championship.getInstance().setDifficulty(userDifficultSelected);
        }
        String userPlayersSelected = playersComboBox.getSelectionModel().getSelectedItem();
        int players;
        if (userPlayersSelected != null) {
            players = Integer.parseInt(String.valueOf(userPlayersSelected.charAt(0)));
        } else {
            players = 1;
        }
        Championship.getInstance().setPlayersNumber(players);
        int racesSelected = Integer.parseInt(racesComboBox.getSelectionModel().getSelectedItem());
        if (racesComboBox != null) {
            Championship.getInstance().setNumberRaces(racesSelected);
        }
        if (gameModeComboBox != null) {
            Championship.getInstance().setGameMode(gameModeComboBox.getSelectionModel().getSelectedItem());
        }

    }

    private void createAllRaces() {
        int numRaces = Championship.getInstance().getRaces().length;
        String[] circuitsArray = new String[numRaces];

        for (int i = 0; i < numRaces; i++) {
            Race race = new Race();
            boolean circuitValid;
            do {
                race.getCIRCUIT().setName();
                for (int j = 0; j < circuitsArray.length; j++) {
                    if (race.getCIRCUIT().getName().equals(circuitsArray[j])) {
                        break;
                    }
                }
                circuitsArray[i] = race.getCIRCUIT().getName();
                circuitValid = true;

            } while (!circuitValid);
            Championship.getInstance().addRace(race, i);
        }
    }

    private void createEmptyDrivers() {
        int races = Championship.getInstance().getRaces().length;
        for (int i = 0; i < races; i++) {
            int drivers = Championship.getInstance().getPlayersNumber();
            Championship.getInstance().getRaces()[i].createEmptyDrivers(drivers);
        }
    }
}
