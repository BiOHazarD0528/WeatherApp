package edu.monash.fit2081.fit2081weatherapp;

public class WeatherItem {
    private String temperature;
    private String precipitation;
    private String humidity;
    private String wind;

    public WeatherItem(String temperature, String precipitation, String humidity, String wind) {
        this.temperature = temperature;
        this.precipitation = precipitation;
        this.humidity = humidity;
        this.wind = wind;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getPrecipitation() {
        return precipitation + "%";
    }

    public void setPrecipitation(String precipitation) {
        this.precipitation = precipitation;
    }

    public String getHumidity() {
        return humidity + "%";
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }
}
