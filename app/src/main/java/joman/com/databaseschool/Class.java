package joman.com.databaseschool;

import java.io.Serializable;

public class Class implements Serializable {
    private String classID, nip, className;

    public Class(String classID, String nip, String className) {
        this.classID = classID;
        this.nip = nip;
        this.className = className;
    }

    public String getClassID() {
        return classID;
    }

    public String getNip() {
        return nip;
    }

    public String getClassName() {
        return className;
    }
}
