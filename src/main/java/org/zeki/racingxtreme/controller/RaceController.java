package org.zeki.racingxtreme.controller;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.zeki.racingxtreme.model.Championship;
import org.zeki.racingxtreme.model.Cube;
import org.zeki.racingxtreme.model.Driver;

import java.util.*;

public class RaceController {
    @FXML
    private Button nextRaceBtn;
    @FXML
    private VBox podiumBox;
    @FXML
    private HBox roundsBox;
    @FXML
    private ImageView podium1Image;

    @FXML
    private ImageView podium2Image;

    @FXML
    private ImageView podium3Image;

    @FXML
    private Label winnerLabel;
    @FXML
    private Label resultsLabel;

    @FXML
    private Button nextPlayerBtn;
    @FXML
    private StackPane circuitPane;
    @FXML
    private Button throwCubeBtn;
    @FXML
    private VBox cubeBoxArea;
    @FXML
    private ImageView cubeImageView;
    @FXML
    private Label cubeResultLabel;
    @FXML
    private Label roundNumLabel;
    @FXML
    private Label feedBackLabel;
    @FXML
    private ImageView car1ImgView;
    @FXML
    private ImageView car2ImgView;
    @FXML
    private ImageView car3ImgView;
    @FXML
    private ImageView car4ImgView;
    @FXML
    private HBox driversBox;
    @FXML
    private Label circuitLengthLabel;
    @FXML
    private Label circuitNameLabel;
    @FXML
    private Label weatherNameLabel;
    @FXML
    private ImageView wheatherImageView;
    @FXML
    private ComboBox<String> statsBox;
    @FXML
    private Label points1Label;
    @FXML
    private Label points2Label;
    @FXML
    private Label points3Label;
    @FXML
    private Label points4Label;
    @FXML
    private Label position1Label;
    @FXML
    private Label position2Label;
    @FXML
    private Label position3Label;
    @FXML
    private Label position4Label;
    @FXML
    private GridPane answersBox;
    @FXML
    private VBox questionBox;
    @FXML
    private Label questionLabel;
    @FXML
    private Label timeLabel;
    @FXML
    private Label answer1Label;

    @FXML
    private Label answer2Label;

    @FXML
    private Label answer3Label;

    @FXML
    private Label answer4Label;

    Cube cube = new Cube();
    PauseTransition currentPause = new PauseTransition();
    HashMap<Integer, Label> kmPlayersMap = new HashMap<>();
    HashMap<Integer, Label> comboAcumulateMap = new HashMap<>();
    HashMap<Integer, ImageView> carImageMap = new HashMap<>();
    private Timeline timeline;
    private Timeline questionTimer;
    ArrayList<ImageView> carsImages = new ArrayList<>();
    private int frameAnimation = 0;
    int currentRace = 0;
    int currentPlayer = 0;
    int selectedAnswer = -1;


    @FXML
    public void initialize() {

        setImagesCarMap();
        createDriversCards();
        setWeatherCard(currentRace);
        loadStatsBox();
        loadCurrentRound();
        updateStatsArea();
        loadCarImage();
        //resizeCars();
        executeRound();

    }

    @FXML
    private void goToNexRace() {
        podiumBox.setVisible(false);
        startNewRace();
    }

    @FXML
    private void goToOtherStat(ActionEvent event) {
        updateStatsArea();
    }

    @FXML
    private void throwcube() {
        setTimerAnimationCube();
        showAnimationCube();
    }

    @FXML
    void setAnswer0(MouseEvent event) {
        removeSelectedItem();
        Node node = (Node) event.getSource();
        node.getStyleClass().add("answerSelected");
        selectedAnswer = 0;
    }

    @FXML
    void setAnswer1(MouseEvent event) {
        removeSelectedItem();
        Node node = (Node) event.getSource();
        node.getStyleClass().add("answerSelected");
        selectedAnswer = 1;
    }

    @FXML
    void setAnswer2(MouseEvent event) {
        removeSelectedItem();
        Node node = (Node) event.getSource();
        node.getStyleClass().add("answerSelected");
        selectedAnswer = 2;
    }

    @FXML
    void setAnswer3(MouseEvent event) {
        removeSelectedItem();
        Node node = (Node) event.getSource();
        node.getStyleClass().add("answerSelected");
        selectedAnswer = 3;
    }

