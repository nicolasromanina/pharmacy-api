package com.example.narnia.pharmacy.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;

@Document(collection = "customers")
public class Customer {

    @Id
    private String id;

    private String name;
    private String phone;
    
    private LocalDate lastVisit;
    private String status;

    // Constructors
    public Customer() {}

    public Customer(String name, String phone, LocalDate lastVisit, String status) {
        this.name = name;
        this.phone = phone;
        this.lastVisit = lastVisit;
        this.status = status;
    }

    // Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getLastVisit() {
        return lastVisit;
    }

    public void setLastVisit(LocalDate lastVisit) {
        this.lastVisit = lastVisit;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
