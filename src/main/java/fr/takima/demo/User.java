package fr.takima.demo;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "picture")
    private  String picture;
    @Column(name = "cv_title")
    private  String cvTitle;
    @Column(name = "first_name")
    private  String firstName;
    @Column(name = "last_name")
    private  String lastName;
    @Column(name = "age")
    private  String age;
    @Column(name = "phone_number")
    private  String phoneNumber;
    @Column(name = "address")
    private  String address;
    @Column(name = "linkedin")
    private  String linkedin;
    @Column(name = "mail")
    private  String mail;
    @Column(name = "presentation")
    private  String presentation;
    @Column(name = "skill")
    private  String skill;
    @Column(name = "hobby")
    private  String hobby;
    @Column(name = "language")
    private  String language;
    @Column(name = "formations")
    private  String formations;
    @Column(name = "experiences")
    private  String experiences;

    public String getFormations() {
        return formations;
    }

    public void setFormations(String formations) {
        this.formations = formations;
    }

    public String getExperiences() {
        return experiences;
    }

    public void setExperiences(String experiences) {
        this.experiences = experiences;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }



    public String getCvTitle() {
        return cvTitle;
    }

    public void setCvTitle(String cvTitle) {
        this.cvTitle = cvTitle;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPresentation() {
        return presentation;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }


}
