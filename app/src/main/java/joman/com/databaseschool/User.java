package joman.com.databaseschool;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class User implements Serializable {
    private String UserID, Email, FullName, Gender, Phone, Address;
    private LocalDate DOB;

    public User(String userID, String email, String fullName, String gender, String DOB, String phone, String address) {
        UserID = userID;
        Email = email;
        FullName = fullName;
        Gender = gender;
        Phone = phone;
        Address = address;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.DOB = LocalDate.parse(DOB, formatter);
    }

    public String getUserID() {
        return UserID;
    }

    public String getEmail() {
        return Email;
    }

    public String getFullName() {
        return FullName;
    }

    public String getGender() {
        return Gender;
    }

    public String getPhone() {
        return Phone;
    }

    public String getAddress() {
        return Address;
    }

    public LocalDate getDOB() {
        return DOB;
    }
}
