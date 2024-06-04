package com.example.autosharerental;

public class User {
     String firstName;
     String lastName;
     String email;
     String role;

    public User() {
    }

    public User(String name, String email, String role) {
        this.firstName = name;
        this.lastName = email;
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String name) {
        this.firstName = name;
    }
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String name) {
        this.lastName = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Name: " + firstName + " " + lastName + "\nEmail: " + email + "\nRole: " + role;
    }

}
