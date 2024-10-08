package com.bigcorp.booking.cours.rest;

import jakarta.validation.constraints.NotNull;

import javax.swing.*;
import java.time.LocalDate;

public class ClientDto {
    private Integer id;
    private String lastName;
    private String firstName;
    private String email;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
