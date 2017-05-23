package com.deere.customerapi.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Customer {

    @Id
    @NotNull
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;

    @NotNull
    @Column(name = "password", nullable = false)
    private String password;

    private Customer() {
    }

    public Customer(final Integer id, final String password) {
        this.id = id;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
        
    }

    
}
