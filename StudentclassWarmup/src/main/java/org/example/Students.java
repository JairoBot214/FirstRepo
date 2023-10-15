package org.example;

public class Students {
private String firstName;
private String lastName;
private String studentID;

private int yearInCollege;
private boolean International;
private String address;

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

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public int getYearInCollege() {
        return yearInCollege;
    }

    public void setYearInCollege(int yearInCollege) {
        this.yearInCollege = yearInCollege;
    }

    public boolean isInternational() {
        return International;
    }

    public void setInternational(boolean international) {
        International = international;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Students(String firstName, String lastName, String studentID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentID = studentID;
    }

    public Students(String firstName, String lastName, String studentID, int yearInCollege, boolean International, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentID = studentID;
        this.yearInCollege = yearInCollege;
        this.International = International;
        this.address = address;
    }


        public void greeting() {
            System.out.println("Hi, my name is " + this.firstName + " " + this.lastName + "!");
        }

    }

