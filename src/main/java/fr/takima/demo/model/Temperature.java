package fr.takima.demo.model;

import javax.persistence.*;

@Entity(name = "temperature")
public class Temperature {

    @Column(name="temperature_id")
    private long id;

    @Column(name = "temperature")
    private  double temperature;

    @Column(name = "date")
    private  String date;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

}
