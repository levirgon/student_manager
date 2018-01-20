package com.tutexp.student_details.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

/**
 * Created by noushad on 1/20/18.
 */


@Entity
public class Student implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int id;
    private String name;
    private String rollNo;
    private String address;
    private String gender;
    private int imageId;

    public Student(String name, String rollNo, String address, String gender, int imageId) {
        this.name = name;
        this.rollNo = rollNo;
        this.address = address;
        this.gender = gender;
        this.imageId = imageId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
