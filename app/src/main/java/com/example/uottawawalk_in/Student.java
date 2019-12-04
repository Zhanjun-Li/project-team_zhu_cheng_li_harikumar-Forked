package com.example.uottawawalk_in;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class Student {

    private String firstName;
    private String lastName;
    private String studentNumber;
    private String userName;
    private String password;
    private String address;
    private String phoneNumber;
    private String companyName;
    private String generalDescription;
    private boolean isEmployee;
    private boolean isAdmin;
    private boolean isServiceProvider;
    private boolean isLicensed;


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

    public String getStudentNumber() { return studentNumber; }
    public void setStudentNumber(String studentNumber) { this.studentNumber = studentNumber; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) {  this.userName = userName; }

    public String getPassword() { return password; }
    public void setPassword(String password) { password = password;  }

    public boolean getIsEmployee() { return isEmployee; }
    public void setEmployee(boolean employee) { isEmployee = employee; }

    public boolean getIsServiceProvider() {return isServiceProvider;}
    public void setServiceProvider(boolean serviceProvider){isServiceProvider = serviceProvider;}

    public boolean getAdmin() { return isAdmin; }
    public void setAdmin(boolean admin) { isAdmin = admin; }

    public String getAddress(){return address;}
    public void setAddress(String address){this.address=address;}

    public String getPhoneNumber(){return phoneNumber;}
    public void setPhoneNumber(String phoneNumber){this.phoneNumber=phoneNumber;}

    public String getCompanyName(){return companyName;}
    public void setCompanyName(String companyName){this.companyName=companyName;}

    public String getGeneralDescription(){return generalDescription;}
    public void setGeneralDescription(String generalDescription){this.generalDescription=generalDescription;}

    public boolean getIsLicensed(){return isLicensed;}
    public void setIsLicensed(boolean isLicensed){this.isLicensed=isLicensed;}

    public Student () {

    }

    public Student (String firstName, String lastName, String studentNumber, String userName, String password, boolean isEmployee) {
        this.firstName=firstName;
        this.lastName=lastName;
        this.studentNumber=studentNumber;
        this.userName=userName;
        this.password=password;
        this.isEmployee=isEmployee;
        this.isServiceProvider=false;
        this.isAdmin=false;
    }
    public Student (String firstName, String lastName, String studentNumber, String userName, String password, boolean isEmployee,boolean isServiceProvider) {
        this.firstName=firstName;
        this.lastName=lastName;
        this.studentNumber=studentNumber;
        this.userName=userName;
        this.password=password;
        this.isEmployee=isEmployee;
        this.isServiceProvider=isServiceProvider;
        this.isAdmin=false;
    }
    public String toString (Student s) {
        return s.firstName + " " + s.lastName;
    }
    public String toString(){return companyName + " at "+address;}

    public boolean equals (Student s) {
        if (firstName.equals(s.firstName) && lastName.equals(s.lastName) && userName.equals(s.userName))
            return true;
        return false;
    }


}
