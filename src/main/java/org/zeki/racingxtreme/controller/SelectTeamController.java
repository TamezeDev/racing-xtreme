package org.zeki.racingxtreme.controller;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.jetbrains.annotations.NotNull;
import org.zeki.racingxtreme.model.*;
import org.zeki.racingxtreme.util.Path;
import org.zeki.racingxtreme.util.SceneHelper;

import java.io.IOException;
import java.util.ArrayList;

public class SelectTeamController {
    @FXML
    private Button nextButton;
    @FXML
    private TextField usernameTextField;
    @FXML
    private VBox driverCardArea;
    @FXML
    private ImageView topBackground;
    @FXML
    private Label feedBackLabel;
    @FXML
    private GridPane teamsGridPane;
    @FXML
    private VBox teamCardVBox;

    private final Path path = new Path();
    private byte currentPlayer = 0;
    private PauseTransition currentPause = null;

    @FXML
    public void initialize() {
        fitBackground();
        createTeamsArea();
        checkLastPlayer();
    }

    @FXML
    private void goToSelectPlayerScene(ActionEvent event) throws IOException {
        SceneHelper.goToOtherScene(path.getLocalSeleccionMode(), event);
    }

    @FXML
    private void nextButton(ActionEvent event) throws IOException {
        boolean playersReady = checkToNextPlayer();
        if (playersReady) {
            SceneHelper.goToOtherScene(path.getRace(), event);
        }
    }

    private boolean checkDriverSelected(Driver driver) {
        if (currentPlayer != 0) {
            ArrayList<Driver> driversList = Championship.getInstance().getRaces()[0].getDriverList();
            for (int i = 0; i < currentPlayer; i++) {
                if (driversList.get(i).getName().equals(driver.getName())) {
                    return true;
                }
            }
        }
        return false;
    }

    private void fitBackground() {
        BorderPane pane = (BorderPane) topBackground.getParent();
        topBackground.fitWidthProperty().bind(pane.widthProperty());
        topBackground.setPreserveRatio(false);
    }

