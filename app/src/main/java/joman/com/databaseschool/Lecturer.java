package joman.com.databaseschool;

import java.io.Serializable;

public class Lecturer implements Serializable {

    String nip;

    public Lecturer(String nip) {
        this.nip = nip;
    }

    public String getNip() {
        return nip;
    }
}
