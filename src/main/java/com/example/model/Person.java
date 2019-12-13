package com.example.model;

import javax.persistence.*;

@Entity
@Table(name = "person")

public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long personID;
    private String surname;
    private String personname;
    private String position;
    private String birthdate;
    private String gender;
    private int personalcode;

    public Person(String surname, String personname, String position, String birthdate, String gender, int personalcode) {
        this.surname = surname;
        this.personname = personname;
        this.position = position;
        this.birthdate = birthdate;
        this.gender = gender;
        this.personalcode = personalcode;
    }

    public Person(){
    }
    @Column(name = "personid")
    public long getPersonID() {
        return personID;
    }
    public void setPersonID(long personID) {
        this.personID = personID;
    }
    @Column(name = "surname", nullable = false)
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    @Column(name = "name", nullable = false)
    public String getName(){
        return personname;
    }
    public void setName(String personname){
        this.personname=personname;
    }
    @Column(name = "position", nullable = false)
    public String getPosition(){
        return position;
    }
    public void setPosition(String position){
        this.position=position;
    }
    @Column(name = "birthdate", nullable = false)
    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }
    @Column(name = "gender", nullable = false)
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    @Column(name = "personalcode", nullable = false)
    public int getPersonalcode() {
        return personalcode;
    }

    public void setPersonalcode(int personalcode) {
        this.personalcode = personalcode;
    }

    @Override
    public String toString() {
        return "Person [id=" + personID + ", personname=" + personname + ", surname=" + surname + ", position=" + position
                + "]";
    }
}