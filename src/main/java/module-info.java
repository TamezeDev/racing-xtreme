module org.zeki.racingxtreme {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires javafx.graphics;
    requires annotations;
    requires java.desktop;
    requires com.google.gson;
    requires javafx.media;

    opens org.zeki.racingxtreme to javafx.fxml;
    exports org.zeki.racingxtreme;
    exports org.zeki.racingxtreme.controller;
    opens org.zeki.racingxtreme.controller to javafx.fxml;
    opens org.zeki.racingxtreme.model to com.google.gson;
}