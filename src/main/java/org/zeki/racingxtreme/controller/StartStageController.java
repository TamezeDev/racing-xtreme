package org.zeki.racingxtreme.controller;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.zeki.racingxtreme.util.Path;
import org.zeki.racingxtreme.util.SceneHelper;

import java.io.IOException;
import java.util.Objects;

public class StartStageController extends Application {

    @FXML
    private ImageView background;

    private final Path path = new Path();

    @Override
    public void start(Stage stage) throws IOException {
        initStage(stage);
        addMainIcon(stage);
        closeAlert(stage);
    }

    @FXML
    private void loadLocalMode(ActionEvent event) throws IOException {
        SceneHelper.goToOtherScene(path.getLocalSeleccionMode(), event);
    }

    @FXML
    private void initialize() {
        fitBackgroundToParent();
    }

    private void fitBackgroundToParent() {
        StackPane stackPane = (StackPane) background.getParent();
        background.fitWidthProperty().bind(stackPane.widthProperty());
        background.fitHeightProperty().bind(stackPane.heightProperty());
        background.setPreserveRatio(false);
    }

    private void addMainIcon(Stage stage) {
        String pathLogo = Objects.requireNonNull(getClass().getResource(path.getIcon())).toString();
        Image icon = new Image(pathLogo);
        stage.getIcons().add(icon);
    }

    private void closeAlert(Stage stage) {
        stage.setOnCloseRequest(event -> {
            event.consume();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Salida");
            alert.setHeaderText("¿Estas seguro que quieres salir?");
            if (alert.showAndWait().get() == ButtonType.OK) {
                Platform.exit();
                System.exit(1);
            }

        });
    }

    private void initStage(Stage stage) throws IOException {
        //Create new basic stage with logo
        String title = "Racing Xtreme";
        stage.setTitle(title);
        stage.setResizable(true);

        FXMLLoader loader = new FXMLLoader(getClass().getResource(path.getStartStage()));
        Parent root = loader.load();
        Scene scene = new Scene(root, 1920, 1080);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(path.getGlobalStyles())).toExternalForm());

        stage.setMinWidth(1920);
        stage.setMinHeight(1080);
        stage.setScene(scene);
        stage.show();
    }
}
