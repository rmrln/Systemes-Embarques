package fr.takima.demo;

import javax.persistence.*;

@Entity(name = "temperatures")
public class Temperature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "id_patient")
    private  long id_patient;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId_patient() {
        return id_patient;
    }

    public void setId_patient(long id_patient) {
        this.id_patient = id_patient;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

}
