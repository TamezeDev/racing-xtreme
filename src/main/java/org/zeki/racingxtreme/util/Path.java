package org.zeki.racingxtreme.util;

public class Path {

    //FXML
    private final String START_STAGE = "/fxml/startStage-view.fxml";
    private final String LOCAL_SELECCTION_MODE = "/fxml/localSelection-view.fxml";
    private final String SELECT_TEAM = "/fxml/selectTeam-view.fxml";
    private final String RACE = "/fxml/race-view.fxml";

    //CSS
    private final String GLOBALSTYLES = "/css/global-styles.css";

    //IMG
    private final String ICON = "/img/icon/main-icon.png";

    //AUDIO
    private final String STARTSOUND = "/audio/start.mp3";
    private final String RACE_SOUND_1 = "/audio/track1.mp3";
    private final String RACE_SOUND_2 = "/audio/track2.mp3";
    private final String RACE_SOUND_3 = "/audio/track3.mp3";
    private final String RACE_SOUND_4 = "/audio/track4.mp3";
    private final String RACE_SOUND_5 = "/audio/track5.mp3";
    private final String RACE_SOUND_6 = "/audio/track6.mp3";
    private final String VICTORY_SOUND = "/audio/victory.mp3";

    //Getters

    public String getSTARTSOUND() {
        return STARTSOUND;
    }
    public String getRaceSound1() {
        return RACE_SOUND_1;
    }

    public String getRaceSound2() {
        return RACE_SOUND_2;
    }

    public String getRaceSound3() {
        return RACE_SOUND_3;
    }

    public String getRaceSound4() {
        return RACE_SOUND_4;
    }

    public String getRaceSound5() {
        return RACE_SOUND_5;
    }

    public String getRaceSound6() {
        return RACE_SOUND_6;
    }

    public String getVICTORY_SOUND() {
        return VICTORY_SOUND;
    }
    public String getStartStage() {
        return START_STAGE;
    }

    public String getLocalSeleccionMode() {
        return LOCAL_SELECCTION_MODE;
    }

    public String getGlobalStyles() {
        return GLOBALSTYLES;
    }

    public String getIcon() {
        return ICON;
    }
    public String getSelectTeam(){
        return SELECT_TEAM;
    }

    public String getRace(){
        return RACE;
    }
}
