package com.example.narnia.pharmacy.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;

@Document(collection = "sales")
public class Sale {

    @Id
    private String id;

    private LocalDate date;
    private int items;
    private double total;
    private String status;

    // Constructors
    public Sale() {}

    public Sale(LocalDate date, int items, double total, String status) {
        this.date = date;
        this.items = items;
        this.total = total;
        this.status = status;
    }

    // Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getItems() {
        return items;
    }

    public void setItems(int items) {
        this.items = items;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
