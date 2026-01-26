package org.zeki.racingxtreme.model;

import javafx.scene.image.Image;

import java.util.Objects;

public class Weather {

    private enum weather {SOLEADO, LLUVIA}

    private String weatherName;
    private Image imageWeather;

    public Weather() {
        int randomNumber = (int) (Math.random() * weather.values().length);
        String randomWeather = String.valueOf(weather.values()[randomNumber]);
        setWeatherParams(randomWeather);

    }

    private void setWeatherParams(String randomWeather) {
        String imagePath = "/img/icon/";
        switch (randomWeather) {
            case "SOLEADO" -> {
                weatherName = "Soleado";
                imageWeather = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath + "sun.png")));
            }
            case "LLUVIA" -> {
                weatherName = "Lluvia";
                imageWeather = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath + "rain.png")));
            }

        }
    }

    public void setWeatherName(String weatherName) {
        this.weatherName = weatherName;
    }

    public Image getImageWeather() {
        return imageWeather;
    }

    public void setImageWeather(Image imageWeather) {
        this.imageWeather = imageWeather;
    }

    public String getWeatherName() {
        return weatherName;
    }


}