    private void createTeamsArea() {
        Team team = new Team();
        ArrayList<Team> teams = team.getAllTeams();

        int currentTeam = 0;

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++) {
                Label teamName = new Label(teams.get(currentTeam).getName());
                teamName.getStyleClass().add("model-labelB");
                teamName.setPadding(new Insets(0, 0, 15, 0));

                Image image = teams.get(currentTeam).getLogo();

                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(120);
                imageView.setFitHeight(120);
                imageView.setPreserveRatio(false);


                VBox teamCard = new VBox();
                teamCard.setSpacing(20);
                teamCard.setAlignment(Pos.CENTER);
                teamCard.getStyleClass().add("teamCard");
                teamCard.getChildren().addAll(imageView, teamName);

                teamCard.setOnMouseClicked(event -> {
                    teamCardVBox.getChildren().clear();
                    driverCardArea.getChildren().clear();
                    setSelectedCardTeam(teamCard);
                    createCarArea(team, teamName);
                    createDriversArea(team, teamName);
                });

                teamsGridPane.add(teamCard, j, i);
                currentTeam++;

            }
        }
    }

    private void setSelectedCardTeam(Node selectedCard) {
        removeSelectedCardTeam();
        selectedCard.getStyleClass().add("cardSelected");
    }

    private void setSelectedCardDriver(VBox cardBox, Node selectedBox) {
        removeSelectedCardDriver(cardBox);
        selectedBox.getStyleClass().add("cardSelected");
    }

    private void removeSelectedCardTeam() {
        for (Node node : teamsGridPane.getChildren()) {
            node.getStyleClass().remove("cardSelected");

        }
    }

    private void removeSelectedCardDriver(VBox cardBox) {
        for (Node node : cardBox.getChildren()) {
            node.getStyleClass().remove("cardSelected");
        }
    }

    private void removeSideNodes() {
        driverCardArea.getChildren().clear();
        teamCardVBox.getChildren().clear();
    }

    private void createCarArea(Team team, Label label) {
        String teamName = label.getText();
        Car car = new Car();
        team.setCar(car.getCar(teamName));
        //Team name
        Label teamLabel = new Label(teamName);
        teamLabel.getStyleClass().add("model-labelA");
        //Model
        String nameCar = team.getCar().getmodel();
        Label nameCarLabel = new Label(nameCar);
        nameCarLabel.getStyleClass().add("model-labelB");
        //Car image
        Image imageCar = team.getCar().getImage();
        ImageView imageViewCar = new ImageView(imageCar);
        imageViewCar.setFitWidth(150);
        imageViewCar.setPreserveRatio(true);
        //Properties car
        Label speedLabel = new Label("Velocidad");
        Label accelerationLabel = new Label("Aceleración");
        Label hardnessLabel = new Label("Dureza");
        speedLabel.getStyleClass().add("model-labelB");
        accelerationLabel.getStyleClass().add("model-labelB");
        hardnessLabel.getStyleClass().add("model-labelB");

        float speed = (float) (team.getCar().getSpeed() * 0.1);
        float acceleration = (float) (team.getCar().getAcceleration() * 0.1);
        float hardness = (float) (team.getCar().getHardness() * 0.1);
        ProgressBar speedBar = new ProgressBar(0.8);
        ProgressBar accelerationBar = new ProgressBar(acceleration);
        ProgressBar hardnessBar = new ProgressBar(hardness);

        speedBar.setPrefSize(150, 20);
        accelerationBar.setPrefSize(150, 20);
        hardnessBar.setPrefSize(150, 20);

        VBox speedVBox = new VBox(speedLabel, speedBar);
        speedVBox.setAlignment(Pos.CENTER);
        speedVBox.setSpacing(5);

        VBox accelerationVBox = new VBox(accelerationLabel, accelerationBar);
        accelerationVBox.setAlignment(Pos.CENTER);
        accelerationVBox.setSpacing(5);

        VBox hardnessBox = new VBox(hardnessLabel, hardnessBar);
        hardnessBox.setAlignment(Pos.CENTER);
        hardnessBox.setSpacing(5);

        VBox statisticsVBox = new VBox(speedVBox, accelerationVBox, hardnessBox);
        statisticsVBox.setAlignment(Pos.CENTER);
        statisticsVBox.setSpacing(15);

        teamCardVBox.setPrefHeight(400);
        teamCardVBox.getChildren().addAll(teamLabel, nameCarLabel, imageViewCar, statisticsVBox);

    }

    private void createDriversArea(@NotNull Team team, Label label) {
        String teamName = label.getText();
        //set team drivers
        Car car = team.getCar();
        Driver driver = new Driver();
        ArrayList<Driver> driversList = driver.getDriverList(teamName, car, Championship.getInstance().getRaces().length);
        team.setDriversList(driversList);

        for (int i = 0; i < driversList.size(); i++) {
            //Dorsal
            String dorsal = "Nº " + driversList.get(i).getDorsal();
            Label dorsalLabel = new Label(dorsal);
            dorsalLabel.getStyleClass().add("model-labelB");
            //Image
            Image driverImage = driversList.get(i).getImage();
            ImageView driverImageView = new ImageView(driverImage);
            driverImageView.setFitWidth(120);
            driverImageView.setPreserveRatio(true);
            //leftArea
            VBox leftVBox = new VBox(driverImageView, dorsalLabel);
            leftVBox.setAlignment(Pos.CENTER);
            leftVBox.setSpacing(10);
            //Name, lastname
            String fullName = driversList.get(i).getName() + " " + driversList.get(i).getLastName();
            Label fullNameLabel = new Label(fullName);
            fullNameLabel.getStyleClass().add("model-labelB");
            //Stats names
            Label waterSkillLabel = new Label("Hab. Agua");
            waterSkillLabel.getStyleClass().add("model-labelB");
            Label enduranceLabel = new Label("Resistencia");
            enduranceLabel.getStyleClass().add("model-labelB");
            Label luckyLabel = new Label("Suerte");
            luckyLabel.getStyleClass().add("model-labelB");
            //Stats colors
            float water = (float) (driversList.get(i).getWaterSkill() * 0.1);
            float endurance = (float) (driversList.get(i).getEndurance() * 0.1);
            float luck = (float) (driversList.get(i).getLuck() * 0.1);
            ProgressBar waterSkillBar = new ProgressBar(water);
            ProgressBar enduranceBar = new ProgressBar(endurance);
            ProgressBar luckBar = new ProgressBar(luck);
            waterSkillBar.setPrefSize(100, 15);
            enduranceBar.setPrefSize(100, 15);
            luckBar.setPrefSize(100, 15);
            //statsArea
            VBox statsBox = new VBox(waterSkillLabel, waterSkillBar, enduranceLabel, enduranceBar, luckyLabel, luckBar);
            statsBox.setAlignment(Pos.CENTER);
            statsBox.setSpacing(5);
            //rightArea
            VBox rightBox = new VBox(fullNameLabel, statsBox);
            rightBox.setAlignment(Pos.CENTER);
            rightBox.setSpacing(15);
            //Full card
            HBox driverCardBox = new HBox(leftVBox, rightBox);
            driverCardBox.setAlignment(Pos.CENTER);
            driverCardBox.setSpacing(20);
            driverCardBox.setPrefSize(350, 250);
            driverCardBox.getStyleClass().add("driverCard");
            //Click Event
            int finalI = i;
            driverCardBox.setOnMouseClicked(event -> {
                setSelectedCardDriver(driverCardArea, driverCardBox);
                setRaceDriver(driversList.get(finalI));
            });

            driverCardArea.getChildren().add(driverCardBox);
        }
    }

    private void setRaceDriver(Driver driver) {
        for (Race race : Championship.getInstance().getRaces()) {
            race.setDriver(driver, currentPlayer);

        }
    }

    private void setPromptPlayer() {
        if ((currentPlayer + 1) <= Championship.getInstance().getPlayersNumber()) {
            usernameTextField.setPromptText("Nombre Jugador " + (currentPlayer + 2));

        }
    }

    private boolean setUsernamePlayer() {
        if (usernameTextField.getText().isEmpty()) {
            return false;
        }
        if ((currentPlayer) < Championship.getInstance().getPlayersNumber()) {
            String username = usernameTextField.getText();
            Championship.getInstance().getRaces()[0].getDriverList().get(currentPlayer).setNickName(username);
            return true;
        }
        return false;
    }

    private boolean checkToNextPlayer() {
        feedBackLabel.setVisible(false);
        if (!setUsernamePlayer() || !checkCarDriver()) {
            feedBackLabel.setText("Pon tu nombre y selecciona un piloto");
            feedBackLabel.setVisible(true);
            hideFeedbackLabel();
            return false;
        }
        if (checkDriverSelected(Championship.getInstance().getRaces()[0].getDriverList().get(currentPlayer))) {
            feedBackLabel.setText("Piloto ya elegido por otro jugador");
            feedBackLabel.setVisible(true);
            hideFeedbackLabel();
            return false;
        }
        if ((currentPlayer + 1) == Championship.getInstance().getPlayersNumber()) {
            createCpuDrivers();
            return true;
        }
        setUsernamePlayer();
        setPromptPlayer();
        removeSideNodes();
        removeSelectedCardTeam();
        usernameTextField.clear();
        currentPlayer++;
        checkLastPlayer();
        return false;
    }

    private boolean checkCarDriver() {
        try {
            Championship.getInstance().getRaces()[0].getDriverList().get(currentPlayer).getCar().getModel();
            return true;
        } catch (NullPointerException e) {
            return false;

        }
    }

    private void checkLastPlayer() {
        if (currentPlayer == (Championship.getInstance().getPlayersNumber() - 1)) {
            nextButton.setText("Comenzar partida");
        }

    }

    private void hideFeedbackLabel() {
        if (currentPause != null) {
            currentPause.stop();
        }

        currentPause = new PauseTransition(Duration.seconds(2));
        currentPause.setOnFinished(event -> {
            feedBackLabel.setVisible(false);
            currentPause = null;
        });
        currentPause.play();

    }

    private void createCpuDrivers() {
        if (currentPlayer + 1 < 4) {
            String[] teams = {"Racing Bulls", "Haas", "Sauber", "Williams", "Aston Martin", "McLaren", "Mercedes", "Ferrari", "Red bull", "Alpine"};
            int cpuNumber = 1;
            for (int i = currentPlayer + 1; i < 4; i++) {
                Car cpuCar = new Car();
                Driver cpuDriver = new Driver();
                String randomTeam;
                ArrayList<Driver> listTeamDriver;

                boolean driverNotAvailable;
                do {
                    randomTeam = teams[(int) (Math.random() * teams.length)];
                    cpuCar = cpuCar.getCar(randomTeam);
                    listTeamDriver = cpuDriver.getDriverList(randomTeam, cpuCar, Championship.getInstance().getRaces().length);
                    cpuDriver = listTeamDriver.get((int) (Math.random() * listTeamDriver.size()));
                    driverNotAvailable = findSameDriver(cpuDriver);
                } while (driverNotAvailable);
                cpuDriver.setNickName("Cpu " + cpuNumber);
                addCpuToListRaces(cpuDriver);
                cpuNumber++;
            }
        }
    }

    private boolean findSameDriver(Driver checkDriver) {
        ArrayList<Driver> drivers = Championship.getInstance().getRaces()[0].getDriverList();
        for (Driver driver : drivers) {
            if (driver.getName().equals(checkDriver.getName())) {
                return true;
            }
        }
        return false;
    }

    private void addCpuToListRaces(Driver cpuDriver) {
        int numRaces = Championship.getInstance().getRaces().length;
        for (int i = 0; i < numRaces; i++) {
            Championship.getInstance().getRaces()[i].getDriverList().add(cpuDriver);
        }
    }

}