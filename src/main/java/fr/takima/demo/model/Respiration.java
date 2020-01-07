package fr.takima.demo.model;

import javax.persistence.*;

@Entity(name = "respiration")
public class Respiration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long respiration_id;

    @Column(name = "respiration")
    private  double respiration;

    @Column(name = "date")
    private  String date;

    public double getAirflow() {
        return respiration;
    }

    public void setAirflow(int respiration) {
        this.respiration = respiration;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
