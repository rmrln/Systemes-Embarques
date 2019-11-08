package fr.takima.demo;

import javax.persistence.*;

@Entity(name = "positions")
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "id_patient")
    private  long id_patient;

    @Column(name = "position")
    private  int position;

    @Column(name = "date")
    private  String date;

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

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getData(){
        return "" + position;
    }
}
