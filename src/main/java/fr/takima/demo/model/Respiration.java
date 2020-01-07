package fr.takima.demo.model;

import javax.persistence.*;

@Entity(name = "respiration")
public class Respiration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "airflow")
    private  int respiration;

    @Column(name = "date")
    private  String date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getRespiration() {
        return respiration;
    }

    public void setRespiration(int respiration) {
        this.respiration = respiration;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getData(){
        return "" + respiration;
    }
}
