package fr.takima.demo.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "id_medecin")
    private  long id_medecin;
    @Column(name = "first_name")
    private  String firstName;
    @Column(name = "last_name")
    private  String lastName;
    @Column(name = "age")
    private  String age;

    public long getId_medecin() {
        return id_medecin;
    }

    public void setId_medecin(long id_medecin) {
        this.id_medecin = id_medecin;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

}
