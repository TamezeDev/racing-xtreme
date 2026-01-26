package org.zeki.racingxtreme.util;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SceneHelper {
    static Path path = new Path();

    public static void goToOtherScene(String scenePath, ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(SceneHelper.class.getResource(scenePath));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(SceneHelper.class.getResource(path.getGlobalStyles())).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}