    @FXML
    private void goToNextPlayer() {
        feedBackLabel.setVisible(false);
        nextPlayerBtn.setVisible(false);
        executeRound();

    }

    private void resetAllParams() {
        for (int i = 0; i < 4; i++) {
            Championship.getInstance().getRaces()[currentRace].getDriverList().get(i).setCombo(0);
            Championship.getInstance().getRaces()[currentRace].getDriverList().get(i).getCar().setKilometers(0.0);
        }

    }

    private void startNewRace() {
        if (currentRace == Championship.getInstance().getRaces().length - 1) {
            //TODO: VOLVER AL MENU PRINCIPAL o mostrar general
            return;
        }
        feedBackLabel.setVisible(true);
        roundsBox.setVisible(true);
        statsBox.getItems().clear();
        currentPlayer = 0;
        currentRace++;
        resetAllParams();
        resetKmDrivers();
        resetAccumulateCombo();
        loadCurrentRound();
        setWeatherCard(currentRace);
        loadStatsBox();
        resetCars();
        executeRound();
    }

    private void resetCars() {
        for (int i = 0; i < 4; i++) {
            ImageView car = carImageMap.get(i);
            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.5), car);
            tt.setToY(0);
            tt.setInterpolator(Interpolator.EASE_IN);
            tt.play();
        }
    }

    private void createDriversCards() {
        for (int i = 0; i < Championship.getInstance().getRaces()[0].getDriverList().size(); i++) {
            //Player
            String numDriver = String.valueOf(Championship.getInstance().getRaces()[0].getDriverList().get(i).getDorsal());
            String nickname = Championship.getInstance().getRaces()[0].getDriverList().get(i).getNickName();
            Label playerLabel = new Label(numDriver + " - " + nickname);
            playerLabel.getStyleClass().add("model-labelB");
            //Driver image
            Image image = Championship.getInstance().getRaces()[0].getDriverList().get(i).getImage();
            ImageView driverImgView = new ImageView(image);
            driverImgView.setFitWidth(120);
            driverImgView.setPreserveRatio(true);
            //Attributes
            //Name
            String driverName = Championship.getInstance().getRaces()[0].getDriverList().get(i).getName();
            String driverLastname = Championship.getInstance().getRaces()[0].getDriverList().get(i).getLastName();
            Label fullNameDriver = new Label(driverName + " " + driverLastname);
            fullNameDriver.getStyleClass().add("model-labelB");
            //Kilometers
            Label distanceLabel = new Label("Recorrido:");
            distanceLabel.getStyleClass().add("model-labelB");
            Label kmPlayer = new Label(Championship.getInstance().getRaces()[0].getDriverList().get(i).getCar().getKilometers() + " Km");
            kmPlayer.getStyleClass().add("model-labelB");
            kmPlayersMap.put(i, kmPlayer);

            HBox kmBox = new HBox(distanceLabel, kmPlayer);
            kmBox.setSpacing(5);
            kmBox.setAlignment(Pos.CENTER);
            //Extra Acumulate
            Label acumulateLabel = new Label("Acumulado:");
            acumulateLabel.getStyleClass().add("model-labelB");
            Label acumulatePlayer = new Label(String.valueOf(Championship.getInstance().getRaces()[0].getDriverList().get(i).getCombo()));
            acumulatePlayer.getStyleClass().add("model-labelB");
            comboAcumulateMap.put(i, acumulatePlayer);
            HBox acumulateBox = new HBox(acumulateLabel, acumulatePlayer);
            acumulateBox.setSpacing(5);
            acumulateBox.setAlignment(Pos.CENTER);
            //Boxes
            VBox attributesBox = new VBox(fullNameDriver, kmBox, acumulateBox);
            attributesBox.setAlignment(Pos.CENTER);
            attributesBox.setSpacing(5);

            HBox sideBox = new HBox(driverImgView, attributesBox);
            sideBox.setAlignment(Pos.CENTER);
            sideBox.setSpacing(20);
            VBox cardDriverBox = new VBox(playerLabel, sideBox);
            cardDriverBox.setAlignment(Pos.CENTER);
            cardDriverBox.setSpacing(5);

            driversBox.getChildren().add(cardDriverBox);
        }
    }

    private void setWeatherCard(int numRace) {
        circuitNameLabel.setText("Nombre: " + Championship.getInstance().getRaces()[numRace].getCIRCUIT().getName());
        circuitLengthLabel.setText(String.format("Longitud: %.1f km", Championship.getInstance().getRaces()[numRace].getCIRCUIT().getLength()));
        weatherNameLabel.setText("Clima: " + Championship.getInstance().getRaces()[numRace].getWEATHER().getWeatherName());
        wheatherImageView.setImage(Championship.getInstance().getRaces()[numRace].getWEATHER().getImageWeather());
        wheatherImageView.setFitWidth(100);
        wheatherImageView.setPreserveRatio(true);
    }

    private void loadStatsBox() {
        statsBox.getItems().add("Carrera actual");
        for (int i = 0; i < currentRace; i++) {
            statsBox.getItems().add("Resultados carrera " + (i +1));

        }
        statsBox.getItems().add("Campeonato general");
        statsBox.getSelectionModel().select(0);
    }

    private void loadCurrentRound() {
        roundNumLabel.setText("Ronda: " + Championship.getInstance().getRaces()[currentRace].getRound());
    }

    private void updateStatsArea() {
        String selectedStatValue = statsBox.getSelectionModel().getSelectedItem();
        if (selectedStatValue == null) {
            statsBox.getSelectionModel().select(0);
            return;
        }
        if (selectedStatValue.equals("Carrera actual")) {
            ArrayList<Driver> updateDriversList = getCurrentPositions();
            position1Label.setText("1º - " + updateDriversList.get(0).getNickName());
            position2Label.setText("2º - " + updateDriversList.get(1).getNickName());
            position3Label.setText("3º - " + updateDriversList.get(2).getNickName());
            position4Label.setText("4º - " + updateDriversList.get(3).getNickName());
            points1Label.setText(String.format("%.1f km", updateDriversList.get(0).getCar().getKilometers()));
            points2Label.setText(String.format("%.1f km", updateDriversList.get(1).getCar().getKilometers()));
            points3Label.setText(String.format("%.1f km", updateDriversList.get(2).getCar().getKilometers()));
            points4Label.setText(String.format("%.1f km", updateDriversList.get(3).getCar().getKilometers()));
        } else if (selectedStatValue.equals("Campeonato general")) {
            ArrayList<Driver> updateDriversList = getGlobalStats();
            position1Label.setText("1º - " + updateDriversList.get(0).getNickName());
            position2Label.setText("2º - " + updateDriversList.get(1).getNickName());
            position3Label.setText("3º - " + updateDriversList.get(2).getNickName());
            position4Label.setText("4º - " + updateDriversList.get(3).getNickName());
            points1Label.setText("Puntos:  " + updateDriversList.get(0).getTotalScore());
            points2Label.setText("Puntos:  " + updateDriversList.get(1).getTotalScore());
            points3Label.setText("Puntos:  " + updateDriversList.get(2).getTotalScore());
            points4Label.setText("Puntos:  " + updateDriversList.get(3).getTotalScore());
        } else if (selectedStatValue.startsWith("Resultados carrera ")) {
            int currentSearchRace = (checkPreviousRaces(selectedStatValue) -1) ;
            ArrayList<Driver> resultsRace= Championship.getInstance().getRaces()[currentSearchRace].getUpdatePositionsList();
            position1Label.setText("1º - " + resultsRace.get(0).getNickName());
            position2Label.setText("2º - " + resultsRace.get(1).getNickName());
            position3Label.setText("3º - " + resultsRace.get(2).getNickName());
            position4Label.setText("4º - " + resultsRace.get(3).getNickName());
            points1Label.setText("Puntos:  " + resultsRace.get(0).getRaceScores()[currentSearchRace]);
            points2Label.setText("Puntos:  " + resultsRace.get(1).getRaceScores()[currentSearchRace]);
            points3Label.setText("Puntos:  " + resultsRace.get(2).getRaceScores()[currentSearchRace]);
            points4Label.setText("Puntos:  " + resultsRace.get(3).getRaceScores()[currentSearchRace]);
        }
    }

    private void removeSelectedItem() {
        for (Node node : answersBox.getChildren()) {
            node.getStyleClass().remove("answerSelected");
        }
    }

    private void setImagesCarMap() {
        carImageMap.put(0, car1ImgView);
        carImageMap.put(1, car2ImgView);
        carImageMap.put(2, car3ImgView);
        carImageMap.put(3, car4ImgView);
    }

    private void resizeCars() {

        double carHeight = 0.11;
        double containerHeight = circuitPane.getHeight();
        double newCarHeight = containerHeight * carHeight;

        for (ImageView car : carImageMap.values()) {
            car.setPreserveRatio(true);
            car.setFitHeight(newCarHeight);
        }
    }

    private int checkPreviousRaces(String raceItem) {
        System.out.println("DEBUG: Parsing raceItem = '" + raceItem + "'");  // ← Para ver exactamente qué texto

        if (raceItem == null || !raceItem.startsWith("Resultados carrera ")) {
            System.out.println("DEBUG: No startsWith");
            return -1;
        }

        try {
            String numStr = raceItem.substring("Resultados carrera ".length()).trim();
            System.out.println("DEBUG: numStr = '" + numStr + "'");  // ← Para debug

            int raceNumber = Integer.parseInt(String.valueOf(numStr.charAt((numStr.length() - 1))));
            System.out.println("DEBUG: raceNumber=" + raceNumber + ", index=" + raceNumber);

            return raceNumber;
        } catch (Exception e) {
            System.out.println("DEBUG: Parse error: " + e.getMessage());
            return -1;
        }
    }


    private ArrayList<Driver> getGlobalStats() {
        ArrayList<Driver> updateGlobalStats =new ArrayList<>(Championship.getInstance().getRaces()[currentRace].getDriverList()) ;
        for (int i = 0; i < updateGlobalStats.size(); i++) {
            updateGlobalStats.sort(new Comparator<Driver>() {
                @Override
                public int compare(Driver o1, Driver o2) {
                    if (o1.getTotalScore() > o2.getTotalScore()) {
                        return -1;
                    }
                    return 1;
                }
            });
        }
        return updateGlobalStats;
    }

    private ArrayList<Driver> getCurrentPositions() {
        ArrayList<Driver> updateDriversList = Championship.getInstance().getRaces()[currentRace].getUpdatePositionsList();
        for (int i = 0; i < updateDriversList.size(); i++) {
            updateDriversList.sort(new Comparator<Driver>() {
                @Override
                public int compare(Driver o1, Driver o2) {
                    if (o1.getCar().getKilometers() >= o2.getCar().getKilometers()) {
                        return -1;
                    }
                    return 1;
                }
            });
        }
        return updateDriversList;
    }

    private void loadCarImage() {
        carsImages.add(car1ImgView);
        carsImages.add(car2ImgView);
        carsImages.add(car3ImgView);
        carsImages.add(car4ImgView);
        for (int i = 0; i < 4; i++) {
            carsImages.get(i).setImage(Championship.getInstance().getRaces()[currentRace].getDriverList().get(i).getCar().getRaceImage());
        }
    }

    private void showAnimationCube() {
        int randomNum = cube.throwCube();
        throwCubeBtn.setVisible(false);
        feedBackLabel.setVisible(false);

        final int MAX_FRAMES = 25;
        timeline = new Timeline(new KeyFrame(Duration.millis(100), event -> {
            if (frameAnimation < MAX_FRAMES) {
                cubeImageView.setImage(cube.getTurningCubeImages()[frameAnimation % 3]);
                frameAnimation++;
            } else {
                cubeImageView.setImage(cube.getImageCubeNum(randomNum));
                timeline.stop();
                frameAnimation = 0;
                cubeResultLabel.setText("Potencia base: X " + randomNum);
                Championship.getInstance().getRaces()[currentRace].getDriverList().get(currentPlayer).getCar().setBasePower(randomNum);
            }
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void setTimerAnimationCube() {

        PauseTransition pauseTransition = new PauseTransition(Duration.seconds(4));
        pauseTransition.setOnFinished(event -> {
            cubeBoxArea.setVisible(false);
            throwCubeBtn.setVisible(true);
            showQuestionPane();

        });
        pauseTransition.play();
    }

    private void startQuestionTimer() {
        final int[] reamining = {3};
        timeLabel.setText(String.valueOf(reamining[0]));

        questionTimer = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            reamining[0]--;
            timeLabel.setText(String.valueOf(reamining[0]));
            if (reamining[0] <= 0) {
                questionTimer.stop();
                updatePlayerMovement();
            }
        }));

        questionTimer.setCycleCount(3);
        questionTimer.play();
    }

    private void updatePlayerMovement() {

        feedBackLabel.setVisible(false);
        timeLabel.setVisible(false);
        answersBox.setDisable(true);
        cubeResultLabel.setText("Potencia base:");
        checkAnswer(selectedAnswer);
        updateAcumulateBonusQuestion();
        if (checkDamageCar()){
            currentPlayer++;
            selectedAnswer = -1;
            return;
        }
        getTotalKm();
        updateKmDriverLabel();
        moveCar();
        checkReachGoal();
        currentPlayer++;
        selectedAnswer = -1;
    }

    private void executeRound() {
        if (currentPlayer < Championship.getInstance().getPlayersNumber()) {
            showCubePane();
            removeSelectedItem();
            answersBox.setDisable(false);
        } else if (currentPlayer < Championship.getInstance().getRaces()[currentRace].getDriverList().size()) {
            cpuGame();
        } else {
            currentPlayer = 0;
            Championship.getInstance().getRaces()[currentRace].increaseRound();
            loadCurrentRound();
            showCubePane();
            removeSelectedItem();
            answersBox.setDisable(false);
        }
    }

    private void cpuGame() {
        String difficult = Championship.getInstance().getDifficulty();
        Random random = new Random();
        String namePlayer = Championship.getInstance().getRaces()[currentRace].getDriverList().get(currentPlayer).getNickName();
        feedBackLabel.setText(namePlayer + " está haciendo su ronda");
        feedBackLabel.setVisible(true);
        hideFeedbackLabel();
        switch (difficult) {
            case "FÁCIL" -> {
                if (checkDamageCar() && checkDamageCar()){
                    currentPlayer++;
                    return;
                }
                Championship.getInstance().getRaces()[currentRace].getDriverList().get(currentPlayer).getCar().setBasePower((int) (Math.random() * 3) + 1);
                Championship.getInstance().getRaces()[currentRace].getDriverList().get(currentPlayer).setRightQuestion(random.nextBoolean());
                if (Championship.getInstance().getRaces()[currentRace].getDriverList().get(currentPlayer).getCombo() < 2) {
                    updateAcumulateBonusQuestion();
                } else {
                    Championship.getInstance().getRaces()[currentRace].getDriverList().get(currentPlayer).setCombo(0);
                }
            }
            case "NORMAL" -> {
                if (checkDamageCar()){
                    currentPlayer++;
                    selectedAnswer = -1;
                    return;
                }
                Championship.getInstance().getRaces()[currentRace].getDriverList().get(currentPlayer).getCar().setBasePower((int) (Math.random() * 6) + 1);
                Championship.getInstance().getRaces()[currentRace].getDriverList().get(currentPlayer).setRightQuestion(random.nextBoolean());
                if (Championship.getInstance().getRaces()[currentRace].getDriverList().get(currentPlayer).getCombo() < 2) {
                    updateAcumulateBonusQuestion();
                } else {
                    Championship.getInstance().getRaces()[currentRace].getDriverList().get(currentPlayer).setCombo(0);
                }
            }
            case "DIFÍCIL" -> {
                if (checkDamageCar()){
                    currentPlayer++;
                    selectedAnswer = -1;
                    return;
                }
                Championship.getInstance().getRaces()[currentRace].getDriverList().get(currentPlayer).getCar().setBasePower((int) (Math.random() * (6 - 3) + 3));
                Championship.getInstance().getRaces()[currentRace].getDriverList().get(currentPlayer).setRightQuestion(random.nextBoolean());
                updateAcumulateBonusQuestion();
            }
            case "EXTREMO" -> {
                Championship.getInstance().getRaces()[currentRace].getDriverList().get(currentPlayer).getCar().setBasePower((int) (Math.random() * 6));
                Championship.getInstance().getRaces()[currentRace].getDriverList().get(currentPlayer).setRightQuestion(true);
                updateAcumulateBonusQuestion();
            }
        }
        getTotalKm();
        updateKmDriverLabel();
        moveCar();
        checkReachGoal();
        currentPlayer++;


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

    private void showCubePane() {
        questionBox.setVisible(false);
        cubeBoxArea.setVisible(true);
        String namePlayer = Championship.getInstance().getRaces()[currentRace].getDriverList().get(currentPlayer).getNickName();
        feedBackLabel.setText(namePlayer + " -> Lanza el dado para conseguir potencia");
        feedBackLabel.setVisible(true);

    }

    private void showQuestionPane() {
        //LOAD QUESTION
        String namePlayer = Championship.getInstance().getRaces()[currentRace].getDriverList().get(currentPlayer).getNickName();
        feedBackLabel.setText(namePlayer + " -> Responde a la pregunta...Rápido!!!");
        feedBackLabel.setVisible(true);
        timeLabel.setVisible(true);
        questionBox.setVisible(true);
        startQuestionTimer();
        Championship.getInstance().getRaces()[currentRace].getDriverList().get(currentPlayer).setRightQuestion(true);

    }

    private void getTotalKm() {
        //base power
        int baseCube = Championship.getInstance().getRaces()[currentRace].getDriverList().get(currentPlayer).getCar().getBasePower() + 50;
        ArrayList<Integer> extraParams = new ArrayList<>();
        //add car stats
        extraParams.add(Championship.getInstance().getRaces()[currentRace].getDriverList().get(currentPlayer).getCar().getAcceleration());
        extraParams.add(Championship.getInstance().getRaces()[currentRace].getDriverList().get(currentPlayer).getCar().getSpeed());
        //check track water
        if (Championship.getInstance().getRaces()[currentRace].getWEATHER().getWeatherName().equals("Lluvia")) {
            extraParams.add(Championship.getInstance().getRaces()[currentRace].getDriverList().get(currentPlayer).getWaterSkill());
        }
        //check long track
        if (Championship.getInstance().getRaces()[currentRace].getCIRCUIT().getLength() > 400) {
            extraParams.add(Championship.getInstance().getRaces()[currentRace].getDriverList().get(currentPlayer).getEndurance());
        }
        double extras = 0;
        for (int i = 0; i < extraParams.size(); i++) {
            extras += extraParams.get(i);
        }
        extras /= 10;
        //check combo questions
        int accumulateQuestions = 5 * (Championship.getInstance().getRaces()[currentRace].getDriverList().get(currentPlayer).getCombo());
        double totalKmMove = (baseCube * extras) + accumulateQuestions;
        Championship.getInstance().getRaces()[currentRace].getDriverList().get(currentPlayer).getCar().increaseKm(totalKmMove);

    }

    private boolean checkDamageCar(){
        int carEndurance = Championship.getInstance().getRaces()[currentRace].getDriverList().get(currentPlayer).getCar().getHardness();
        int luckDriver = Championship.getInstance().getRaces()[currentRace].getDriverList().get(currentPlayer).getLuck();
        int totalPoints = carEndurance + luckDriver;
        int randomDamage = (int)(Math.random() *20);
        if (randomDamage > totalPoints){
            String nameDriver = Championship.getInstance().getRaces()[currentRace].getDriverList().get(currentPlayer).getNickName();
            feedBackLabel.setText(nameDriver + " tiene problemas mecánicos y no puede moverse");
            feedBackLabel.setVisible(true);
            nextPlayerBtn.setVisible(true);
            return true;
        }
        return false;
    }

    private void updateKmDriverLabel() {
        updateStatsArea();
        Label kmLabel = kmPlayersMap.get(currentPlayer);
        kmLabel.setText(String.format("%.1f km", Championship.getInstance().getRaces()[currentRace].getDriverList().get(currentPlayer).getCar().getKilometers()));
        kmPlayersMap.put(currentPlayer, kmLabel);
    }

    private void updateAcumulateBonusQuestion() {
        if (Championship.getInstance().getRaces()[currentRace].getDriverList().get(currentPlayer).isRightQuestion()) {
            Championship.getInstance().getRaces()[currentRace].getDriverList().get(currentPlayer).increaseCombo();
            Label bonusLabel = comboAcumulateMap.get(currentPlayer);
            bonusLabel.setText(String.valueOf(Championship.getInstance().getRaces()[currentRace].getDriverList().get(currentPlayer).getCombo()));
            comboAcumulateMap.put(currentPlayer, bonusLabel);
        } else {
            Championship.getInstance().getRaces()[currentRace].getDriverList().get(currentPlayer).setCombo(0);
        }
    }

    private void moveCar() {
        ImageView currentImageCar = carImageMap.get(currentPlayer);

        double percentage = getPercentageRace();

        GridPane grid = (GridPane) currentImageCar.getParent();
        double containerHeight = grid.getHeight();
        double noUsableArea = containerHeight * 0.014; // part not available after goal

        double carHeight = currentImageCar.getBoundsInParent().getHeight();
        double usableHeight = containerHeight - carHeight - noUsableArea;

        double newTranslateY = -(usableHeight * percentage);
        currentImageCar.setTranslateY(newTranslateY);

        TranslateTransition tt = new TranslateTransition(Duration.seconds(3), currentImageCar);
        tt.setToY(newTranslateY);
        tt.setInterpolator(Interpolator.EASE_OUT); // más natural

        TranslateTransition shake = new TranslateTransition(Duration.millis(60), currentImageCar);
        shake.setByX(2);
        shake.setAutoReverse(true);
        shake.setCycleCount(4);
        shake.play();

        tt.play();
        nextPlayerBtn.setVisible(true);
    }

    private double getPercentageRace() {
        double currentPosition = Championship.getInstance().getRaces()[currentRace].getDriverList().get(currentPlayer).getCar().getKilometers();

        double circuitLength = Championship.getInstance().getRaces()[currentRace].getCIRCUIT().getLength();

        return currentPosition / circuitLength;  // 0.0 → 1.0
    }

    private void resetAccumulateCombo(){
        for (int i = 0; i <comboAcumulateMap.size(); i++) {
            Label label = comboAcumulateMap.get(i);
            label.setText(String.valueOf(Championship.getInstance().getRaces()[currentRace].getDriverList().get(i).getCombo()));
        }
    }
    private void resetKmDrivers(){
        for (int i = 0; i <kmPlayersMap.size(); i++) {
            Label label = kmPlayersMap.get(i);
            label.setText(String.valueOf(Championship.getInstance().getRaces()[currentRace].getDriverList().get(i).getCar().getKilometers()));
        }
    }

    private void checkReachGoal() {
        double percentage = getPercentageRace();
        if (percentage >= 1) {
            System.out.println("FIN DE CARRERA");
            setRacePoints();
            showWinnerPodium();
        }
    }

    private void setRacePoints() {
        int totalPoints = 10;
        for (int i = 0; i < 3; i++) {
            Championship.getInstance().getRaces()[currentRace].getUpdatePositionsList().get(i).setRaceScores(totalPoints, currentRace);
            Championship.getInstance().getRaces()[currentRace].getUpdatePositionsList().get(i).increaseTotalScore(totalPoints);
            totalPoints -= 2;
        }
        Championship.getInstance().getRaces()[currentRace].getUpdatePositionsList().get(3).setRaceScores(0, currentRace);
    }
    private void showWinnerPodium() {
        if (currentRace == Championship.getInstance().getRaces().length - 1) {
            nextRaceBtn.setText("Resultados del campeonato");
        }
        String winnerName = Championship.getInstance().getRaces()[currentRace].getUpdatePositionsList().get(0).getNickName();
        winnerLabel.setText("🎉🎉🎉  ¡¡¡Enhorabuena " + winnerName + "!!!  🎉🎉🎉");
        feedBackLabel.setVisible(false);
        roundsBox.setVisible(false);
        questionBox.setVisible(false);
        ArrayList<Driver> updatedOrderDrivers = getCurrentPositions();
        podium1Image.setImage(updatedOrderDrivers.get(0).getImage());
        podium2Image.setImage(updatedOrderDrivers.get(1).getImage());
        podium3Image.setImage(updatedOrderDrivers.get(2).getImage());
        podiumBox.setVisible(true);
    }


    private void checkAnswer(int userSelection) {
        //TODO: Actualizar variable boolean test acertado
    }


}


