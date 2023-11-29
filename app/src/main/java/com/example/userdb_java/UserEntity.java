package com.example.userdb_java;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "TABLE_NAME")
public class UserEntity {

    @PrimaryKey(autoGenerate = true)
    private Integer id;

    private String name;

    private String family;

    private String age;

    private String nationalNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getNationalNumber() {
        return nationalNumber;
    }

    public void setNationalNumber(String nationalNumber) {
        this.nationalNumber = nationalNumber;
    }
}