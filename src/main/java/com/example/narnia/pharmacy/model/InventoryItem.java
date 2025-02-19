package com.example.narnia.pharmacy.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;

@Document(collection = "inventory_items")
public class InventoryItem {

    @Id
    private String id;

    private String name;
    private int stock;
    
    private LocalDate expiry;
    private String status;

    // Constructors
    public InventoryItem() {}

    public InventoryItem(String name, int stock, LocalDate expiry, String status) {
        this.name = name;
        this.stock = stock;
        this.expiry = expiry;
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public LocalDate getExpiry() {
        return expiry;
    }

    public void setExpiry(LocalDate expiry) {
        this.expiry = expiry;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
