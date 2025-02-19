package com.example.narnia.pharmacy.repository;

import com.example.narnia.pharmacy.model.InventoryItem;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends MongoRepository<InventoryItem, String> {

    Optional<InventoryItem> findById(String id);

    void deleteById(String id);
}
