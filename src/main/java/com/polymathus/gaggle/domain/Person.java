package com.polymathus.gaggle.domain;

public class Person {

    private String firstName = "";
    private String lastName = "";
    private String fullName = "";

    /**
     *  Public Constructor
     */
    public Person(){

    }

//    public Person(String firstName, String lastName){
//   }

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

    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
