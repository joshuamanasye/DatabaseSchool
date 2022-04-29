package joman.com.databaseschool;

import java.io.Serializable;

public class Student implements Serializable {
    private String userID, nisn, classID;

    public Student(String userID, String nisn, String classID) {
        this.userID = userID;
        this.nisn = nisn;
        this.classID = classID;
    }

    public String getUserID() {
        return userID;
    }

    public String getNisn() {
        return nisn;
    }

    public String getClassID() {
        return classID;
    }
}
