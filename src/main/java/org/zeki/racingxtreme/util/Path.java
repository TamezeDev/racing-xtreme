package org.zeki.racingxtreme.util;

public class Path {

    //FXML
    private final String STARTSTAGE = "/fxml/startStage-view.fxml";
    private final String LOCALSELECCTIONMODE = "/fxml/localSelection-view.fxml";
    private final String SELECTTEAM = "/fxml/selectTeam-view.fxml";
    private final String RACE = "/fxml/race-view.fxml";

    //CSS
    private final String GLOBALSTYLES = "/css/global-styles.css";

    //IMG
    private final String ICON = "/img/icon/main-icon.png";

    //Getters
    public String getStartStage() {
        return STARTSTAGE;
    }

    public String getLocalSeleccionMode() {
        return LOCALSELECCTIONMODE;
    }

    public String getGlobalStyles() {
        return GLOBALSTYLES;
    }

    public String getIcon() {
        return ICON;
    }
    public String getSelectTeam(){
        return SELECTTEAM;
    }

    public String getRace(){
        return RACE;
    }
}
