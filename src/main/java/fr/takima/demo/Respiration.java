package fr.takima.demo;

import javax.persistence.*;

@Entity(name = "respiration")
public class Respiration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "id_patient")
    private  long id_patient;

    @Column(name = "airflow")
    private  int airflow;

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

    public int getAirflow() {
        return airflow;
    }

    public void setAirflow(int airflow) {
        this.airflow = airflow;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getData(){
        return "" + airflow;
    }
}
